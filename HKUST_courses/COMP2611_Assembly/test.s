.data
h: .word 1 2 3 4 #h is an array of size 4
s: .word 5

# the 3 lines below let the system know the program begins here
.text
.globl __start
__start:

la $s0, h	#obtain starting adress of array h, s0 = x (a constant)
lw $s2, 8($s0)	# $s2 = third content of array h[2]

la $s1, s
lw $s3, -12($s1)
sub $s3, $s3, $s1	# value of $s3?
sw $s3, 0($s0)		# how is value of array h changed
