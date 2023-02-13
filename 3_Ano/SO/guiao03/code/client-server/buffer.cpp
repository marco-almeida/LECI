#include "buffer.h"

#include <string.h>

#include <stdexcept>

#define SOLVED 0

namespace buffer {

BUFFER* create() {
    /* create the shared memory */
    int bufferID =
        pshmget(IPC_PRIVATE, sizeof(BUFFER), 0600 | IPC_CREAT | IPC_EXCL);

    /*  attach shared memory to process addressing space */
    BUFFER* buffer = (BUFFER*)pshmat(bufferID, NULL, 0);

    buffer->bufferid = bufferID;  // save the id of the shared memory

    /* create access, full and empty semaphores */
    buffer->semid = psemget(IPC_PRIVATE, 1, 0600 | IPC_CREAT | IPC_EXCL);

    set_solved(*buffer);

    return buffer;
}

void destroy(BUFFER& _buffer) {
    int buffid = _buffer.bufferid;
    /* detach shared memory from process addressing space */
    pshmdt(&_buffer);

    /* destroy the shared memory */
    pshmctl(buffid, IPC_RMID, NULL);
}

void clear(BUFFER& _buffer) {
    _buffer.length = 0;
    memset(_buffer.data, 0, BSIZE);
}

void write(BUFFER& _buffer, char data[], int length) {

    if (_buffer.length + length >= BSIZE) {
        throw std::runtime_error("Buffer length exceeded");
    } else {
        memcpy(&_buffer.data[_buffer.length], data, length);
        _buffer.length += length;
    }
}

void read(BUFFER& _buffer, char* dest) {

    memcpy(dest, _buffer.data, _buffer.length);
    clear(_buffer);
}

void wait_until_solved(BUFFER& _buffer) {
    struct sembuf op = {0 , -1 , 0};
    psemop(_buffer.semid, &op , 1); //put semaphore at 0
    psemop(_buffer.semid, &op , 1); //wait until server increments semaphore
}

void set_solved(BUFFER& _buffer){ 
    struct sembuf op = {0 , 1 , 0};
    psemop(_buffer.semid, &op , 1); // increment semaphore
}
}  // namespace buffer