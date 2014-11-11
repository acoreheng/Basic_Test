package com.test;


/**
 * @author AcoreHeng
 * @version 创建时间：2012-6-26 上午10:53:49
 */
public class TestPurview {
	/**
	 * @param userPurview	用户具有的总权限
	 * @param optPurview	一个操作要求的权限为一个整数
	 * @return
	 */
	public static boolean checkPower(long userPurview, long optPurview){
		long purviewValue= (long)Math.pow(2, optPurview);
		return (userPurview&purviewValue)==purviewValue;
	}
	
	public static void main(String[] args) {
		System.out.println(checkPower(29, 2));
		long sum=0;
		for (long i = 0; i < 100; i++) {
			sum+=(long)Math.pow(2, i);
			System.out.println(i+"++"+sum);
			if(sum<0){
				break;
			}
		}
	}
}
