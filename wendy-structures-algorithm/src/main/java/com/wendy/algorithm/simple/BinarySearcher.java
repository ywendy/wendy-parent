package com.wendy.algorithm.simple;

/**
 * 二分查找，折半查找 .<br/>
 * 找出x在数组中的下标，如果没有找到返回-1
 * 
 * @author tony
 *
 */
public class BinarySearcher {

	public static int binarySearch(int[] arr, int x) {

		int low = 0, heigh = arr.length - 1;

		while (low <= heigh) {

			int mid = (low + heigh) / 2;
			if (arr[mid] > x) {
				heigh = mid - 1;
			} else if (arr[mid] < x) {
				low = mid + 1;
			} else {
				return mid;
			}

		}

		return -1;
	}
	public static void printArray(int[] arr){
		String value = "arr  :  ";
		String index = "index:  ";
		for (int i = 0; i < arr.length; i++) {
			index +=" "+i+",";
			value +=" "+arr[i]+",";
		}
		System.out.println(value);
		System.out.println(index);
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,5,6,8,10,23,45,67};
		printArray(arr);
		System.out.println("binarySearch method result : "+binarySearch(arr, 10));
	}
	

}
