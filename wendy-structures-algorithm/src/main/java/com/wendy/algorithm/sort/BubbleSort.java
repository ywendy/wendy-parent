package com.wendy.algorithm.sort;
/**
 * 冒泡排序 
 * 
 * 每次两辆比较，交换位置，把最大的向上冒泡（移动）
 * @author tony
 *
 */
@SortedMethod("冒泡排序")
public class BubbleSort extends AbstractSorted {
	

	@Override
	public void sort(int[] arr) {

		for (int i = arr.length-1; i >0; --i) {
			
			for (int j = 0; j < i; ++j) {
				if(arr[j+1]<arr[j]){
					arr[j]^=arr[j+1];
					arr[j+1]^=arr[j];
					arr[j] ^= arr[j+1];
				}
			}
			
		}
		
	}

}
