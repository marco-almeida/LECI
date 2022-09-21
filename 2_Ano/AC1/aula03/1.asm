# Mapa de registos:
# $t0 - soma
# $t1 - value
# $t2 - i
	.data
int: 	.asciiz "Introduza um numero: "
val: 	.asciiz "Valor ignorado\n"
sum: 	.asciiz "A soma dos positivos é: "
	.eqv print_string,4
	.eqv print_int10,1
	.eqv read_int,5
	.text						
	.globl main
	
main:	li $t0,0		# soma = 0
	li $t2,0		# i = 0
while:	bge $t2,5,endWhile	# while (i < 5) {

	li $v0,print_string
	la $a0,int
	syscall			# print_string("Introduza um numero: ");
	
	li $v0,read_int
	syscall
	or $t1,$0,$v0		# value = read_int();
	
	ble $t1,0,else		# if(value > 0) 
	add $t0,$t0,$t1		# soma = soma + value;
	j incremento
else:				# else
	li $v0,print_string
	la $a0,val
	syscall			# print_string("Valor ignorado\n");
incremento:
	add $t2,$t2,1		# i++
	j while
endWhile:			# }
	li $v0,print_string
	la $a0,sum
	syscall			# print_string("A soma dos positivos é: ");
	
	li $v0,print_int10
	la $a0,($t0)
	syscall			# print_int10(soma);
	
	jr $ra			# }
		
