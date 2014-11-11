package test;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * key管理器
 * 
 * @author AcoreHeng
 * @version 创建时间：2012-12-14 上午10:09:43
 */
public class KeyUtil {
	
	private static final int[] EVEN={0,2,4,6,8};
	private static final int[] ODD={1,3,5,7,9};
	/**
	 * 获取一个从左到右奇数位为奇数，偶数位为偶数的6位数
	 * @return
	 */
	public static String getKey(){
		Random random=new Random();
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < 6; i++) {
			if(i%2==0){
				sb.append(ODD[random.nextInt(5)]);
			}else{
				sb.append(EVEN[random.nextInt(5)]);
			}
		}
		return sb.toString();
	}
	
	/**
	 * @param key
	 * @return
	 */
	public static boolean keyVerify(String key){
		if(key!=null&&key.length()==6&&Pattern.compile("[0-9]*").matcher(key).matches()){
			boolean flag=false;
			for (int i = 0,len=key.length(); i < len; i++) {
				flag=Short.valueOf(String.valueOf(key.charAt(i)))%2!=i%2;
				if(!flag){
					return false;
				};
			}
			return true;
		}else{
			return false;
		}
		
	}
	public static void main(String[] args) {
		System.out.println(getKey());
			System.out.println(keyVerify(getKey()));
	}
}
