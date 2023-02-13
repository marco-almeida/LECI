/*
 *
 *  ...
 *
 *  \author Artur Pereira - 2022
 */

#include <inttypes.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <libgen.h>
#include <termios.h>

#include <string>
#include <iostream>
#include <map>

#include "somm22.h"

using namespace somm22;

/* ******************************************** */
/* print help message */
static void printUsage(const char *cmd_name)
{
    printf("Sinopsis: %s [OPTIONS] «infile»\n"
           "  OPTIONS:\n"
           "  -o outfile     --- set log file (default: stdout)\n"
           "  -f fit-policy  --- set allocation policy, one of f[irst], n[ext], b[est], or w[orst] (default: f)\n"
           "  -k address     --- memory size used by OS (default: 0x2000)\n"
           "  -m size        --- memory size (default: 0x10000)\n"
           "  -c size        --- memory chunk size (default: 16)\n"
           "  -O outfile     --- set probbing file (default: stdout)\n"
           "  -P num-num     --- set probe ID range (default: 0-0)\n"
           "  -A num-num     --- add range of IDs to probe configuration\n"
           "  -R num-num     --- remove range of IDs from probe configuration\n"
           "  -y             --- use yad as pause interface\n"
           "  -b module      --- set binary mode for «module» (one of sim, mem, peq, pct, or all) (default: all group)\n"
           "  -g module      --- set group mode for «module» (one of sim, mem, peq, pct, or all) (default: all group)\n"
           "  -h             --- print this help\n",
           cmd_name);
}

namespace somm22
{

    bool pauseTerm()
    {
        static bool firstTime = true;
        static struct termios prev, cur;
        if (firstTime)
        {
            firstTime = false;
            tcgetattr(STDIN_FILENO, &prev);
            cur = prev;
            cur.c_lflag &= (~ICANON);
            tcsetattr(STDIN_FILENO, TCSANOW, &cur);
        }

        printf("Continue (Y/n)? ");
        fflush(stdout);
        while (true)
        {
            int res = getchar();
            if (res == '\n')
                break;
            printf("\n");
            if (res == 'n' or res == 'N')
                return false;
            if (res == 'y' or res == 'Y')
                break;
            printf("Bad option! Continue (Y/n)? ");
            fflush(stdout);
        }
        return true;
    }

    bool pauseYad()
    {
        static char cmd[1000];
        sprintf(cmd, "yad"
                     " --image=gtk-media-pause"
                     " --title Continue?"
                     " --on-top"
                     " --button=gtk-yes:0"
                     " --button=gtk-no:1"
                     " --text=\"Press OK to continue\n\nPress Cancel or ESC to abort\"");
        if (system(cmd) != 0)
            return false;
        return true;
    }

    bool (*pause)(void) = pauseTerm;
}

