typedef cyclicbarrier {
	int total;
	int nready;
	bool broken;
};

inline new(c, v) {
	c.total = v;
	c.broken = false;
	c.nready = 0;
};

inline await(c) {
	atomic {
		c.nready++;
		printf("process %d is waiting\n", _pid);
		(c.nready == c.total) -> skip;
	}
}

inline getNumberWaiting(c, res) {
	res = c.nready;
}

inline getParties(c, res) {
	res = c.total;
}

inline isBroken(c, res) {
	res = c.broken;
}

inline reset(c) {
	atomic {
		if 
			::(c.nready > 0 && c.nready < c.total) ->
				c.broken = true;
				printf("BrokenBarrierException: Some process is waiting!\n");
			::else skip;		
		fi;

		assert(!c.broken);
		c.broken = false;
		c.nready = 0;
	}
}


