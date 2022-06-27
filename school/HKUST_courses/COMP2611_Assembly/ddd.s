.data
newLine: .asciiz "\n"

.text
.globl __start
__start:

add $s0, $zero, $zero
doLoop:
addi $s0, $s0, 2
slti $t0, $s0, 10
bne $t0, $zero, doLoop

li $v0, 1
addi $a0, $s0, 0
syscall
li $v0, 4
la $a0, newLine
syscall
li $v0, 1
addi $a0, $t0, 0
syscall
prrintSkip: