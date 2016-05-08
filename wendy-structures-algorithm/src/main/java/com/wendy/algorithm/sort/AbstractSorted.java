package com.wendy.algorithm.sort;
/**
 * 抽象出打印数组的方法.
 * @author tony
 *
 */
public abstract class AbstractSorted {
	
	
	/**
	 * 排序抽象.
	 */
	public abstract  void sort(int[] arr);
	
	protected void sorted(int[] arr){
		if(arr == null || arr.length<2){
			return ;
		}
		sort(arr);
	}

	

}
