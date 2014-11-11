package com.address;


import cn.idchecker.check.Checker;

public class Test {
	public static void main(String[] args) {
		Checker checker = new Checker("12000019450321432X");
		System.out.println(checker.check()+"   "+checker.getAddr());
		System.out.println("118179650222042".length());
		System.out.println(test(21, 18));
	}
	public static String test(int a,int b){
		System.out.println(a+"="+Integer.toBinaryString(a));
		System.out.println(b+"="+Integer.toBinaryString(b));
		System.out.println((a&b)+"="+Integer.toBinaryString(a&b));
		return Integer.toBinaryString(a&b);
	}
	
}
