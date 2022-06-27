#=================#
# THE DALEKS GAME #
#=================#

#---------- DATA SEGMENT ----------
	.data
dalek:	.word 0:399   
# assuming game object size 40x50
width:  .word 510    # 510 game board width
height: .word 505    # 450 game board height plus some space (55) for game state info
drwho:	.word 230 200	#Putting Drwho at the center of the board
msg0:	.asciiz "Enter the number of Daleks you want? "
msg1:	.asciiz "Invalid size!\n"
msg2:	.asciiz "Enter the seed for random number generator? "
msg3:	.asciiz "Dr. Who was hit!"
msg4:	.asciiz "Score: "
msg5:	.asciiz "Screwdriver: "
msg6:	.asciiz "Level: "
border1:	.asciiz "-----------------------------------------\n"
border2:	.asciiz "|                                       |\n"
space: .asciiz " "
newline: .asciiz "\n"

title: .asciiz "THE DALEKS GAME"
dalek_symbol:	.asciiz "dalek.png"
drwho_symbol1:	.asciiz	"drwho1.png"
drwho_symbol2:	.asciiz	"drwho2.png"
rubber_symbol:	.asciiz	"rubble.png"
backgroundImg: .asciiz "background.gif"
images: .word 0:4  # array for file name of all game images

msgTest: .asciiz "Test"
openingBracket: .asciiz "( "
closingBracket: .asciiz " )"
comma: .asciiz ", "
tab: .asciiz "\t"
txtInvalidPosition: .asciiz "Invalid character position!"
msgSonicScrewdriverTrue: .asciiz "|=========SYSTEM ALERT===========|\nFUTURISTIC SONIC SCREW DRIVER ACTIVATED\nAll pathetic daleks in proximity will be sent to oblivion\n==================="
msgSonicScrewdriverFalse: .asciiz "SONIC SCREW DRIVER is out of ammunition!!!"

#---------- TEXT SEGMENT ----------
	.text
	.globl __start
__start:


main:
#-------(Start main)------------------------------------------------

	jal setting				# the game setting

	# create image file name array
	la $t0, images
	la $t1, drwho_symbol1
	sw $t1, 0($t0)

	la $t1, drwho_symbol2
	sw $t1, 4($t0)

	la $t1, dalek_symbol
	sw $t1, 8($t0)

	la $t1, rubber_symbol
	sw $t1, 12($t0)

	jal createGame

	ori $s4, $zero, 1			# level = 1
	or $s7, $zero, $zero			# died = 0 (false)
	or $s2, $zero, $zero			# score = 0
	
	jal createGameObjects
	jal setGameStateOutput
	jal initgame				# initalize the game
	jal updateGameObjects
	
	
	
	li $v0, 100   # create and show the game screen
	li $a0, 4
	syscall

	j main1a

main1:
	jal redrawScreen   # redraw the updated game screen

main1a:
	
	li $v0, 32   # pause some milliseconds
	li $a0, 100
	syscall	

	beq $s7, $zero, main3			# if (!died) goto main3
	jal setGameoverOutput			# Drwho was hit by a dalek. GAME OVER!
	jal redrawScreen   # redraw the updated game screen
	j end_main
	
main3:
	jal drwho_moves				# read the user's input and Drwho moves
	jal daleks_move				# all daleks move
	jal update_state			# update the internal game states
	jal updateGameObjects

	jal is_lv_up
	beq $v0, $zero, main1			# if (!is_lv_up) goto main1
	addi $s4, $s4, 1			# increment level
	sll $s0, $s0, 1				# dalek_num = dalek_num * 2
	
	ori $t0, $zero, 99
	slt $t4, $t0, $s0
	beq $t4, $zero, main4
	ori $s0, $zero, 99
	
main4:	
	jal createGameObjects
	jal setGameStateOutput
	jal initgame
	jal updateGameObjects
	j main1

#-------(End main)--------------------------------------------------
end_main:

# Terminate the program
#----------------------------------------------------------------------
#li $v0, 100
#li $a0, 6
#syscall
ori $v0, $zero, 10
syscall

# Function: Setting up the game
setting:
#===================================================================


