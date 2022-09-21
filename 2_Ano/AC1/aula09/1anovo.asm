.data
num:	.float 2.59375
zer:	.float 0.0
.text
.globl main
main:
	# Mapa de registos:
	# val : $f2
	# res : $f0
do:	
	li	$v0,5
	syscall			# val = read_int()

	mtc1	$v0,$f4		# f2 = float(val)
	cvt.s.w $f2,$f4		# f2 = float(val)
	
	l.s	$f4,num
	mul.s	$f0,$f4,$f2	# res = (float)val * 2.59375;
	
	li	$v0,2
	mov.s	$f12,$f0
	syscall			# print_float( res );
	
	l.s	$f6,zer		
	c.eq.s	$f0,$f6
	bc1f	do

	jr	$ra
