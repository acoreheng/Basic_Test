package com.sort;

/**
 * The bubble sorting demo in Java.
 * 
 * @author AcoreHeng
 * @version 创建时间：2012-7-31 下午1:47:48
 */
public class BubbleSort {
	public static void bubbleSort(int[] values) {
		boolean isOrdered = true;// 认为数组是有序的
		int temp = 0;// 数组元素交换时的临时变量
		for (int i = 0, len = values.length - 1; i < len; i++) {
			for (int j = 0; j < len - 1-i; j++) {
				if (values[j] > values[j + 1]) {
					temp = values[j];
					values[j] = values[j + 1];
					values[j + 1] = temp;
					isOrdered = false;// 发生元素交换，数组是无序的。
				}
			}
			// 判断是否可以结束数组排序
			if (!isOrdered) {
				isOrdered = true;// 再次认为数组是有序的
			} else {
				break;// 跳出外层for循环
			}
		}
	}

	public static void main(String[] args) {
		// 初始化数组
		//int[] values = { 1, -1, 3, 3, 2, 9, -10, 7, 6, 5 };
		int[] values = { 1, 2,3,4 };
		// 调用方法
		bubbleSort(values);

		// 打印数组
		for (int i = 0; i < values.length; i++) {
			System.out.println("values[" + i + "] = " + values[i]);
		}

	}
}
