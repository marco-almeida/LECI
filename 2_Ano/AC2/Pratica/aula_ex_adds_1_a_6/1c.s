.data
.equ SFR_BASE_HI, 0xBF88 # 16 MSbits of SFR area 
.equ TRISE, 0x6100   # TRISE address is 0xBF886100 
.equ PORTE, 0x6110   # PORTE address is 0xBF886110 
.equ LATE,  0x6120   # LATE  address is 0xBF886120
.equ TRISB, 0x6040
.equ PORTB, 0x6050
.equ LATB,  0x6060
.text
.globl main
main:
    lui     $t1, SFR_BASE_HI #  
    lw      $t2, TRISE($t1) # READ  (Mem_addr = 0xBF880000 + 0x6100) -> obter configuracao de bits 
    andi    $t2, $t2, 0xFF00 # MODIFY (bit0= (0 means OUTPUT)) -> meter bit 0 como output, logo, a 0
    sw      $t2, TRISE($t1) # WRITE (Write TRISE register) -> guardar configuracao

    lw      $t2, TRISB($t1) # READ  (Mem_addr = 0xBF880000 + 0x6100) -> obter configuracao de bits 
    ori     $t2, $t2, 0x000F # MODIFY (bit1= (1 means INPUT)) -> meter bit 0 como input, logo, a 1
    sw      $t2, TRISB($t1) # WRITE (Write TRISB register) -> guardar configuracao

    do:
        lw      $t0,PORTB($t1) # read input bits config
        andi    $t0,$t0,0x000F       
        xori    $t0,$t0,0x000F # 4 bits menos significativos negados
        lw      $t2,PORTB($t1) # read input bits config
        andi    $t2,$t2,0x0001       
        sll     $t2,$t2,7
        lw      $t3,PORTB($t1) # read input bits config
        andi    $t3,$t3,0x0002       
        sll     $t3,$t3,5
        lw      $t4,PORTB($t1) # read input bits config
        andi    $t4,$t4,0x0004       
        sll     $t4,$t4,3
        lw      $t5,PORTB($t1) # read input bits config
        andi    $t5,$t5,0x0008       
        sll     $t5,$t5,1
        
        lw      $t6,LATE($t1)   # get output bits
        andi    $t6,$t6,0xFF00 # clean output[0] before placing input

        or      $t3,$t3,$t2     
        or      $t3,$t3,$t4     
        or      $t3,$t3,$t5     
        or      $t6,$t6,$t3     
        or      $t6,$t6,$t0
        sw      $t6,LATE($t1)   # write
    j   do
    jr  $ra
