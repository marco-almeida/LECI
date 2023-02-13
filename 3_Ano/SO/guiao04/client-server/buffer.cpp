#include "buffer.h"

#include <string.h>

#include <stdexcept>

namespace buffer {

BUFFER* create() {
    BUFFER* buffer = new BUFFER;
    buffer->solved = PTHREAD_COND_INITIALIZER;
    buffer->accessCR = PTHREAD_MUTEX_INITIALIZER;

    if (buffer == NULL) {
        perror("Fail creating buffer");
        exit(EXIT_FAILURE);
    }

    buffer->length = 0;
    buffer->_solved = false;

    return buffer;
}

void destroy(BUFFER& _buffer) {
    delete &_buffer;  
}

void clear(BUFFER& _buffer) {
    mutex_lock(&_buffer.accessCR);
    _buffer.length = 0;
    memset(_buffer.data, 0, BSIZE);
    mutex_unlock(&_buffer.accessCR);
}

void write(BUFFER& _buffer, char data[], int length) {
    mutex_lock(&_buffer.accessCR);

    if (_buffer.length + length >= BSIZE) {
        throw std::runtime_error("Buffer length exceeded");
    } else {
        memcpy(&_buffer.data[_buffer.length], data, length);
        _buffer.length += length;
    }

    mutex_unlock(&_buffer.accessCR);
}

void read(BUFFER& _buffer, char* dest) {
    mutex_lock(&_buffer.accessCR);
    
    memcpy(dest, _buffer.data, _buffer.length);
    //clear(_buffer);. nao posso dar clear porque o clear chama o mutex_lock outra vez
    _buffer.length = 0;

    mutex_unlock(&_buffer.accessCR);
}

void wait_until_solved(BUFFER& _buffer) {
    mutex_lock(&_buffer.accessCR);

    while (!_buffer._solved) {
        cond_wait(&_buffer.solved, &_buffer.accessCR);
    }

    mutex_unlock(&_buffer.accessCR);
}

void set_solved(BUFFER& _buffer) {
    mutex_lock(&_buffer.accessCR);
    cond_broadcast(&_buffer.solved);
    _buffer._solved = true;
    mutex_unlock(&_buffer.accessCR);
}
}  // namespace buffer