setting1:
	ori $t0, $zero, 100			# Max number of daleks
	
	la $a0, msg0				# Enter the number of Daleks you want?
	ori $v0, $zero, 4
	syscall
	
	ori $v0, $zero, 5			# cin >> dalek_num
	syscall
	or $s0, $v0, $zero

	slt $t4, $t0, $s0
	bne $t4, $zero, setting3
	slti $t4, $s0, 1
	bne $t4, $zero, setting3
	j setting2

setting3:
	la $a0, msg1
	ori $v0, $zero, 4			# Invalid size
	syscall
	j setting1

setting2:
	la $a0, newline
	ori $v0, $zero, 4
	syscall

	la $a0, msg2				# Enter the seed for random number generator?
	ori $v0, $zero, 4
	syscall
	
	ori $v0, $zero, 5			# cin >> seed
	syscall
	or $s1, $v0, $zero

	ori $v0, $zero, 40    #set seed
	li $a0, 1
	or $a1, $s1, $zero
	syscall


	jr $ra

#---------------------------------------------------------------------------------------------------------------------
# Function: initalize to a new level
# Generate random positions for Drwho and the daleks
# Set the limit for screwdrivers

initgame: 			
#===================================================================


	addi $sp, $sp, -12
	sw $s5, 8($sp)
	sw $s6, 4($sp)
	sw $ra, 0($sp)

	ori $s3, $zero, 1
	
	la $s5, dalek
	sll $s6, $s0, 4
	add $s6, $s5, $s6
initgame_outer_loop:
	ori $a0, $zero, 48
	jal randnum
	
	sw $v0, 0($s5)
	
	ori $a0, $zero, 41
	jal randnum
	
	sw $v0, 4($s5)
	
	la $t2, drwho
	lw $t0, 0($t2)
	lw $t1, 0($s5)
	sub $t1, $t1, $t0
	sltiu $t0, $t1, 1
	sub $t3, $zero, $t0
	
	lw $t0, 4($t2)
	lw $t1, 4($s5)
	sub $t1, $t1, $t0
	sltiu $t0, $t1, 1
	sub $t3, $t3, $t0
	ori $t0, $zero, 2
	beq $t3, $t0, initgame_outer_loop
	
	sw $zero, 8($s5)
	la $t7, dalek
	addi $t7, $t7, -16
	
initgame_inner_loop:
	addi $t7, $t7, 16
	beq $t7, $s5, initgame_inner_loop_continue
	lw $t0, 0($t7)
	lw $t1, 0($s5)
	bne $t0, $t1, initgame_inner_loop
	lw $t0, 4($t7)
	lw $t1, 4($s5)
	bne $t0, $t1, initgame_inner_loop
	addi $s5, $s5, -16
	
initgame_inner_loop_continue:
	addi $s5, $s5, 16
	bne $s5, $s6, initgame_outer_loop

	
	lw $s5, 8($sp)
	lw $s6, 4($sp)
	lw $ra, 0($sp)
	addi $sp, $sp, 12
	jr $ra

#---------------------------------------------------------------------------------------------------------------------
# Function: update game objects

updateGameObjects:				
#===================================================================


	addi $sp, $sp, -12
	sw $s5, 8($sp)
	sw $s6, 4($sp)
	sw $ra, 0($sp)


	la $t0, drwho
	lw $a2, 0($t0)
	lw $a3, 4($t0)

	li $v0, 100	# drwho's location
	li $a0, 12
	li $a1, 6				
	syscall

	li $v0, 100	# drwho's image index
	li $a0, 11
	li $a1, 6
	#addi $a2, $zero, 0	
	addi $a2, $v1, 0	#movement of drwho	
	syscall

	
	
	la $s5, dalek
	sll $s6, $s0, 4
	add $s6, $s6, $s5
	li $t9, 7
print2:
	lw $a2, 0($s5)
	lw $a3, 4($s5)
	li $v0, 100	# dalek's location
	li $a0, 12
	addi $a1, $t9, 0				
	syscall
	
	
	lw $t1, 8($s5)
	ori $t0, $zero,1
	
	################## THIS LINE HAS BEEN CHANGED FROM DEFAULT ####################
	bne $t1, $zero, print3	# NOTE ::: CHANGED FROM beq $t1, $t0, print3.
	
	ori $t0, $zero,2
	
	################## PRINT 5 DOES NOT IMPOSE ANY EFFECT ######################
	beq $t1, $t0, print5
	
	#li $v0, 4
	#la $a0, msgTest
	#syscall
	
	li $v0, 100	# dalek's image index
	li $a0, 11
	addi $a1, $t9, 0
	li $a2, 2	
	syscall

	j print4
	
