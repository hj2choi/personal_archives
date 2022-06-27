#------- DATA SEGMENT ----------------
	.data

spaceSizeX: .word 0
spaceSizeY: .word 0
characterPosX: .word 0
CharacterPosY: .word 0


txtWelcome: .asciiz "Hello, welcome to 2D vector space.\n"
txtEnterBoundaryX: .asciiz "Enter your world size in pixels.\nEnter space size x: "
txtEnterBoundaryY: .asciiz "Enter space size y: "
txtEnterX: .asciiz "Now, enter your character's position.\nEnter character position x: "
txtEnterY: .asciiz "Enter character position y: "
txtEnterMove: .asciiz "enter your caracter movement in numpad!! (0,1,2,3,4,5,6,7,8,9,t,r)\n"
txtInvalidPosition: .asciiz "Invalid Character position!!! => "
txtWorldSize: .asciiz "World size = "
txtCharacterPos: .asciiz "Character pos = "

newLine: .asciiz "\n"
openingBracket: .asciiz "( "
comma: .asciiz ", "
closingBracket: .asciiz " )"



#------- TEXT SEGMENT ----------------
	.text
	.globl __start
__start:

##### Main function starts here.

mainLoop:
	# print out information and accepts five values.
	
	li $v0, 4
	la $a0, txtWelcome
	syscall
	
	vectorInput:
		li $v0, 4	# ask for space x
		la $a0, txtEnterBoundaryX
		syscall
		li $v0, 5	# get input for space x
		syscall
		la $t0, spaceSizeX
		sw $v0, 0($t0)
	
		li $v0, 4	# ask for space y
		la $a0, txtEnterBoundaryY
		syscall
		li $v0, 5	# get input for space y
		syscall
		sw $v0, 4($t0)
	
		li $v0, 4	# ask for character x
		la $a0, txtEnterX
		syscall
		li $v0, 5	# get input for character x
		syscall
		sw $v0, 8($t0)
	
		li $v0, 4	# ask for character y
		la $a0, txtEnterY
		syscall
		li $v0, 5	# get input for character y
		syscall
		sw $v0, 12($t0)
	
	# store four location values to be passed onto boundarycheck function.
	sub $s0, $s0, $s0	# loop initialization statement
	stackPushLoop:
		slti $t0, $s0, 4	# loop condition statement
		beq $t0, $zero, escapeStackPushLoop
		
		la $t1, spaceSizeX
		sll $t0, $s0, 2
		add $t1, $t0, $t1
		lw $t1, 0($t1)	# retrieve one of the four values
		
		#push to stack.
		addi $sp, $sp, -4
		sw $t1, 0($sp)	# store t1, the value into the stack, for arguments.
		
		#print out values for debug.
		#li $v0, 1
		#add $a0, $t1, $zero
		#syscall
		#li $v0, 4
		#la $a0, newLine
		#syscall
		
		addi $s0, $s0, 1	# loop increment statement
		j stackPushLoop
	escapeStackPushLoop:
	
	jal boundaryCheck
	beq $a0, $zero, vectorInput	# if input is invalid, jump back to the vectorInput.
	
	jal eachMove





	#terminate the program
	li $v0, 10
	syscall

###### void eachMove()
eachMove:
	# push jump register
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	
	# print world size.
	la $v0, 4
	la $a0, newLine
	syscall
	li $v0, 4
	la $a0, txtWorldSize
	syscall
	
	la $t0, spaceSizeX
	lw $a0, 0($t0)
	lw $a1, 4($t0)
	jal printVector2D

	# print character position
	li $v0, 4
	la $a0, txtCharacterPos
	syscall
	
	la $t0, characterPosX
	lw $a0, 0($t0)
	lw $a1, 4($t0)
	jal printVector2D
	
	# enter next character move
	li $v0, 4
	la $a0, txtEnterMove
	syscall
	
	li $v0, 5
	syscall
	#v0 is now next move order
	



exitEachMove:

	# pop jump register
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	
	# jump back to wherever it came from
	jr $ra

