package com.recursion;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int[] arr = {3, 1, 2, 4, 1, 5, 2, 6, 4};
//		mergeSort(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
		Implementation imp = new Implementation();
		int[] ans = new int[1];
		imp.mergeSort(arr, ans);
		System.out.println("Answer: " + ans[0]);
		System.out.println(Arrays.toString(arr));
	}
	
	static private void mergeSort(int[] arr, int low, int high) {
		if(low == high)
			return;
		int mid = (low+high)/2;
		mergeSort(arr, low, mid);
		mergeSort(arr, mid+1, high);
		merge(arr, low, mid, high);
	}
	
	static private void merge(int[] arr, int low, int mid, int high) {
		int left = low;
		int right = mid+1;
		int[] temp = new int[high-low+1];
		int i = 0;
		while(left <= mid && right <= high) {
			if(arr[left] <= arr[right]) {
				temp[i ++] = arr[left ++];
			}
			else
				temp[i ++] = arr[right ++];
		}
		while(left <= mid) {
			temp[i ++] = arr[left ++];
		}
		while(right <= high) {
			temp[i ++] = arr[right ++];
		}
		for(int j = 0; j < temp.length; j ++) {
			arr[low + j] = temp[j];
		}
	}
	
}

/*
 * Application:
 * Problem link:
 * https://www.codingninjas.com/studio/problems/number-of-inversions_6840276?utm_source=youtube&utm_medium=affiliate
 * 
 * 
 */


class Implementation{
	
	public void mergeSort(int[] arr, int[] ans) {
		breakdown(arr, 0, arr.length-1, ans);
	}
	
	private void breakdown(int[] arr, int low, int high, int[] ans) {
		if(low == high)
			return;
		int mid = (low + high)/2;
		breakdown(arr, low, mid, ans);
		breakdown(arr, mid+1, high, ans);
		merge(arr, low, mid, high, ans);
	}
	
	private void merge(int[] arr, int low, int mid, int high, int[] ans) {
		int[] temp = new int[high-low+1];
		int h = high;
		int l = low;
		int m1 = mid+1;
		int i = 0;
		while(l <= mid && m1 <= h) {
			if(arr[l] <= arr[m1]) {
				temp[i] = arr[l];
				l ++;
			}
			else {
				System.out.println("entered: "+ (mid-low+1));
				ans[0] += (mid-low+1);
				temp[i] = arr[m1];
				m1 ++;
			}
			i ++;
		}
		while(l <= mid) {
			temp[i] = arr[l];
			l ++;
			i ++;
		}
		while(m1 <= h) {
			temp[i] = arr[m1];
			m1 ++;
			i ++;
		}
		for(int j = 0; j < temp.length; j ++) {
			arr[low ++] = temp[j];
		}
		
		
	}
}


