print3:
	
	li $v0, 100	# dalek's image index
	li $a0, 11
	addi $a1, $t9, 0
	li $a2, 3	
	syscall
	j print4

print5:
	li $v0, 100	# dalek's image index
	li $a0, 11
	addi $a1, $t9, 0
	li $a2, -1	
	syscall

print4:	
	addi $t9, $t9, 1
	addi $s5, $s5, 16
	bne $s5, $s6, print2
	
	li $v0, 100	# Score number
	li $a0, 14
	li $a1, 1
	addi $a2, $s2, 0	
	syscall

	li $v0, 100	# Scewdriver number
	li $a0, 14
	li $a1, 3
	addi $a2, $s3, 0	
	syscall

	li $v0, 100	# level number
	li $a0, 14
	li $a1, 5
	addi $a2, $s4, 0	
	syscall

	
finish:
	lw $s5, 8($sp)
	lw $s6, 4($sp)
	lw $ra, 0($sp)
	addi $sp, $sp, 12
	
	jr $ra
	
#----------------------------------------------------------------------------------------------------------------------
# Function: set the game state's output objects

setGameStateOutput:				
#===================================================================


	li $t0, 485
	
	li $v0, 100	# Score string
	li $a0, 13
	li $a1, 0
	la $a2, msg4				
	syscall
	
	li $v0, 100	# Score string's location
	li $a0, 12
	li $a1, 0
	li $a2, 30
	move $a3, $t0				
	syscall

	li $v0, 100	# Score number
	li $a0, 14
	li $a1, 1
	addi $a2, $s2, 0	
	syscall
	
	li $v0, 100	# Score number's location
	li $a0, 12
	li $a1, 1
	li $a2, 80
	move $a3, $t0				
	syscall


	li $v0, 100	# Screwdriver string
	li $a0, 13
	li $a1, 2
	la $a2, msg5				
	syscall
	
	li $v0, 100	# Screwdriver string's location
	li $a0, 12
	li $a1, 2
	li $a2, 210
	move $a3, $t0				
	syscall

	li $v0, 100	# Scewdriver number
	li $a0, 14
	li $a1, 3
	addi $a2, $s3, 0	
	syscall
	
	li $v0, 100	# Scewdriver number's location
	li $a0, 12
	li $a1, 3
	li $a2, 300
	move $a3, $t0				
	syscall


	li $v0, 100	# level string
	li $a0, 13
	li $a1, 4
	la $a2, msg6				
	syscall
	
	li $v0, 100	# level string's location
	li $a0, 12
	li $a1, 4
	li $a2, 410
	move $a3, $t0				
	syscall

	li $v0, 100	# level number
	li $a0, 14
	li $a1, 5
	addi $a2, $s4, 0	
	syscall
	
	li $v0, 100	# level number's location
	li $a0, 12
	li $a1, 5
	li $a2, 460
	move $a3, $t0				
	syscall

	jr $ra
	
	
	
	
	

############################
# Please add your code here#
############################

#--------------------------------------------------------------------------------------------------------------------
# Function: print vector. void printVector(a0, a1);
printVector2D:
#===================================================================
	or $t0, $a0, $zero
	or $t1, $a1, $zero
	
	li $v0, 4
	la $a0, openingBracket
	syscall

	li $v0, 1
	or $a0, $t0, $zero
	syscall
	
	li $v0, 4
	la $a0, comma
	syscall
	
	li $v0, 1
	or $a0, $t1, $zero
	syscall
	
	li $v0, 4
	la $a0, closingBracket
	syscall

	li $v0, 4
	la $a0, newline
	syscall

	jr $ra


#----------------------------------------------------------------------------------------------------------------------