###### void printVector2D(int a0, int a1)
printVector2D:
	add $t0, $a0, $zero
	add $t1, $a1, $zero

	li $v0, 4
	la $a0, openingBracket
	syscall
	
	li $v0, 1
	add $a0, $t0, $zero
	syscall
	
	li $v0, 4
	la $a0, comma
	syscall
	
	li $v0, 1
	add $a0, $t1, $zero
	syscall
	
	li $v0, 4
	la $a0, closingBracket
	syscall
	
	li $v0, 4
	la $a0, newLine
	syscall





exitPrintVector2D:
	jr $ra


###### $a0 boundaryCheck(int boundaryX, int boundaryY, int posX, int posY)
boundaryCheck:
#unwrapping input parameters into temporary variables.
lw $t3, 0($sp)
addi $sp, $sp, 4
lw $t2, 0($sp)
addi $sp, $sp, 4
lw $t1, 0($sp)
addi $sp, $sp, 4
lw $t0, 0($sp)
addi $sp, $sp, 4
	
	#print out input parameter value.
	#li $v0, 1
	#add $a0, $t0, $zero
	#syscall
	#li $v0, 4
	#la $a0, newLine
	#syscall
	
	#li $v0, 1
	#add $a0, $t1, $zero
	#syscall
	#li $v0, 4
	#la $a0, newLine
	#syscall
	
	#li $v0, 1
	#add $a0, $t2, $zero
	#syscall
	#li $v0, 4
	#la $a0, newLine
	#syscall
	
	#li $v0, 1
	#add $a0, $t3, $zero
	#syscall
	#li $v0, 4
	#la $a0, newLine
	#syscall
	
	#check for boundaries, and return $a0 with boolean value.
	sgt $a0, $t2, $t0	# check if xPos <= sizeX
	seq $a0, $a0, 0		# invert (xPos>sizeX)
	beq $a0, $zero, exitBoundaryCheckFalse		# return if a0 == false
	
	slt $a0, $t2, $zero	# check if xPos >= 0
	seq $a0, $a0, 0
	beq $a0, $zero, exitBoundaryCheckFalse
	
	sgt $a0, $t3, $t1	# check if yPos <= sizeY
	seq $a0, $a0, 0
	beq $a0, $zero, exitBoundaryCheckFalse
	
	slt $a0, $t3, $zero	# check if yPos >=0
	seq $a0, $a0, 0
	beq $a0, $zero, exitBoundaryCheckFalse

exitBoundaryCheckTrue:
	jr $ra

exitBoundaryCheckFalse:

	#push register value
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	
	# print(invalid position!! Character position:);
	li $v0, 4
	la $a0, txtInvalidPosition
	syscall
	
	# pass parameters and call printVector2D()
	add $a0, $t2, $zero
	add $a1, $t3, $zero
	jal printVector2D
	
	#pop register value
	lw $ra, 0($sp)
	addi $sp, $sp, 4


	add $a0, $zero, $zero
	jr $ra



###### $a0, $a1 moveCharacter(boundaryX, boundaryY, posX, posY, input)
moveCharacter:
	#push register value







exitMoveCharacter:
	#pop register value



# Main function (caller function) starts here
#	li $s0, 4          # saving "preserve" registers is not needed in the very first function (main)
#	li $s1, 3
#	li $t0, 10
#	add $t1, $s0, $t0

#	addi $sp, $sp, -4  # save "non-preserve" register (in caller function) whose value will still be needed after calling another function. t0 is not needed.
#	sw $t1, 0($sp)
     
#	jal callee              # call a function
#	addi $t2, $v0, 0   # f returned 25 in v0 and 50 in v1
#	add $t3, $v1, $zero      # now t2 = 25 and t3 = 50

#	lw $t1, 0($sp)     # restore saved register
#	addi $sp, $sp, 4
	
#	add $s2, $s1, $t1  # s2 = 17 (10 + 4 + 3)

# Terminate the program
#	li $v0, 10
#	syscall

### void callee( )
#callee:
#	addi $sp, $sp, -8  # save "preserve" registers (in callee function) that will be modified in this function
#	sw $s0, 0($sp)
#	sw $s1, 4($sp)

#	li $s0, 2
#	li $s1, 3
#	li $t0, 20
#	add $t1, $s0, $t0  
#	add $v0, $s1, $t1
#	add $v1, $v0, $v0

#	lw $s0, 0($sp)     # restore saved registers
#	lw $s1, 4($sp)
#	addi $sp, $sp, 8
#	jr $ra             # function returns

