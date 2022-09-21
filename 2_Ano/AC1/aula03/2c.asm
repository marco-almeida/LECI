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
        syscall                 # print_string("\nO valor em binério é: );

        li $t2,0                # i = 0

for:    bge $t2,32,endFor       # while (i < 32)
        rem $t3,$t2,4           # i % 4
        bne $t3,0,elsee         # f((i % 4) == 0) // resto da divisão inteira

        li $v0,print_char
        la $a0,' '
        syscall                 # print_char(' ');
elsee:

        srl $t1,$t0,31          # bit = value >> 31;
      
        li $v0,print_char
        addi $t1,$t1,0x30
        la $a0,($t1)
        syscall                 # print_char('0' + bit);

        add $t2,$t2,1           # i++
        sll $t0,$t0,1           # value = value << 1;      // shift left de 1 bit
        j for

endFor: 			 # }
        jr $ra                  # }


