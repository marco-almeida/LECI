#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

#include "delays.h"
#include "process.h"

int main(void)
{
  printf("Before the fork:\n");
  printf("  PID = %d, PPID = %d\n", getpid(), getppid());

  pfork();
  printf("After the fork:\n");
  printf("  PID = %d, PPID = %d\n",getpid(), getppid());
  bwRandomDelay(0, 0);
  printf("  Was I printed by the parent or by the child process? How can I know it?\n"); 
  
  return EXIT_SUCCESS;
}

