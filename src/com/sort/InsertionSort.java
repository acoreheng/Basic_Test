package com.sort;

/**
 * The insertion sorting demo in Java.
 * <a href="http://my.oschina.net/arthor" class="referer" target="_blank">@author</a>  Belin Wu
 * @version 1.0 2012-07-29
 */
public class InsertionSort {
	
	/**
	 * Sort the int array by insertion sorting.
	 * @param values the int array for sorting.
	 */
	public static void insertionSort(int[] values) {
		for (int i = 0; i < values.length; i++) {
			int insertedValue = values[i]; // 待插入的数组元素
			
			// 进行数组排序，j作为游标。
			// 当insertedValue元素前还有其他数组元素并且值比它小的时候，交换这两个元素，游标继续向左步进。
			for (int j = i - 1; (j >= 0) && (values[j] > insertedValue); j--) {
				values[j + 1] = values[j];
				values[j] = insertedValue;
			}
		}
	}
	
	/**
	 * Test the insertion sorting.
	 * @param args the console arguments as a string array.
	 */
	public static void main(String[] args) {
		// 初始化数组
		int[] values = {1, -1, 3, 3, 2, 9, -10, 7, 6, 5};

		// 调用方法
		insertionSort(values);
		
		// 打印数组
		for (int i = 0; i < values.length; i++) {
			System.out.println("values[" + i + "] = " + values[i]);
		}
		
		/* 输出结果
		values[0] = -10
		values[1] = -1
		values[2] = 1
		values[3] = 2
		values[4] = 3
		values[5] = 3
		values[6] = 5
		values[7] = 6
		values[8] = 7
		values[9] = 9
		*/
	}
}