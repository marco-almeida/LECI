.data
str1:	.asciiz	"Arquitetura de "
str2:	.space	50
str3:	.asciiz	"\n"
str4:	.asciiz	"Computadores I"
.text
.globl main
	main:
	la	$a0,str2
	la	$a1,str1
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	strcpy						# strcpy(str2, str1);
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	
	la	$a0,str2
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	print_string					# print_string(str2);
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	
	la	$a0,str3
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	print_string					# print_string("\n");
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	
	la	$a0,str2
	la	$a1,str4
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	strcat						# strcat(str2,....);
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	
	la	$a0,($v0)
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	print_string					# print_string( strcat(str2, "Computadores I") );
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	
	jr	$ra
	
strcat:
	# $a0 = string destino ( concatenar no fim desta string)
	# $a1 = string a concatenar
 	move	$t0,$a0
 	move	$t5,$a0
 	move	$t1,$a1
 	
while:	lb	$t2,0($t0)
	beq	$t2,'\0',endw
	addi	$t0,$t0,1
	j	while
endw:
	move	$a0,$t0
	move	$a1,$t1
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	jal	strcpy						# strcpy(p, src);
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	move	$v0,$t5
	jr	$ra

strcpy:
	# $a0 = destination of copied string
	# $a1 = source string pointer
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
	li	$v0,4
	syscall
	jr	$ra
	