# function : $a0 boundaryCheck(int posX = a0, int posY = a1)
#
#	a0 and a1 are input parameters=> xPos and yPos
#	returns a0 as true or false value.
#	you are notified by log if boundarycheck returns false.

boundaryCheck:
#===================================================================
	#unwrapping input parameters into temporary variables.
	la $t2, width
	lw $t0, 0($t2)		# t0 = width
	lw $t1, 4($t2)		# t1 = height
	addi $t1, $t1, -55	# height = height - (game state info)
	or $t2, $a0, $zero	# t2 = posX
	or $t3, $a1, $zero	# t3 = posY
	
	
	#check for boundaries, and return $a0 with boolean value.
	#
	# NOTE : This function DOES NOT check for exact boundary position. It is adjusted for drwho's pixel sizes.
	
	addi $t0, $t0, -40
	sgt $a0, $t2, $t0	# check if xPos <= sizeX-40
	seq $a0, $a0, 0		# invert (xPos>sizeX)
	beq $a0, $zero, exitBoundaryCheckFalse		# return if a0 == false
	
	slt $a0, $t2, $zero	# check if xPos >= 0
	seq $a0, $a0, 0
	beq $a0, $zero, exitBoundaryCheckFalse
	
	addi $t1, $t1, -50
	sgt $a0, $t3, $t1	# check if yPos <= sizeY-50
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


#----------------------------------------------------------------------------------------------------------------------
# Function: (a0,a1) computeDeltaPos (char input=>a0)
# 
# input parameter = a0,
# return value
# a0 = delta xPos
# a1 = delta yPos
# Description : computes delta position based on keystroke. The function returns (0,0) if input is non_integer value or invalid.

computeDeltaPos:
#===================================================================
	#unwrap input parameter and initialization
	or $t0, $a0, $zero	#t0 = input value = a0
	or $a0, $zero, $zero	#deltaX = 0
	or $a1, $zero, $zero	#deltaY = 1
	
	# map input values into switch statements. If input value is none of them, deltaPos = (0,0);
	beq $t0, 56, deltaNorth
	beq $t0, 50, deltaSouth
	beq $t0, 52, deltaWest
	beq $t0, 54, deltaEast
	beq $t0, 55, deltaNorthWest
	beq $t0, 49, deltaSouthWest
	beq $t0, 57, deltaNorthEast
	beq $t0, 51, deltaSouthEast
	j exitComputeDeltaPos
	
	
	# switch statements.
	deltaNorth:
		addi $a1, $a1, -10
		j exitComputeDeltaPos
	deltaSouth:
		addi $a1, $a1, 10
		j exitComputeDeltaPos
	deltaWest:
		addi $a0, $a0, -10
		j exitComputeDeltaPos
	deltaEast:
		addi $a0 $a0, 10
		j exitComputeDeltaPos
	deltaNorthWest:
		addi $a0, $a0, -10
		addi $a1, $a1, -10
		j exitComputeDeltaPos
	deltaSouthWest:
		addi $a0, $a0, -10
		addi $a1, $a1, 10
		j exitComputeDeltaPos
	deltaNorthEast:
		addi $a0, $a0, 10
		addi $a1, $a1, -10
		j exitComputeDeltaPos
	deltaSouthEast:
		addi $a0, $a0, 10
		addi $a1, $a1, 10
		j exitComputeDeltaPos

exitComputeDeltaPos:
	jr $ra


#----------------------------------------------------------------------------------------------------------------------

# function : $a0 sonicScrewdriver(int posX = a0, int posY = a1)
#
#	a0 and a1 are input parameters=> xPos and yPos
#	returns a0 as status value.
#	you are notified by log if sonicScrewdriver returns false.

