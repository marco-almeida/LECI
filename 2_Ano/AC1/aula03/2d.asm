# Mapa de registos:
# $t0 - value
# $t1 - bit
# $t2 - i
# $t3 - flag
# $t4 - space
        .data
int:    .asciiz "Introduza um numero: "
val:    .asciiz "\nO valor em bin�rio �:  "
        .eqv print_string,4
        .eqv read_int,5
        .eqv print_char,11

        .text
        .globl main
main:   li 	$v0,print_string
        la 	$a0,int
        syscall				# print_string("Introduza um numero: ");

        li 	$v0,read_int
        syscall
        or 	$t0,$0,$v0		# value = read_int();

        li 	$v0,print_string
        la 	$a0,val
        syscall                 	# print_string("\nO valor em bin�rio �:  ");

        li 	$t2,0               	 # i = 0
        li 	$t4,1                	# space = 0

for:    bge 	$t2,32,endFor       	# while (i < 32)
        srl 	$t1,$t0,31          	# bit = value >> 31;
        add 	$t5,$t1,0x30        	#
        bne 	$t5,'1',else         	# if (bit + 0x30 == '1'){
        li 	$t3,1               	# flag = 1;
else:
        bne 	$t3,1,elseee        	# if (flag == 1){
        li 	$v0,print_char
        la 	$a0,($t5)
        syscall                 	# printf("%c", 0x30 + bit);
 
        rem 	$t5,$t2,4           	# space % 4
        bne 	$t5,0,elsee         	# if (space % 4 == 0){

        li 	$v0,print_char
        la 	$a0,' '
        syscall                 	# print_char(' ');
elsee:
	addi 	$t4,$t4,1		# space++;
elseee:

        addi    $t2,$t2,1           	# i++
        sll     $t0,$t0,1           	# value = value << 1;      // shift left de 1 bit
        j for

endFor: 			    	# }
        jr $ra                  	# }


