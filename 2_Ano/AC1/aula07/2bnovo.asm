.data
str:	.asciiz	"ITED - orievA ed edadisrevinU"
.text
.globl main
	main:
# Mapa de registos:
# str: $a0 -> $s0 (argumento é passado em $a0)
# p1: $s1 (registo callee-saved)
# p2: $s2 (registo callee-saved)
#
	la	$a0,str
	addiu 	$sp,$sp,-16 # reserva espaço na stack
	sw 	$ra,0($sp) # guarda endereço de retorno
	sw 	$s0,4($sp) # guarda valor dos registos
	sw 	$s1,8($sp) # $s0, $s1 e $s2
	sw 	$s2,12($sp) #
	jal	strrev
	move	$a0,$v0
	li	$v0,4
	syscall
	lw 	$ra,0($sp) # repõe endereço de retorno
	lw 	$s0,4($sp) # repõe o valor dos registos
	lw 	$s1,8($sp) # $s0, $s1 e $s2
	lw 	$s2,12($sp) #
	addiu 	$sp,$sp,16 # liberta espaço da stack
	jr	$ra
	#############################################################################################################
strrev:
	move 	$s0,$a0 # registo "callee-saved"
	move 	$s1,$a0 # p1 = str
	move 	$s2,$a0 # p2 = str
while1: 	# while( *p2 != '\0' ) {
	lb	$t2,0($s2)
	beq	$t2,'\0',endw1
	addi	$s2,$s2,1	# p2++;
	j 	while1 # }
endw1:
	addiu	$s2,$s2,-1 		# p2--; aqui a 1ª da s1 vai para a ultima da s2
while2: 	# while(p1 < p2) {
	bge	$s1,$s2,endw2
	move 	$a2,$s1 #
	move 	$a3,$s2 #
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal 	exchange # exchange(p1,p2)
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	addi	$s1,$s1,1
	addiu	$s2,$s2,-1
	j 	while2
endw2:
	move 	$v0,$s0 # return str
	jr 	$ra # termina a sub-rotina

exchange:			# void exchange(char *c1, char *c2) $a2,$a3
	lb	$t0,0($a2)		# $t0 = *c1
	lb	$t1,0($a3)		# $t1 = *c2
	sb	$t1,0($a2)		# *c1 = *c2
	sb	$t0,0($a3)		# *c2 = *c1
	jr	$ra
	
