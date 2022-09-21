# Mapa de registos:
# $t0 - value
# $t1 - bit
# $t2 - i
        .data
int:    .asciiz "Introduza um numero: "
val:    .asciiz "\nO valor em binário é: "
        .eqv print_string,4
        .eqv read_int,5
        .eqv print_char,11

        .text
        .globl main
main:   li $v0,print_string
        la $a0,int
        syscall                 # print_string("Introduza um numero: ");

        li $v0,read_int
        syscall
        or $t0,$0,$v0           # value = read_int();

        li $v0,print_string
        la $a0,val
        syscall                 # print_string("\nO valor em binário é: ");

        li $t2,0                # i = 0

for:    bge $t2,32,endFor       # while (i < 32)
        rem $t3,$t2,4           # i % 4
        bne $t3,0,elsee         # f((i % 4) == 0) // resto da divisÃ£o inteira

        li $v0,print_char
        la $a0,' '
        syscall                 # print_char(' ');
elsee:

        andi $t1,$t0,0x80000000 # bit = value & 0x80000000; // isola bit 31

        beq $t1,0,else          # if(bit != 0)
        li $v0,print_char
        la $a0,'1'
        syscall                 # print_char('1');
        j incr
else:                           # else
        li $v0,print_char
        la $a0,'0'
        syscall                 # print_char('0');

incr:   add $t2,$t2,1           # i++
        sll $t0,$t0,1           # value = value << 1;      // shift left de 1 bit
        j for

endFor: 			 # }
        jr $ra                  # }


