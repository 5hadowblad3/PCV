#include "structure.pml"
cyclicbarrier c;
proctype productor() {
	int cnt = 0;

	do
	:: (cnt == 10) -> 
		await(c);
		break;
	::else cnt++;
	od;
	printf("Number %d thread is finish\n", _pid);
}	


init {
	new(c, 3);
	atomic {	
		run productor();
		run productor();
		run productor();
	}	
}

