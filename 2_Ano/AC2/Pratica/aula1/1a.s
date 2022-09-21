.data
.text
.globl main
main:
    do:
        li $v0,2                    #
        syscall                     # c = getChar(); 
        move $a0,$v0                #

        li $v0,3                    #
        addi    $a0,$a0,1
        syscall                     # putChar( c + 1);
        addiu   $a0,$a0,-1
        
        bne $a0,'\n',do             # } while( c != '\n' );
    li $v0,0                        # return 0;
jr $ra
