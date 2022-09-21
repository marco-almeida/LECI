	# AULA 11 - Ex. 1
	
	.eqv print_float,2
	.eqv print_double,3
	.eqv print_string,4
	.eqv read_int,5
	.eqv read_float, 6
	.eqv read_double, 7
	.eqv print_chr, 11
	.eqv read_char, 12
	.eqv print_ui10, 36

	.eqv of_id, 0
	.eqv of_fn, 4
	.eqv of_ln, 22
	.eqv of_gr, 40
	#########################################################################
	.data
				# static student stg = {72343, "Napoleao", "Bonaparte", 5.1}; 
stg:	.word 72343		# unsigned int id_number; 
	.asciiz "Napoleao"	# char first_name[18]; # ocupa 8 + 1(NULL) bytes portanto
	.space 9		# 9 para 18 9
	.asciiz "Bonaparte"	# char last_name[15]; 
	.space 5		# 10 - 5
	.align 2		# align 2 aka alinhar em mutliplos de 2^2
	.float 5.1		# float grade; 

str1: 	.asciiz "\nN. Mec: "
str2: 	.asciiz "\nNome: "
str3: 	.asciiz "\nNota: "
	.text
	.globl main
main: 	la $a0,str1
	li $v0,print_string
	syscall					# print_string("\nN. Mec: ");
	
	la $t0, stg				# t0 fica com o valor do inicio de stg
	lw $a0,of_id($t0)			# 
	li $v0,print_ui10			# print_intu10(stg.id_number);
	syscall 
 
 	la $a0,str2
 	li $v0,print_string
 	syscall					# print_string("\nNome: ");
 	
 	la $a0, stg
 	addiu $a0,$a0,of_ln
 	li $v0,print_string
 	syscall					# print_string(stg.last_name);
 
 	li $a0,','
 	li $v0,print_chr
 	syscall					# print_char(',');

   	la $a0, stg
 	addiu $a0,$a0,of_fn
 	li $v0,print_string
 	syscall					# print_string(stg.first_name);

   	la $a0,str3
 	li $v0,print_string
 	syscall					# print_string("\nNota: ");
   
 	la $t0,stg
 	l.s $f12,of_gr($t0) 
	li $v0,print_float			# print_float(stg.grade);
	syscall  
  
   	li $v0,0				# return 0;
   	jr $ra 
   	