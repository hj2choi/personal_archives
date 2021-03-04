#-----Data segment-------
	.data

value: .word 1


txtWelcome: .asciiz "Hello, welcome to recursion example.Enter any number.\n"
txtRetVal: .asciiz "RetVal = "
newLine: .asciiz "\n"
txtRecursiveCall: .asciiz "recursive call n="
txtRecursiveCallEnd: .asciiz "recursive call returned n="
#-----Text segment------
	.text
	.globl __start
__start:


MainLoop:
	li $v0, 4
	la $a0, txtWelcome
	
	syscall
	
	li $v0, 5
	syscall
	
	add $s1, $v0, $zero
	
	#push to stack
	addi $sp, $sp, -4
	sw $s1, 0($sp)
	
	jal Function
	
	# pop return values from stack
	lw $t1, 0($sp)
	addi $sp, $sp, 4
	
	li $v0, 4
	la $a0, txtRetVal
	syscall
	
	li $v0, 1
	add $a0, $t1, $zero
	syscall
	
	li $v0, 4
	la $a0, newLine
	syscall
	
j MainLoop





Function:
	# pop from stack, to retrieve argument
	lw $t1, 0($sp)
	addi $sp, $sp, 4

	#push j register to stack.
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	
	#push function argument to stack.
	addi $sp, $sp, -4
	sw $t1, 0($sp)

	j RecursiveFactorial
	# pop return value from stack
	lw $t1, 0($sp)
	addi $sp, $sp, 4

FunctionExit:
	#pop j register from stack
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	
	# push return values to stack
	addi $sp, $sp, -4
	sw $t1, 0($sp)
	jr $ra








#-----int factorial(int t1)
RecursiveFactorial:
	#pop the argument from stack.
	lw $t1, 0($sp)
	addi $sp, $sp, 4
	
	#push jump register to stack
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	
	base_case:
		slti $t2, $t1, 2
		beq $t2, $zero, non_base_case
		addi $t3, $zero, 1
		j RecursiveFactorialExit
	
	non_base_case:
		## push jump register to stack
		#addi $sp, $sp, -8
		#sw $ra, 4($sp)
		#push current t1(N)value to stack. Prevent overwriting issue
		addi $sp, $sp, -4
		sw $t1, 0($sp)
		# push argument to stack
		addi $t1, $t1, -1
		addi $sp, $sp, -4
		sw $t1, 0($sp)
		
		#debug printout
		li $v0, 4
		la $a0, txtRecursiveCall
		syscall
		li $v0, 1
		add $a0, $zero, $t1
		syscall
		li $v0, 4
		la $a0, newLine
		syscall
		li $v0, 1
		add $a0, $zero, $t3
		syscall
		li $v0, 4
		la $a0, newLine
		syscall
		
		#add $t2, $t1, $zero	# to prevent overwriting issue of registers
		
		jal RecursiveFactorial
		#pop current t1(N)value from stack. Prevent overwriting issue
		lw $t1, 0($sp)
		addi $sp, $sp, 4
		#debug printout
	li $v0, 4
	la $a0, txtRecursiveCallEnd
	syscall
	li $v0, 1
	add $a0, $zero, $t1
	syscall
	li $v0, 4
	la $a0, newLine
	syscall
	li $v0, 1
	add $a0, $zero, $t3
	syscall
	li $v0, 4
	la $a0, newLine
	syscall
		# pop retVal from stack
		lw $t3, 0($sp)
		addi $sp, $sp, 4
		
		# n*factorial(n-1)
		multu $t1,$t3
		mflo $t3
		# return the retVal

RecursiveFactorialExit:
	#pop jump register from stack
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	
	# push return value from stack
	addi $sp, $sp, -4
	sw $t3, 0($sp)
	
	
	
	
	jr $ra
	
	
