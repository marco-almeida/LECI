	# AULA 11 - Ex. 1bc
	
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
stg:	.space 4		# unsigned int id_number; 
first_name: .space 18		# char first_name[18]; # ocupa 8 + 1(NULL) bytes portanto
last_name: .space 15		# char last_name[15]; 
	.align 2		# align 2 aka alinhar em mutliplos de 2^2
grade:	.space 4		# float grade; 

str1: 	.asciiz "\nN. Mec: "
str2: 	.asciiz "\nPrimeiro Nome: "
str3: 	.asciiz "\nNota: "
str4:	.asciiz "\nÚltimo Nome: "
	.text
	.globl main
main: 	la $a0,str1
	li $v0,print_string
	syscall					# print_string("\nN. Mec: ");
	
	li $v0,read_int
	syscall					# read_int();
	la $t0, stg				# t0 fica com o valor do inicio de stg
	sw $v0,0($t0)				# stg.id_number = read_int();
	
 	la $a0,str2
 	li $v0,print_string
 	syscall					# print_string("Primeiro Nome: ");
 	
 	li $v0,8
 	la $a0,first_name
 	li $a1,17
 	syscall					# read_string(stg.first_name, 17);
 	
 	la $a0,str4
 	li $v0,print_string
 	syscall					# print_string("Último Nome: ");
 	
 	li $v0,8
 	la $a0,last_name
 	li $a1,14
 	syscall					# read_string(stg.last_name, 14);
 	
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
 	
 	li $v0,6
 	syscall					# read_float()
 	
 	la $t0,grade
 	s.s $f0,0($t0)
 	
   	la $a0,str3
 	li $v0,print_string
 	syscall					# print_string("\nNota: ");
   
 	la $t0,stg
 	l.s $f12,of_gr($t0) 
	li $v0,print_float			# print_float(stg.grade);
	syscall  
  
   	li $v0,0				# return 0;
   	jr $ra 
   	