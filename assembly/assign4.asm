//class name: CPSC 355 -T01     id number: 30010000   name: jingyi. li
title1:	.string "initial pyramid values:\n"
title2:	.string "Changed pyramid values:\n"
first:	.string "First"
second:	.string "Second"
First_origin:	.string "pyramid %s origin = (%d, %d)\n"
pyramid_values:	.string "\tBase width = %d Base lenght = %d\n"
pyramid_height:	.string "\tHeight = %d\n"
pyramid_volume:	.string "\tVolume = %d\n\n"

define(pyramid_base, x8)
define(values, w19)
define(result, w24)

// integer type x, y  points
x_s = 0
y_s = 4
	
// integer type dimension
width_s = 0
length_s = 4

//pyramid
point_origin = 0
dimension_base = 8	
height_s = 16
volume_s = 20
	
total_byte_s= volume_s+4					//total 24 bytes, space that needed for all the variables in the first pyramid			
alloc= -(total_byte_s + total_byte_s + 16) &-16			//-64 + -16 
dealloc = -alloc
	
	.balign 4	
	.global main
//local variables first and second in main
first_p_s = 16							//16, stack frame
second_p_s = first_p_s + total_byte_s				//40, starting place for the second pyramid
	
main:	stp x29, x30, [sp, alloc]!				//save frame pointer and link register
	mov x29, sp						//update frame pointer
	
	add pyramid_base, x29, 0				//put the stack in the indirect result location register (x8), calculate pyramid_base address
	mov x0, first_p_s					//put first_p_s in x0 to pass the first argument to the newPyramid
	bl newPyramid						//call newPyramid()
	
	mov x0, second_p_s					//put second_p_s in x0 to pass the first argument to the newPyramid
	bl newPyramid						//call newPyramid()

	adrp x0, title1						//calculating the address of "title1"
	add x0, x0, :lo12:title1
	bl printf						//print function

	adrp x0, first						//calculate the address of "first", set the "first" as the first argument that need to be passed
	add x0, x0, :lo12:first
	add x1, x29, first_p_s					//put the pointer address in the register x1, (second argument)
	bl printPyramid						//call printPyramid function

	adrp x0, second						////calculate the address of "second", set the "second" as the first argument 
	add x0, x0, :lo12:second
	add x1, x29, second_p_s					//put the pointer address in the register x1, (second argument)
	bl printPyramid						//call printPyramid()

	add x0, x29, first_p_s					//calculate and put the first_p_s address in the register x0, set as first argument
	add x1, x29, second_p_s					//calculate and put the second_p_s address in the register x1, set as second argument
	bl equalSize						//call equalSize()
	mov result, w0						//register w0 contains the returned value from equalSize, put the value in result
	
	cmp result, 0						//compare the result with 0
	b.eq	final						//if the result is equals to 0, then the program will jump to final
	add x0, x29, first_p_s					//the result is not equals to 0, then calculate and put the first_p_s address in the register x0, as first argument
	mov w1, -5						//set -5 as the second argument
	mov w2, 7						//set 7 as the third argument
	
	bl move							//call move()
	
	add x0, x29, second_p_s					//calculate and put the second_p_s address in the register x0, as first argument
	mov w1, 3						//set 3 as the second argument
	
	bl scale						//call scale()
	
	b final							//jump to final
	
move:	stp x29, x30, [sp, -16]!
	mov x29, sp

	ldr w10, [x0, x_s + point_origin]			//read from the pointer first_p_s + x_s, put the value in the register w10
	add w10, w10, w1					//w1 contains -5, calculation w10- -5 and put the answer in w10
	str w10, [x0, x_s +point_origin]			//write the new value in w10 to the pointer first_p_s + x_s

	ldr w10, [x0, y_s + point_origin]			//read from the pointer first_p_s + y_s, put the value in the register w10
	add w10, w10, w2					//w2 contains 7, calculation w10 + 7 and restore the answer in w10
	str w10, [x0, y_s + point_origin]			//write the new value in w10 to the pointer first_p_s + x_s

	ldp x29, x30, [sp], 16
	ret							//return 

