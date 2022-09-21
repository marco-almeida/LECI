.data
um:	.float	1.0
dois:	.float	4
.text
.globl main
main:
	l.s	$f0,dois
	li	$a0,2
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	xtoy
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	mov.d	$f12,$f0
	li	$v0,2
	syscall
	jr	$ra

xtoy:
# float xtoy(float x, int y)
# float x : $f0
# int y : $a0
	li	$t0,0						# i = 0;
	l.s	$f2,um						# result = 1.0;
for:
	addiu	$sp,$sp,-8
	sw	$ra,0($sp)
	sw	$a0,4($sp)
	jal	absa
	lw	$ra,0($sp)
	lw	$a0,4($sp)
	addiu	$sp,$sp,8
	move	$t1,$v0						# 
	bge	$t0,$t1,endfor					# for(i < abs(y))
	ble	$a0,0,else					# if(y > 0)
	mul.s	$f2,$f2,$f0					# result *= x;
	j	endif						# 
else:								# else
	div.s	$f2,$f2,$f0					# result /= x;
endif:
	addiu	$t0,$t0,1
	j	for
endfor:
	mov.s	$f0,$f2						# return result;
	jr	$ra
	
absa:
# int abs(int val)
# val : $a0
	bge	$a0,0,endif1		# if(val < 0)
	subu	$a0,$0,$a0		# val = -val;
endif1:
	move	$v0,$a0			# return val; 
	jr	$ra
