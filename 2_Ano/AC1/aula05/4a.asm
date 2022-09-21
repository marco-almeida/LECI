# Mapa de registos
# ...
# houve_troca: $t4
# p: $t5
# pUltimo: $t6
.data
lista:	.space	40
str1:	.asciiz "\nIntroduzir inteiro: "
str2:	.asciiz ";"
.text
.globl main

main:
	# codigo para leitura de valores
	la	$t5,lista			# p = $t0
	addiu	$t6,$t5,40
	
while:	beq	$t5,$t6,endw			# while (i < size)
	
	li	$v0,4
	la	$a0,str1
	syscall					# print_string("\nIntroduzir inteiro: ")
	
	li	$v0,5
	syscall					# read_int()
	
	sw	$v0,0($t5)
	addi	$t5,$t5,4
	
	j 	while
endw:
 	la 	$t5,lista			# t5 = &lista[0]
 	li 	$t6,10 				#
 	sll 	$t6,$t6,2 			# $t7 = (SIZE – 1) * 4
 	addu 	$t6,$t6,$t5			# $t7 = lista + (SIZE – 1)
do: 						# do {
	subu 	$t6,$t6,4 			# $t7 = SIZE – 1
	li 	$t4,0 				# houve_troca = FALSE;
	la	$t5,lista			#   $t5 = lista
for:	bge	$t5,$t6,endfor
	lw	$t7,0($t5)			#     $t0 = *p
	lw	$t8,4($t5)			#     $t1 = *(p+1)
	
if:	ble	$t7,$t8,endif
	sw	$t8,0($t5)
	sw	$t7,4($t5)
	li	$t4,1
endif:
	addi	$t5,$t5,4
	j	for	
endfor:
	bne	$t4,1,endww	# } while( houve_troca == TRUE );
	j	do
endww:

	# print lista
	
	la	$t5,lista
	addiu	$t1,$t5,40
	
printa:	bge	$t5,$t1,acaba
	lw	$a0,0($t5)
	li	$v0,1
	syscall
	
	li	$v0,4
	la	$a0,str2
	syscall
	
	addi	$t5,$t5,4
	j printa
acaba:
	jr	$ra