scale:	stp x29, x30, [sp, -16]!
	mov x29, sp

	ldr w10, [x0, dimension_base + width_s]			//read from the pointer second_p_s + width_s, put the value in the register w10
	mul w10, w10, w1					//w1 contains 3, calculation w10*3 and put the answer in w10
	str w10, [x0, dimension_base +width_s]			//write the new value in w10 to the pointer second_p_s + width_s
	mov w11, w10						//make a copy of the value in w10

	ldr w10, [x0, dimension_base + length_s]		//read from the pointer second_p_s + length_s, put the value in the register w10
	mul w10, w10, w1					//w1 contains 3, calculation w10*3 and put the answer in w10
	str w10, [x0, dimension_base + length_s]		//write the new value in w10 to the pointer second_p_s + length_s
	mov w12, w10						//make a copy of the value in w10

	ldr w10, [x0, height_s]					//read from the pointer second_p_s + height_s, put the value in the register w10
	mul w10, w10, w1					//w1 contains 3, calculation w10*3 and put the answer in w10
	str w10, [x0, height_s]					//write the new value in w10 to the pointer second_p_s + height_s
	mov w13, w10						//make a copy of the value in w10

	mul w11, w11, w12					// w11 * w12 = w11, new width * new length = product
	mul w11, w11, w13					// product * new height = product
	sdiv w10, w11, w1					// product / 3 
	str w10, [x0, volume_s]					//write the new value in w10 to the pointer second_p_s + volume_s

	ldp x29, x30, [sp], 16
	ret
	
equalSize:	stp x29, x30, [sp, -16]!
	mov x29, sp
	mov result, 0						// put 0 in result

	ldr w11, [x0, dimension_base + width_s]			//read from the pointer first_p_s + width_s, put the value in the register w11		
	ldr w12, [x1, dimension_base + width_s]			//read from the pointer second_p_s + width_s, put the value in the register w12
	cmp w11, w12						//compare the two values in w11 and w12
	b.ne equalSize_done					//if the two values are not the same, the program will jump to equalSize_done
	
	ldr w11, [x0, dimension_base + length_s]		//read from the pointer first_p_s + length_s, put the value in the register w11	
	ldr w12, [x1, dimension_base + length_s]		//read from the pointer second_p_s + length_s, put the value in the register w12
	cmp w11, w12						//compare the two values in w11 and w12
	b.ne equalSize_done					//if the two values are not the same, the program will jump to equalSize_done

	ldr w11, [x0, height_s]					//read from the pointer first_p_s + height_s, put the value in the register w11
	ldr w12, [x1, height_s]					//read from the pointer second_p_s + height_s, put the value in the register w12
	cmp w11, w12						//compare the two values in w11 and w12
	b.ne equalSize_done					//if the two values are not the same, the program will jump to equalSize_done

	mov result, 1						//put 1 in result if width, length and height for the two pyramids are same.

equalSize_done:	mov w0, result					//put result in the register w0
	ldp x29, x30, [sp], 16
	ret							//return result

printPyramid:	stp x29, x30, [sp, -16]!
	mov x29, sp

	mov x20, x1						//make a copy of the second argument
	mov x1, x0						//move the first argument in the register x1, the first agument to be printed
	ldr w2, [x20, x_s + point_origin]			//read from the pointer x20 + x_s, put the value in the register w2, set as the second argument
	ldr w3, [x20, y_s + point_origin]			//read from the pointer x20 + y_s, put the value in the register w3, set as the third argument
	adrp x0, First_origin					//calculate the address of First_origin		
	add x0, x0, :lo12:First_origin
	bl printf						//print

	ldr w1, [x20, dimension_base + width_s]			//read from the pointer address x20 + width_s, put the value in the register w1, set as the first argument
	ldr w2, [x20, dimension_base + length_s]		//read from the address x20 + length_s, put the value in the register w2, set as the second argument
	adrp x0, pyramid_values					//calculate the address of pyramid_values
	add x0, x0, :lo12:pyramid_values
	bl printf						//print

	ldr w1, [x20, height_s]					//read from the address x20 + width_s, put the value in the register w1, set as the first argument
	adrp x0, pyramid_height					//calculate the address for pyramid_height
	add x0, x0, :lo12:pyramid_height
	bl printf						//print

	ldr w1, [x20, volume_s]					//read from the address x20 + volume_s, put the value in the register w1, set as the first argument
	adrp x0, pyramid_volume					//calculate the address for pyramid_volume
	add x0, x0, :lo12:pyramid_volume			
	bl printf						//print

	ldp x29, x30, [sp], 16
	ret							//return
	
	define(p_base, x9)					//local variable p
