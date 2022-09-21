/*
 */

#include <inttypes.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <libgen.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <string.h>
#include <time.h>
#include <errno.h>
#include <stdarg.h>

#include <map>
#include <string>
#include <iostream>
#include <stdexcept>

#include "ull.h"

/* ******************************************** */

static void printMenu(void)
{
    printf("\n"
           "+===============================================+\n"
           "|            Manipulation functions             |\n"
           "+===============================================+\n"
           "| 0 --- quit                                    |\n"
           "| 1 --- reset the list of students              |\n"
           "| 2 --- insert new student                      |\n"
           "| 3 --- get student's name, given the number    |\n"
           "| 4 --- remove student, through number          |\n"
           "| 5 --- print list in ascending order of number |\n"
           "| 6 --- load list of students from file         |\n"
           "+===============================================+\n");
}

/* ******************************************** */

static void printUsage(const char *cmd_name)
{
    printf("Sinopsis: %s [OPTIONS]\n"
           "  OPTIONS:\n"
           "  -i «fname»  --- file containing list of student registers\n"
           "  -n          --- operate in non-interactive mode (dfl: interactive)\n"
           "  -h          --- print this help\n", cmd_name);
}

/* ******************************************** */
/* menu handling functions */
/* ******************************************** */


/* ******************************************** */

void menuChoiceQuit()
{
    exit(EXIT_SUCCESS);
}

/* ******************************************** */

void menuChoiceReset()
{
}

/* ******************************************** */

void menuChoiceInsert()
{
}

/* ******************************************** */

void menuChoiceQuery()
{
}

/* ******************************************** */

void menuChoiceRemove()
{
}

/* ******************************************** */

void menuChoicePrint()
{
}

/* ******************************************** */

void menuChoiceLoad()
{
}

/* ******************************************** */

void getChoiceAndCallHandler()
{
    /* ask for command */
    printf("Your command: ");
    uint32_t cmd;
    char *line = NULL;
    size_t n = 0;
    if (getline(&line, &n, stdin) == -1) {
        perror("Fail getting line of input (for nmec)");
        exit(EXIT_FAILURE);
    }

    /* validate the input */
    int n1 = 0, n2 = 0;
    sscanf(line, "%u%n %*c%n", &cmd, &n1, &n2);
    if (n1 == 0 or n2 != 0) {
        fprintf(stderr, "Wrong input format: %s", line);
        delete line;
        return;
    }

    switch(cmd) 
    {
        case 0:
            menuChoiceQuit();
            break;
        case 1:
            menuChoiceReset();
            break;
        case 2:
            menuChoiceInsert();
            break;
        case 3:
            menuChoiceQuery();
            break;
        case 4:
            menuChoiceRemove();
            break;
        case 5:
            menuChoicePrint();
            break;
        case 6:
            menuChoiceLoad();
            break;
        default:
            fprintf(stderr, "Wrong action required (%u)\n", cmd);
            return;
    }
}

/* ******************************************** */
/* The main function */
int main(int argc, char *argv[])
{
    const char *progName = basename(argv[0]); // must be called before dirname!
    bool interactiveMode = true;

    /* process command line options */
    int opt;
    while ((opt = getopt(argc, argv, "i:nh")) != -1)
    {
        switch (opt)
        {
            case 'h':          /* help mode */
            {
                printUsage(progName);
                return EXIT_SUCCESS;
            }
            case 'i':   /* load from file */
            {
                // ACP: ignored for now
                break;
            }
            case 'n':  // turn interactive mode off
            {
                interactiveMode = false;
                break;
            }
            default:
            {
                fprintf(stderr, "[\e[31;2m%s\e[0m]: Wrong option.\n", progName);
                printUsage(progName);
                return EXIT_FAILURE;
            }
        }
    }

    /* check non existence of mandatory argument */
    if ((argc - optind) != 0)
    {
        fprintf(stderr, "%s: Wrong number of mandatory arguments.\n", progName);
        printUsage(progName);
        return EXIT_FAILURE;
    }

    /* do the job */
    if (interactiveMode) {
        while (true)
        {
            printMenu();
            getChoiceAndCallHandler();
        }
    }

    return EXIT_SUCCESS;
}     /* end of main */
