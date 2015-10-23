package com.kirdow.fibonacci;

public class Fibonacci {
	
	//We meed no constructor, so we leave it empty
	private Fibonacci() { }
	
	//This is where the fibonacci number is generated. It's static because we don't
	//need to store variables in this class
	public static int get(int n) {
		//If n is less than or equal to 2, we return 1
		//why? see this:
		//The fibonacci sequence takes the last 2 numbers and add them to get the new one
		//Look at the indexes of all these, 1st and 2nd index is both 1.
		//That's why we return 1 if it's less that or equal to 2
		// 1 1 2 3 5 8 16
		// 1 2 3 4 5 6 7
		if (n <= 2) return 1;
		//If it's greater than 2, we return get(n-1) + get(n-2).
		//Because it could be described as "f(n) = f(n-1)+f(n-2)
		return get(n - 1) + get(n - 2);
	}
	
}
