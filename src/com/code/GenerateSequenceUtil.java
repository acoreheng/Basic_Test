package com.code;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
public class GenerateSequenceUtil {
 
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);
    private final static Format dateFormat = new SimpleDateFormat("MMddHHmmssS");
    private final static NumberFormat numberFormat = new DecimalFormat("0000");
    private static int seq = 0;
 
    private static final int MAX = 9999;
 
    /**
     * 时间格式生成序列
     * @return String
     */
    public static synchronized String generateSequenceNo() {
 
        Calendar rightNow = Calendar.getInstance();
 
        StringBuffer sb = new StringBuffer();
        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);
        numberFormat.format(seq, sb, HELPER_POSITION);
        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
    	System.out.println(System.currentTimeMillis());
    	/*int a=0;
    	for (int i = 0; i < 10000; i++) {
    		System.out.println(generateSequenceNo());
    		try{ 
    			Thread.sleep(1);
    			a++;
    			}catch(Exception   any){}
    		if(a==2)break;
		}*/
    	for (int i = 0; i < 10; i++) {
    		System.out.println(test1());
		}
    	for (int i = 0; i < 10; i++) {
    		System.out.println(test2());
		}
    	for (int i = 0; i < 10; i++) {
    		System.out.println(test3());
		}
    	for (int i = 0; i < 10; i++) {
    		System.out.println(test4());
		}
	}
    
    public static  String  test1(){
    	Date date=new Date();
    	String result=new SimpleDateFormat("yyyyMMddHHmmss").format(date);//获取时间 14
    	String temp=System.currentTimeMillis()+"";
    	result+=temp.substring(temp.length()-2);//获取微妙后两位
    	return result;
    }
    
    public static String test2(){
    	Date date=new Date();
    	String result=new SimpleDateFormat("yyyyMMdd").format(date);//获取时间 8
    	String temp=System.currentTimeMillis()+"";
    	result+=temp.substring(temp.length()-8);//获取秒后五位
    	return result;
    }
    
    public static String test3(){
    	String[] strs={"A","B","C","D","E","F","G","H","I","J"};
    	Date date=new Date();
    	String result=strs[1]+strs[1]+ new SimpleDateFormat("MMdd").format(date);//获取时间 8
    	String temp=System.currentTimeMillis()+"";
    	result+=temp.substring(temp.length()-8);//获取秒后五位
    	result+=Math.round(Math.random()*100)+"";//获取秒后五位
    	return result;
    }
    
    public static String test4(){
    	Random rd = new java.util.Random(System.currentTimeMillis());
		long num = Math.abs(rd.nextLong());
		System.out.println(num);
		return String.valueOf(num).substring(9);
    }
}
