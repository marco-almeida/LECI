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
	jal max
	
	mov.d $f12,$f0
	li $v0,3
	syscall
	
	lw $ra,0($sp)
	addi $sp,$sp,4
	jr $ra
	
max:
# a0 : p*
# a1 : n
	addiu	$t1,$a0,72			# double *u = p+n–1;
  	l.d	$f0,0($a0)			# double max = *p;
  	addiu	$a0,$a0,8			# *p++
  	
for_:	bgt	$a0,$t1,endfor_
	l.d	$f2,0($a0)
	c.le.d	$f0,$f2
	bc1f	endif
	mov.d	$f0,$f2				
endif:
  	addiu	$a0,$a0,8			# *p++;
  	j	for_
endfor_:
	jr	$ra



	