sonicScrewdriver:
#===================================================================
	# push function register to stack
	addi $sp, $sp, -4
	sw $ra, 0($sp)
		
	#jal printVector2D
	
	# unwrap input parameters. And initialize return value
	or $t0, $a0, $zero	# t0 = posX
	or $t1, $a1, $zero	# t1 = posY
	ori $a0, $zero, 1	# a0 initialized to true
	
	# if (sonicScrewDriverCount <= 0) return false;
	sgt $t2, $s3, $zero
	beq $t2, $zero, exitSonicScrewDriverFalse

	# execute FUTURISTIC SONIC SCREW DRIVER WEAPON and SEND DALEKS TO OBLIVION

	la $a3, dalek	# a3 = base adress of dalek
	
	or $t9, $zero, $zero	# t9 = counter for outer loop
	SONICSCREWDRIVERLOOP:
		slt $t2, $t9, $s0
		beq $t2, $zero, EXITSONICSCREWDRIVERLOOP
	
		sll $t2, $t9, 4
		add $a2, $a3, $t2	# delak[i]
		
		# retrieve delak[i].posX
		lw $t2, 0($a2)
		# retrieve delak[i].posY
		lw $t3, 4($a2)
		
		# Validate x position range.
		sub $a0, $t2, $t0	# delak[i].xPos - xPos
		sub $a1, $t3, $t1	# delak[i].yPos - yPos
		
		sgt $t4, $a0, -61
		beq $t4, $zero, CONTINUESONICSCREWCDRIVERLOOP
		
		addi $t8, $zero, 61
		slt $t4, $a0, $t8
		beq $t4, $zero, CONTINUESONICSCREWCDRIVERLOOP
		
		sgt $t4, $a1, -61
		beq $t4, $zero, CONTINUESONICSCREWCDRIVERLOOP
		
		slt $t4, $a1, $t8
		beq $t4, $zero, CONTINUESONICSCREWCDRIVERLOOP
		
		
		# if the position satisfies all conditions, send the unit to oblivion.
		addi $t4, $zero, -100
		addi $t8, $zero, -100
		sw $t4, 0($a2)
		sw $t8, 4($a2)
		ori $t4, $zero, 1
		sw $t4, 8($a2)
		
		CONTINUESONICSCREWCDRIVERLOOP:
		addi $t9, $t9, 1
		j SONICSCREWDRIVERLOOP
	EXITSONICSCREWDRIVERLOOP:








exitSonicScrewDriverTrue:
	#pull function register from stack
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	
	li $v0, 4
	la $a0, msgSonicScrewdriverTrue
	syscall
	li $v0, 4
	la $a0, newline
	syscall
	
	ori $a0, $zero, 1

	addi $s3, $s3, -1	#screwDriverCount--;
	jr $ra

exitSonicScrewDriverFalse:
	#pull function register from stack
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	
	li $v0, 4
	la $a0, msgSonicScrewdriverFalse
	syscall
	li $v0, 4
	la $a0, newline
	syscall


	or $a0, $zero, $zero	# a0 = false
	jr $ra
	
	
	
# function : void teleport()
#
#	teleport to random position.
#	no input & return values required.
#	

teleport:
#===================================================================
	# push function register to stack
	addi $sp, $sp, -4
	sw $ra, 0($sp)

	ori $a0, $zero, 48
	jal randnum
	add $t2, $v0, $zero	# t2 = randnum 1
	
	ori $a0, $zero, 41
	jal randnum
	add $t3, $v0, $zero	# t3 = randnum 2 
	
	# assign new position
	la $t0, drwho
	sw $t2, 0($t0)
	sw $t3, 4($t0)


exitTeleport:
	#pull function register from stack
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra
	
	
	
#----------------------------------------------------------------------------------------------------------------------
# Function: set the gameover output objects

setGameoverOutput:				
#===================================================================
	#set objectId
	addi $t0, $s0, 7


	#set the string
	li $v0, 100
	li $a0, 13
	add $a1, $t0,$zero
	la $a2, msg3
	syscall
	
	#set the object location
	li $a0, 12
	li $a2, 100
	li $a3, 250
	syscall

	#set the string font
	li $a0, 16
	li $a2, 32
	li $a3, 1
	li $t0, 1
	syscall
	
	#set the string color
	li $a0, 15
	li $a2, 0xff0000
	syscall
	
	jr $ra
#----------------------------------------------------------------------------------------------------------------------
# Function: 1 read the user's input and 2 handle Drwho's movement

#note:  function drwho_moves returns a value (stored in $v1, make sure $v1 is not changed by other functions) for function dalek_moves
#	when $v1 == 1, legal keystroke is received, daleks can move one step towards drwho;
#	when $$v1 == 0, no (or illegal) keystroke is received, daleks should keep their positions and wait for the next keystroke
drwho_moves:
#===================================================================

