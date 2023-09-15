package com.sliding.window;

//Sliding window algo works on putting a window length
//window length can be constant or it might keep changing according to question
//this example is dynamic window problem
//basically we put two pointers indicating window start and window end
//we'll make sure that window length always satisfies the condition


//DESCRIPTION:

//Given an array of positive integers nums and a positive integer target, 
//return the minimal length of a subarray whose sum is greater than or equal to target. 
//If there is no such subarray, return 0 instead.
//
//Example 1:
//
//Input: target = 7, nums = [2,3,1,2,4,3]
//Output: 2
//Explanation: The subarray [4,3] has the minimal length under the problem constraint.
//Example 2:
//
//Input: target = 4, nums = [1,4,4]
//Output: 1
//Example 3:
//
//Input: target = 11, nums = [1,1,1,1,1,1,1,1]
//Output: 0
 

public class MinimumSizeSubarraySum {

	public static void main(String[] args) {
		System.out.println("Answer: " + minSubArrayLen(7, new int[] {2,3,1,2,4,3}));
	}
	
	public static int minSubArrayLen(int target, int[] nums) {
        int start = 0; 
        int running_sum = 0;
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i ++){
        	running_sum += nums[i];
//        	the moment running_sum becomes >= target, then we start calculating the length of subarray
            while(running_sum >= target){
//            	keep calculating length until the running_sum becomes < target.
                ans = Math.min(ans, i - start + 1);
//                and correspondingly reduce running_sum
                running_sum -= nums[start];
//                keep decreasing window start
                start ++;
            }
        }
        return ans == Integer.MAX_VALUE? 0: ans;
    }

}
