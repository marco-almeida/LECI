.data
# classe xyz
a1:	.asciiz	"Str_1"	# ocupa 6 bytes									# 0
	.space 8											# 14
	.align 2											# 16
i:	.word 2021											# 16
a2:	.asciiz "Str_2" # ocupa 6 bytes									# 20
	.space 11											# 37
	.align 2											# 40
g:	.double 2.718281828459045									# 40
str1:	.asciiz	","
.text
.globl main
main:
	la	$a0,a1			
	li	$v0,4
	syscall
	
	la	$a0,str1
	syscall
	
	la	$t0,a1
	addiu	$t0,$t0,16
	lw	$a0,0($t0)
	li	$v0,1
	syscall
	
	li	$v0,4
	la	$a0,str1
	syscall
	
	addiu	$t0,$t0,4
	move	$a0,$t0
	syscall
	
	la	$a0,str1
	syscall
	
	addiu	$t0,$t0,20
	l.d	$f12,0($t0)
	li	$v0,3
	syscall											# é aqui
	mov.d	$f0,$f12
	
	jr	$ra