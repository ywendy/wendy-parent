package com.wendy.algorithm.sort;

/**
 * 插入排序
 * 
 * <br>
 * +----------+----+----+----+----+----+----+------------+<br>
 * |原始数组       | 34 | 8  | 64 | 51 | 32 | 21 | 移动的位置    |<br>
 * +----------+----+----+--------------------------------+<br>
 * |p=1趟之后    | 8  | 34 | 64 | 51 | 32 | 21 |     1      |<br>
 * +----------+----+----+----+----+----+----+------------+<br>
 * |p=2趟之后    | 8  | 34 | 64 | 51 | 32 | 21 |     0      |<br>
 * +----------+----+----+----+----+----+----+------------+<br>
 * |p=3趟之后    | 8  | 34 | 51 | 64 | 32 | 21 |     1      |<br>
 * +----------+----+----+----+----+----+----+------------+<br>
 * |p=4趟之后    | 8  | 32 | 34 | 51 | 64 | 21 |     3      |<br>
 * +----------+----+----+----+----+----+----+------------+<br>
 * |p=5趟之后    | 8  | 21 | 32 | 34 | 51 | 64 |     4      |<br>
 * +----------+----+----+----+----+----+----+------------+<br>
 *
 *<br>
 * <strong>插入排序思想：</strong>
 * 
 * <p>
 * 已知位置0到p-1上的元素已经处于排过序的状态，将待排序元素插入到0~p-1 的位置中.
 * 
 * 时间复杂度：O(N^2) 最坏的情况下输入一个逆序的数组，达到O(N^2)
 * 
 * 最好的情况下输入为有序的，时间复杂度可以达到O（N） ==>内层循环进不去，只做比较
 * 
 * @author tony
 *
 */
@SortedMethod("插入排序")
public class InsertSort extends AbstractSorted {
	
	
	@Override
	public void sort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int j;
		for (int p = 1; p < arr.length; p++) {
			int tmp = arr[p];
			for (j = p; j > 0 && arr[j - 1] > tmp; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = tmp;
		}
	}

}
