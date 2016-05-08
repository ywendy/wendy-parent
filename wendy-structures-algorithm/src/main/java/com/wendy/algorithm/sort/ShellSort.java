package com.wendy.algorithm.sort;
/**
 * 希尔排序（发明者：Donald Shell）
 * 时间复杂度：O(N^2)
 * @author tony
 *
 */
@SortedMethod("希尔排序")
public class ShellSort extends AbstractSorted {

	@Override
	public void sort(int[] arr) {
		int j;

		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {

				int tmp = arr[i];
				for (j = i; j >= gap && tmp < arr[j - gap]; j -= gap) {
					arr[j] = arr[j - gap];
				}
				arr[j] = tmp;
			}
		}

	}

}
