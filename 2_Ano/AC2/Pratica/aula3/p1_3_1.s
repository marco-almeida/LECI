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
    lui     $t1, SFR_BASE_HI #  
    lw      $t2, TRISE($t1) # READ  (Mem_addr = 0xBF880000 + 0x6100) -> obter configuracao de bits 
    andi    $t2, $t2, 0xFFFE # MODIFY (bit0= (0 means OUTPUT)) -> meter bit 0 como output, logo, a 0
    sw      $t2, TRISE($t1) # WRITE (Write TRISE register) -> guardar configuracao

    lw      $t2, TRISD($t1) # READ  (Mem_addr = 0xBF880000 + 0x6100) -> obter configuracao de bits
    # andi    $t2,$t2,0xFEFF
    ori     $t2, $t2, 0x0100 # MODIFY (bit8= 1 (1 means INPUT)) -> meter bit 0 como input, logo, a 1
    sw      $t2, TRISD($t1) # WRITE (Write TRISB register) -> guardar configuracao

    while:
    lw      $t2,PORTD($t1) # read input bits config
    andi    $t2,$t2,0x0100       # get rd8 value only

    lw      $t3,LATE($t1)   # get output bits
    andi    $t3,$t3,0xFFFE # clean output[0] before placing input
    srl     $t2,$t2,8
    or      $t3,$t3,$t2     # re0 = rd8
    sw      $t3,LATE($t1)   # write
    j       while
jr  $ra
