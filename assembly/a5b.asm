//class name: CPSC 355 -T01     id number: 30010000   name: jingyi. li
define(i_r, w19)
define(argc_r, w20)	
define(argv_r, x21)
define(day_v, w22)
define(month_v, w23)
define(base_season, x26)
define(base_month, x27)
define(season_v, w28)

fmt:	.string "%s %d%s is %s\n"
error_day:	.string "At least one of your values is not in range\n"

th_m:	.string "th"
st_m:	.string "st"
nd_m:	.string "nd"
rd_m:	.string "rd"
	
spr_m:	.string "Spring"
sum_m:	.string "Summer"
fal_m:	.string "Fall"
win_m:	.string "Winter"

jan_m:	.string "January"
feb_m:	.string "February"
mar_m:	.string "March"
apr_m:	.string "April"
may_m:	.string "May"
jun_m:	.string "June"
jul_m:	.string "July"
aug_m:	.string "August"
sep_m:	.string "September"
oct_m:	.string "October"
nov_m:	.string "November"
dec_m:	.string "December"

	.data
	.balign 8
season_m:	.dword win_m, spr_m, sum_m, fal_m								//external pointer array for season
month_m:	.dword jan_m, feb_m, mar_m, apr_m, may_m, jun_m, jul_m, aug_m, sep_m, oct_m, nov_m, dec_m       //external pointer array for month
date_m:	.dword st_m, nd_m, rd_m, th_m										//external pointer array for suffix for the day of the month

	.text
	.balign 4
	.global main

main:	stp x29, x30, [sp, -16]!
	mov x29, sp
	mov argc_r, w0							//copy argc, the size of the arguments
	mov argv_r, x1							//copy argv, argument in string format
	
	mov i_r, 1							//set i_r

	cmp argc_r, 3							//checking the size (if the user enters two argument + name for the program = total of three arguments)
	b.ne loop_end							//if not, jump to loop_end
	
range_c:	ldr x0, [argv_r, i_r, SXTW 3]				//getting the first command-line argument, second one in the stack
	bl atoi								//call atoi library function to convert string into integer
	mov month_v, w0							//put the returned integer in month_v
	add i_r, i_r, 1							//i_r = i_r +1
	ldr x0, [argv_r, i_r, SXTW 3]					//getting the second command-line argument
	bl atoi								//call atoi function
	mov day_v, w0							//put the returned result in day_v
	
day_check:	cmp day_v, 0						//checking the range of the user input
	b.le loop_end							//if the day is less or equal than 0, loop end
	cmp day_v, 31							//if the day is greater than 31
	b.gt loop_end							//the loop will end
	cmp month_v, 0							//if the month is less or equal than 0, loop end
	b.le loop_end
	cmp month_v, 12							//if the month is greater than 12
	b.gt loop_end							//jump to loop_end
	b season_s
	
loop_end: adrp x0, error_day						//error message
	add x0, x0, :lo12:error_day
	bl printf							//printf
	b prog_end
	
season_s: cmp month_v, 3						//compare the month with 3
	b.gt spring_m							//if the month is bigger than three, then the season cannot be winter
	mov season_v, 0							//assign season_v to 0, season is winter
	b.ne date_s							//if the month is not equals to 3, then the season is winter and can be jump to the date_s
	cmp day_v, 21							//the month is three, so we have to check if the date is greater or equal to 21 
	b.lt date_s							//if the date is less than 21, the month is still winter. otherwise the month is spring
	
spring_m:	cmp month_v, 6						//compare the month with 6
	b.gt summer_m							//if the month is bigger than 6, then the season cannot be spring
	mov season_v, 1							//otherwise assign season_v to 1 (spring) for now
	b.ne date_s							//if the month is not equals to 6, then the season is spring and can be jump to the date_s
	cmp day_v, 21							//the month is 6, so we have to check if the date is greater or equal to 21 
	b.lt date_s							//if the date is less than 21, the month is still spring. otherwise the month is summer
	
summer_m:	cmp month_v, 9						//compare the month with 9
		b.gt fall_m						//if the month is bigger than 9, then the season cannot be summer. go to fall_m
		mov season_v, 2						//otherwise assign season_v to 2 (summer) for now
		b.ne date_s						//if the month is not equals to 9, then the season is summer and can be jump to the date_s
		cmp day_v, 21						//the month is 9, so we have to check if the date is greater or equal to 21 
		b.lt date_s						//if the date is less than 21, the month is still summer. otherwise the month is fall
		
fall_m:	mov season_v, 3							//the only season still left is fall, so assign season_v to 3 (fall) for now 
	cmp month_v, 12							//compare the month with 12
	b.ne date_s							//if the month is not equals to 12, then the season is fall and can be jump to the date_s
	cmp day_v, 21							//otherwise compare the date to 21
	b.lt date_s							//if the date is less than 21, the month is fall. otherwise the month is winter
	
winter_m:	mov season_v, 0						//winter season

date_s:	cmp day_v, 1							//check if the date is 1
	b.eq st_s							//if equal than go to st_s
	cmp day_v, 21							//check if the date is 21
	b.eq st_s							//if equal than go to st_s
	cmp day_v, 31							//check if the date is 31
	b.eq st_s							//if equal than go to st_s
	cmp day_v, 2							//check if the date is 2
	b.eq nd_s							//if equal than go to nd_s
	cmp day_v, 22							//check if the date is 22
	b.eq nd_s							//if equal than go to nd_s
	cmp day_v, 3							//check if the date is 3
	b.eq rd_s							//if equal than go to rd_s
	cmp day_v, 23							//check if the date is 23
	b.eq rd_s							//if equal than go to rd_s
	b th_s								//if the date is not in any of the number above, then jump to th_s
	
st_s:	mov i_r, 0							//assign i_r to 0(st suffix)
	b print_value
nd_s:	mov i_r, 1							//assign i_r to 1(nd suffix)
	b print_value
rd_s:	mov i_r, 2							//assign i_r to 2(rd suffix)
	b print_value
th_s:	mov i_r, 3							//assign i_r to 3(th suffix)

print_value:	adrp base_month, month_m				//calculate the base_month address
	add base_month, base_month, :lo12:month_m
	sub month_v, month_v, 1						//since the array start at 0 to 11, curren month is from 1 to 12, so month = month -1
	ldr x1, [base_month, month_v, SXTW 3]				//setting the first argument, month
	mov w2, day_v							//setting the second argument, date
	
	adrp x24, date_m							//calculate the address for date_m
	add x24, x24, :lo12:date_m
	ldr x3, [x24, i_r, SXTW 3]					//setting the third argument

	adrp base_season, season_m					//calculate the base_season address
	add base_season, base_season, :lo12:season_m
	ldr x4, [base_season, season_v, SXTW 3]				//setting the fourth argument, season
	
	adrp x0, fmt
	add x0, x0, :lo12:fmt
	bl printf							//printf

prog_end: ldp x29, x30, [sp], 16					//return
	ret
