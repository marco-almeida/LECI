.data
str:	.asciiz "cobbler"
.text
.globl main
main:
	la	$a0,str
	
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	
	jal	getNumbers
	
	lw	$ra,0($sp)
	addi	$sp,$sp,4
	
	jr	$ra

getNumbers:
	li	$s1,0
while:	lb	$t0,0($a0)
	beq	$t0,'\0',endw
	addi	$a0,$a0,1
	addi	$s1,$s1,1
	j	while
endw:
	jr	$ra