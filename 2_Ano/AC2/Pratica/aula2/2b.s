.data
str0: .asciiz "Delay desejado (ms): "
str1: .asciiz "Counter: "
.text
.globl main
main:
    li  $v0,8               #
    la  $a0,str0            #
	syscall				    # printstring("Delay desejado (ms): ");

	li  $v0,5               #
	syscall                 #
	move 	$t9,$v0			# readInt();

	li  $v0,3               #
	li  $a0,'\n'            #
	syscall                 # putchar(\n);
	
    li  $t0,0               # counter = 0;
    while:
	li  $v0,8               #
	la  $a0,str1            #
	syscall				    # print("counter");

    li  $v0,6               #
    move    $a0,$t0         #
	li   $a1, 0x0004000A    #  $a1 = 10 | 4 << 16
    syscall                 # printInt(counter++, 10 | 4 << 16);

	move	$a0,$t9         #
    addiu   $sp,$sp,-4      #
    sw  $ra,0($sp)          #
    jal delay               # delay(ms)
    lw  $ra,0($sp)          #
    addiu   $sp,$sp,4       #

    addi    $t0,$t0,1       # counter++;

    li  $v0,3               #
	li  $a0,'\r'            #
	syscall                 # putchar(\r);

        j   while
    li  $v0,0
jr  $ra

delay:
    # void delay(unsigned int ms)
    # ms : $a0
    li  $v0,12                  #
    syscall                     # resetCoreTimer();
    mul $a0,$a0,20000
    while2: 
        li  $v0,11
        syscall                 # readCoreTimer();
        ble $v0,$a0,while2      # while(readCoreTimer() < 200000);
    jr  $ra
