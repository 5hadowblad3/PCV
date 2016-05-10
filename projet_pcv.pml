#include "structure.pml"

proctype productor(cyclicbarrier c; int  i) 
{
	int cnt = 0;
	bool ready = false;

	do
	:: (cnt == 10 && !ready) -> 
		ready = true;
		recv(c);
		await(c);
		release(c);
		printf("Number %d thread is ready\n", i);
	::(ready) -> skip;
	::(c.done) -> break;
	::else cnt++;
	od;
	printf("Number %d thread enter new period\n", i);
}	


init {
	atomic {
		cyclicbarrier ca;
		new(ca, 3);
		run productor(ca, 0);
		run productor(ca, 1);
		run productor(ca, 2);
	}	
}
 

