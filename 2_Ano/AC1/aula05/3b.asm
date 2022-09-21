.data
array:	.space	40
str1:	.asciiz "\nIntroduzir inteiro: "
str2:	.asciiz ";"
.text
.globl main
main: 
	# Mapa de registos
	# array: $t0
	# array+size: $t1
	# houve_troca: $t4
	# i: $t5
	# lista: $t6
	# lista + i: $t7
	# c�digo para leitura de valores
	la	$t0,array			# p = $t0
	addiu	$t1,$t0,40
	
while:	beq	$t0,$t1,endw			# while (i < size)
	
	li	$v0,4
	la	$a0,str1
	syscall					# print_string("\nIntroduzir inteiro: ")
	
	li	$v0,5
	syscall					# read_int()
	
	sw	$v0,0($t0)
	addi	$t0,$t0,4
	
	j 	while
endw:	
	la 	$t6,array 				#
do: 						# do {
 	li 	$t4,0 				# houve_troca = FALSE;
 	li 	$t5,0 				# i = 0;
for: 	bge 	$t5,9,endfor 			# while(i < SIZE-1){
if: 	sll 	$t7,$t5,2 				# $t7 = i * 4
 	addu 	$t7,$t7,$t6 			# $t7 = &lista[i]
 	lw 	$t8,0($t7) 				# $t8 = lista[i]
 	lw 	$t9,4($t7) 				# $t9 = lista[i+1]
 	bleu 	$t8,$t9,endif 			# if(lista[i] > lista[i+1]){
 	sw 	$t8,4($t7) 				# lista[i+1] = $t8
 	sw 	$t9,0($t7) 				# lista[i] = $t9
 	li 	$t4,1 						# }
endif:
	addi	$t5,$t5,1	#   i++;
	j	for
endfor:
	bne	$t4,1,endww	# } while( houve_troca == TRUE );
	j	do
endww:
	# print array
	
	la	$t0,array
	addiu	$t1,$t0,40
	
printa:	bge	$t0,$t1,acaba
	lw	$a0,0($t0)
	li	$v0,1
	syscall
	
	li	$v0,4
	la	$a0,str2
	syscall
	
	addi	$t0,$t0,4
	j printa
acaba:
	jr	$ra
	