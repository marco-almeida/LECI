.data
lista:	.space	20
str1:	.asciiz "\nIntroduza um numero: "
.text
.globl main
# i: $t0
# lista: $t1
# lista + i: $t2 
main:
	li	$t0,0		# i
	la	$t1,lista
	
for:	bge	$t0,5,endfor
	li	$v0,5
	syscall			# read_int
	
	sll	$t2,$t0,2	# multiplicar indice por 4
	addu	$t2,$t1,$t2
	sw	$v0,0($t2)
	
	
	addi	$t0,$t0,1
	j 	for
endfor:
	jr	$ra
	
	
	
	
		# houve_troca: $t4
	# i: $t5
	# lista: $t6
	# lista + i: $t7
	la	$t2
do:	
	li	$t4,0				# houveTroca = 0;
	li	$t5,0				# i = 0;
for:	bge	$t5,36,endfor
	sll	$t2,$t5,2
	
	
	
	addi	$t5,$t5,1			# i++;
	j 	for
endfor:
	
	bne	$t4,1,enddo			# } while (houveTroca==TRUE);
	j 	do
enddo:
	
