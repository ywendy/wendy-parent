package com.wendy.algorithm.sort;

public class SortedProxy {

	private Class<? extends AbstractSorted> clazz;

	public SortedProxy(Class<? extends AbstractSorted> clazz) {
		this.clazz = clazz;

	}

	public void sort(int[] arr) {
		AbstractSorted sorted = null;
		try {
			sorted = this.clazz.newInstance();
		} catch (Exception e) {
			System.out.println("构造排序方法失败!");
			e.printStackTrace();
			return;
		}

		String sortedAlgorithmMethod = getSortedAlgorithmMethod();
		System.out.println("排序算法:" + ((sortedAlgorithmMethod == null || "".equals(sortedAlgorithmMethod))
				? this.clazz.getName() : sortedAlgorithmMethod));

		System.out.print("排序前：");
		printArray(arr);
		long startTime = System.nanoTime();
		sorted.sorted(arr);
		long endTime = System.nanoTime();
		
		System.out.print("排序后：");
		printArray(arr);
		System.out.println(sortedAlgorithmMethod+" 总耗时："+(endTime-startTime)+" ns");
		System.out.println();

	}

	private String getSortedAlgorithmMethod() {
		SortedMethod method = this.getAnnotation();
		if (method != null) {
			return method.value();
		}
		return null;
	}

	private SortedMethod getAnnotation() {
		if (this.clazz.isAnnotationPresent(SortedMethod.class)) {
			SortedMethod method = this.clazz.getAnnotation(SortedMethod.class);
			return method;
		}
		return null;
	}

	private static void printArray(int[] arr) {

		if (arr == null || arr.length < 1) {
			return;
		}
		String str = "[";
		for (int i = 0; i < arr.length; i++) {
			if (i != arr.length - 1) {
				str += arr[i] + ",";
			} else {
				str += arr[i] + "]";
			}
		}
		System.out.println(str);
	}

}