############################
# Please add your code here#
############################
	#push to procedure stack..
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	
	#initialize for return value.
	or $v1, $zero, $zero	# v1, bool validMove = 0; initialized for false.
	
	#read user input as keystroke.
	jal getInput
	add $t5, $v0, $zero	# t5 = inputChar();
	
	# retrieve current drwho pos
	la $t0, drwho
	lw $t6, 0($t0)		# t6 = drwho xPos
	lw $t7, 4($t0)		# t7 = drwho yPos
	
	#resolve special moves
	beq $t5, 114, USESONICSCREWDRIVER
	beq $t5, 116, useTeleport
	beq $t5, 53, move_valid			# special move type. Stays still
	j exitSpecialMoveHandler
	
	USESONICSCREWDRIVER:
		or $a0, $t6, $zero
		or $a1, $t7, $zero
		jal sonicScrewdriver
		sne $v1, $a0, $zero		# v1 = sonicSrewDriver();
		
		j move_invalid
	
	useTeleport:
		ori $v1, $zero, 1		# v1 = true
		jal teleport
		j move_invalid
		
	exitSpecialMoveHandler:
		
	
	# call computeDeltaPos(char input) and retrieve delta position value.
	or $a0, $t5, $zero
	jal computeDeltaPos
	or $t0, $a0, $a1		# check if a0 and a1 are equal and zero. =>t0
	beq $t0, $zero, move_invalid	# jump to move_invalid if player does not move.
	add $t6, $t6, $a0		# increment xPos by delta value
	add $t7, $t7, $a1		# increment yPos by delta value
	
	# bundary check of drwho
	or $a0, $t6, $zero
	or $a1, $t7, $zero
	jal boundaryCheck
	beq $a0, $zero, move_invalid
	
	# Store the temporary position value to the memory, if drwho position is valid.
	move_valid:
		la $t0, drwho
		sw $t6, 0($t0)
		sw $t7, 4($t0)
		ori $v1, $zero, 1
		
	move_invalid:
		
	
	
	#pop from procedure stack
	lw $ra, 0($sp)
	addi $sp, $sp, 4
#----------------------------------------------------------------------------------------------------------------------
# Function: move the daleks

daleks_move:
#===================================================================
	
	
	addi $sp, $sp, -8
	sw $s5, 4($sp)
	sw $s6, 0($sp)

	la $t0, drwho
	lw $t2, 0($t0)
	lw $t3, 4($t0)

	la $s5, dalek
	sll $s6, $s0, 4
	add $s6, $s5, $s6
	
	#if no (illegal) keystroke is received, daleks should keep still
	beq $v1, 0, daleks_move_end

	
daleks_move_loop:
	#push to procedure stack..
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	
	#pop from procedure stack
	lw $ra, 0($sp)
	addi $sp, $sp, 4

	lw $t0, 8($s5)
	bne $t0, $zero, daleks_move_continue

#update x coordinate
	lw $t4, 0($s5)
	slt $t0, $t2, $t4
	beq $t2, $t4, dalek_x_upd
	beq $t0, 0, dalek_x_add_10
	beq $t0, 1, dalek_x_min_10
	
dalek_x_add_10:
	addi, $t4, $t4, 10
	j dalek_x_upd
dalek_x_min_10:
	addi, $t4, $t4, -10
	j dalek_x_upd
dalek_x_upd:	
	sw $t4, 0($s5)

#update y coordinate	
	lw $t5, 4($s5)

	slt $t0, $t3, $t5
	beq $t3, $t5, dalek_y_upd
	beq $t0, 0, dalek_y_add_10
	beq $t0, 1, dalek_y_min_10
	
dalek_y_add_10:
	addi, $t5, $t5, 10
	j dalek_y_upd
dalek_y_min_10:
	addi, $t5, $t5, -10
	j dalek_y_upd
dalek_y_upd:
	sw $t5, 4($s5)

daleks_move_continue:
	addi $s5, $s5, 16
	bne $s5, $s6, daleks_move_loop
	
	lw $s5, 4($sp)
	lw $s6, 0($sp)
	addi $sp, $sp, 8

	
