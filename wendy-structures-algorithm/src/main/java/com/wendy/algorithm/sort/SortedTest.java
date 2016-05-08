package com.wendy.algorithm.sort;
/**
 * 排序算法测试类.
 * @author tony
 *
 */
public class SortedTest {
	 private static int[] arr = {23,4,67,8,89,12,41};
	 private int[] a ;

	 public int[] getArray(){
		 return this.a;
	 }
	 
	public SortedTest(int[] a) {
		this.a = a;
	}

	private  void resetArray(){
		a = new int[]{23,4,67,8,89,12,41};
	}
	
	public static void main(String[] args) {
		SortedTest test = new SortedTest(arr);
		
		SortedProxy insertSort = new SortedProxy(InsertSort.class);
		insertSort.sort(test.getArray());
		test.resetArray();
		SortedProxy bubbleSort = new SortedProxy(BubbleSort.class);
		bubbleSort.sort(test.getArray());
		test.resetArray();
		SortedProxy selectSort = new SortedProxy(SelectSort.class);
		selectSort.sort(test.getArray());
		
		
		test.resetArray();
		SortedProxy shellSort = new SortedProxy(ShellSort.class);
		shellSort.sort(test.getArray());
		
		
		
	}

}
