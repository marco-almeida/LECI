.data
str1:	.asciiz "I serodatupmoC ed arutetiuqrA"
str2:	.space 31
str3:	.asciiz	"\n"
str4:	.asciiz "String too long: "
.text
.globl main
	main:
	la	$a0,str1
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	strlen
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	move	$t9,$v0					# strlen(str1) = $t9
	
	bgt	$t9,30,else				# if(strlen(str1) <= STR_MAX_SIZE) {
	
	la	$a0,str2
	la	$a1,str1
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	strcpy					# strcpy(str2, str1);
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	
	la	$a0,str2
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	print_string				# print_string(str2);
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	
	la	$a0,str3
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	print_string				# print_string("\n");
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	
	la	$a0,str2
	addiu	$sp,$sp,-16
	sw	$ra,0($sp)
	sw	$s0,4($sp)
	sw	$s1,8($sp)
	sw	$s2,12($sp)
	jal	strrev					# strrev(str2);
	lw	$ra,0($sp)
	lw	$s0,4($sp)
	lw	$s1,8($sp)
	lw	$s2,12($sp)
	addiu	$sp,$sp,16
	
	move	$a0,$v0
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	print_string				# print_string(strrev(str2)); 
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	
	li	$t8,0					# exit_value = 0;
	j	endif
else:
	la	$a0,str4
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	print_string				# print_string("String too long: ");
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	
	move	$a0,$t9
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	print_int10				# print_int10(strlen(str1)); 
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	
	li	$t8,-1
endif:
	jr	$ra
	
	#############################################################################################################
	
strlen:
	move	$t0,$a0
	li	$v0,0
while:	lb	$t1,0($t0)
	beq	$t1,'\0',endw
	addi	$v0,$v0,1
	addi	$t0,$t0,1
	j	while
endw:
	jr	$ra
	
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
	
strcpy:
	# $a1 = source string pointer
	# $a0 = destination of copied string
	move	$t1,$a1				# $t1 = &string;
	move	$v0,$a0				# return v0
	li	$t0,0				# i = 0;
do:
	addu	$t2,$t1,$t0			# index of string
	lb	$t3,0($t2)			# get char of index
	sb	$t3,0($a0)			# store char
	beq	$t3,'\0',endww			# till end
	addiu	$t0,$t0,1			# i++;
	addi	$a0,$a0,1
	j	do
endww:
	jr	$ra
	
print_string:
	#a0 = string
	li	$v0,4
	syscall
	jr	$ra
	
print_int10:
	li	$v0,1
	syscall
	jr	$ra
	