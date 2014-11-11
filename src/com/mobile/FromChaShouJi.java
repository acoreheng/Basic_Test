package com.mobile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class FromChaShouJi {
	private static int count = 0;
	private static int countN = 1;
	private static int currentcount = 0;
	private static int conTryTimes = 0;// 超时尝试
	private static int NoNum = 0;// 若该号码段没有记录则再生成新号码，但最多尝试2次----0-1
	private static StringBuffer Strbuf = new StringBuffer();
	private static int serviceProviderLen = 3;
	private static Properties pr = new Properties();
	static {
		try {
			pr.load(new FileReader("properties"+File.separator+"MobileInfo.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		count = Integer.parseInt(pr.getProperty("count"));
		countN = Integer.parseInt(pr.getProperty("countN"));
		serviceProviderLen = getserviceProviderLen(getserviceProvider());
		System.out.println("count:" + count + "---countN:" + countN
				+ "---serviceProviderLen:" + serviceProviderLen);
	}

	private static String getNum() {
		String num = "" + count;
		if (serviceProviderLen == 3) {
			switch (num.length()) {
			case 1:
				num = "000" + num;
				break;
			case 2:
				num = "00" + num;
				break;
			case 3:
				num = "0" + num;
				break;
			default:
				break;
			}
		} else if (serviceProviderLen == 4) {
			switch (num.length()) {
			case 1:
				num = "00" + num;
				break;
			case 2:
				num = "0" + num;
				break;
			default:
				break;
			}
		}
		return num;// 一共返回7位
	}

	private static String getMobileCardInfo(String MobileNum) {
		String result = "";
		int read;
		char[] c = new char[120];
		try {
			URL url = new URL("http://tool.webmasterhome.cn/mobile.asp?hm="
					+ MobileNum);
			URLConnection con = url.openConnection();
			con.setConnectTimeout(15000);
			InputStreamReader in = new InputStreamReader(url.openStream());
			while ((read = in.read()) != -1) {
				if (read == '该') {
					result = "该手机号码段暂时还没有收录";
					break;
				} else if (read == '您') {
					in.read(c);
					result = new String(c);
					result = result.replaceAll("&nbsp;", "");
					result = result.replaceAll("(邮政编码[:：]\\d+).*", "$1");
					result = result.replaceAll("<br>(\\r?\\n\\s+)*", ",");
					result = result.replaceAll("查询的手机号码段：(\\d+),", "$1=");
					result = result.trim();
					break;
				}
			}
			if (read == -1) {
				result = "查询错误";
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ConnectException e) {
			if (conTryTimes < 2) {
				result = getMobileCardInfo(MobileNum);
				conTryTimes++;
				e.printStackTrace();
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 保存最新的count
	 */
	private static void stroeCount() {
		pr.setProperty("count", count + "");
		pr.setProperty("countN", countN + "");
		try {
			pr.store(new FileWriter("properties"+File.separator+"MobileInfo.properties"),"MobileCard Information");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getserviceProvider() {
		String serviceProvider = "";
		switch (countN) {
		case 1:
			serviceProvider = "130";
			break;
		case 2:
			serviceProvider = "131";
			break;
		case 3:
			serviceProvider = "132";
			break;
		case 4:
			serviceProvider = "133";
			break;
		case 5:
			serviceProvider = "1340";
			break;
		case 6:
			serviceProvider = "1341";
			break;
		case 7:
			serviceProvider = "1342";
			break;
		case 8:
			serviceProvider = "1343";
			break;
		case 9:
			serviceProvider = "1344";
			break;
		case 10:
			serviceProvider = "1345";
			break;
		case 11:
			serviceProvider = "1346";
			break;
		case 12:
			serviceProvider = "1347";
			break;
		case 13:
			serviceProvider = "1348";
			break;
		case 14:
			serviceProvider = "1349";
			break;
		case 15:
			serviceProvider = "135";
			break;
		case 16:
			serviceProvider = "136";
			break;
		case 17:
			serviceProvider = "137";
			break;
		case 18:
			serviceProvider = "138";
			break;
		case 19:
			serviceProvider = "139";
			break;
		case 20:
			serviceProvider = "145";
			break;
		case 21:
			serviceProvider = "147";
			break;
		case 22:
			serviceProvider = "150";
			break;
		case 23:
			serviceProvider = "151";
			break;
		case 24:
			serviceProvider = "152";
			break;
		case 25:
			serviceProvider = "153";
			break;
		case 26:
			serviceProvider = "155";
			break;
		case 27:
			serviceProvider = "156";
			break;
		case 28:
			serviceProvider = "157";
			break;
		case 29:
			serviceProvider = "158";
			break;
		case 30:
			serviceProvider = "159";
			break;
		case 31:
			serviceProvider = "180";
			break;
		case 32:
			serviceProvider = "181";
			break;
		case 33:
			serviceProvider = "182";
			break;
		case 34:
			serviceProvider = "183";
			break;
		case 35:
			serviceProvider = "185";
			break;
		case 36:
			serviceProvider = "186";
			break;
		case 37:
			serviceProvider = "187";
			break;
		case 38:
			serviceProvider = "188";
			break;
		case 39:
			serviceProvider = "189";
			break;
		}
		return serviceProvider;
	}

	private static int getserviceProviderLen(String serviceProvider) {
		return serviceProvider.length();
	}

	private static String generalMobileNum(String serviceProvider) {
		String MobileNum = "";
		// if (serviceProvider.length() == 3) {
		// MobileNum = serviceProvider + FromChaShouJi.getNum() + "46"
		// + random.nextInt(10) + "8";
		// } else if (serviceProvider.length() == 4) {
		// MobileNum = serviceProvider + FromChaShouJi.getNum() + "6"
		// + random.nextInt(10) + "8";
		// }
		if (serviceProvider.length() == 3 || serviceProvider.length() == 4) {
			MobileNum = serviceProvider + FromChaShouJi.getNum() + "46" + NoNum
					+ "8";
		} else {
			System.out.println("error");
			System.out.println(MobileNum);
			System.exit(0);
		}
		return MobileNum;
	}

	public static void storeMobileInfo(String serviceProvider, int num) {

		Write2File wf = new Write2File();
		// System.out.println(MobileNum);
		// System.out.println(FromChaShouJi.getMobileCardInfo(MobileNum));
		while (FromChaShouJi.count <= num) {
			String MobileNum = "";
			// int NoNum = 0;//若该号码段没有记录则再生生新号码，但最多尝试5次//隐藏得很深的错误1，每次循环都是0
			MobileNum = generalMobileNum(serviceProvider);
			System.out.print("serviceProvider:" + serviceProvider);
			System.out.println("---MobileNum:" + MobileNum);
			String result = FromChaShouJi.getMobileCardInfo(MobileNum);
			conTryTimes = 0;// 超时尝试归零
			System.out.println(result);
			if (result == "该手机号码段暂时还没有收录") {
				if (NoNum < 1) {
					// MobileNum=generalMobileNum(serviceProvider);//隐藏得很深的错误2，此处多余
					NoNum++;
					continue;
				} else {
					count++;
					NoNum = 0;
					continue;
				}

			} else if (result == "查询错误") {
				stroeCount();
				if (currentcount > 0) {// 处理跳出while后没有添加入库的数据
					wf.writeToFile(Strbuf.toString(), "MobileInfoText.txt");// 主要要保存Strbuf内的数据
				}
				System.exit(0);
			} else {
				Strbuf.append(result + "\r\n");
				currentcount++;// 添加一条记录
				// 缓存10条记录后入库
				if (currentcount == 10) {
					wf.writeToFile(Strbuf.toString(), "MobileInfoText.txt");
					Strbuf.delete(0, Strbuf.length());
					currentcount = 0;
				}
				count++;
			}
		}
		/*
		 * 3位号码段运营商地区段最多有9999条数据, 4位号码段运营商地区段最多有999条数据
		 */
		if ((serviceProviderLen == 3 && count == 10000)
				|| (serviceProviderLen == 4 && count == 1000)) {
			count = 0;
			countN++;
		}
		if (currentcount > 0) {// 处理跳出while后没有添加入库的数据
			wf.writeToFile(Strbuf.toString(), "MobileInfoText.txt");
		}
		stroeCount();// 主要要保存Strbuf内的数据
	}

	public static void main(String[] args) {
		// System.out.println(getMobileCardInfo("13296024608"));
		// System.out.println(getMobileCardInfo("13296034628"));
		if(FromChaShouJi.countN>39)
			{
				System.out.println("已下载完成,数据保存在程序根目录MobileInfoText.txt,3秒后自动退出.");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		if (serviceProviderLen == 3) {
			storeMobileInfo(getserviceProvider(), 4999);
			System.gc();
			storeMobileInfo(getserviceProvider(), 9999);
		}
		if (serviceProviderLen == 4) {
			storeMobileInfo(getserviceProvider(), 999);
			System.gc();
		}
	}
}
