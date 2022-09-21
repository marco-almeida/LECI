	.data
 	.text
 	.globl main
main:	ori $t0,$0,0xf0f0f0f	# valor inicial de bin
	srl $t3,$t0,1 		# armazenar valor de bin
	xor $t1,$t0,$t3		# obter gray
 	jr $ra 			# fim do programa