# i: $t0
# array: $t1
# array[i]: $t2
.data
array:.word str1,str2,str3
str1: .asciiz "Array"
str2: .asciiz "de"
str3: .asciiz "ponteiros"
.text
.globl main
main:
	li	$t0,0			# i = 0;
	la 	$t1,array 		# $t1 = &array[0]
	
for: 	bge	$t0,3,endfor		# while(i < 3){
	sll 	$t2,$t0,2 		#	i * 4
	addu 	$t2,$t2,$t1 		# $t2 = &array[i]
	lw 	$a0,0($t2) 		# $a0 = array[i]
	
	li	$v0,4
	syscall
	addi	$t0,$t0,1		# i++;
	
	li	$v0,11
	la	$a0,'\n'
	syscall
	
	j	for
endfor:
	jr $ra 