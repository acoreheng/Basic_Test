package com.mobile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Write2File {
	private FileWriter writer;
	private static Logger logger = Logger.getLogger("Write2File");

	public void writeToFile(String add, String fileName) {

		// 方案1:读取旧newspecialwords.properties文件的内容，拼接新内容(使用方案)
		// 方案2:不读取旧文件
		try {
			writer = new FileWriter(fileName, true);
			// 坑人的windows系统啊，换行符怎么是\r\n?而不是\n\r
			writer.write(add + "\r\n");
			writer.flush();
		} catch (MalformedURLException e) {
			logger.log(Level.WARNING, "非法的URL");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.log(Level.WARNING,
					"Error: 找不到MobileInfo系统配置文件,或其他程序正在占用该文件而导致无法访问！");
			e.printStackTrace();
		} catch (IOException e) {
			logger.log(Level.WARNING, "IO异常");
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				logger.log(Level.WARNING, "关闭流IO异常");
				e.printStackTrace();
			}
		}

	}

	public void Write(String add) {

		// 方案1:读取旧newspecialwords.properties文件的内容，拼接新内容(不推荐使用方案)
		// 方案2:不读取旧文件
		try {
			String OldInfo = readOldInfo();
			writer = new FileWriter("src/MobileInfo.properties");
			// 坑人的windows系统啊，换行符怎么是\r\n?而不是\n\r
			writer.append(OldInfo + "\r\n" + add);
			writer.flush();
		} catch (MalformedURLException e) {
			logger.log(Level.WARNING, "非法的URL");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.log(Level.WARNING,
					"Error: 找不到MobileInfo系统配置文件,或其他程序正在占用该文件而导致无法访问！");
			e.printStackTrace();
		} catch (IOException e) {
			logger.log(Level.WARNING, "IO异常");
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				logger.log(Level.WARNING, "关闭流IO异常");
				e.printStackTrace();
			}
		}

	}

	private String readOldInfo() {
		StringBuffer OldInfo = new StringBuffer();
		char[] in = new char[32];
		Reader input = null;
		int hasread = 0;
		try {
			input = new FileReader("src/MobileInfo.properties");
			while ((hasread = input.read(in)) > 0) {
				OldInfo.append(new String(in, 0, hasread));
			}
		} catch (FileNotFoundException e) {
			// 注意这里没有找到文件的话会自动创建一个指定的文件，详情请看FileReader源代码
			logger.log(Level.WARNING,
					"Error: 找不到MobileInfo系统配置文件,或其他程序正在占用该文件而导致无法访问！");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return OldInfo.toString();
	}
}
