package com.recursion;

import java.util.LinkedList;
import java.util.List;

/*
 * Given an array of distinct integers candidates and a target integer target, 
 * return a list of all unique combinations of candidates where the chosen numbers sum 
 * to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. 
Two combinations are unique if the 
frequency
 of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up 

Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:

Input: candidates = [2], target = 1
Output: []
to target is less than 150 combinations for the given input.
 */
public class CombinationSum {

	public static void main(String[] args) {
		int[] candidates = { 2, 3, 6, 7 };
		int target = 7;
		List<List<Integer>> ans = combinationSum(candidates, target);
		for (List<Integer> li : ans)
			System.out.println(li);
	}

	private static List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> ans = new LinkedList<>();
		rec(candidates, target, 0, 0, new LinkedList(), ans, candidates.length);
		return ans;
	}

	private static void rec(int[] candidates, int target, int s, int idx, List<Integer> list, List<List<Integer>> ans,
			int len) {
//      adding to the result set once the target sum found
		if (s == target) {
			ans.add(new LinkedList(list));
			return;
		}
//		we'll return in case if sum exceeds target or curretn index goes out of length of array
		else if (s > target || idx >= len)
			return;
//      pick the current index;
//      note: we will keep picking the same index until or unless the sum exceeds target or the target being found.
		list.add(candidates[idx]);
		rec(candidates, target, s + candidates[idx], idx, list, ans, len);
//      removing the current index.
		list.remove(list.size() - 1);
//      Calling the next index
		rec(candidates, target, s, idx + 1, list, ans, len);

	}

}