daleks_move_end:	
	jr $ra

#----------------------------------------------------------------------------------------------------------------------
# Function: checks if dr who is still alive or not. Returns $v0
check_drwho_state:
#===================================================================
	#push to procedure stack..
	addi $sp, $sp, -4
	sw $ra, 0($sp)

	ori $v0, $zero, 1	#drwho is alive
	
	or $a3, $zero, $zero	# a3=counter starting from 0
	la $a1, dalek		# a1=base adress of dalek
	la $a2, drwho		# a2=base adress of drwho
	
	
	check_drwho_loop:
		slt $t0, $a3, $s0		# if (a0<s0) break;
		beq $t0, $zero, exit_check_drwho_loop
		
		sll $t0, $a3, 4		# t0 = displacement
		add $t0, $t0, $a1	# t0 = t0+baseAdress
		lw $t1, 0($t0)		# access array element xPos
		lw $t2, 4($t0)		# access array element yPos
		lw $t3, 0($a2)		# access drwho xPos
		lw $t4, 4($a2)		# access drwho yPos
		
		seq $t0, $t1, $t3	# (xPos == delak[i].xPos)
		seq $t1, $t2, $t4	# (yPos == delak[i].yPos)
		and $t0, $t0, $t1
		beq $t0, $zero, drwhoAlive	# dr who is dead
		
		or $v0, $zero, $zero		# set v0 when drwho is dead
		
		j exitCheckDrwhoState
		drwhoAlive:
		
		addi $a3, $a3, 1		# a0++;
		j check_drwho_loop
	exit_check_drwho_loop:

exitCheckDrwhoState:
	#pull function register from stack
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra

#----------------------------------------------------------------------------------------------------------------------
# Function: checks if dr who is still alive or not. Returns $v0
newRubber:
#===================================================================
	#push to stack
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	
	#initialization and setup
	
	la $a3, dalek	# a3 = base adress of dalek
	
	

	or $t8, $zero, $zero	# t8 = counter for outer loop
	rubberLoop_outer:
		#if !(t8<s0) break;
		slt $t0, $t8, $s0
		beq $t0, $zero, exitRubberLoop_outer
		
		addi $t9, $t8, 1	# t9 = t8+1, counter for inner loop
		rubberLoop_inner:
			#if !(t9<s0) break;
			slt $t0, $t9, $s0
			beq $t0, $zero, exitRubberLoop_inner
			
			# retrieve t1 = dalek[t8].posX
			sll $t0, $t8, 4
			add $t0, $t0, $a3
			lw $t1, 0($t0)
			
			# retrieve t2 = dalek[t8].posY
			lw $t2, 4($t0)
			
			# retrieve t3 = dalek[t9].posX
			sll $t0, $t9, 4
			add $t0, $t0, $a3
			lw $t3, 0($t0)
			
			# retrieve t4 = dalek[t9].posY
			lw $t4, 4($t0)
			
			# if (t1 == t3 && t2 == t4) {dalek[t8].isRubble=true; dalek[t9].isRubble=true; setImage; continue;}
			seq $t0, $t1, $t3	# (t1==t3)
			seq $t1, $t2, $t4	# (t2==t4)
			and $t0, $t0, $t1
			beq $t0, $zero, continueRubberLoop_inner	# COLLISION
			
			rubberOperator:
			# dalek[t8].isRubble=true;
			ori $t1, $t1, 1	# t1=1
			sll $t0, $t8, 4
			add $t0, $t0, $a3
			
			lw $t2, 8($t0)
			bne $t2, $zero, rubberOperand
			
			addi $s2, $s2, 10	# score +=10;
			sw $t1, 8($t0)		# isRubble=true
			
			rubberOperand:
			# if (!dalek[t9].isRubble) 
			sll $t0, $t9, 4		# dalek[t9]
			add $t0, $t0, $a3
			lw $t2, 8($t0)
			bne $t2, $zero, continueRubberLoop_inner	#if (isRubble) continue;
			
			# else {score+=10; dalek[t9].isRubble=true;}
			addi $s2, $s2, 10	# score += 10;
			sw $t1, 8($t0)		# isRubble = true
			
			continueRubberLoop_inner:
			#increment
			addi $t9, $t9, 1
			j rubberLoop_inner
		exitRubberLoop_inner:
		
		
		#increment
		addi $t8, $t8, 1
		j rubberLoop_outer
	exitRubberLoop_outer:



