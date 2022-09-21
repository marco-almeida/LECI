.data
.text
.globl main
main:
    li  $t0,0       # counter = 0;
    while:
        li  $v0,12
        syscall         # resetCoreTimer();

        while2: 
            li  $v0,11
            syscall         # readCoreTimer();
            ble $v0,20000000,while2         # while(readCoreTimer() < 200000);
        endw:

        li  $v0,6                   #
        move    $a0,$t0             #
        li  $a1,0X0004000A          #
        syscall                     # printInt(counter++, 10 | 4 << 16);

        li  $v0,3                   #
        li  $a0,'\r'                #
        syscall                     # putChar('\r');

        addi    $t0,$t0,1           # counter++;                     

        j   while
    li  $v0,0
jr  $ra
