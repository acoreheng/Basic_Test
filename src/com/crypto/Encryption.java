package com.crypto;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class Encryption {
	public static final String HEXSTRING = "0123456789ABCDEF";
	/**
	 * MD5加密字符串
	 * @param originalText
	 * @return
	 * @throws Exception
	 */
	public static String md5(String originalText) throws Exception {
		byte buf[] = originalText.getBytes("ISO-8859-1");
		StringBuffer hexString = new StringBuffer();
		String result = "";
		String digit = "";
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(buf);
			byte[] digest = algorithm.digest();
			for (int i = 0; i < digest.length; i++) {
				digit = Integer.toHexString(0xFF & digest[i]);
				if (digit.length() == 1) {
					digit = "0" + digit;
				}
				hexString.append(digit);
			}
			result = hexString.toString();
		} catch (Exception ex) {
			result = "";
		}
		return result.toUpperCase();
	}
	
	/**
	 * @param md5str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String hexchar2bin(String md5str)
			throws UnsupportedEncodingException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				md5str.length() / 2);

		for (int i = 0; i < md5str.length(); i = i + 2) {
			baos.write((HEXSTRING.indexOf(md5str.charAt(i)) << 4 | HEXSTRING
					.indexOf(md5str.charAt(i + 1))));
		}

		return new String(baos.toByteArray(), "ISO-8859-1");
	}
	public static String getPassword(String userName, String password,
			String verifycode) throws Exception {
		String P = hexchar2bin(md5(password));
		String U = md5(P + hexchar2bin(userName.replace("\\x", "").toUpperCase()));
		String V = md5(U + verifycode.toUpperCase());
		return V;
	}
	public static void main(String[] args) {
		try {
			System.out.println(getPassword("12312312", "w23423423", "erwqtrewrt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
