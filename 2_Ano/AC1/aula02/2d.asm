	.data
	.text
	.globl main
main:	ori $t2,$0,0xf0f0f0f	# bin
	srl $s0,$t0,1 		# armazenar valor de bin
	xor $t0,$t0,$t3		# gray
	or $t1,$t0,$0		# t0 para gray t1 para num t2 para bin
	srl $s0,$t1,4		# (num >> 4)
	xor $t1,$t1,$s0		# num = num ^ (num >> 4)
	srl $s0,$t1,2		# (num >> 2)
	xor $t1,$t1,$s0		# num = num ^ (num >> 2)
	srl $s0,$t1,1		# (num >> 1)
	xor $t1,$t1,$s0		# num = num ^ (num >> 1)
	or $t2,$t1,$0		# bin = num
	jr $ra 			# fim do programa
