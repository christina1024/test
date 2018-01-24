//class name: CPSC 355 -T01     id number: 30010000   name: jingyi. li
define(value, w19)
define(head_a, x20)
define(tail_v, w21)
define(head_v, w22)
define(queue_a, x23)
define(tail_a, x24)

FALSE=0
TRUE=1
QUEUESIZE=8
MODMASK=0x7
alloc=(-QUEUESIZE * 4)& -16
dealloc= - alloc

			.text
queue_F:	.string "\nQueue overflow! Cannot enqueue into a full queue.\n"	
dequeue_F:	.string "\nQueue underflow! Cannot dequeue from an empty queue.\n"
display_F:	.string "\nEmpty queue\n"
display2_F:	.string "\nCurrent queue contents:\n"
display3_F:	.string "  %d"
display4_F:	.string "<-- head of queue"
display5_F:	.string "<-- tail of queue"
newLine_F:	.string "\n"

		.data						//global variables
		.global head_m			
head_m:	.word	-1						//int head = -1  4byte integer
	.global tail_m
tail_m:	.word	-1 						//int tail = -1 4byte integer

		.bss
queue:	.skip	QUEUESIZE*4					//queue[QUEUESIZE] an interger array list with size 8
	
	.text
	.balign 4
	.global enqueue						//make enqueue() to the other module
	
enqueue:	stp x29, x30, [sp, alloc]!			//calculating the address
	mov x29, sp
	adrp head_a, head_m					//Getting address for head
	add head_a, head_a, :lo12:head_m			//getting low order part address
	adrp tail_a, tail_m					//getting address for tail
	add tail_a, tail_a, :lo12:tail_m			//getting low order part address
	
	mov value, w0						//w0 contains the parameter from the calling method and restore the value in the register called value
	bl queueFull						//queueFull()
	cmp w0, 1						//compare the returned value from queseFull() to 1
	b.eq queueFull_true					//if equal, the program jumps to queuFull_true
	bl queueEmpty						//queueEmpty()
	cmp w0, 1						//compare the returned value with 1
	b.eq queueEmpty_true					//if the value is equal to 1, the program will then jump to queueEmpty_true
	
	ldr tail_v, [tail_a]					//tail_v = tail
	add tail_v, tail_v, 1					//tail = tail +1
	and tail_v, tail_v, MODMASK				//tail = tail & MODMASK
	str tail_v, [tail_a]					//restore the new tail value
	b enqueue_end						//jump to enqueue_end
		
queueFull_true:	adrp x0, queue_F				//calculating the address
	add x0, x0, :lo12:queue_F
	bl printf						//printf()
	b enqueue_return					//jump to enqueue_return
	
queueEmpty_true:	
	mov head_v, 0						//head = 0
	str head_v, [head_a]					//restore head
	mov tail_v, head_v					//tail = head
	str tail_v, [tail_a]					//restore tail
	
enqueue_end:	adrp queue_a, queue				//calculating the address for queue
	add queue_a, queue_a, :lo12:queue
	ldr tail_v, [tail_a]					//tail
	str value, [queue_a, tail_v, SXTW 2]			//restore the value
	
enqueue_return:	ldp x29, x30, [sp], dealloc			//return
	ret
	
	.balign 4
	.global dequeue						//make dequeue visible to the other module
	
dequeue:	stp x29, x30, [sp, alloc]!			//calculating the address
	mov x29, sp
	adrp head_a, head_m					//calculate the address for head
	add head_a, head_a, :lo12:head_m			//lower part of the address
	adrp tail_a, tail_m					//calculate the address for tail
	add tail_a, tail_a, :lo12:tail_m			//lower part of the address
	bl queueEmpty						//queueEmpty()
	cmp w0, 1						//compare the returned value with 1
	b.eq de_qF_end						//the program jumps to de_qF_end if the value is equals to 1
	ldr head_v, [head_a]					//getting place for head
	ldr value, [queue_a, head_v, SXTW 2] 			//getting the head value
	ldr tail_v, [tail_a]					//getting the place for tail
	cmp tail_v, head_v					//compare head and tail
	b.eq tail_head						//the program jumps to tail_head if the head and the tail are the same
	add head_v, head_v, 1					//head = head +1
	and head_v, head_v, MODMASK				//head = head & MODMASK
	str head_v, [head_a]					//restore the new head
	mov w0, value						//setting the reture value
	b dequeue_return					//jump to dequeue_return
	