exitNewRubber:
	#pop from stack
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra
#----------------------------------------------------------------------------------------------------------------------
# Function: updating the internal game states like score, also checking any new rubber and life state of Drwho

update_state:
#===================================================================

############################
# Please add your code here#
############################
	#push to procedure stack..
	addi $sp, $sp, -4
	sw $ra, 0($sp)

	jal check_drwho_state
	seq $s7, $v0, $zero	# s7(died)=!v0
	
	jal newRubber
	
	
exit_update_state:
	#pull function register from stack
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra
#----------------------------------------------------------------------------------------------------------------------
# Function: check if a new level is reached	
# return $v0: 0 -- false, 1 -- true

is_lv_up:
#===================================================================

############################
# Please add your code here#
############################
	#push to procedure stack..
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	
	#ori $t3, $zero, 1
	ori $v0, $zero, 1
	
	la $t0,dalek
	
	or $t9, $zero, $zero
	lvLoop:
		slt $t1, $t9, $s0
		beq $t1, $zero, exitLvLoop
	
		sll $t1, $t9, 4
		add $t1, $t1, $t0
		lw $t1, 8($t1)
		
		sne $v0, $t1, $zero
		beq $v0, $zero, exitLvLoop
	
	
	continueLvLoop:
		addi $t9, $t9, 1
		j lvLoop
	
	exitLvLoop:


exit_is_lv_up:
	#pull function register from stack
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	
		
	jr $ra


#----------------------------------------------------------------------------------------------------------------------
# Function: get keystroke character from keyboard
# return $v0: ASCII value of keystroke character

getInput:
#===================================================================
	addi $v0, $zero, 0

	lui $t8, 0xffff
	lw $t7, 0($t8)
	andi $t7,$t7,1
	beq $t7, $zero, getInput1
	lw $v0, 4($t8)

getInput1:	
	jr $ra

#----------------------------------------------------------------------------------------------------------------------


# Function: generate a random number and return it times 10 in $v0
# $a0 = range
randnum:
#===================================================================
	li $v0, 42
	addi $a1, $a0, 0
	li $a0, 1 
	syscall
	li $t0, 10
	mult $t0, $a0 
	mflo $v0

	jr $ra
#----------------------------------------------------------------------------------------------------------------------


## Function: create game
createGame:
#===================================================================
	li $v0, 100	
	li $a0, 1

	la $t0, width
	lw $a1, ($t0) 
	la $t0, height
	lw $a2, ($t0)

	la $a3, title
	la $t0, backgroundImg
	syscall

	li $v0, 100
	li $a0, 3
	li $a1, 4
	la $a2, images
	syscall
 
	jr $ra
#----------------------------------------------------------------------------------------------------------------------
## Function: create game objects
createGameObjects:
#===================================================================

	li $v0, 100	
	li $a0, 2
	addi $a1, $s0, 8   # besides daleks, need 8 extra game objects for Drwho and text outputs
	syscall
 
	jr $ra
#----------------------------------------------------------------------------------------------------------------------

## Function: redraw game screen
redrawScreen:
#===================================================================
	li $v0, 100   # redraw the updated game screen
	li $a0, 5
	syscall

	jr $ra
#----------------------------------------------------------------------------------------------------------------------
## Function: pause execution for X milliseconds from the specified time T (some moment ago). If the current time is not less than (T + X), pause for only 1ms.    
# $a0 = specified time T (lower 32 bits of the time returned from a previous syscalll of code 30)
# $a1 = X amount of time to pause in milliseconds 
pauseExecution:
#===================================================================
	andi $a0, $a0, 0x3fffffff
	add $t0, $a0, $a1

	li $v0, 30
	syscall
	andi $a0, $a0, 0x3fffffff

	sub $a0, $a0, $t0

	bgt $a0, $zero, positiveTime
	li $a0, 1     # pause for at least 1ms

positiveTime:

	li $v0, 32	 
	syscall

	jr $ra
#----------------------------------------------------------------------------------------------------------------------
