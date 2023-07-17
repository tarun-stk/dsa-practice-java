package com.recursion;

import java.util.LinkedList;
import java.util.List;

public class PrintAllUniqueSubsequencesWhenAllElementsInArrayAreUnique {

	/*
	 * Subsequence is a contiguous or non-contiguous sequences of the given 
	 * array which follows order.
	 * ex: {1,2,3,4,5}
	 * valid subsequence: {} {1,2,4,5} {1,4} {1,3,5}
	 * invalid subsequence: {2,1} {3,5,4} -> because 2 came before 1.
	 * 										and 5 came before 4.
	 * 		which are not following order.
	 * 
	 * 
	 * Note: This logic works only when elements are unique./
	 * If duplicate elements are there, then this will not print unique subsequences, it can 
	 * print repeated subsequences.
	 * for ex: {1, 2, 2}
	 * ans can contain {1, 2} & {1, 2} -> where these not unique.
	 * */
	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4, 5};
		List<List<Integer>> ans = subsets(nums);
		for(List<Integer> li: ans) {
			System.out.println(li);
		}
	}

	static public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        subsequences(ans, new LinkedList(), nums, 0, nums.length);
        return ans;
    }
	
//	pick & non-pick approach
    static private void subsequences(List<List<Integer>> ans, List<Integer> list, int[] nums, int idx, int len){
        if(idx >= len){
            ans.add(new LinkedList(list));
            return;
        }
//      picking up the current element and going for the picked call
        list.add(nums[idx]);
        subsequences(ans, list, nums, idx+1, len);
        list.remove(list.size()-1);
//      After removing the current element going for the non picked call.
        subsequences(ans, list, nums, idx+1, len);
    }
	
}