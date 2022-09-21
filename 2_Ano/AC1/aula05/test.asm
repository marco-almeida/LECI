        # Mapa de registos
        # i: $t0
        # lista: $t1
        # lista[i]: $t2
        .data
str1:   .asciiz "; "
str2:   .asciiz "\nConteudo do array:\n"
lista:  .word 8,-4,3,5,124,-15,87,9,27,15   # a diretiva ".word" alinha num endereço múltiplo de 4
        .eqv    print_int10,1
        .eqv    print_string,4
        .eqv    SIZE,10 
        .text
        .globl main
main:   li      $v0,print_string                       
        la      $a0,str2
        syscall                          # print_string("\nConteudo do array:\n");

        la      $t1,lista                  # p = lista
        li      $t0,0                      # i = 0
while:  bgeu    $t0,SIZE,endw                 # while(p < lista+SIZE) {
        lb		$t2, 0($t1)		# 
        addi    $t1,$t1,4

        li      $v0,print_int10
        la      $a0,($t2)
        syscall                             # print_int10( *p );

        addiu $t0,$t0,1                     # p++;
        j while                             # }
endw:
        jr      $ra                             # termina o programa
