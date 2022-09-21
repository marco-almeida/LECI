      .data
str1: .asciiz "So para chatear"
str2: .asciiz "AC1 - P\n"
      .eqv print_string,4

      .text
      .globl main
main: la $a0, str2              # instrução virtual, decomposta pelo
                                # assembler em 2 instruções nativas
      ori $v0,$0,print_string   # $v0 = 4
      syscall                   # print_string(str2)
      la $a0, str1
      syscall
      jr $ra                    # fim do programa