/* ******************************************** */
/* The main function */
int main(int argc, char *argv[])
{
    const char *progName = basename(argv[0]);

    /* a convenient mapping for allocation policy selection */
    std::map<std::string, AllocationPolicy> pm;
    pm["f"] = FirstFit;
    pm["n"] = NextFit;
    pm["b"] = BestFit;
    pm["w"] = WorstFit;

    /* a convenient mapping for binary selection */
    std::map<std::string, void (*)(bool)> selMap;
    selMap["all"] = setBinaryMode;
    selMap["sim"] = simSetBinaryMode;
    selMap["pct"] = pctSetBinaryMode;
    selMap["mem"] = memSetBinaryMode;
    selMap["peq"] = peqSetBinaryMode;

    /* open probing system, all */
    soProbeOpen(stdout, 0, 0);

    /* default values for command line options */
    const char* logfile = "/dev/tty"; // ACP: analisar a hipótese de usar stream
    AllocationPolicy memPolicy = FirstFit;
    uint32_t memChunkSize = 16;
    uint32_t memSize = 0x10000;
    uint32_t memSizeOS = 0x2000;

    /* process command line options */
    int opt;
    while ((opt = getopt(argc, argv, "o:f:k:m:c:dO:P:A:R:yb:g:h")) != -1)
    {
        switch (opt)
        {
        case 'o': // set output file
        {
            logfile = optarg;
            break;
        }
        case 'f': // set memory allocation fit policy
        {
            if (pm.find(optarg) == pm.end())
            {
                fprintf(stderr, "%s: Fail setting memory allocation fit policy ('%s').\n", progName, optarg);
                printUsage(progName);
                return EXIT_FAILURE;
            }
            memPolicy = pm[optarg];
            break;
        }
        case 'k': // set memory size used by OS
        {
            int n = 0;
            sscanf(optarg, "%u%n", &memSizeOS, &n);
            if ((size_t)n == strlen(optarg))
                break;
            n = 0;
            sscanf(optarg, "%x%n", &memSizeOS, &n);
            if ((size_t)n == strlen(optarg))
                break;
            fprintf(stderr, "%s: Bad argument (%s) to '-k' option.\n", progName, optarg);
            return EXIT_FAILURE;
        }
        case 'm': // set memory size
        {
            int n = 0;
            sscanf(optarg, "%u%n", &memSize, &n);
            if ((size_t)n == strlen(optarg))
                break;
            n = 0;
            sscanf(optarg, "%x%n", &memSize, &n);
            if ((size_t)n == strlen(optarg))
                break;
            fprintf(stderr, "%s: Bad argument (%s) to '-m' option.\n", progName, optarg);
            return EXIT_FAILURE;
        }
        case 'c': // set memory chunk size
        {
            int n = 0;
            sscanf(optarg, "%u%*[^\n]%*c%n", &memChunkSize, &n);
            if (n != 0)
                break;
            n = 0;
            sscanf(optarg, "0%*[xX]%x%*[^\n]%*c%n", &memChunkSize, &n);
            if (n != 0)
                break;
            fprintf(stderr, "%s: Bad argument (%s) to '-c' option.\n", progName, optarg);
            return EXIT_FAILURE;
        }
        case 'O': /* set probbing file */
        {
            soProbeFile(optarg);
            break;
        }
        case 'P': /* set ID range to probing system */
        {
            uint32_t lower, upper;
            uint32_t cnt = 0;
            if ((sscanf(optarg, "%d%*[,-]%d %n", &lower, &upper, &cnt) != 2) or (cnt != strlen(optarg)))
            {
                fprintf(stderr, "%s: Bad argument to '-p' option.\n", progName);
                printUsage(progName);
                return EXIT_FAILURE;
            }
            soProbeSetIDs(lower, upper);
            break;
        }
        case 'A': /* add IDs to probe conf */
        {
            uint32_t lower, upper;
            uint32_t cnt = 0;
            if ((sscanf(optarg, "%d%*[,-]%d %n", &lower, &upper, &cnt) != 2) or (cnt != strlen(optarg)))
            {
                fprintf(stderr, "%s: Bad argument to '-A' option.\n", basename(argv[0]));
                printUsage(basename(argv[0]));
                return EXIT_FAILURE;
            }
            soProbeAddIDs(lower, upper);
            break;
        }
        case 'R': /* remove IDs from probe conf */
        {
            uint32_t lower, upper;
            uint32_t cnt = 0;
            if ((sscanf(optarg, "%d-%d %n", &lower, &upper, &cnt) != 2) or (cnt != strlen(optarg)))
            {
                fprintf(stderr, "%s: Bad argument to '-R' option.\n", basename(argv[0]));
                printUsage(basename(argv[0]));
                return EXIT_FAILURE;
            }
            soProbeRemoveIDs(lower, upper);
            break;
        }
        case 'y': // set YAD mode
        {
            somm22::pause = pauseYad;
            ;
            break;
        }
        case 'b': // set binary mode
        {
            if (selMap.find(optarg) == selMap.end())
            {
                fprintf(stderr, "%s: Fail setting binary/group mode: wrong option ('%s').\n", progName, optarg);
                printUsage(progName);
                return EXIT_FAILURE;
            }
            selMap[optarg](true);
            break;
        }
        case 'g': // set binary mode
        {
            if (selMap.find(optarg) == selMap.end())
            {
                fprintf(stderr, "%s: Fail setting binary/group mode: wrong option ('%s').\n", progName, optarg);
                printUsage(progName);
                return EXIT_FAILURE;
            }
            selMap[optarg](false);
            break;
        }
        case 'h':
        {
            printUsage(progName);
            return 0;
        }
        default:
        {
            fprintf(stderr, "%s: Wrong option (\"-%c\".\n", progName, opt);
            printUsage(progName);
            return EXIT_FAILURE;
        }
        }
    }

    /* check existence of mandatory argument: simulation data file */
    if ((argc - optind) != 1)
    {
        fprintf(stderr, "%s: Wrong number of mandatory arguments.\n", progName);
        printUsage(progName);
        return EXIT_FAILURE;
    }
    const char *infile = argv[optind];

    /* check simulation data file format */
    if (simCheckInputFormat(infile) == false)
    {
        fprintf(stderr, "%s: \"%s\": Wrong simulation data file format.\n", progName, infile);
        return EXIT_FAILURE;
    }

    /* init modules */
    simInit();
    memInit(memSize, memSizeOS, memChunkSize, memPolicy);
    pctInit(infile);
    peqInit(infile);
    logOpen(logfile);

    /* print initial modules' state */
    logPrint("\n===========================================================\n");
    logPrint("===========================================================\n");
    logPrint("Current time: %u; current step: %u\n", simGetCurrentSimTime(), simGetCurrentSimStep());
    logPrint("===========================================================\n");
    logPrint("===========================================================\n\n");
    pctLog();
    memLog();
    peqLog();
    logPrint("Current event mask: %s\n", peqEventMaskAsString(simGetCurrentSimMask()));
    if (somm22::pause() == false)
    {
        logClose();
        exit(EXIT_SUCCESS);
    }

    /* run the simulation */
    while (not peqIsEmpty())
    {
        Event e = peqPeekNext(simGetCurrentSimMask());
        simRun(1);
        logPrint("\n===========================================================\n");
        logPrint("===========================================================\n");
        peqLogEvent(&e, "Event processed");
        logPrint("Current time: %u; step: %u\n", simGetCurrentSimTime(), simGetCurrentSimStep());
        logPrint("===========================================================\n");
        logPrint("===========================================================\n\n");
        /* print current module states */
        pctLog();
        memLog();
        peqLog();
        logPrint("Next event mask: %s\n", peqEventMaskAsString(simGetCurrentSimMask()));
        if (somm22::pause() == false)
        {
            logClose();
            exit(EXIT_SUCCESS);
        }
    }
    logClose();

    /* development tests
    memPrint(logfile, NEW);
    pctPrint(logfile, APPEND);
    void *p1 = memAlloc(111, 0x200);
    void *p2 = memAlloc(222, 0x400);
    void *p3 = memAlloc(333, 0x800);
    void *p4 = memAlloc(444, 0x200);
    memLog();
    memFree(p2);
    memFree(p4);
    memFree(p1);
    memFree(p3);
    memPrint(logfile, NEW);
    memLog();
    exit(0);
    */
}
