.data
stg:	.asciiz	"tone bicla"
.text
.globl main
main:
	addiu	$sp,$sp,-4
	sw	$ra,0($sp)
	la	$a0,stg
	jal	strlen
	lw	$ra,0($sp)
	addiu	$sp,$sp,4
	move	$a0,$v0
	li	$v0,1
	syscall
	jr	$ra
	
strlen:
	move	$t0,$a0
	li	$v0,0
while:	lb	$t1,0($t0)
	beq	$t1,'\0',endw
	addi	$v0,$v0,1
	addi	$t0,$t0,1
	j	while
endw:
	jr	$ra
	