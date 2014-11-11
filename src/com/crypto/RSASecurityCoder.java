package com.crypto;


import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.util.ArrayUtils;
import com.util.UnicodeUtil;




/**
 * RSA加密解密类
 */
public class RSASecurityCoder{
	// 非对称加密密钥算法
	private static final String Algorithm="RSA";
	private static final String Algorithm_BC="RSA/ECB/PKCS1Padding";
	// 密钥长度，用来初始化
	private static final int Key_Size=1024;
	
	// 公钥
	private final byte[] publicKey;
	
	// 私钥
	private final byte[] privateKey;
	
	
	
	/**
	 * 构造函数，在其中生成公钥和私钥
	 * @throws Exception
	 */
	public RSASecurityCoder() throws Exception{
		// 得到密钥对生成器
		KeyPairGenerator kpg=KeyPairGenerator.getInstance(Algorithm);
		kpg.initialize(Key_Size);
		
		// 得到密钥对
		KeyPair kp=kpg.generateKeyPair();
		
		// 得到公钥
		RSAPublicKey keyPublic=(RSAPublicKey)kp.getPublic();
		publicKey=keyPublic.getEncoded();
		
		// 得到私钥
		RSAPrivateKey keyPrivate=(RSAPrivateKey)kp.getPrivate();
		privateKey=keyPrivate.getEncoded();
	}
	
	/**
	 * 用公钥对字符串进行加密
	 * 
	 */
	public byte[] getEncryptArray(String originalString,byte[] publicKeyArray) throws Exception{
		if(originalString==null)return ArrayUtils.EMPTY_BYTE_ARRAY;
		// 得到公钥
		X509EncodedKeySpec keySpec=new X509EncodedKeySpec(publicKeyArray);
		KeyFactory kf=KeyFactory.getInstance(Algorithm);
		PublicKey keyPublic=kf.generatePublic(keySpec);
		
		// 加密数据
		Cipher cp=Cipher.getInstance(Algorithm_BC);
        cp.init(Cipher.ENCRYPT_MODE, keyPublic);
        
        //return cp.doFinal(originalString.getBytes());
        
        // 加密时超过117字节就报错。为此采用分段加密的办法来加密
        byte[] dataReturn=ArrayUtils.EMPTY_BYTE_ARRAY;
        originalString= UnicodeUtil.gbEncoding(originalString);//转Unicode码
        byte[] data=originalString.getBytes();
        for (int i = 0; i < data.length; i += 116) {  
            byte[] doFinal = cp.doFinal(ArrayUtils.subarray(data, i,  
                    i + 116));  
            dataReturn = ArrayUtils.addAll(dataReturn, doFinal);  
        } 
        
    
        return dataReturn;
	}
	
	public String getEncryptArray(String originalString,String publicKey) throws Exception{
		byte[] publicKeyArray=Base64.decode(publicKey);
        return Base64.encode(getEncryptArray(originalString, publicKeyArray));
	}
	
	
	/**
	 * 使用私钥进行解密
	 * 
	 */
	public String getDecryptString(byte[] encryptedDataArray) throws Exception{
		// 得到私钥
		PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(privateKey);
		KeyFactory kf=KeyFactory.getInstance(Algorithm);
		PrivateKey keyPrivate=kf.generatePrivate(keySpec);
		
		// 解密数据
		Cipher cp=Cipher.getInstance(Algorithm_BC);
        cp.init(Cipher.DECRYPT_MODE, keyPrivate);
        
        // byte[] arr=cp.doFinal(encryptedDataArray);
        //return new String(arr);
        
        // 解密时超过128字节就报错。为此采用分段解密的办法来解密
        StringBuilder sb = new StringBuilder(); 
        byte[] data=encryptedDataArray;
        for (int i = 0; i < data.length; i += 128) {  
            byte[] doFinal = cp.doFinal(ArrayUtils.subarray(data, i,  
                    i + 128));  
            sb.append(new String(doFinal));  
        }  
        // 得到解密后的字符串
		return UnicodeUtil.decodeUnicode(sb.toString());//Unicode解码
	}
	public String getDecryptString(String encryptedData) throws Exception{
		byte[] encryptedDataArray=Base64.decode(encryptedData);
        // 得到解密后的字符串
		return new String(getDecryptString(encryptedDataArray));
	}

	public byte[] getPublicKey() {
		return publicKey;
	}
	
	public String getPublicKeyStr(){
		//return Base64.encode(publicKey);
		return Base64.encode(publicKey);
	}
	
	public static void main(String[] arr) throws Exception{
		String str="你好，世界！ Hello,world!";
		 str = "{'result':true,'information':[{'parking_lot_id':4,'parking_lot_name':'北京某某停车场1','parking_lot_latitude':'40.0969','parking_lot_longitude':'116.328','parking_lot_phone':'13261231126'},{'parking_lot_id':5,'parking_lot_name':'北京朝阳停车场2','parking_lot_latitude':'39.9269','parking_lot_longitude':'116.318','parking_lot_phone':'1'},{'parking_lot_id':6,'parking_lot_name':'北京某某停车场2','parking_lot_latitude':'39.92','parking_lot_longitude':'116.458','parking_lot_phone':'2'},{'parking_lot_id':8,'parking_lot_name':'4停车场2','parking_lot_latitude':'39.83','parking_lot_longitude':'116.258','parking_lot_phone':'4'},{'parking_lot_id':9,'parking_lot_name':'东城区停车场2','parking_lot_latitude':'39.92','parking_lot_longitude':'116.408','parking_lot_phone':'5'},{'parking_lot_id':10,'parking_lot_name':'北京某某停车场3','parking_lot_latitude':'39.71','parking_lot_longitude':'116.558','parking_lot_phone':'6'},{'parking_lot_id':12,'parking_lot_name':'北京某某停车场4','parking_lot_latitude':'39.69','parking_lot_longitude':'116.358','parking_lot_phone':'8'}]}";//要加密的数据
		System.out.println("准备用公钥加密的字符串为："+str);
		
		// 用公钥加密
		RSASecurityCoder rsaCoder=new RSASecurityCoder();
		byte[] publicKey=rsaCoder.getPublicKey();		
		byte[] encryptArray=rsaCoder.getEncryptArray(str, publicKey);
		
		System.out.print("用公钥加密后的结果为:");
        for(byte b:encryptArray){
            System.out.print(b);
        }
        System.out.println();
		
		// 用私钥解密
		String str1=rsaCoder.getDecryptString(encryptArray);
		System.out.println("用私钥解密后的字符串为："+str1);
	}
}