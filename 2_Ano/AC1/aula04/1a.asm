.data
	ptr_p: .space 20
	
	.text
	.globl main
main:
	# $t0 -> num
	# $t1 -> i
	# $t2 -> endereço da String
	# $t3 -> endereço posição i da String
	# $t4 -> conteúdo  de str[i]
	

	la $a0,ptr_p
	li $a1, 20
	ori $v0,$0,8
	syscall
	or $t2,$a0,$0

	or $t0,$0,$0
	or $t1,$0,$0

		
While:	
	add $t3,$t1,$t2
	lb $t4,0($t3)
	beq $t4,0x00,EndWhile
	
If:	
	blt $t4,0x30,Endif
	bgt $t4,0x39,Endif
	addi $t0,$t0,1
	
Endif:	
	addi $t1,$t1,1
	j While
	
EndWhile:
	or $a0,$0,$t0
	ori $v0,$0,1
	syscall
	
	jr $ra