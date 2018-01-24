//class name: CPSC 355 -T01     id number: 30010000   name: jingyi. li
	.text
fmt1:	.string "Error opening file: %s\nAborting.\n"
fmt2:	.string "%.10f \t %.10f\n"
title1:	.string "\noriginal number  cube root\n\n"
num:	.double 0r1.0e-10	

	define(input_file, x19)
	define(fd_r, w20)
	define(read_r, x21)
	define(buf_base, x22)
	define(int_c, d23)
	define(result, d24)
	define(dx_value, d14)								//registers used in function
	define(x_value, d11)
	define(y_value, d12)
	define(dy_value, d13)
	
	buf_size = 8
	alloc = -(16+ buf_size*8) & -16
	dealloc = - alloc
	buf_s = 16
	SIZE = -100
	
	.balign 4
	.global main
main:	stp x29, x30, [sp, alloc]!
	mov x29, sp
	
	adrp x28, num									//calculating the address for num
	add x28, x28, :lo12:num 
	

	//store input file name to the register
	ldr input_file, [x1, 8]
	
	//open existing file for input

openin:	mov w0, SIZE									//1st arg for (cwd)
	mov x1, input_file								//2nd arg pathname
	mov w2, 0									//3rd arg read-only
	mov w3, 0									//4th arg not used
	mov x8, 56									//openat I/O request
	svc 0										//call system function
	mov fd_r, w0									//Record file descriptor

	//error checking
	cmp w0, 0									//compare the returned value with 0
	b.ge openok									//file open ok

	adrp x0, fmt1									//otherwise display the error message
	add x0, x0, :lo12:fmt1								//calculating the address for fmt1
	mov x1, input_file								//store the conmmand line argument in x1
	bl printf									//system call print
	mov w0, -1									//return -1
	b exit										//go the exit

openok:	add buf_base, x29, buf_s							//file open ok, calculating the address for buf_base
	adrp x0, title1
	add x0, x0, :lo12:title1							//calculating the address for title1
	bl printf

read_f:	mov w0, fd_r									//1st arg fd_r
	mov x1, buf_base								//2nd arg (buf)
	mov w2, buf_size								//3rd arg (n)
	mov x8, 63									//read I/O request
	svc 0										//call system function
	mov read_r, x0									//record number of bytes actually read

	cmp read_r, buf_size								//detect the end of the input file
	b.ne end									//if not equals to 16, go to end
	
	ldr d0, [buf_base]								//getting the real number and restore the number in a d register, d0

	bl calculation									//call calculation()

	fmov result, d0									//put the returned value in result
	adrp x0, fmt2									//calculating the address for fmt2
	add x0, x0, :lo12:fmt2
	ldr d0, [buf_base]								//setting the first argument 
	fmov d1, result									//2ne argument
	bl printf									//system call print()

	b read_f									//loop to the top
	
	// Close the binary file
end:	mov w0, fd_r									//1st arg (fd_r)
	mov x8, 57									// close I/O request
	svc 0										// call system function
	mov w0, 0									//return 0

exit:	ldp x29, x30, [sp], dealloc
	ret
	
	
calculation:	stp x29, x30, [sp, -16]!
	mov x29, sp
	fmov d10, d0									//passed argument (input) in d10
	fmov d17, 3.0									//put 3.0 in d17
	fdiv x_value, d10, d17								//x = input / 3
	
top_cal:	fmov d17, 3.0								//d17 = 3.0
	fmul y_value, x_value, x_value							//y = x * x
	fmul y_value, y_value, x_value							//y = x * x * x
	fmov d15, x_value								//save a coppy for x value
	fsub dy_value, y_value, d10							//dy = y - input
	fmul dx_value, x_value, x_value							//dy/dx = x * x
	fmul dx_value, dx_value, d17							//dy/dx = 3.0 * x * x
	fdiv dx_value, dy_value, dx_value
	fsub x_value, x_value, dx_value							//x = x - dy / (dy/dx)
	fabs dy_value, dy_value								//abs(dy)
	ldr d17, [x28]									//read from x28
	fmul d16, d10, d17								//d16 = input * 1.0e-10
	
	fcmp dy_value, d16								//compare dy (error) with d16
	b.ge top_cal									//if the error is greater, then repeat the calculation
	fmov d0, d15									//otherwise return x
	
cal_exit:	ldp x29, x30, [sp], 16
	ret
