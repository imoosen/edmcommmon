package com.fiveone.edm.common.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 和Base64一起结合使用的加密解密工具类
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月11日 下午2:50:58
 * @version: 1.0
 * @since: JDK1.7
 */
public class EncryptUtil {

	// SecretKey 负责保存对称密钥  
    private static SecretKey secreKey;  
    // Cipher负责完成加密或解密工作  
    private static Cipher cipher;  
    // 该字节数组负责保存加密的结果  
    private static byte[] cipherResult;  
  
    public EncryptUtil(String key) {  
    	try {
    		byte[] bytes = Base64Util.decodeBase64(key);
    		secreKey = new SecretKeySpec(bytes, "Blowfish");  
    		cipher = Cipher.getInstance("Blowfish");  
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }  
  
    /** 
     * 对字符串加密  
     * @param str 
     * @return 
     */  
    private static byte[] EncryptByte(String str) {  
    	byte[] bytes = str.getBytes(); 
        try {  
        	 // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式  
        	cipher.init(Cipher.ENCRYPT_MODE, secreKey);  
        	// 加密，结果保存进cipherByte  
        	cipherResult = cipher.doFinal(bytes);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return cipherResult;  
    }  
  
    /** 
     * 对字符串解密  
     * @param bytes 
     * @return 
     */  
    private static byte[] DecodeByte(byte[] bytes) {  
        try {  
        	// 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式  
        	cipher.init(Cipher.DECRYPT_MODE, secreKey);  
        	// 解密，结果保存进cipherByte  
        	cipherResult = cipher.doFinal(bytes);
        } catch (Exception e) {  
            e.printStackTrace();  
        }    
        return cipherResult;  
    }  
  
    /** 
     * 密文cipher，通过Base64Util进行转换成byte[]，然后解密成byte形式的明文，再转换成String 
     * @param base64str_cipher 
     * @return 
     */  
    public static String decodeStr(String base64str_cipher) {  
        // 用Base64Util对String进行转换，转换成byte[]  
        byte[] bytes = Base64Util.decodeBase64(base64str_cipher);  
        // 解密byte形式的密文，转换成了String形式的密文  
        byte[] deByte = DecodeByte(bytes);  
        String str = new String(deByte);  
        return str;  
    }  
  
    /** 
     * 明文text，加密成byte形式的密文，然后用Base64Util进行转换成String 
     * @param text 
     * @return 
     */  
    public static String encryptStr(String text) {  
        // 加密String形式的明文，成了byte形式的密文  
        byte[] bytes = EncryptByte(text);  
        // 用Base64Util对byte进行转换，转换成String  
        String base64str_cipher = Base64Util.encodeBase64(bytes);  
        return base64str_cipher;  
    }  
      
    /** 
     * 生成符合Blowfish的key，String类型，可用于该类的构造函数   
     * @return 
     */  
   private static String GenerateKey() {  
    	String tmp_sdk = null;
    	try{
    		// 实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)  
    		KeyGenerator keygen = KeyGenerator.getInstance("Blowfish");  
    		// 生成密钥  
    		SecretKey secrekey = keygen.generateKey();  
    		tmp_sdk = Base64Util.encodeBase64(secrekey.getEncoded());  
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
        return tmp_sdk;  
    }  
  
   /* *//**
     * 测试加密解密
     * @param args
     * @throws Exception
     *//*
    public static void main(String[] args) throws Exception {  
  
        String tmp_seckey = EncryptUtil.GenerateKey();  
        System.out.println("SecretKet: " + tmp_seckey);  
        EncryptUtil u = new EncryptUtil(tmp_seckey); 
  
        String msg = "hello world";  
        String cipher = EncryptUtil.encryptStr(msg);  
        System.out.println("cipher: " + cipher);  
        String str = EncryptUtil.decodeStr(cipher);  
        System.out.println("plain: " + str);  
  
    }  
  */
}  

