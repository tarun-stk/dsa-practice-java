package com.recursion;

import java.util.LinkedList;
import java.util.List;

public class AllKindOfSubsequenceProblems {
	
	public static void main(String[] args) {
		int target = 4;
		int[] nums = {1, 2, 3, 4, 5, 1, 6, 2, 8};
		printAllSubsequencesWhoseSumEqualsK(nums, target);
		printOnlyOneSubsequencesWhoseSumEqualsK(nums, target);
		System.out.print("Number of subsequences with sum as " + target + " are: ");
		System.out.println(countNumberOfSubsequencesWhoseSumEqualsK(nums, target));
	}
	
	static private void printAllSubsequencesWhoseSumEqualsK(int[] arr, int sum) {
		print(0, 0, arr, sum, new LinkedList<>(), arr.length);
	}
	
	static void print(int idx, int carryingSum, int[] arr, int sum, List<Integer> list, int len) {
		if(idx == len) {
			if(carryingSum == sum) {
				System.out.println(list);
			}
			return;
		}
		list.add(arr[idx]);
		print(idx+1, carryingSum+arr[idx], arr, sum, list, len);
		list.remove(list.size()-1);
		print(idx+1, carryingSum, arr, sum, list, len);
	}
	
	static private void printOnlyOneSubsequencesWhoseSumEqualsK(int[] arr, int sum) {
		print1(0, 0, arr, sum, new LinkedList<>(), arr.length);
	}
	
	static boolean print1(int idx, int carryingSum, int[] arr, int sum, List<Integer> list, int len) {
		if(idx == len) {
			if(carryingSum == sum) {
				System.out.println(list);
				return true;
			}
			return false;
		}
		list.add(arr[idx]);
		if(print1(idx+1, carryingSum+arr[idx], arr, sum, list, len))
			return true;
		list.remove(list.size()-1);
		if(print1(idx+1, carryingSum, arr, sum, list, len))
			return true;
		return false;
	}
	
	static private int countNumberOfSubsequencesWhoseSumEqualsK(int[] arr, int sum) {
		return count(0, 0, arr, sum, arr.length);
	}
	
	static int count(int idx, int carryingSum, int[] arr, int sum, int len) {
		if(idx == len) {
			if(carryingSum == sum) {
				return 1;
			}
			return 0;
		}
		int left = count(idx+1, carryingSum+arr[idx], arr, sum, len);
		int right = count(idx+1, carryingSum, arr, sum, len);
		return left + right;
	}

}