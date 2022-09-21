.data
sum:	.double 0.0
array:	.space 80
	.text
	.globl main
main:	li $t0,0
	addi $sp,$sp,-4
	sw $ra,0($sp)
	
	la $t3,array
for:	bge $t0,10,endfor
	li $v0,7
	syscall
	
	s.d $f0,0($t3)
	addi $t3,$t3,8
	
	addi $t0,$t0,1
	j for
endfor:	
	la $a0,array
	la $a1,10
	jal average
	
	mov.d $f12,$f0
	li $v0,3
	syscall
	
	lw $ra,0($sp)
	addi $sp,$sp,4
	jr $ra
	
	
	

average:
# double average(double *array, int n)
# *array : $a0
# n      : $a1
	l.d	$f2,sum			# double sum = 0.0;	
	li	$t0,0
for2:	bge	$t0,$a1,endfor2
	l.d	$f4,0($a0)		# $f4 = array[i-1]
	add.d	$f2,$f2,$f4
	addi 	$a0,$a0,8
	addi 	$t0,$t0,1
	j	for2
endfor2:
	mtc1.d	$a1,$f8			# int to double
	cvt.d.w	$f8,$f8
	div.d	$f0,$f2,$f8		# return sum / (double)n;
	jr	$ra
	