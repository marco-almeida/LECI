.data
.equ SFR_BASE_HI, 0xBF88 # 16 MSbits of SFR area 
.equ TRISE, 0x6100   # TRISE address is 0xBF886100 
.equ PORTE, 0x6110   # PORTE address is 0xBF886110 
.equ LATE,  0x6120   # LATE  address is 0xBF886120
.text
.globl main
main:
    lui     $t1, SFR_BASE_HI #  
    lw      $t2, TRISE($t1) # READ  (Mem_addr = 0xBF880000 + 0x6100) -> obter configuracao de bits 
    andi    $t2, $t2, 0xFFF0 # MODIFY (bit0= (0 means OUTPUT)) -> meter bit 0 como output, logo, a 0
    sw      $t2, TRISE($t1) # WRITE (Write TRISE register) -> guardar configuracao

    reset:   
        lw      $t0,LATE($t1)   # get output bits
        andi    $t0,$t0,0xFFF0 # reset
        sw      $t0,LATE($t1)   # write
    do:
        li      $v0,2
        syscall             # getChar

        blt     $v0,'0',tudo
        bgt     $v0,'3',tudo

        bne     $v0,'0',if0
        lw      $t3,LATE($t1)   # get output bits
        andi    $t3,$t3,0xFFF0 # clean output[0] before placing input
        ori     $t3,$t3,0x0001
        sw      $t3,LATE($t1)   # write
        j       do
        if0:
        bne     $v0,'1',if1
        lw      $t3,LATE($t1)   # get output bits
        andi    $t3,$t3,0xFFF0 # clean output[0] before placing input
        ori     $t3,$t3,0x0002
        sw      $t3,LATE($t1)   # write
        j       do
        if1:
        bne     $v0,'2',if2
        lw      $t3,LATE($t1)   # get output bits
        andi    $t3,$t3,0xFFF0 # clean output[0] before placing input
        ori     $t3,$t3,0x0004
        sw      $t3,LATE($t1)   # write
        j       do
        if2:
        lw      $t3,LATE($t1)   # get output bits
        andi    $t3,$t3,0xFFF0 # clean output[0] before placing input
        ori     $t3,$t3,0x0008
        sw      $t3,LATE($t1)   # write
        j       do
        tudo:
        lw      $t3,LATE($t1)   # get output bits
        andi    $t3,$t3,0xFFF0 # clean output[0] before placing input
        ori     $t3,$t3,0x000F
        sw      $t3,LATE($t1)   # write
        
        li      $a0,1000
        addiu   $sp,$sp,-4      #
        sw      $ra,0($sp)          #
        jal     delay               # 
        lw      $ra,0($sp)          #
        addiu   $sp,$sp,4       #
        j       reset
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
