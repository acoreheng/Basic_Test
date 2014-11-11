package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/***************************************************************************************************
 * <b>function:</b> 日期工具类：将util.Date日期转换成大写日期格式
 * 
 * @fileName DateUtils.java
 * @createDate 2010-5-27 上午10:24:47
 */
public class DateUtils {
	//TODO 日期格式
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDSS = "yyyyMMddss";
	public static final String HH_MM = "HH:mm";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM_SS_MMM = "yyyy-MM-dd HH:mm:ss:mmm";

	//TODO 格式化
	/**
	 * @return
	 */
	protected static String getDefaultDateFormat() {
		return YYYY_MM_DD_HH_MM_SS;
	}
	/**
	 * 获得默认日期格式
	 */
	protected static DateFormat getDateFormat() {
    	return new SimpleDateFormat(getDefaultDateFormat());
    }
	/**
	 * 获得指定日期格式
	 */
	protected static DateFormat getDateFormat(String format) {
		return new SimpleDateFormat(format);
	}
	/**
	 * 根据日期格式格式化时间
	 */
	protected static String format(String format, Date date) {
		return getDateFormat(format).format(date);
	}
	/**
	 * 获得系统当前时间
	 */
	public static String nowDate() {
		return getDateFormat().format(System.currentTimeMillis());
	}
	/**
	 * 获得系统当前时间
	 */
	public static String nowDate(String df) {
		if (df==null)
			return getDateFormat().format(System.currentTimeMillis());
		else
			return getDateFormat(df).format(System.currentTimeMillis());
	}
	
	//
	/**
	 * 当前时间加上N天
	 */
	public static Date addDay(int days) {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day + days);
		return calendar.getTime();
	}

	/**
	 * 当前时间增加N月
	 */
	public static Date addMonth(int months) {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, day + months);
		return calendar.getTime();
	}
	/**
	 * 时间差计算
	 */
	public static String costTime(long time1, long time2) {
		long sub = time1 - time2;
		// yyyy-MM-dd HH:mm:ss
		String time = "";
		// 多少小时
		long remainder = sub % (3600 * 1000);
		long result = sub / (3600 * 1000);
		if (result < 10) {
			time += "00" + result;
		} else if (result < 100) {
			time += "0" + result;
		} else {
			time += "" + result;
		}
		// 多少分钟
		sub = remainder;
		remainder = sub % (60 * 1000);
		result = sub / (60 * 1000);
		if (result < 10) {
			time += ":0" + result;
		} else {
			time += ":" + result;
		}
		// 多少秒
		sub = remainder;
		result = sub / (1000);
		if (result < 10) {
			time += ":0" + result;
		} else {
			time += ":" + result;
		}

		return time;
	}

	/**
	 * 时间差计算
	 * @param startTime 开始时间
	 * @param minute 限制时间
	 * @return 剩余毫秒数
	 */
	public static long costTime(String startTime, String minute) throws ParseException {
		Date date = getDateFormat(YYYY_MM_DD_HH_MM_SS).parse(startTime);
		long originalTimeMillis = date.getTime();
		long currentTimeMillis = System.currentTimeMillis();
		long minuteTimeMillis = Long.parseLong(minute) * 60 * 1000;
		return minuteTimeMillis - (currentTimeMillis - originalTimeMillis);
	}
	//计算时间
	/**
	 * 时间差计算
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param minute 限制时间
	 * @return 剩余毫秒数
	 */
	public static long costTime(String startTime, String endTime, String minute) throws Exception {
		long originalTimeMillis = convertTimeMillis(startTime);
		long currentTimeMillis = convertTimeMillis(endTime);
		long minuteTimeMillis = Long.parseLong(minute) * 60 * 1000;
		return minuteTimeMillis - (currentTimeMillis - originalTimeMillis);
	}
	
	/**
	 * 将指定时间转换为毫秒数
	 * @param time 指定时间
	 */
	public static long convertTimeMillis(String time) throws Exception {
		try {
			return getDateFormat(YYYY_MM_DD_HH_MM_SS).parse(time).getTime();
		} catch (Exception e) {
			return getDateFormat(getDefaultDateFormat()).parse(time).getTime();
		}
	}
	// TODO 日期转化为大小写
	/**
	 * @param date
	 * @return
	 */
	public static String dateToUpper(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DAY_OF_MONTH);
		return numToUpper(year) + "年" + monthToUppder(month) + "月"
				+ dayToUppder(day) + "日";
	}

	/***
	 * <b>function:</b> 将数字转化为大写
	 * 
	 * @createDate 2010-5-27 上午10:28:12
	 * @param num
	 *            数字
	 * @return 转换后的大写数字
	 */
	public static String numToUpper(int num) {
		// String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
		String u[] = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		// String u[] = {"○", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
		char[] str = String.valueOf(num).toCharArray();
		String rstr = "";
		for (int i = 0; i < str.length; i++) {
			rstr = rstr + u[Integer.parseInt(str[i] + "")];
		}
		return rstr;
	}

	/***
	 * <b>function:</b> 月转化为大写
	 * 
	 * @createDate 2010-5-27 上午10:41:42
	 * @param month
	 *            月份
	 * @return 返回转换后大写月份
	 */
	public static String monthToUppder(int month) {
		if (month < 10) {
			return numToUpper(month);
		} else if (month == 10) {
			return "十";
		} else {
			return "十" + numToUpper(month - 10);
		}
	}

	/***
	 * <b>function:</b> 日转化为大写
	 * 
	 * @createDate 2010-5-27 上午10:43:32
	 * @param day
	 *            日期
	 * @return 转换大写的日期格式
	 */
	public static String dayToUppder(int day) {
		if (day < 20) {
			return monthToUppder(day);
		} else {
			char[] str = String.valueOf(day).toCharArray();
			if (str[1] == '0') {
				return numToUpper(Integer.parseInt(str[0] + "")) + "十";
			} else {
				return numToUpper(Integer.parseInt(str[0] + "")) + "十"
						+ numToUpper(Integer.parseInt(str[1] + ""));
			}
		}
	}
}