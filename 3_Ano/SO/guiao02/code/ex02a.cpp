#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/signal.h>

int main(int argc, char** argv)
{
    printf("ex02a PID = %u\n", getpid());
    // pid_t = strtoul(argv[1]);
    // kill(pid_t, SIGINT);
    kill(getpid(), SIGINT);
    printf("Shouldn't be able to see this print\n");
    return EXIT_SUCCESS;
}
