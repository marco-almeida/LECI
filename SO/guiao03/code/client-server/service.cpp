#include "service.h"

#include <errno.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <unistd.h>

#include "buffer.h"
#include "fifo.h"
#include "process.h"

namespace service {
#define N 10

static buffer::BUFFER *pool[N];  // pool of buffers
static fifo::FIFO *freeBuffers;
static fifo::FIFO *pendingRequests;

void create() {
    freeBuffers = fifo::create();
    pendingRequests = fifo::create();
    int i = 0;

    for (i = 0; i < N; i++) {
        fifo::in(*freeBuffers, i);
    }

    for (i = 0; i < N; i++) {
        pool[i] = buffer::create();
    }
}

void destroy() {
    fifo::destroy(*freeBuffers);
    fifo::destroy(*pendingRequests);

    for (int i = 0; i < 10; i++) {
        buffer::destroy(*pool[i]);
    }
}

void callService(ServiceRequest &req, ServiceResponse &res) {
    int id;
    fifo::out(*freeBuffers, id);  // take a buffer out of fifo of free buffers
    char op = (char)req.op;
    buffer::write(*pool[id], &op, sizeof(char));  // put request data on buffer
    buffer::write(*pool[id], req.data, req.size);
    fifo::in(*pendingRequests, id);  // add buffer to fifo of pending requests
    buffer::wait_until_solved(*pool[id]);  // blocked til response is available
    res.data = (char *)malloc(sizeof(char) * pool[id]->length);
    res.size = pool[id]->length;
    buffer::read(*pool[id], res.data);
}

static void produceResponse(ServiceRequest &req, ServiceResponse &res) {
    if (req.op == operation::letters) {
        uint letters = 0;
        for (int i = 0; i < req.size; i++) {
            if ((req.data[i] >= 'a' && req.data[i] <= 'z') ||
                (req.data[i] >= 'A' && req.data[i] <= 'Z'))
                letters++;
        }
        char *response = (char *)malloc(2 * sizeof(char));
        response[0] = (char)letters;
        response[1] = '\0';
        res.data = response;
        res.size = 2 * sizeof(char);
    }

    else if (req.op == operation::digits) {
        uint digits = 0;
        for (int i = 0; i < req.size; i++) {
            if (req.data[i] >= '0' && req.data[i] <= '9') digits++;
        }
        char *response = (char *)malloc(2 * sizeof(char));
        response[0] = (char)digits;
        response[1] = '\0';
        res.data = response;
        res.size = 2 * sizeof(char);
    }
}

void processService() {
    int id;
    fifo::out(*pendingRequests, id);  // get id

    ServiceRequest req;
    req.data = (char *)malloc((pool[id]->length) * sizeof(char));
    req.size = (pool[id]->length) * sizeof(char);
    buffer::read(*pool[id], req.data);

    operation op = (operation)(int)(req.data[0]);
    req.op = op;
    ServiceResponse res;
    produceResponse(req, res);  // produce response
    free(req.data);
    buffer::clear(*pool[id]);
    buffer::write(*pool[id], res.data, res.size);
    free(res.data);
    buffer::set_solved(*pool[id]);
}



}  // namespace service