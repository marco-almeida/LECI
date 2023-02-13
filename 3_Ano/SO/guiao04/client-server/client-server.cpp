#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <ctime>

#include "service.h"
#include "thread.h"

const char* random_strings[] = {
    "Lorem",    "23",   "is",        "simply",  "459",
    "of",       "the",  "1400",      "323",     "typesetting",
    "industry", "1500", "unchanged", "desktop", "78"};

service::ServiceRequest get_random_request() {
    service::ServiceRequest request;
    uint random_string_idx =
        rand() % (sizeof(random_strings) / sizeof(random_strings[0]));
    uint str_size = strlen(random_strings[random_string_idx]);
    request.data = (char*)malloc(sizeof(char) * str_size);
    memcpy(request.data, random_strings[random_string_idx], str_size);
    request.size = str_size;
    return request;
}

void* produce_and_get_response(void*) {
    srand(gettid());
    service::ServiceRequest sreq = get_random_request();
    printf("Client query (proccess %d):text = %s\n", gettid(), sreq.data);
    service::ServiceResponse response;
    service::callService(sreq, response); // a dar erro aqui
    printf("Server awnser (process %d): %d\n", gettid(), response.data[0]);
    free(sreq.data);
    free(response.data);
    return NULL;
}

void* consume(void*) {
    printf("Consuming (thread id %d)\n", gettid());
    service::processService();
    printf("thread id %d successfully consumed\n", gettid());
    return NULL;
}

int main(int argc, char* argv[]) {
    unsigned int producers = 4;
    unsigned int consumers = 4;
    service::create();

    pthread_t cpid[consumers];

    for (unsigned int i = 0; i < consumers; i++) {
        thread_create(&cpid[i] , NULL , consume , NULL);
    }

    pthread_t ppid[producers];

    for (unsigned int i = 0; i < producers; i++) {
        thread_create(&ppid[i] , NULL , produce_and_get_response , NULL);
    }

    for (unsigned int i = 0; i < consumers; i++) {
        thread_join(cpid[i] , NULL);
        printf("Consumer %ld terminated\n", cpid[i]);
    }

    for (unsigned int i = 0; i < producers; i++) {
        thread_join(ppid[i], NULL);
        printf("Producer %ld terminated\n", ppid[i]);
    }

    service::destroy();

    return EXIT_SUCCESS;
}