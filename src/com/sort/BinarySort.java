package com.sort;
/**
 * @author AcoreHeng
 * @version 创建时间：2012-7-31 下午2:02:29
 */
public class BinarySort {
	public static int binarySort(int[] values, int value){
		int low = 0; 
        int high = values.length-1; 
         int temp=0;
        while(low <= high){ 
        	temp = (low+high)/2; //**
                if (values[temp] == value) 
                        return temp; 
                else if (values[temp] > value) 
                        high = temp-1; 
                else 
                        low = temp +1; 
        } 
        return -1; 
	}
	public static void main(String[] args) {
		 int[] values={19,12,3,22,6,7,21,11,43};  
		 System.out.println(binarySort(values, 7)); 
	}
}