newPyramid:	stp x29, x30, [sp, alloc]!
	mov x29, sp
	
	mov x11, x0						//put the value passed by main in the register x11
	add p_base, x29, x11					//calculate the address of x11
	mov values, 0						//put 0 in values
	str values, [p_base, x_s + point_origin]		//write values to the stack, p.origin.x=0
	mov values, 0						//put 0 in values
	str values, [p_base, y_s + point_origin]		//write values to the stack, p.origin.y=0
	mov values, 2						//assign 2 to value
	mov w20, values						//make a coppy of values, and put it into w20
	str values, [p_base, dimension_base + width_s]		//write values to the stack, p.base.width=2
	mov values, 2						//assign 2 to value
	mul w21, values, w20					//length * width = product w21
	str values, [p_base, dimension_base + length_s]		//write values to the stack, p.base.length=2
	mov values, 3						//put 3 in values
	str values, [p_base, height_s]				//write values to the stack, p.base.height=3
	mul w21, w21, values					//product * height = product w21
	mov w20, 3						//move 3 into the register w20
	sdiv values, w21, w20					// product / 3 = volume
	str values, [p_base, volume_s]				//write values to the stack, p.base.volume= length * width * height / 3

	add x12, x11, x_s+ point_origin				//w11 contains the value passed by main + x_s to get the location for x, then put the result in x12
	ldr w10, [p_base, x_s + point_origin]			//read from the stack p_base + x_s, and put the x value in w10 
	str w10, [pyramid_base, x12]				//write the value in w10, to the stack set in main
	add x12, x11, y_s+ point_origin				//w11 contains the value passed by main + y_s to get the location for y, then put the result in x12
	ldr w10, [p_base, y_s+ point_origin]			//read from the stack p_base + y_s, and put the y value in w10 
	str w10, [pyramid_base, x12]				//write the value in w10, to the stack set in main
	add x12, x11, dimension_base + width_s			//pyramid_base + width_s to get the location for width
	ldr w10, [p_base, dimension_base + width_s]		//read from the stack p_base + width_s, and put the width in w10 
	str w10, [pyramid_base, x12]				//write the value in w10, to the stack set in main
	add x12, x11, dimension_base + length_s			//pyramid_base + length_s to get the location for length
	ldr w10, [p_base, dimension_base + length_s]		//read from the stack p_base + length_s, and put the length in w10 
	str w10, [pyramid_base, x12]				//write the value in w10, to the stack set in main
	add x12, x11, height_s					//pyramid_base + height_s to get the location for height
	ldr w10, [p_base, height_s]				//read from the stack p_base + height_s, and put the height in w10 
	str w10, [pyramid_base, x12]				//write the value in w10, to the stack set in main
	add x12, x11, volume_s					//pyramid_base + volume_s to get the location for volume
	ldr w10, [p_base, volume_s]				//read from the stack p_base + volume_s, and put the volume in w10 
	str w10, [pyramid_base, x12]				//write the value in w10, to the stack set in main


	ldp x29, x30, [sp], dealloc
	ret							//return

final:	adrp x0, title2						//calculate the address for "title2"
	add x0, x0, :lo12:title2
	bl printf						//print title2

	adrp x0, first						//calculate the address for "first", also set it as the first argument to be passed
	add x0, x0, :lo12:first
	add x1, x29, first_p_s					//calculate the address of first_p_s and set it as the second argument
	bl printPyramid						//call printPyramid()

	adrp x0, second						//calculate the address for "second", also set it as the first argument to be passed
	add x0, x0, :lo12:second
	add x1, x29, second_p_s					//calculate the address of second_p_s and set it as the second argument
	bl printPyramid						//call printPyramid()
	
	ldp x29, x30, [sp], dealloc
	ret

