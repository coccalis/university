.data
str: .space 17

.text
.globl main
main:

li  $v0, 8   #scan a string
la  $a0, str
la  $a1, 17
syscall

add $t0, $zero, $zero   #counter

loop:

        lb  $a0, str($t0)   #read each character

        beq $a0, '\0', exit  #make sure the string is not yet over

        addi   $t0, $t0, 1     #i++

j loop


exit:

la $a0, '\n'
li $v0, 11     #change line
syscall


reverseloop:
       
        subiu   $t0, $t0, 1     #i--
        
        li  $v0, 11     #print a char  
        lb  $a0, str($t0)   
        syscall

        beq $a0, '\0', reverseexit   
        

j reverseloop
reverseexit:

la $a0, '\n'
li $v0, 11     #change line
syscall



li  $v0, 10
syscall
