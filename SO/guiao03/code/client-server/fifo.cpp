#include "fifo.h"
#include <stdexcept>

namespace fifo {
#define N 10  // fifo size

/* index of access, full and empty semaphores */
#define ACCESS 0
#define NITEMS 1
#define NSLOTS 2

/* ************************************************* */

static void down(int semid, unsigned short index) {
    struct sembuf op = {index, -1, 0};
    psemop(semid, &op, 1);
}

/* ************************************************* */

static void up(int semid, unsigned short index) {
    struct sembuf op = {index, 1, 0};
    psemop(semid, &op, 1);
}

/* ************************************************* */

/* create a FIFO in shared memory, initialize it, and return its id */
FIFO* create() {
    /* create the shared memory */
    int fifoId = pshmget(IPC_PRIVATE, sizeof(FIFO), 0600 | IPC_CREAT | IPC_EXCL);

    /*  attach shared memory to process addressing space */
    FIFO* fifo = (FIFO*)pshmat(fifoId, NULL, 0);

    fifo->fifoId = fifoId;  // save the id of the shared memory
    /* init fifo */
    for (uint32_t i = 0; i < N; i++) {
        fifo->slot[i] = -1;  // empty slot
    }

    fifo->ii = 0;
    fifo->ri = 0;
    fifo->cnt = 0;

    /* create access, full and empty semaphores */
    fifo->semid = psemget(IPC_PRIVATE, 3, 0600 | IPC_CREAT | IPC_EXCL);

    /* init semaphores */
    for (uint32_t i = 0; i < N; i++) {
        up(fifo->semid, NSLOTS);
    }
    up(fifo->semid, ACCESS);

    if (fifo == NULL) throw std::runtime_error("Error creating buffer");
    return fifo;
}

/* ************************************************* */

void destroy(FIFO& _fifo) {
    /* destroy the shared memory */
    pshmctl(_fifo.fifoId, IPC_RMID, NULL);

    /* detach shared memory from process addressing space */
    pshmdt(&_fifo);
}

/* ************************************************* */

void in(FIFO& _fifo, int value) {
    /* decrement emptiness, blocking if necessary, and lock access */
    down(_fifo.semid, NSLOTS);
    down(_fifo.semid, ACCESS);

    /* Insert pair */
    _fifo.slot[_fifo.ii] = value;
    _fifo.ii = (_fifo.ii + 1) % N;
    _fifo.cnt++;

    /* unlock access and increment fullness */
    up(_fifo.semid, ACCESS);
    up(_fifo.semid, NITEMS);  // trocar este com o de cima se der erro
}

/* ************************************************* */

/* Retrieval of a pair <id, value> from the FIFO */

void out(FIFO& _fifo, int& valuep) {
    /* decrement fullness, blocking if necessary, and lock access */
    down(_fifo.semid, NITEMS);
    down(_fifo.semid, ACCESS);

    /* Retrieve pair */
    valuep = _fifo.slot[_fifo.ri];
    _fifo.ri = (_fifo.ri + 1) % N;
    _fifo.cnt--;

    /* unlock access and increment fullness */
    up(_fifo.semid, ACCESS);
    up(_fifo.semid, NSLOTS);
}

/* ************************************************* */

}  // namespace fifo
