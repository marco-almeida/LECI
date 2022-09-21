.equ SFR_BASE_HI, 0xBF88 # 16 MSbits of SFR area 
.equ TRISE, 0x6100   # TRISE address is 0xBF886100 
.equ PORTE, 0x6110   # PORTE address is 0xBF886110 
.equ LATE,  0x6120   # LATE  address is 0xBF886120
.equ TRISB, 0x6040
.equ PORTB, 0x6050
.equ LATB,  0x6060
.data
.text
.globl main
main:
    lui     $t1, SFR_BASE_HI #  
    lw      $t2, TRISE($t1) # READ  (Mem_addr = 0xBF880000 + 0x6100) -> obter configuracao de bits 
    andi    $t2, $t2, 0xFFF0 # MODIFY (bit0= (0 means OUTPUT)) -> meter bit 0-3 como output, logo, a 0
    sw      $t2, TRISE($t1) # WRITE (Write TRISE register) -> guardar configuracao

    lw      $t2, TRISB($t1) # READ  (Mem_addr = 0xBF880000 + 0x6100) -> obter configuracao de bits 
    ori     $t2, $t2, 0x000F # MODIFY  -> meter bit 0-3 como input, logo, a 1
    sw      $t2, TRISB($t1) # WRITE (Write TRISB register) -> guardar configuracao

    # ###########################################################################################

    while:
    lw      $t2,PORTB($t1) # read input bits config
    andi    $t2,$t2,0x000F       # get rb0 value only
    xori    $t2,$t2,0x0009	    # negate RB0 and RB3

    lw      $t3,LATE($t1)   # get output bits
    andi    $t3,$t3,0xFFF0 # clean output[0]-[3] before placing input
    or      $t3,$t3,$t2     # re0 = rb0
    sw      $t3,LATE($t1)   # write
    j       while
jr  $ra
