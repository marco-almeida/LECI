/*
 *  @brief A simple FIFO, whose elements are pairs of integers,
 *      one being the id of the producer and the other the value produced
 *
 * @remarks safe, non busy waiting version
 *
 *  The following operations are defined:
 *     \li insertion of a value
 *     \li retrieval of a value.
 *
 * \author (2016-2022) Artur Pereira <artur at ua.pt>
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdbool.h>
#include <errno.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <stdint.h>

#include "fifo.h"
#include "delays.h"
#include "process.h"

namespace fifo
{
    /** \brief internal storage size of <em>FIFO memory</em> */
    #define  FIFOSZ         5

    /*
     *  \brief Type of the shared data structure.
     */
    struct ITEM
    {
        uint32_t id;     ///< id of the producer
        uint32_t value;  ///< value stored
    };

    /* when using shared memory, the size of the data structure must be fixed */
    struct FIFO
    { 
        int semid;          ///< syncronization semaphore array
        uint32_t ii;        ///< point of insertion
        uint32_t ri;        ///< point of retrieval
        uint32_t cnt;       ///< number of items stored
        ITEM slot[FIFOSZ];  ///< storage memory
    };

    int fifoId = -1;
    FIFO *fifo = NULL;

    /* ************************************************* */

    /* index of access, full and empty semaphores */
    #define ACCESS 0
    #define NITEMS 1
    #define NSLOTS 2

    /* ************************************************* */

    static void down(int semid, unsigned short index)
    {
        struct sembuf op = {index, -1, 0};
        psemop(semid, &op, 1);
    }

    /* ************************************************* */

    static void up(int semid, unsigned short index)
    {
        struct sembuf op = {index, 1, 0};
        psemop(semid, &op, 1);
    }


    /* ************************************************* */

    /* create a FIFO in shared memory, initialize it, and return its id */
    void create(void)
    {
        /* create the shared memory */
        fifoId = pshmget(IPC_PRIVATE, sizeof(FIFO), 0600 | IPC_CREAT | IPC_EXCL);

        /*  attach shared memory to process addressing space */
        fifo = (FIFO*)pshmat(fifoId, NULL, 0);

        /* init fifo */
        uint32_t i;
        for (i = 0; i < FIFOSZ; i++)
        {
            fifo->slot[i].id = 99;
            fifo->slot[i].value = 99999;
        }
        fifo->ii = fifo->ri = 0;
        fifo->cnt = 0;

        /* create access, full and empty semaphores */
        fifo->semid = psemget(IPC_PRIVATE, 3, 0600 | IPC_CREAT | IPC_EXCL);

        /* init semaphores */
        for (i = 0; i < FIFOSZ; i++)
        {
            up(fifo->semid, NSLOTS);
        }
        up(fifo->semid, ACCESS);
    }

    /* ************************************************* */

    void destroy()
    {
        /* detach shared memory from process addressing space */
        pshmdt(fifo);

        /* destroy the shared memory */
        pshmctl(fifoId, IPC_RMID, NULL);
    }

    /* ************************************************* */

    /* Insertion of a pair <id, value> into the FIFO  */
    void in(uint32_t id, uint32_t value)
    {
        /* decrement emptiness, blocking if necessary, and lock access */
        down(fifo->semid, NSLOTS);
        down(fifo->semid, ACCESS);

        /* Insert pair */
        fifo->slot[fifo->ii].value = value;
        gaussianDelay(0.1, 0.5);
        fifo->slot[fifo->ii].id = id;
        fifo->ii = (fifo->ii + 1) % FIFOSZ;
        fifo->cnt++;

        /* unlock access and increment fullness */
        up(fifo->semid, ACCESS);
        up(fifo->semid, NITEMS);
    }

    /* ************************************************* */

    /* Retrieval of a pair <id, value> from the FIFO */

    void out (uint32_t * idp, uint32_t * valuep)
    {
        /* decrement fullness, blocking if necessary, and lock access */
        down(fifo->semid, NITEMS);
        down(fifo->semid, ACCESS);

        /* Retrieve pair */
        *valuep = fifo->slot[fifo->ri].value;
        fifo->slot[fifo->ri].value = 99999;
        *idp = fifo->slot[fifo->ri].id;
        fifo->slot[fifo->ri].id = 99;
        fifo->ri = (fifo->ri + 1) % FIFOSZ;
        fifo->cnt--;

        /* unlock access and increment fullness */
        up(fifo->semid, ACCESS);
        up(fifo->semid, NSLOTS);
    }

    /* ************************************************* */

}
