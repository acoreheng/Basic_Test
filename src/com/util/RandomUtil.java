package com.util;

import java.util.Random;

/**
 * 随机数工具类
 * @author AcoreHeng
 * @version 创建时间：2012-10-12 上午10:41:58
 */
public class RandomUtil {
	private static Random random;

	/**
	 * 获取随机整数
	 * @param range
	 * @return
	 */
	public static int getRandomInt(int range) {
		return Math.abs(random.nextInt()) % 10;
	}

	/**
	 * @return
	 */
	public static Random getRandom() {
		return new Random();
	}

	public static Random getRandom(int seed) {
		return new Random(seed);
	}
	public static void main(String[] args) {
		 Random random1 = new Random(100);
         System.out.println(random1.nextInt());
         System.out.println(random1.nextFloat());
         System.out.println(random1.nextBoolean());
         Random random2 = new Random(100);
         System.out.println(random2.nextInt());
         System.out.println(random2.nextFloat());
         System.out.println(random2.nextBoolean());
	}

}
