.data
data: .word 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
dataLength: .word 0
textWelcome: .asciiz "\nHello, Enter series of numbers until you are satisfied. Enter 999 when you are done\n"
msgInput: .asciiz "st number : "
msgNewLine: .asciiz "\n"
textPrintData: .asciiz "\n your dataset:\n"
textMenu: .asciiz "\n\n\n\n\n	[MENU]\n	[1] print dataset\n	[2] add data\n	[3] delete from top\n	[4] sort data (ascending order)\n	[5] view statistics  (DO NOT USE THIS WITH EMPTY DATASET. I WAS TOO LAZY TO IMPLEMENT PROTECTION)\n	[6] Reset all to 0\n	[9] exit\nEnter your choice : "
textDataLength: .asciiz "size = "
textOpeningBracket: .asciiz ",   { "
textClosingBracket: .asciiz " } "
textComma: .asciiz ", "
textAnyKeyToContinue: .asciiz "\nEnter any key to continue..."
textAddDataWelcome: .asciiz "Enter series of data. If you want to exit, enter 999\n"
textDeleteFromTopWelcome: .asciiz "\nEnter number of data you want to delete from top. DO NOT DELETE MORE THAN CURRENT SIZE\n"
textDeleteFromTopError: .asciiz "\nInvalid input! Try again\n"
textSortWelcome: .asciiz "\nSort compeleted!\n"
textStatisticsWelcome: .asciiz "\n\n\n	[STATISTICS]\n"
textMean: .asciiz "Mean value in integer = "
textMax: .asciiz "\nMax = "

.text
.globl __start
__start:

#####################
# Setup, Welcome screen and Collect data
#####################
la $s4, data		#load adress of data.
la $s5, dataLength	#load adress of dataLength. to s5
la $s6, Main		#load adress of instruction Main
#$s6 stores jump register




li $v0, 4
la $a0, textWelcome	# welcome text
syscall

#Enter input Loop. Until user is done entering, it keeps on asking for input.
InputLoop:

li $v0, 1		# print out current dataLength.
lw $a0, 0($s5)		
addi $a0, $a0, 1	# make the dataLength value pretty.
syscall

li $v0, 4
la $a0, msgInput	# st nuber
syscall

li $v0, 5		# take input
syscall

beq $v0, 999, ExitInputLoop

lw $s0, 0($s5)		# load dataLength
sll $s1, $s0, 2		# multiply dataLength by 4 and store it to s1
add $t0, $s1, $s4	# make temp variable to store array index
sw $v0, 0($t0)		# add input value to array
addi $s0, $s0, 1	# add 1 to dataLength
sw $s0, 0($s5)		# store it back to dataLength

j InputLoop	#Keep accepting input

ExitInputLoop:


#j PrintProcedure


##############################################
# Main menu. Where you select your choices and jump to procedures.
#############################################
Main:
li $v0, 4
la $a0, textMenu
syscall

li $v0, 5
syscall

beq $v0, 1, PrintProcedure
beq $v0, 2, DataAddProcedure
beq $v0, 3, DeleteFromTopProcedure
beq $v0, 4, SortProcedure
beq $v0, 5, StatisticsCalculateProcedure
beq $v0, 6, ClearAllProcedure
beq $v0, 9, ExitProgram
j Main

###################################
# print out all the data
###################################

PrintProcedure:
li $v0, 4
la $a0, textPrintData
syscall

li $v0, 4
la $a0, textDataLength
syscall
li $v0, 1
lw $a0, 0($s5)
syscall
li $v0, 4
la $a0, textOpeningBracket
syscall

sub $t0, $t0, $t0	# initiate counter, until it becomes equal to dataLength 
lw $s0, 0($s5)		# load dataLength
#lw $t2, 0($s5)		# load base array adress of data

dataPrintLoop:
sll $t1, $t0, 2	#for t0, sift left logical t0 by 2
add $t1, $s4, $t1	# add t1 and s5

slt $t2, $t0, $s0	# t2 = t0<s0
bne $t2, 1, ExitDataPrintLoop	# until counter and dataLength is equal,
#print array[i]
li $v0, 1
lw $a0, 0($t1)	# load array data with index
syscall	#print

addi $t0, $t0, 1	# add 1 to t0

beq $t0, $s0, ExitDataPrintLoop	# DO NOT PRINT LAST COMMA
#print comma
li $v0, 4
la $a0, textComma
syscall

j dataPrintLoop
ExitDataPrintLoop:

li $v0, 4
la $a0, textClosingBracket
syscall
li $v0, 4
la $a0, textAnyKeyToContinue
syscall
li $v0, 5
syscall
ExitPrintProcedure:
jr $s6	# go back to main menu after the procedure


##################################################
# Add more data to the dataset
##################################################

DataAddProcedure:
li $v0, 4
la $a0, textAddDataWelcome
syscall


DataAddLoop:
lw $s0, 0($s5)	# let s0 to be dataLength
sll $t1, $s0, 2	# t1 is processed dataLength value, as to be used as index
add $t1, $s4, $t1	# add base adress of data by t1.

# print out enter messages
li $v0, 1
addi $a0, $s0, 1
syscall
li $v0, 4
la $a0, msgInput
syscall

# take input
li $v0, 5	
syscall

# if input value is equal to 999, exit loop
beq $v0, 999, DataAddLoopExit

sw $v0, 0($t1)	# put the value into the array

addi $s0, $s0, 1	# add 1 to dataLength register
sw $s0, 0($s5)	# and put it into the dataLength memory

