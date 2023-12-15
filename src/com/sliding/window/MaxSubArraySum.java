package com.sliding.window;

//DESCRIPTION
//Given an integer array nums, find the subarray
// with the largest sum, and return its sum.
//
//SAMPLE OUTPUT
//Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//Output: 6
//Explanation: The subarray [4,-1,2,1] has the largest sum 6.

public class MaxSubArraySum {

	public static void main(String[] args) {
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println("Answer: " + maxSubArray(nums));
	}
	
	 // Kadane's Alogrithim'
//	works by keeping the subarray which only has positive sum
//	if sum becomes less than 0 at anypoint it is no needed to put 
//	in the subarray as going forwards it only decreases the subarray sum
    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i: nums){
            sum += i;
            max = Math.max(max, sum);
            if(sum < 0)
                sum = 0;
            
        }
        return max;
    }

}
