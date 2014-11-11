package com.crypto;



/**
 * 信息安全实用类
 */
public class SecurityUtil{
	// 用于加密解密的RSA编码类
	private static RSASecurityCoder coder;
	
	/**
	 * 初始化coder的静态构造子
	 */
	static{
		try {
			coder=new RSASecurityCoder();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static RSASecurityCoder getCoder() {
		return coder;
	}
	
	public static String DecyptByPrivateKey(String params) throws Exception{
		return SecurityUtil.getCoder().getDecryptString(params);
	}
	
	public static String EncyptByPublickey(String data,String publicKey) throws Exception{
		return SecurityUtil.getCoder().getEncryptArray(data, publicKey);//公钥加密
	}
	
	
	
	
	
}