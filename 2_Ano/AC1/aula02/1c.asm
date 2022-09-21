	.data
 	.text
 	.globl main
main:	ori $t0,$0,0x0F1E 	# substituir val_1 e val_2 pelos 0001.0010.0011.0100
 	nor $t1,$t0,$t1		# 
 	jr $ra 			# fim do programa