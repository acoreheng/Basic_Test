package com.util;


/**
 * byte[]数据操作类
 * @author HappyHeng
 *
 */
public class ArrayUtils {
	public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];//空字节数组
	
	/**
	 * 数组截取
	 * @param array
	 * @param startIndexInclusive
	 * @param endIndexExclusive
	 * @return
	 */
	public static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return EMPTY_BYTE_ARRAY;
        }

        byte[] subarray = new byte[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }
	/**
	 * 数组增加
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static byte[] addAll(byte[] array1, byte[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        byte[] joinedArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }
	
	/**
	 * @param array
	 * @return
	 */
	public static byte[] clone(byte[] array) {
        if (array == null) {
            return null;
        }
        return (byte[]) array.clone();
    }
}