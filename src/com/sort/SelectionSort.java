package com.sort;

/**
 * The selection sorting demo in Java.
 * <a href="http://my.oschina.net/arthor" class="referer" target="_blank">@author</a>  Belin Wu
 * @version 1.0 2012-07-29
 */
public class SelectionSort {
	
	/**
	 * Sort the int array by selection sorting.
	 * @param values the int array for sorting.
	 */
	public static void selectionSort(int[] values) {
		int temp = 0; // 数组元素交换时的临时变量
		
		// 进行数组排序
		for (int i = 0; i < values.length - 1; i++) {
			// 认为数组第i个元素是第i趟中的最小值，记录该索引。
			int minValueIndex = i;
			
			for (int j = i + 1; j < values.length; j++) {
				if (values[minValueIndex] > values[j]) {
					minValueIndex = j; // 之前的假设不成立，重新记录数组中最小值的索引值。
				}
			}
			
			// 当minValueIndex值发生了变化才进行数组元素的交换
			if (minValueIndex != i) {
				temp = values[i];
				values[i] = values[minValueIndex];
				values[minValueIndex] = temp;
			}
		}
	}
	
	/**
	 * Test the selection sorting.
	 * @param args the console arguments as a string array.
	 */
	public static void main(String[] args) {
		// 初始化数组
		int[] values = {1, -1, 3, 3, 2, 9, -10, 7, 6, 5};

		// 调用方法
		selectionSort(values);
		
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