tail_head: mov head_v, -1					//head = -1
	mov tail_v, -1						//tail = -1
	str head_v, [head_a]					//restore the new head
	str tail_v, [tail_a]					//restore the new tail
	mov w0, value						//setting the reture value
	b dequeue_return					//jump to dequeue_return

de_qF_end:	adrp x0, dequeue_F				//if case calculating the address 
	add x0, x0, :lo12:dequeue_F
	bl printf						//print
	mov w0, -1						//setting the 

dequeue_return:	ldp x29, x30, [sp], dealloc			//return
	ret
	
queueFull:	stp x29, x30, [sp, -16]!			//function queueFull
	mov x29, sp
	ldr tail_v, [tail_a]					//getting the tail value
	add tail_v, tail_v, 1					//tail = tail +1
	and tail_v, tail_v ,MODMASK				//tail = tail & MODMASK
	ldr head_v, [head_a]					//getting the head value
	cmp tail_v, head_v					//compare head and value
	b.eq queueFull_end					//if two values are the same, then jump to queueFull_end
	mov w0, 0						//setting the return integer
	b queueFull_return					//jump to queueFull_return
	
queueFull_end:	mov w0, 1					//else case return 0

queueFull_return:	ldp x29, x30, [sp], 16			//return
	ret

queueEmpty: stp x29, x30, [sp, -16]!				//queueEmptu function
	mov x29, sp
	ldr head_v, [head_a]					//getting the head value
	cmp head_v, -1						//compare head with -1 
	b.eq queueEmpty_end					//if no value has being entered, jump to queueEmpty_end
	mov w0, 0						//return 0
	b queueEmpty_return					
	
queueEmpty_end:	mov w0, 1					//return true
queueEmpty_return: ldp x29, x30, [sp], 16
	ret
	
	.global display						//called by main in c
	
define(i, w25)							//register i
define(j, w26)							//register j
define(count, w27)						//count
display:	stp x29, x30, [sp, alloc]!
	mov x29, sp
	adrp head_a, head_m					//calculating the address for head
	add head_a, head_a, :lo12:head_m
	adrp tail_a, tail_m					//calculating the address for tail
	add tail_a, tail_a, :lo12:tail_m
	
	mov j, 0						//j = 0
	bl queueEmpty						//call queueEmpty()
	cmp w0, 1						//compare the returned value with 1
	b.eq display_end					//if true, jump to display_end
	ldr head_v, [head_a]					//getting head
	ldr tail_v, [tail_a]					//getting tail
	
	sub count, tail_v, head_v				//count = tail - head
	add count, count, 1					//count = count + 1
	cmp count, 0						//compre the current count with 0
	b.gt display_n						//if count is bigger than 0, then jump to display_n
	add count, count, QUEUESIZE				//if not, count = count + 8

display_n:	adrp x0, display2_F				//calculating the address for display2_F
	add x0, x0, :lo12:display2_F
	bl printf						//printf
	mov i, head_v						//i = head
	
display_loop:	cmp j, count					//compare j with count
	b.ge display_return					//if j is bigger than count, function end
	ldr w1, [queue_a, i, SXTW 2]				//setting the first argument
	adrp x0, display3_F
	add x0, x0, :lo12:display3_F
	bl printf						//printf
	cmp i, head_v						//compare i with head
	b.ne display_next					//if the values are not equal, the program jumps to display_next
	adrp x0, display4_F					//calculating the address
	add x0, x0, :lo12:display4_F
	bl printf						//printf
	
display_next:	cmp i, tail_v					//compare i with tail
	b.ne display_back
	adrp x0, display5_F					//if equal
	add x0, x0, :lo12:display5_F
	bl printf						//printf
	
display_back:	adrp x0, newLine_F				//calculate the address for newLine_F
	add x0, x0, :lo12:newLine_F
	bl printf						//printf
	add i, i, 1						//i = i +1 
	and i, i, MODMASK					//i = i & MODMASK
	add j, j, 1						//j = j +1
	b display_loop
	
display_end:	adrp x0, display_F				//print
	add x0, x0, :lo12:display_F
	bl printf
	
display_return:	ldp x29, x30, [sp], dealloc			//return
	ret
	
	
	
	
