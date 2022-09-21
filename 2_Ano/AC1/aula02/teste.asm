.data
    myMessage: .asciiz "Hello World \n"

.text
.globl main
main: 

    ori $v0,$0,4
    la $a0,myMessage
    syscall
    li $v0,10
    syscall
