.data
numeros:	.word	8,-4,3,5,124,-12,87,9,27,15
str1:		.asciiz "; "
str2:		.asciiz "\nConteudo do array\n"
.text
.globl main
main:
	# Mapa de registos
	# p: $t0
	# *p: $t1
	# lista+Size: $t2
	
	la	$t0,numeros
	addiu	$t2,$t0,40
	li	$v0,4
	la	$a0,str2
	syscall			
	
for:	bge	$t0,$t2,endfor
	
	lw	$t1,0($t0)
	
	li	$v0,1
	la	$a0,($t1)
	syscall

	li	$v0,4
	la	$a0,str1
	syscall
	
	addiu	$t0,$t0,4
	j 	for
endfor:
	jr	$ra

	
