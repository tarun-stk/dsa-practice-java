package com.recursion;

public class CheckIfStringIsPalindromeOrNot {
	
	public static void main(String[] args) {
		String s1 = "tarunnurat";
		String reverse = reverse(s1, 0, s1.length());
		System.out.println(s1.equals(reverse));
		System.out.println(isPalindrome(s1, 0, s1.length()-1));
	}
	
	private static String reverse(String s, int idx, int len) {
		if(idx >= len)
			return "";
		String str = "";
		return str + reverse(s, idx+1, len) + s.charAt(idx);
		
	}
	
	private static boolean isPalindrome(String s, int first, int last) {
		if(first >= last)
				return true;
		if(s.charAt(first) != s.charAt(last))
			return false;
		return isPalindrome(s, first+1, last-1);
	}

}












