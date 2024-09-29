.data 

msg: .asciiz "Enter a string: "
str: .space 17


.text
main:


 li $v0,4
 la $a0,msg
 syscall
   
 la $a0, msg 
 li $v0, 8
 la $a0,str
 li $a1, 17
 syscall
  


  la $s0, str	
  addi $s3, $0, '\n'	# $s2 = '\n'

loop:
	lb $s1, 0($s0)
	beq $s1, $s3, end	# Break if byte is '\n'
	addi $s2, $s2, 1	# αυξηση του counter
	addi $s0, $s0, 1	# αυξηση του str address
	j loop
end:
      
  sb	$0, 0($s0)	#replace newline with 0
  li $v0, 1	
  add $a0, $s2, $0	
  syscall		


li $v0, 10
syscall
