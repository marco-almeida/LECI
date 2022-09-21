.equ SFR_BASE_HI, 0xBF88 # 16 MSbits of SFR area 
.equ TRISE, 0x6100   # TRISE address is 0xBF886100 
.equ PORTE, 0x6110   # PORTE address is 0xBF886110 
.equ LATE,  0x6120   # LATE  address is 0xBF886120
.equ TRISB, 0x6040
.equ PORTB, 0x6050
.equ LATB,  0x6060
.equ TRISD, 0x60C0
.equ PORTD, 0x60D0
.equ LATD,  0x60E0
.data
.text
.globl main
main:
    li      $t0,0
    lui     $t1, SFR_BASE_HI #  
    lw      $t2, TRISE($t1) # READ  (Mem_addr = 0xBF880000 + 0x6100) -> obter configuracao de bits 
    andi    $t2, $t2, 0xFFF0 # MODIFY (bit0= (0 means OUTPUT)) -> meter bit 0 como output, logo, a 0
    sw      $t2, TRISE($t1) # WRITE (Write TRISE register) -> guardar configuracao

    lw      $t2,TRISB($t1)      # read
    ori     $t2,$t2,0x000F      # modify
    sw      $t2,TRISB($t1)      # write

    while:
    
    lw      $t3,LATE($t1)   # get output bits
    andi    $t3,$t3,0xFFF0 # clean output[0] before placing input
    or      $t3,$t3,$t0     # re0 = v
    sw      $t3,LATE($t1)   # write

    # lw      $t3,LATB($t1)
    # andi    $t3,$t3,0xFFFE
    # or      $t3,$t3,$t0
    # sw      $t3,LATB($t1)
    
    addiu   $sp,$sp,-4      #
    sw      $ra,0($sp)          #
    li      $a0,1000             #
    jal     delay               # delay(500) -> 0,5s à espera
    lw      $ra,0($sp)          #
    addiu   $sp,$sp,4       #
    blt     $t0,15,else
        li      $t0,0
        j       endif
    else:
        addi    $t0,$t0,1
    endif:
    
    j       while
jr  $ra

delay:
    # void delay(unsigned int ms)
    # ms : $a0
    li  $v0,12                  #
    syscall                     # resetCoreTimer();
    mul $a0,$a0,20000
    while_delay: 
        li  $v0,11
        syscall                 # readCoreTimer();
        ble $v0,$a0,while_delay      # while(readCoreTimer() < 200000);
    jr  $ra
