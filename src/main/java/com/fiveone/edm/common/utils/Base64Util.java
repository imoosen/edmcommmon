package com.fiveone.edm.common.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * 基于Apache的Base64加密解密
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月11日 上午10:54:12
 * @version: 1.0
 * @since: JDK1.7
 */
public class Base64Util {
	
	/**
	 * 实现原理：
	 * Base64实际上是对二进制码做分组转换操作
	 * 1.每3个8位二进制码位一组，转换为4个6位二进制码为一组（不足6位时地位补0）。
	 * 	 3个8位二进制码和4个6位二进制码长度都是24位。
	 * 2.对获得的4个6位二进制码补位，每个6位二进制码添加两位高位0，组成4个8位二进制码。
	 * 3.将获得的4个8位二进制码转换为4个十进制码。
	 * 4.将获得的十进制码转换为Base64字符表中对应的字符。 
	 */
	
	/**
	 * 使用Base64加密算法加密字符串 
	 * @param text
	 * @return
	 */
	public static String encodeStr(String text) {  
        byte[] bytes = text.getBytes();  
        byte[] b = new Base64().encode(bytes);  
        String str = new String(b);  
        str = str.replaceAll("\\+", "_");
		str = str.replaceAll("/", "-");
		str = str.replaceAll("=", ".");
		str = str.replaceAll("\\s", "");
		return str;  
    } 
	   
	/**
	 * 使用Base64加密算法加密字符串 
	 */
    public static String encodeBase64(byte[] bytes){  
        byte[] b = new byte[1024];  
        b = Base64.encodeBase64(bytes);  
        return new String(b);  
    }  
	
	/**
	 * 使用Base64解密加密字符串
	 * @param encodeStr
	 * @return
	 */
	public static String decodeStr(String encodeStr) {  
        byte[] bytes = encodeStr.getBytes();  
        byte[] b = new Base64().decode(bytes);  
        String str = new String(b); 
        str = str.replaceAll("_", "+");
		str = str.replaceAll("-", "/");
		str = str.replaceAll("\\.", "=");
        return str;  
    } 
	
	/**
	 * 使用Base64解密加密字符串
	 * @param encodeStr
	 * @return
	 */  
    public static byte[] decodeBase64(String encodeStr) {  
        byte[] bytes = new byte[1024];  
        bytes = Base64.decodeBase64(encodeStr);        
        return bytes;  
    }  
	
    /**
     * 测试加密解密
     * @param args
     *//*
    public static void main(String[] args) {
    	
    	 String str = "中国合伙人";  
         byte[] s = str.getBytes();  
         
         String st = Base64Util.encodeBase64(s);  
         System.out.println("Base64 加密数据：" + st); 
         
         byte[] b = Base64Util.decodeBase64(st);  
         System.out.println("Base64 解密数据：" +new String(b));  
         
         st = Base64Util.encodeStr(str);
         System.out.println("加密后：" + st);
         
         st = Base64Util.decodeStr(st);
         System.out.println("解密后：" + st);
         
	}*/
}
