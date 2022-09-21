.data
zero:	.double	0.0
um:	.double	1.0
meio:	.double 0.5
valor:	.double	25.0
.text
.globl main
main:
	l.d	$f12,valor
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	sqrt
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	mov.d	$f12,$f0
	li	$v0,3
	syscall


sqrt:
# double sqrt(double val)
# val : $f12
	l.d	$f0,zero		# $f0 = 0.0
	l.d	$f4,um			# xn = 1.0; 
	li	$t0,0			# int i = 0;
	
	c.le.d	$f12,$f0			# if(val > 0.0) 
	bc1t	else
do:
	mov.d	$f2,$f4			# aux = xn;
	div.d	$f8,$f12,$f4		# val / xn
	add.d	$f8,$f8,$f4		# xn + val/xn
	l.d	$f10,meio		# $f10 = 0.5
	mul.d	$f4,$f8,$f10		# xn = 0.5 * (xn + val/xn)
	c.eq.d	$f2,$f4
	bc1t	enddo
	addiu	$t0,$t0,1
	bge	$t0,25,enddo		# } while((aux != xn) && (++i < 25));
	j	do
else:					# } else
	l.d	$f4,zero
enddo:
	mov.d	$f0,$f4			# return xn;
	jr	$ra