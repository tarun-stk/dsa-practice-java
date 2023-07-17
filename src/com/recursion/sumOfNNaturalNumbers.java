package com.recursion;

public class sumOfNNaturalNumbers {

	public static void main(String[] args) {
		int n = 100;
		long ans = sum(n);
		long expected = n * (n + 1) / 2;
		System.out.println("ans:" + ans + " expected: " + expected);
	}

	static private long sum(int n) {
		if (n == 0)
			return 0;
		return n + sum(n - 1);
	}

}
