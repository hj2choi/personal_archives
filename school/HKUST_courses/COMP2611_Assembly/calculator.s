.data
op: .word 1	#correspond to s0
opr: .word 2	#correspond to s1
ans: .word 3	#correspond to s2
msgAskInput: .asciiz "Hello, welcome to random exercise. Enter first number :\n"
msgTest1: .asciiz "yay you entered a number. You just entered "
msgAskInput2: .asciiz "\nEnter second number!!\n"
msgAns: .asciiz "\nThings works so far!!! The answer to the operation is  "
msgChoice: .asciiz "\nChoose your operation type. [1] = Addition, [2] = Subtraction, [3] = Multiplication :\n"

.text
.globl __start
__start:

# print msgAskInput
la $a0, msgAskInput
li $v0, 4
syscall


#get input value to v0
li $v0, 5
syscall

#store into s0 register and memory
#add $s0, $v0, $zero
la $s0, op
sw $v0,0($s0)

#whatever took me 1 munite to make now takes me an hour


#####################
# TAKE INPUT, with s0, s1
#####################
# print message
li $v0, 4
la $a0, msgTest1
syscall


#print out number that is just entered
li $v0, 1
lw $a0, 0($s0)
syscall

#print out: ask for second input
li $v0, 4
la $a0, msgAskInput2
syscall

#get input to v0, and store it into opr
li $v0, 5
syscall

la $s1, opr
sw $v0, 0($s1)


#print out the number you just entered : print message and then print integer
li $v0, 4
la $a0, msgTest1
syscall

li $v0, 1
lw $a0, 0($s1)
syscall



#Ask for the operation type
li $v0, 4
la $a0, msgChoice
syscall

li $v0, 5
syscall

# switch statement: branch to either addition, subtraction or multiplication
addi $t0, $zero, 1
beq $v0, $t0, Addition
addi $t0, $zero, 3
beq $v0, $t0, Multiplication
addi $t0, $zero, 2
beq $v0, $t0, Subtraction
j Terminate

#####################
# PERFORM OPERATION HERE, with s0, s1 and s2
#####################



# perform addition
Addition:
lw $t0, 0($s0)
lw $t1, 0($s1)
add $t2, $t0, $t1


#perform subtraction
Subtraction:
lw $t0, 0($s0)
lw $t1, 0($s1)

sub $t2, $t0, $t1


# perform multiplication. Add t0 to t2 t1 times.
Multiplication:
lw $t0, 0($s0)
lw $t1, 0($s1)
add $t2, $zero, $zero
sub $t3, $t3, $t3	#initialize t3 as a loop counter

MultiplicationLoop:
slt $t4, $t3, $t1	# t3 less than t1?
bne $t4, 1, ExitMultiplicationLoop
addi $t3, $t3, 1	# add 1 to t3, counter.
add $t2, $t2, $t0	# add t0 to t2
j MultiplicationLoop

ExitMultiplicationLoop:

la $s2, ans
sw $t2, 0($s2)

#####################
# PRINT OUT THE RESULT
#####################
#print out the result
li $v0, 4
la $a0, msgAns
syscall

li $v0, 1
lw $a0, 0($s2)
syscall

Terminate:
#terminate the program
li $v0, 10
syscall

