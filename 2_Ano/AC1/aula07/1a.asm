.data
# O argumento da fun��o � passado em $a0
# O resultado � devolvido em $v0
# Sub-rotina terminal: n�o devem ser usados registos $sx
strlen: 
	li 	$t1,0 			# len = 0;
while: 	lb 	$t0,0($a0) 		# while(*s++ != '\0')
 	addiu 	$a0,$a0,1 		#
 	beq 	$t0,'\0',endw 		# {
 	addi 	$t1,$t1,1 		# len++;
 	j 	while 			# }
endw: 	move 	$v0,$t1 		# return len;
 	jr 	$ra 			# 