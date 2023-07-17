package com.recursion;

public class Fibonacci {

	public static void main(String[] args) {
		int n = 20;
		long result = fibonacci(n);
		System.out.println(result);
	}
	
	private static int fibonacci(int n) {
		if(n <= 1)
			return n;
		return fibonacci(n-1) + fibonacci(n-2);
	}
	
}
