	.data
 	.text
 	.globl main
main:
 	or $a0,$0,$t5
 	ori $v0,$0,1
 	syscall			#a
 	ori $v0,$0,5
 	syscall
 	or $a0,$v0,$0
 	ori $v0,$0,1
 	syscall
 	jr $ra 			# fim do programa