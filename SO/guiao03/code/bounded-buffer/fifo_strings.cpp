#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdbool.h>
#include <errno.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <stdint.h>
#include <string.h>

#include "fifo_strings.h"
#include "delays.h"
#include "process.h"

namespace fifostrings
{
    /** \brief internal storage size of <em>FIFOSTR memory</em> */
    #define  FIFOSTRSZ         5

    /*
     *  \brief Type of the shared data structure.
     */
    struct ITEM
    {
        uint32_t id;     ///< id of the producer
        uint32_t value;  ///< value stored
        char msg[40];   // msg stored
    };

    /* when using shared memory, the size of the data structure must be fixed */
    struct FIFOSTR
    { 
        int semid;          ///< syncronization semaphore array
        uint32_t ii;        ///< point of insertion
        uint32_t ri;        ///< point of retrieval
        uint32_t cnt;       ///< number of items stored
        ITEM slot[FIFOSTRSZ];  ///< storage memory
    };

    int fifoId = -1;
    FIFOSTR *fifo = NULL;

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

    /* create a FIFOSTR in shared memory, initialize it, and return its id */
    void create(void)
    {
        /* create the shared memory */
        fifoId = pshmget(IPC_PRIVATE, sizeof(FIFOSTR), 0600 | IPC_CREAT | IPC_EXCL);

        /*  attach shared memory to process addressing space */
        fifo = (FIFOSTR*)pshmat(fifoId, NULL, 0);

        /* init fifo */
        uint32_t i;
        for (i = 0; i < FIFOSTRSZ; i++)
        {
            fifo->slot[i].id = 99;
            fifo->slot[i].value = 99999;
            strcpy(fifo->slot[i].msg, (char*)"Nothing here");  
        }
        fifo->ii = fifo->ri = 0;
        fifo->cnt = 0;
    
        /* create access, full and empty semaphores */
        fifo->semid = psemget(IPC_PRIVATE, 3, 0600 | IPC_CREAT | IPC_EXCL);

        /* init semaphores */
        for (i = 0; i < FIFOSTRSZ; i++)
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

    /* Insertion of a pair <id, value> into the FIFOSTR  */
    void in(uint32_t id, uint32_t value, char* msg)
    {
        /* decrement emptiness, blocking if necessary, and lock access */
        down(fifo->semid, NSLOTS);
        down(fifo->semid, ACCESS);

        /* Insert pair */
        fifo->slot[fifo->ii].value = value;
        gaussianDelay(0.1, 0.5);
        fifo->slot[fifo->ii].id = id;
        strcpy(fifo->slot[fifo->ii].msg, msg);  
        fifo->ii = (fifo->ii + 1) % FIFOSTRSZ;
        fifo->cnt++;

        /* unlock access and increment fullness */
        up(fifo->semid, ACCESS);
        up(fifo->semid, NITEMS);
    }

    /* ************************************************* */

    /* Retrieval of a pair <id, value> from the FIFOSTR */

    void out (uint32_t * idp, uint32_t * valuep, char** msg)
    {
        /* decrement fullness, blocking if necessary, and lock access */
        down(fifo->semid, NITEMS);
        down(fifo->semid, ACCESS);

        /* Retrieve pair */
        *valuep = fifo->slot[fifo->ri].value;
        fifo->slot[fifo->ri].value = 99999;
        *idp = fifo->slot[fifo->ri].id;
        *msg = fifo->slot[fifo->ri].msg;
        fifo->slot[fifo->ri].id = 99;
        fifo->ri = (fifo->ri + 1) % FIFOSTRSZ;
        fifo->cnt--;

        /* unlock access and increment fullness */
        up(fifo->semid, ACCESS);
        up(fifo->semid, NSLOTS);
    }

    /* ************************************************* */

}
