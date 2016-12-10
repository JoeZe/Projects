#Name: Joe Zeng
#Last modified date: 12/8/2016
#My program does in C++ is to ask the user to input 10 integers and save it into arrayA[10], then print out every numbers in arrayA[10]. Finally compute the sum and print it.
#The code in C++ will be the following:
#	int arrayA[10];
#	int a=0, sum=0;   
#	while(a<10){
#		cout << "Enter an integer to store in element " << a+1 << " of array A: ";  //proompt user to enter an integer
#		cin >>  arrayA[a]; // store an integer into the arrayA
#		sum+=arrayA[a]; // computes the sum
#		a++; //increment counter
#	}
#	a=0; // reset the counter
#	while(a<10){
#		cout <<"Element " << a+1 << " of array A contains: " << arrayA[a] << endl; //print every integers in the array
#		a++; // increment counter
#	}
#	cout << "The sum of 10 elements of array A is: " << sum; //print the sum         
#List of registers used: $t0 is used to store the address of array of A
# $t1 is used to store the value of counter
# $s0 is used to store the sum
# $t6 is used to store the value to get from array of A
# $v0 is used to return the value that read from the user
# $a0 is used to pass arguments from other registers 
# $zero is used to add zero, so the register $t1 can be start from 0

	.data	#data declaration section
arrayA:	.space 40 	# declare 40 bytes of storage to hold the array of 10 integers
input:	.asciiz "Enter an integer to store in element "
input1:	.asciiz " of array A: "
newline:	.asciiz "\n"
sum:	.word 0
outputsum:	.asciiz "The sum of 10 elements of array A is: "
output:	.asciiz "Element "
output1:	.asciiz " of Array A contains: "

	.text
main:	
	la $t0, arrayA 	#load the address of arrayA into $t0
	addi $t1, $zero, 0 	#declare the counter to 0 and store it register $t1
	lw $s0, sum	#load the sum into $s0
	
while:
	beq $t1, 10,reset 	#branch to reset if $t1 equals 10. if not equal, then do the instruction below.
	
	#print the string of input
	li $v0, 4	#system call code for print string
	la $a0, input	#load address of input into $a0
	syscall 	#print the string
	
	addi $t1, $t1, 1 	#increment counter by 1 in the $t1 
	
	#print integer to count at which element
	li $v0, 1	#system call code for print integer
	move $a0,$t1 	#move value to be printed to $a0
	syscall	#print the integer
		
	#print the input1
	li $v0, 4	#system call code for print string
	la $a0, input1	#load address of input1 into $a0
	syscall	#print the string
	
	#read the integer input by the user
	li $v0, 5	#system call code for read integer
	syscall	#reads the value into $v0
	
	
	sw $v0, 0($t0)	#stores the value that enter by the user to the array
	addi $t0, $t0, 4	#increment pointer
	add $s0, $s0, $v0	#compute the sum and store it in $s0 register
	
	j while 	#jump to the label while
	
reset:
	#clear $t1 and $t0 to 0 so it can starts print from the first element to the last elememt
	addi $t1, $zero, 0	#reset the counter in register $t1 to 0
	subi $t0, $t0, 40	#decrement the pointer to 0
	j print	#jump to the label print
	
print:	
	beq $t1, 10, total	#branch to total if $t1 equals 10. if not equal, then do the instruction below
	
	#print the output
	li $v0, 4	#system call code for print string	
	la $a0, output	#load address of output into $a0
	syscall	#prints the string
	
	addi $t1, $t1, 1	#increment counter by 1 and store it in $t1
	
	#print an integer to count which element in the array
	li $v0, 1	#system call code for print integer
	move $a0,$t1	#moves value to be printed to $a0
	syscall	#prints the integer
	
	#print the output1
	li $v0, 4	#system call code for print string
	la $a0, output1	#loads address of output1 into $a0
	syscall	#prints the string
	
	lw $t6, 0($t0)	#gets an element from the array and load it into $t6
	
	#print the value in the array
	li $v0, 1	#system call code for print integer
	move $a0, $t6	#moves value in $t6 to be printed to $a0
	syscall	#print the integer
	
	#print a new line
	li $v0, 4	#system call code for print a new lin	
	la $a0, newline	#loads address of newline into $ao
	syscall	#prints the newline
	
	addi $t0, $t0, 4	#increments pointer
	
	j print 	#jumps to the label print
	
total:
	#print the outputsum
	li $v0, 4	#system call code for print string
	la $a0, outputsum	#load address of outputsum into $a0
	syscall	#print the string
	
	#print the sum
	li $v0, 1	#system call code for print integer
	move $a0, $s0	#move the value of sum to be printed to $s0
	syscall	#print the integer
	
	#exit the program
	li $v0, 10 	#terminate program run 
	syscall 	#and return control to system
