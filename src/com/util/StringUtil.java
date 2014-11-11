package com.util;
/**
 * 字符串处理工具类
 * @author AcoreHeng
 * @version 创建时间：2012-10-12 上午10:53:20
 */
public class StringUtil {
	/**
	 * 字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return str==null;
	}
	
	/**
	 * 字符串是否为null或者"" 
	 * @param str
	 * @return 
	 */
	public static boolean isBlank(String str){
		if(isEmpty(str)){
			return false;
		}
		return str.length()<=0;
	}
}
