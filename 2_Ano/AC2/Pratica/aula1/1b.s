.data
.text
.globl main
main:

do:
	li $v0,1
	syscall
	move $t0,$v0
	beq $t0,0,else
	li $v0,3
	move $a0,$t0
	syscall
	j	endif
else:
	li $v0,3
	la $a0,'.'
	syscall
endif:
	bne $t0,'\n',do
	li $v0,0
	jr $ra
