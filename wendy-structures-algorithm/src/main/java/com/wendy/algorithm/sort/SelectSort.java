package com.wendy.algorithm.sort;
/**
 * 选择排序
 * 时间复杂度（O(N^2)）
 * @author tony
 *
 */
@SortedMethod("选择排序")
public class SelectSort extends AbstractSorted {

	@Override
	public void sort(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			int tmp ,index = i;//index用来保存最小的那个值的索引，用来和i交换
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[index] > arr[j]) {
					index = j;
				}
			}
			tmp = arr[index];
			arr[index] = arr[i];
			arr[i] = tmp;
	
		
		}

	}

}
