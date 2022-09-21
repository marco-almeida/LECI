# Mapa de registos
# str: $s0
# val: $s1
# O main é, neste caso, uma sub-rotina intermédia
#nao gosto disto 
.data
str: .space 33
.text
.globl main
main: 	
do1:
 	li 	$v0,5
 	syscall 
 	move 	$s1,$v0 # val = read_int()
 	move	$a0,$v0
 	li	$a1,2
 	la	$a2,str
 	addiu 	$sp,$sp,-16 	# reserva espaço na stack
 	sw 	$ra,0($sp) 	# guarda registos $sx e $ra
 	sw	$s0,4($sp)
 	sw	$s1,8($sp)
 	sw	$s2,12($sp)
 	jal	itoa
 	lw	$ra,0($sp)
	lw	$s0,4($sp)
	lw	$s1,8($sp)
	lw	$s2,12($sp)
	addiu	$sp,$sp,16
	
 	move	$v0,$a0
 	li	$v0,4
 	syscall
 	bne 	$s1,0,do1 # } while(val != 0)
 	li 	$v0,0 	# return 0;
 	jr $ra # termina programa

# Mapa de registos
# n: $a0 -> $s0
# b: $a1 -> $s1
# s: $a2 -> $s2
# p: $s3
# digit: $t0
	# char *itoa(unsigned int n, unsigned int b, char *s)
	itoa: 	
 		move 	$s0,$a0 # copia n, b e s para registos
		move	$s1,$a1 # "callee-saved"
 		move 	$s2,$a2 #
 		move	$s3,$s2	# p = s;
	do: 	rem	$t0,$s0,$s1		# do {
 		div	$s0,$s0,$s1
 		move	$a0,$t0
 		
 		addiu	$sp,$sp,-4
 		sw	$ra,0($sp)
 		jal	toascii
 		lw	$ra,0($sp)
 		addiu	$sp,$sp,4
 		
 		move	$t2,$v0		# toascii(digit)
 		sw	$t2,0($s3)	# *p++ = toascii( digit );
 		addi	$s3,$s3,1	#
 		
 		bgt 	$s0,0,do # } while(n > 0);
 		sb 	$0,0($s3) # *p = 0;
 		move	$a0,$s2
		addiu	$sp,$sp,-16
		sw	$ra,0($sp)
		sw	$s0,4($sp)
		sw	$s1,8($sp)
		sw	$s2,12($sp)
		jal	strrev
		lw	$ra,0($sp)
		lw	$s0,4($sp)
		lw	$s1,8($sp)
		lw	$s2,12($sp)
		addiu	$sp,$sp,16
 		jr 	$ra #