.data
a1:	.asciiz	"St1" # ocupa 4 bytes												# 0
	.space 6														# 4
	.align 3														# 10
g:	.double 3.141592653589													# 16
a2:	.word 291,756														# 24
v:	.byte 'X'														# 32
	.align 2														# 33
k:	.float	1.983														# 36
str1:	.asciiz ","
.text
.globl main
main:
	la	$a0,a1
	li	$v0,4
	syscall					# St1
	
	la	$a0,str1
	syscall					# ,
	
	la	$t0,a1
	addiu	$t0,$t0,16
	l.d	$f12,0($t0)
	mov.d	$f2,$f12			# [s2.g]
	li	$v0,3
	syscall					# 3.141592653589
	
	li	$v0,4
	la	$a0,str1
	syscall					# ,
	
	la	$t0,a1
	addiu	$t0,$t0,24
	li	$v0,1
	lw	$a0,0($t0)
	syscall					# 291
	
	li	$v0,4
	la	$a0,str1
	syscall					# ,
	
	addiu	$t0,$t0,4
	li	$v0,1
	lw	$a0,0($t0)
	mtc1.d	$a0,$f4
	cvt.d.w	$f4,$f4				# s2.a2[1]
	syscall					# 756
	
	li	$v0,4
	la	$a0,str1
	syscall					# ,
	
	li	$v0,11
	la	$t0,a1
	addiu	$t0,$t0,32
	lb	$a0,0($t0)
	syscall					# X
	
	li	$v0,4
	la	$a0,str1
	syscall					# ,
	
	li	$v0,2
	la	$t0,a1
	addiu	$t0,$t0,36
	l.s	$f12,0($t0)
	mov.d	$f6,$f12
	cvt.d.s $f6,$f6			# k
	syscall
	
	mul.d	$f2,$f2,$f4
	div.d	$f0,$f2,$f6
	jr	$ra