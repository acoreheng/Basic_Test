package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author AcoreHeng
 * @version 创建时间：2012-7-9 下午3:39:23
 */
public class Test {
	public static void main(String[] args) {
		int[] is = new int[] { 1, 2, 3, 4, 5, 11, 23, 123 };
		System.out.println(Arrays.binarySearch(is, 12) >= 0);
		List<Integer> aaList = new ArrayList<Integer>();
		aaList.add(new Integer(0));
		aaList.add(new Integer(1));
		aaList.add(new Integer(2));
		aaList.add(1234);
		aaList.add(123);
		aaList.add(123);
		aaList.add(123);
		aaList.add(123);
		aaList.add(123);
		System.out.println(aaList.contains(123 + 23));
		Integer[] iss = aaList.toArray(new Integer[aaList.size()]);
		System.out.println(Arrays.binarySearch(iss, 123) >= 0);
		byte b=-128;
		System.out.println(Integer.toBinaryString(b));
	}
}
