# Mapa de registos
#  $t0 - value
#  $t1 - bit
#  $t2 - i
#  $t3 - temp vars
#  $t4 - flag

	.data
		str1: .asciiz "Introduza um numero: "
		str2: .asciiz "\nO valor em binario 'e: "
		.eqv	print_string,4
		.eqv	read_int,5
		.eqv	print_char,11
		.text
		.globl main

main:
	la	$a0,str1
	li	$v0,print_string
	syscall				# print_string("Introduza um numero: ");
	
	li	$v0,read_int
	syscall
	or	$t0,$0,$v0		# value = read_int();
	
	la	$a0,str2
	li	$v0,print_string
	syscall				# print_string("\nO valor em binario 'e: ");
	
	li	$t2,0			# i = 0
	li	$t4,0			# flag = 0
for:
	bgeu	$t2,32,endfor	 	# if ( i >= 32) break;
	
	srl	$t1,$t0,31		# bit = value >> 31;
	
	beq	$t4,1,forcont		# if (flag == 1)
	beq	$t1,1,forcont		# if (bit == 1)
	j forfinal
forcont:
	li	$t4,1

	rem	$t3,$t2,4		# temp = i % 4
	bne	$t3,$0,endremif		# if ( temp == 0 ) 
	li	$a0,' '
	li	$v0,print_char
	syscall				#   print_char(' ');
	
endremif:
	addi	$t1,$t1,0x30
	or	$a0,$t1,$0
	li	$v0,print_char
	syscall				# print_char(bit + 0x30); (0x30 = '0') (offset)

forfinal:
	sll	$t0,$t0,1		# value = value << 1; (next bit)
	addi	$t2,$t2,1		# i = i + 1;
	j 	for
endfor:
	jr	$ra