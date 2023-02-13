#define BSIZE 128
#include <errno.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <unistd.h>

#include "thread.h"

namespace buffer {

struct BUFFER {
    int length;
    char data[BSIZE];
    bool _solved;
    pthread_cond_t solved;
    pthread_mutex_t accessCR;
};

BUFFER* create();
void destroy(BUFFER& _buffer);
void clear(BUFFER& _buffer);
void write(BUFFER& _buffer, char data[], int length);
void read(BUFFER& _buffer, char* dest);

void wait_until_solved(BUFFER& _buffer);
void set_solved(BUFFER& _buffer);
}  // namespace buffer