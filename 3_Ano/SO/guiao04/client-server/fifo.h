#ifndef __SO_IPC_FIFO_
#define __SO_IPC_FIFO_
#include <errno.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <unistd.h>

#include "thread.h"
namespace fifo {

struct FIFO {
    uint32_t ii;   // point of insertion
    uint32_t ri;   // point of retrieval
    uint32_t cnt;  // number of items stored
    int slot[10];      // id of buffers

    pthread_mutex_t accessCR;
    pthread_cond_t fifoNotFull;
    pthread_cond_t fifoNotEmpty;
};

/** \brief create a FIFO in shared memory, initialize it, and return its id */
FIFO* create();

/** \brief destroy the shared FIFO given id */
void destroy(FIFO& _fifo);

/**
 *  \brief Insertion of a value into the FIFO.
 * \param _fifo FIFO to be used
 * \param value value to be stored
 */
void in(FIFO& _fifo, int value);

/**
 *  \brief Retrieval of a value from the FIFO.
 * \param _fifo FIFO to be used
 * \param valuep pointer to recipient where to store the value
 */
void out(FIFO& _fifo, int& valuep);

}  // namespace fifo
#endif /* __SO_IPC_FIFO_ */
