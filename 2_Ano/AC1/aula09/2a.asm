.data
.text
.globl main
main:
	li	$v0,7
	syscall
	
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	f2c
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	
	mov.d	$f12,$f0
	li	$v0,3
	syscall
	
	jr	$ra
	
f2c:
# double f2c(double ft)
# ft : $f0
	li	$t0,5
	li	$t1,9
	li	$t2,32
	
	mtc1.d $t0,$f6
	mtc1.d $t1,$f2
	mtc1.d $t2,$f4
	
	cvt.d.w $f6,$f6
	cvt.d.w $f2,$f2
	cvt.d.w $f4,$f4
	
	sub.d	$f4,$f0,$f4	# ft - 32.0
	mul.d	$f4,$f4,$f6	# (ft - 32) * 5
	div.d	$f0,$f4,$f2	# return (5.0 / 9.0 * (ft – 32.0)); 
	
	jr	$ra