j DataAddLoop
DataAddLoopExit:

la $s6, DataAddProcedureExit
j PrintProcedure
DataAddProcedureExit:
la $s6, Main	# go back to main
jr $s6

#####################################################
# DELETE FROM TOP
#####################################################

DeleteFromTopProcedure:

li $v0, 4
la $a0, textDataLength
syscall
li $v0, 1
lw $a0, 0($s5)
syscall
li $v0, 4
la $a0, textDeleteFromTopWelcome
syscall

#get input and check if the input value is smaller than size
li $v0, 5
syscall

lw $s0, 0($s5)	# now s0 is dataLength
sub $t0, $s0, $v0
bltz $t0, DeleteFromTopInvalidInputError
j DeleteFromTopProceed

# if the input is invalid
DeleteFromTopInvalidInputError:
li $v0, 4
la $a0, textDeleteFromTopError
syscall
j DeleteFromTopProcedure

# if the input is valid, proceed
DeleteFromTopProceed:
sw $t0, 0($s5)		# store register back to datasetLength memory

la $s6, DeleteFromTopProcedureExit
j PrintProcedure
DeleteFromTopProcedureExit:
la $s6, Main
jr $s6
#####################################################
# SORT TIHE DATA, BUBBLE SORT
#####################################################

SortProcedure:

lw $s2, 0($s5)		# s2 = dataLength


sub $s0, $s0, $s0		# s0 = i, initialize
SortLoopOuter:
slt $t0, $s0, $s2
beq $t0, $zero, SortLoopOuterExit	# if !(i < dataLength) Exit Outer

sub $s1, $s1, $s1		# s1 = j, initialize
SortLoopInner:
slt $t0, $s1, $s2
beq $t0, $zero, SortLoopInnerExit	# if !(j < dataLength) Exit Inner
#SWAP data[i] and data[j]

# load data[i] and data[j]
sll $t0, $s0, 2			
add $t0, $t0, $s4		# t0 is adress location of data[i]
lw $s3, 0($t0)			# s3 = data[i]

sll $t1, $s1, 2			
add $t1, $t1, $s4		# t1 is adress location of data[j]
lw $s7, 0($t1)			# s7 = data[j]

# if statement.. if !(data[i]<data[j]) Exit swap
slt $t2, $s3, $s7
beq $t2, $zero, SwapExit
# swap data[i] with data[j]
sw $s3, 0($t1)
sw $s7, 0($t0)

SwapExit:


addi $s1, $s1, 1
j SortLoopInner
SortLoopInnerExit:

addi $s0, $s0, 1
j SortLoopOuter
SortLoopOuterExit:


li $v0, 4
la $a0, textSortWelcome
syscall

la $s6, SortProcedureExit
j PrintProcedure
SortProcedureExit:
la $s6, Main
jr $s6







#####################################################
# CALCULATE STATISTICS.
#####################################################

StatisticsCalculateProcedure:

li $v0, 4
la $a0, textStatisticsWelcome
syscall
li $v0, 4
la $a0, textMean
syscall

###################### Calculate Mean: calculate sum and divide by dataLength #################
#register initialization
lw $s0, 0($s5)		# dataLength
sub $s1, $s1, $s1	# sum
sub $t0, $t0, $t0	# counter
sub $s2, $s2, $s2	# resulting value of sum/dataLength

#enter loop to calculate DataSum
StatisticsMeanLoop:
sll $t1, $t0, 2
add $t1, $t1, $s4	# adress location of array index t0
lw $t2, 0($t1)		# t2 = data[t0]
add $s1, $s1, $t2	# s1 +=t2

addi $t0, $t0, 1
beq $t0, $s0, StatisticsMeanLoopExit	# check condition. Loops until t0 and dataLength are equal

j StatisticsMeanLoop
StatisticsMeanLoopExit:

# print out s1, sum of all data
#li $v0, 1
#add $a0, $s1, $zero
#syscall

#Implement division.
StatisticsDivisionLoop:
sub $s1, $s1, $s0
bltz $s1, StatisticsDivisionLoopExit
addi $s2, $s2, 1
j StatisticsDivisionLoop
StatisticsDivisionLoopExit:

# print out s2, division result, the mean
li $v0, 1
add $a0, $s2, $zero
syscall

###################### Calculate Max #################
add $s0, $zero, $zero	# current Max = 0
add $s1, $zero, $zero	# counter = 0
lw $s2, 0($s5)		# s2 = arrayLength
# s4 = base adress of array
MaxLoop:
# while (counter < arrayLength)
slt $t0, $s1, $s2	
beq $t0, $zero, MaxLoopExit

# if (array[counter]>currentMax) curMax=array[counter];
sll $t0, $s1, 2
add $t0, $t0, $s4
lw $t0, 0($t0)		# t0 = array[counter]
sgt $t1, $t0, $s0	# t0>currentMax
beq $t1, $zero, SkipIf		# if(t1)
add $s0, $zero, $t0
SkipIf:

# ++counter
addi $s1, $s1, 1
j MaxLoop
MaxLoopExit:

li $v0, 4
la $a0, textMax
syscall
li $v0, 1
add $a0, $zero, $s0
syscall

la $s6, StatisticsCalculateProcedureExit
j PrintProcedure
StatisticsCalculateProcedureExit:
la $s6, Main
jr $s6


################################################
# CLEAR ALL DATA (Reset to zero)
################################################
ClearAllProcedure:
j Main

######################## EXIT THE PROGRAMMMM ####################
ExitProgram:
li $v0, 10
syscall
