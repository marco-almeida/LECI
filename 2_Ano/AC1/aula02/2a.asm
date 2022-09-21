	.data
 	.text
 	.globl main
main:	li  $t0,0x12345678 	# dar valor a t0
 	sll $t2,$t0,1 		# Shift left logical
 	srl $t3,$t0,1 		# Shift right logical
 	sra $t4,$t0,1 		# Shift right arithmetic		
 	jr $ra 			# fim do programa