/**
 * @file
 *
 * \brief A producer-consumer application, implemented using processes,
 *      and shared memory.
 *
 * \remarks The return status of the processes are ignored
 *
 * \author (2016-2022) Artur Pereira <artur at ua.pt>
 */

#include  <stdio.h>
#include  <stdlib.h>
#include  <libgen.h>
#include  <unistd.h>
#include  <sys/wait.h>
#include  <sys/types.h>
#include  <math.h>
#include <stdint.h>

#include  "fifo.h"
#include  "delays.h"
#include  "process.h"

#define USAGE "Synopsis: %s [options]\n"\
	"\t----------+-------------------------------------- ---------------\n"\
	"\t  Option  |          Description                                 \n"\
	"\t----------+------------------------------------------------ -----\n"\
	"\t -i num   | number of iterations (dfl: 10; max: 100)   \n"\
	"\t -p num   | number of producers/consumers (dfl: 4; max 50)       \n"\
	"\t -h       | this help                                            \n"\
	"\t----------+----------------------------------------------- ------\n"

/* ******************************************************* */

/* The shared memory is created before the producer and consumer processes
 * are launched, so after the forks its id is in all the created processes.
 * But, its address (the fifo variable) must be initialized in every process.
 */

/* ******************************************************* */

/* The producer process */
int producer(uint32_t id, uint32_t niter)
{
    /* make the job */
    uint32_t i;
    for (i = 0; i < niter; i++)
    {
        /* insert an item into the fifo */
        uint32_t value = i * 10000 + id;
        fifo::in(id, value);

        /* do something else */
        gaussianDelay(10, 5);

        /* print them */
            printf("\e[30;00mThe value %05u was produced by process P%02u!\e[0m\n", value, id);
    }

    //printf("Producer %u is quiting\n", id);
    exit(EXIT_SUCCESS);
}

/* ******************************************************* */

/* The consumer process */
int consumer(uint32_t id, uint32_t niter)
{
    /* make the job */
    uint32_t i;
    for (i = 0; i < niter; i++)
    {
        /* do something else */
        gaussianDelay(10, 5);

        /* retrieve an item from the fifo */
        uint32_t pid, value;
        fifo::out(&pid, &value);

        /* print them */
        if (value == 99999 || pid == 99 || (value % 100) != pid)
            printf("\e[31;01mThe value %05u was produced by process P%02u!\e[0m\n",
                    value, pid);
        else
            printf("\e[32;01mThe value %05u was produced by process P%02u!\e[0m\n",
                    value, pid);
    }

    //printf("Consumer %u is quiting\n", id);
    exit(EXIT_SUCCESS);
}

/* ******************************************************* */

/* main process: it starts the simulation and launches the producer and consumer processes */
int main(int argc, char *argv[])
{
    uint32_t niter = 10; ///< number of iterations
    uint32_t nproducers = 4;   ///< number of consumers and producers
    uint32_t nconsumers = 4;   ///< number of consumers and producers

    /* command line processing */
    int option;
    while ((option = getopt(argc, argv, "i:p:h")) != -1)
    {
        switch (option)
        {
            case 'i':
                niter = atoi(optarg);
                if (niter > 100)
                {
                    fprintf(stderr, "Too many iterations!\n");
                    fprintf(stderr, USAGE, basename(argv[0]));
                    return EXIT_FAILURE;
                }
                break;
            case 'p':
                nproducers = nconsumers = atoi(optarg);
                if (nproducers > 50)
                {
                    fprintf(stderr, "Too many processes!\n");
                    fprintf(stderr, USAGE, basename(argv[0]));
                    return EXIT_FAILURE;
                }
                break;
            case 'h':
                printf(USAGE, basename(argv[0]));
                return EXIT_SUCCESS;
            default:
                fprintf(stderr, "Non valid option!\n");
                fprintf(stderr, USAGE, basename(argv[0]));
                return EXIT_FAILURE;
        }
    }

    /* create the shared memory */
    fifo::create();

    /* start random generator */
    srand(getpid());

    /* launching the consumers */
    int cpid[nconsumers];   /* consumers' ids */
    printf("Launching %d consumer processes, each performing %d iterations\n", nconsumers, niter);
    for (uint32_t id = 0; id < nconsumers; id++)
    {
        if ((cpid[id] = pfork()) == 0)
        {
            consumer(id, niter);
            exit(0);
        }
        else
        {
            printf("- Consumer process %d was launched\n", id);
        }
    }

    /* launching the producers */
    int ppid[nproducers];   /* producers' ids */
    printf("Launching %d producer processes, each performing %d iterations\n", nproducers, niter);
    for (uint32_t id = 0; id < nproducers; id++)
    {
        if ((ppid[id] = pfork()) == 0)
        {
            producer(id, niter);
            exit(0);
        }
        else
        {
            printf("- Producer process %d was launched\n", id);
        }
    }

    /* wait for processes to conclude */
    for (uint32_t id = 0; id < nproducers; id++)
    {
        pid_t pid = pwaitpid(ppid[id], NULL, 0);
        printf("Producer %d (process %d) has terminated\n", id, pid);
    }
    for (uint32_t id = 0; id < nconsumers; id++)
    {
        pid_t pid = pwaitpid(cpid[id], NULL, 0);
        printf("Consumer %d (process %d) has terminated\n", id, pid);
    }

    /* destroy the shared memory */
    fifo::destroy();

    return EXIT_SUCCESS;
}

