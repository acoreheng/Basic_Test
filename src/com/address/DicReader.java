package com.address;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;



public class DicReader {
	public static String readAddress(String addrNum) {
		char first = addrNum.charAt(0);
		if ((first == '1') || (first == '2') || (first == '3')
				|| (first == '4') || (first == '5') || (first == '6')) {
			String filePath = first + ".dic";
			String addr = readAddress(filePath, "UTF-8", addrNum);
			return addr;
		}
		return null;
	}

	public static String readAddress(String filePath, String charset,
			String addrNum) {
		String addr = null;
		try {
			InputStream is = DicReader.class.getResourceAsStream(filePath);
			BufferedReader buffReader = new BufferedReader(
					new InputStreamReader(is, charset));
			String line;
			while ((line = buffReader.readLine()) != null) {
				if (addrNum.equals(line.substring(0, 6))) {
					addr = line.substring(7, line.length());
					break;
				}
			}
			buffReader.close();
		} catch (FileNotFoundException e) {
			System.err.println("找到不地址码文件");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("读取地址码文件失败");
			e.printStackTrace();
		}
		return addr;
	}
	public static Map<String, String> readAddress1(String filePath, String charset) {
		Map<String, String> map=new HashMap<String, String>();
		try {
			InputStream is = DicReader.class.getResourceAsStream(filePath);
			BufferedReader buffReader = new BufferedReader(
					new InputStreamReader(is, charset));
			String line;
			while ((line = buffReader.readLine()) != null) {
				map.put(line.substring(0, 6), line.substring(7, line.length()).trim());
			}
			buffReader.close();
		} catch (FileNotFoundException e) {
			System.err.println("找到不地址码文件");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("读取地址码文件失败");
			e.printStackTrace();
		}
		return map;
	}
	
	public static void main(String[] args) {
		Map<String, String> map=DicReader.readAddress1("total.dic", "UTF-8");
		int i=0;
		for (Map.Entry<String, String> e : map.entrySet()) {
			i++;
			System.out.println(i+">>>"+e.getKey()+">>>>"+e.getValue()+">>>>"+e.getValue());
		}
	}
}