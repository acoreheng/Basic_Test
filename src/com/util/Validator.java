package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证类
 * 
 * @author AcoreHeng
 * @version 创建时间：2012-10-11 上午11:26:15
 */
public class Validator {
	/**
	 * 电子邮件
	 */
	public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-]\\w+)*(\\.\\w+([-]\\w+)*){1,3}$";// 电子邮箱验证规则
	/**
	 * 经度
	 */
	public static final String LONGITUDE = "^[EW]?((\\d|[1-9]\\d|1[0-7]\\d)[\\s\\-,;°度](\\d|[0-5]\\d)[\\s\\-,;′分](\\d|[0-5]\\d)(\\.\\d{1,2})?[\\s\\-,;\"秒]?$)|(180[\\s\\-,;°度]0[\\s\\-,;′分]0[\\s\\-,;\"秒]?$)";
	/**
	 * 纬度
	 */
	public static final String LATITUDE = "^[NS]?((\\d|[1-8]\\d)[\\s\\-,;°度](\\d|[0-5]\\d)[\\s\\-,;′分](\\d|[0-5]\\d)(\\.\\d{1,2})?[\\s\\-,;\"秒]?$)|(90[\\s\\-,;°度]0[\\s\\-,;′分]0[\\s\\-,;\"秒]?$)";
	public static final String INTEGER = "^-?[1-9]\\d*$";// 整数
	public static final String NAGATIVE_INTEGER = "^-?[1-9]\\d*$";// 负整数
	public static final String NUMBER = "^([+-]?)\\d*\\.?\\d+$";// 数字
	public static final String POSITIVE_INTEGER_0 = "^[1-9]\\d*|0$";//正整数 + 0
	public static final String NAGATIVE_INTEGER_0= "^-[1-9]\\d*|0$";//负整数+0
	public static final String URL="^(https?|ftp):\\/\\/(((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:)*@)?(((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]))|((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?)(:\\d*)?)(\\/((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)+(\\/(([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)*)*)?)?(\\?((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|[\\uE000-\\uF8FF]|\\/|\\?)*)?(\\#((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|\\/|\\?)*)?$";
	public static final String TELEPHONE="^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$";//固定电话
	public static final String MOBILEPHONE="^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+[0-9]{8})$";//手机号
	public static final String NUMBER_LETTER="^[0-9a-zA-Z]+$";//字母数字
	
	/**
	 * 判断是否是邮箱地址 注：当str为null时也是false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		return match(EMAIL, str);
	}
	/**
	 * 是否是个数字包括正负整数,以及0
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str){
		return match(NUMBER, str);
	}
	/**
	 * @param regex
	 *            正则表达式字符串
	 * @param str
	 *            要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	public static boolean match(String regex, String str) {
		if (regex == null || str == null) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * i是否在min和max之间
	 * 
	 * @param i
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean between(int i, int min, int max) {
		return i > min && i < max;
	}

	public static void main(String[] args) {
		String regex = "^(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})~(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})$";// 01:06~02:12
		//System.out.println(match(regex, "01:06~02:12"));
		regex = "^-?(?:(?:180(?:\\.0{1,15})?)|(?:(?:(?:1[0-7]\\d)|(?:[1-9]?\\d))(?:\\.\\d{1,15})?))$";// 经度
		//System.out.println(match(regex, "-176.0023"));
		regex = "^-?(?:90(?:\\.0{1,15})?|(?:[1-8]?\\d(?:\\.\\d{1,15})?))$";// 纬度
		//System.out.println("");
		regex="^[\\w]{2,8}$";
		//System.out.println("23aaa".matches(regex));
		regex="^\\d{4}(/\\d{4})?$";
		//System.out.println(match(regex, "2332/1212"));
		//System.out.println(match(URL, "http://www.baidu.com"));
		
		//英文验证
		regex="^[a-zA-Z0-9][\\sa-zA-Z0-9_]{0,59}$";
		//System.out.println(match(regex, "91s 23"));
		regex="^([a-zA-Z0-9][\\sa-zA-Z0-9_]{1,59})$";
		System.out.println(match(regex, "218 we"));
		
		regex="^.+/main/main_index.action.?$";
		System.out.println(match(regex, "http://127.0.0.1:8020/main/main_index.action?_menuId=4028835e3cf54bd5013cf5503fdc0004"));
	}
}
