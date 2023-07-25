package com.recursion.dynamicprogramming.subsequences;

import java.util.Arrays;

//Given two strings text1 and text2, return the length of their longest common subsequence. 
//If there is no common subsequence, return 0.
//
//A subsequence of a string is a new string generated from the original string with some characters 
//(can be none) deleted without changing the relative order of the remaining characters.
//
//For example, "ace" is a subsequence of "abcde".
//A common subsequence of two strings is a subsequence that is common to both strings.

public class FindLengthOfLongestCommonSubsequence {

	public static void main(String[] args) {
		
		String s1 = "degkc";
		String s2 = "abdskdsc";
		
//		In the above case longest common subsequence would be: dkc of length 3.
		
//		Using recursion
//		TC: O(2^n * 2^m) --where n = length of s1, m = length of s2
//		Because we're doing two ops staying at same index and moving to next index.
//		SC: O(n*m)
		System.out.println("Using only recursion: " + findLongestCommonSubsequenceUsingRecursion(s1, s2));
//		Using memoization
//		TC: O(n*m) --where n = length of s1, m = length of s2
//		SC: O(n*m) + O(n + m)
//		Where O(n*m) is for dp array
//		O(n+m) is for recursion stack space
		System.out.println("Using only Memoization: " + findLongestCommonSubsequenceUsingMemoization(s1, s2));
	}

	private static int findLongestCommonSubsequenceUsingRecursion(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		return findUsingRecursion(0, 0, s1.toCharArray(), s2.toCharArray(), n, m);
	}

	private static int findUsingRecursion(int i, int j, char[] s1, char[] s2, int n, int m) {
		if(i == n || j == m)
			return 0;
//		If you find same char at both strings, it means it can be part of your common sequence
//		so add 1 and move both strings to next index.
		if(s1[i] == s2[j]) {
			return 1+findUsingRecursion(i+1, j+1, s1, s2, n, m);
		}
		
//		if chars are different in both strigns
//		then two possibilities
//		1. what if current char of s1 can form subsequence with futre char of s2
//			so stay s1 at same pos and move s2.
//		2. what if current char of s2 can form subsequence with futre char of s1
//			so stay s2 at same pos and move s1.
//		Finally take max of both
		return Math.max(findUsingRecursion(i+1, j, s1, s2, n, m), findUsingRecursion(i, j+1, s1, s2, n, m));
	}
	
	
//	For memoization it is simple just look at changing parameters
//	and initialize a dp array, fill it -1
//	while returning each recursion call memoize it and return
//	when it calls repeated recursion call, then it just uses dp array to get the result, instead
//	of going for further recursion calls
	private static int findLongestCommonSubsequenceUsingMemoization(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		int[][] dp = new int[n][m];
		for(int i = 0; i < n; i ++)
			Arrays.fill(dp[i], -1);
		return findUsingMemoization(0, 0, s1.toCharArray(), s2.toCharArray(), n, m, dp);
	}

	private static int findUsingMemoization(int i, int j, char[] s1, char[] s2, int n, int m, int[][] dp) {
		if(i == n || j == m)
			return 0;
		if(dp[i][j] != -1)
				return dp[i][j];
		if(s1[i] == s2[j]) {
			return dp[i][j] = 1+findUsingMemoization(i+1, j+1, s1, s2, n, m, dp);
		}
		return dp[i][j] = Math.max(findUsingMemoization(i+1, j, s1, s2, n, m, dp), findUsingMemoization(i, j+1, s1, s2, n, m, dp));
	}

}
