/**
 * @file
 *
 * \brief A producer-consumer application, implemented using threads.
 *
 * \author (2016-2022) Artur Pereira <artur at ua.pt>
 */

#include  <stdio.h>
#include  <stdlib.h>
#include  <libgen.h>
#include  <unistd.h>
#include  <sys/wait.h>
#include  <sys/types.h>
#include  <pthread.h>
#include  <math.h>
#include <stdint.h>

#include  "fifo.h"
#include  "delays.h"
#include  "thread.h"

#define USAGE "Synopsis: %s [options]\n"\
	"\t----------+-------------------------------------- ---------------\n"\
	"\t  Option  |          Description                                 \n"\
	"\t----------+------------------------------------------------ -----\n"\
	"\t -i num   | number of iterations (dfl: 10; max: 100)             \n"\
	"\t -p num   | number of producers/consumers (dfl: 4; max 50)       \n"\
	"\t -h       | this help                                            \n"\
	"\t----------+----------------------------------------------- ------\n"

/* Argument type for producer and consumer threads */
struct ARGV
{
    unsigned int id;      ///< thread id
    unsigned int niter;   ///< number of iterations
};

/* ******************************************************* */

/* The producer thread */
void *producer(void *argp)
{
    /* cast argument to real type */
    ARGV* argv = (ARGV*)argp;

    /* make the job */
    unsigned int i;
    for (i = 0; i < argv->niter; i++)
    {
        /* insert an item from the fifo */
        unsigned int id = argv->id;
        unsigned int value = i * 100 + id;
        fifo::in(id, value);

        /* print them */
        printf("\e[30;01mThe value %05u was produced by thread P%02u!\e[0m\n", value, id);

        /* do something else */
        gaussianDelay(10, 5);
    }

    printf("Producer %u is quiting\n", argv->id);
    return NULL;
}

/* ******************************************************* */

/* The consumer thread */
void *consumer(void *argp)
{
    /* cast argument to real type */
    ARGV* argv = (ARGV*)argp;

    /* make the job */
    unsigned int i;
    for (i = 0; i < argv->niter; i++)
    {
        /* do something else */
        gaussianDelay(10, 5);

        /* retrieve an item from the fifo */
        unsigned int pid, value;
        fifo::out(&pid, &value);

        /* print them */
        if (value == 99999 || pid == 99 || (value % 100) != pid)
            printf("\e[31;01mThe value %05u was consumed by thread P%02u!\e[0m\n", value, pid);
        else
            printf("\e[32;01mThe value %05u was consumed by thread P%02u!\e[0m\n", value, pid);
    }

    printf("Consumer %u is quiting\n", argv->id);
    return NULL;
}

/* ******************************************************* */

/*   main thread: it starts the simulation and generates the producer and consumer threads */
int main(int argc, char *argv[])
{
    uint32_t niter = 10; ///< number of iterations
    uint32_t nthr = 4;   ///< number of consumers and producers

    /* command line processing */
    int option;
    while ((option = getopt(argc, argv, "i:t:h")) != -1)
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
            case 't':
                nthr = atoi(optarg);
                if (nthr > 100)
                {
                    fprintf(stderr, "Too many threads!\n");
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

    /* init fifo */
    fifo::create();

    /* start random generator */
    srand(getpid());

    /* launching the consumers */
    pthread_t cthr[nthr];   /* consumers' ids */
    ARGV carg[nthr];        /* consumers' args */
    printf("Launching %d consumer threads, each performing %d iterations\n", nthr, niter);
    for (uint32_t i = 0; i < nthr; i++)
    {
        carg[i].id = i;
        carg[i].niter = niter;
        thread_create(&cthr[i], NULL, consumer, &carg[i]);
    }

    /* launching the producers */
    pthread_t pthr[nthr];   /* producers' ids */
    ARGV parg[nthr];        /* producers' args */
    printf("Launching %d producer threads, each performing %d iterations\n", nthr, niter);
    for (uint32_t i = 0; i < nthr; i++)
    {
        parg[i].id = i;
        parg[i].niter = niter;
        thread_create(&pthr[i], NULL, producer, &parg[i]);
    }

    /* wait for threads to conclude */
    for (uint32_t i = 0; i < nthr; i++)
    {
        thread_join(pthr[i], NULL);
        printf("Producer thread %d has terminated\n", i);
    }
    for (uint32_t i = 0; i < nthr; i++)
    {
        thread_join(cthr[i], NULL);
        printf("Consumer thread %d has terminated\n", i);
    }

    return EXIT_SUCCESS;
}

