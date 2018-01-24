//class name: CPSC 355 -T01     id number: 30010000   name: jingyi. li
define(i, w19)
define(j, w20)
define(temp, w21)
define(previous_value, w22)
define(previous_j, w23)
define(base, x24)

arrayBase=16+4
size=50
arraySize=size*4
alloc=-(16+arraySize)&-16
dealloc=-alloc

.text
sorted_s:	.string "\nSorted array:\n"
output_string:	.string "v[%d]: %d\n"

	.balign 4
	.global main
main:	stp x29, x30, [sp, alloc]!
	mov x29, sp
	mov i, 0								//initialize varible i
	add base, x29, arrayBase						//calculate array base address

load:	cmp i, size								//initialize the loop (which will loop the same size as the array)
	b.ge loop_end								//jump to the loop_end if the arraylist reach the number of size.

	bl rand									//using rand operation to print a random number
	and temp, w0, 0xFF							//the random number will be in between 0 and 255, and will be stored in temp.	
	str temp, [base, i, SXTW 2]						//use str operation to write the newly generated random number on the stack. using SXTW (2^2) operation to extend to 8 bytes.
	mov w1, i								//set the first argument.
	mov w2, temp								//set the second argument.
	adrp x0, output_string							//calculating the address of "output_string" 
	add x0, x0, :lo12:output_string
	bl printf								//print the output_string

	add i, i, 1								//loop increases 1
	
	b load									//going back to the top of the loop.
	
loop_end:	mov i, 1							//reset for i
	
sort_loop:	cmp i, size							//initialize another loop
	b.ge final								//the program jumps to final, if i is greater or equal than the size.
	ldr temp, [base, i, SXTW 2]						//get the integer on the stack and restore the number in temp.
	mov j, i								//make a coppy of the value in i.
	
sorting:	mov previous_j, j						//initialize the previous_j.
	sub previous_j, previous_j, 1						//make the previous_j as the previous number on the stack.
	cmp previous_j, 0							//check if reach the top of the stack.
	b.lt write_in								//if yes than will jump to write_in
	ldr previous_value, [base, previous_j, SXTW 2]				//read from the stack and put the previous number in the register called previous_value.
	cmp temp, previous_value						//compare temp(the current intger that need to find a place to put) with previous_value(the number alredy sorted)
	b.ge write_in								//if the current integer is bigger, then will go to write_in.
	str previous_value, [base, j, SXTW 2]					//if the current integer is smaller, then replace the current integer with the bigger integer 
	sub j, j, 1								//move on to the next previous number on stack.
	b sorting								//going back to sorting.

write_in:	str temp, [base, j, SXTW 2]					//record the integer in temp on the stack.
	add i, i, 1								//loop increases by 1.
	b sort_loop								//back to sort_loop.

final:	adrp x0, sorted_s							//calculating the address for sorted_s.
	add x0, x0, :lo12:sorted_s
	bl printf								//print sorted_s
	mov i, 0								//reset for i.

print_list:	cmp i, size							//initialize another loop to print the array.
	b.ge done
	mov w1, i								//set the first varible.
	ldr w2, [base, i, SXTW 2]						//read the number from the stack.
	adrp x0, output_string							//calculating the address for output_string.
	add x0, x0, :lo12:output_string
	bl printf								//print output_string.
	
	add i, i, 1								//loop increases by 1.

	b print_list								//going back to the top of the loop.

done:	mov w0, 0								//return 0 in main.
	ldp x29, x30, [sp], dealloc						//restore reguster and return to calling code.
	ret
