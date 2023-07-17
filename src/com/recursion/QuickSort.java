package com.recursion;

import java.util.Arrays;

/*
 * Quick sort Algo sorts each element called as pivot
 * step 1. in each step pick up an element and call it as pivot and find its correct index in sorted 
 * array.
 * step 2. after finding correct index, choose left portion and right portion from that index
 * and repeat step 1.
 */

public class QuickSort {

	public static void main(String[] args) {
//		int[] arr = { 4, 6, 2, 5, 7, 9, 1, 3 };
		int[] arr = { 9, 9, 9, 8, 2, 3, -6  };
		quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));

	}

	private static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
//			function sort() gives the partition index
//			meaning the correct index of the pivot element in the sorted array
			int partionIndex = sort(arr, low, high);
//			now go to left part of the array from pivot index and sort it. 
			quickSort(arr, low, partionIndex - 1);
//			now go to right part of the array from pivot index and sort it. 
			quickSort(arr, partionIndex + 1, high);
		}
	}

//	This function finds the correct index of the pivot element and returns back
//	pushes all the elements less than pivot to left & 
//	elements greater than pivot to right
	private static int sort(int[] arr, int low, int high) {
//		remember you can pick any element as pivot
//		like low
//		like high
//		like any random element in between low & high
//		picking the pivot element at the low index
		int pivot = arr[low];
		int i = low;
		int j = high;
		while (i < j) {
//			finding an index where element is greater than pivot
			while (arr[i] <= pivot && i < high)
				i++;
//			finding an index where element is less than or equal to pivot
			while (arr[j] > pivot && j > low)
				j--;
//			once found elements less/equal than pivot and greater than pivot and if i<j then time to swap
			if (i < j)
				swap(i, j, arr);
		}
//		At the end of the loop jth index would be the correct index for the pivot element
//		in the sorted array, so swap and return j
		swap(j, low, arr);
		return j;
	}

//	This function just swaps two elements 
	private static void swap(int i, int j, int[] arr) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
