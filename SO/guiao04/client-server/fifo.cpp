#include "fifo.h"

#include <stdexcept>

namespace fifo {
#define N 10  // fifo size

/* create a FIFO in shared memory, initialize it, and return its id */
FIFO* create() {
    //FIFO* fifo = new FIFO;
    FIFO* fifo = (FIFO*)malloc(sizeof(FIFO));
    fifo->fifoNotFull = PTHREAD_COND_INITIALIZER;
    fifo->fifoNotEmpty = PTHREAD_COND_INITIALIZER;
    fifo->accessCR = PTHREAD_MUTEX_INITIALIZER;

    if (fifo == NULL) {
        perror("Fail creating fifo");
        exit(EXIT_FAILURE);
    }
    for (uint32_t i = 0; i < N; i++) {
        fifo->slot[i] = -1;
    }
    fifo->ii = fifo->ri = 0;
    fifo->cnt = 0;

    // cond_broadcast(&fifo->fifoNotFull);
    // mutex_unlock(&fifo->accessCR);

    return fifo;
}

/* ************************************************* */

void destroy(FIFO& _fifo) {
    delete &_fifo; 
}

/* ************************************************* */

static bool isFull(FIFO& _fifo) { return _fifo.cnt == N; }

static bool isEmpty(FIFO& _fifo) { return _fifo.cnt == 0; }

void in(FIFO& _fifo, int value) {
    mutex_lock(&_fifo.accessCR);
    /* wait while fifo is full */
    while (isFull(_fifo)) {
        cond_wait(&_fifo.fifoNotFull, &_fifo.accessCR);
    }
    _fifo.slot[_fifo.ii] = value;
    _fifo.ii = (_fifo.ii + 1) % N;
    _fifo.cnt++;

    cond_broadcast(&_fifo.fifoNotEmpty);
    mutex_unlock(&_fifo.accessCR);
}

/* ************************************************* */

/* Retrieval of a pair <id, value> from the FIFO */

void out(FIFO& _fifo, int& valuep) {
    mutex_lock(&_fifo.accessCR);

    while (isEmpty(_fifo)) {
        cond_wait(&_fifo.fifoNotEmpty, &_fifo.accessCR);
    }
    /* Retrieve pair */
    valuep = _fifo.slot[_fifo.ri];
    _fifo.ri = (_fifo.ri + 1) % N;
    _fifo.cnt--;

    cond_broadcast(&_fifo.fifoNotFull);
    mutex_unlock(&_fifo.accessCR);
}

/* ************************************************* */

}  // namespace fifo
