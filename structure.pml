typedef semaphore {
	byte cpt;
};

typedef cyclicbarrier {
	int total;
	semaphore s;
	int nready;
	bool done;
};

inline new(c, v) {
	atomic {
		c.total = v;
		c.done = false;
		initialise(c, 1);
	}
}

inline recv (c) {
	atomic {
		if
		::(c.s.cpt > 0)->c.s.cpt--;
        fi;
	}
};

inline release (c) {
	atomic {
		c.s.cpt++;
	}
};

inline initialise(c, v) {
    atomic {
        c.s.cpt = v;
    }

};

inline await(c) {
	atomic {
		c.nready++;
		if
		::(c.nready == c.total) -> c.done = true;
		::else skip;
		fi; 
	}
}


inline reset(c) {
	atomic {
		c.nready = 0;
		c.done = false;
	}	
}


