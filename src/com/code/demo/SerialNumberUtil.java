package com.code.demo;

import java.util.concurrent.TimeUnit;


/**
 * @author AcoreHeng
 * @version 创建时间：2012-10-15 下午4:23:46
 */
public class SerialNumberUtil {
	private final static  String FILEPATH=SerialNumberUtil.class.getResource("/")+"EveryDaySerialNumber.dat";
	 public static void main(String[] args) throws InterruptedException {
	        SerialNumber serial = new FileEveryDaySerialNumber(FILEPATH.indexOf(":"), FILEPATH.substring(5));
	        while(true) {
	            System.out.println(serial.getSerialNumber());
	            TimeUnit.SECONDS.sleep(2);
	        }
	    }
}
