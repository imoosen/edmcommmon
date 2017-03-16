package com.fiveone.edm.common.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES加密解密工具类
 * DES加密介绍
 * DES是一种对称加密算法，可以用来进行网络数据传输加密,所谓对称加密算法即：加密和解密使用相同密钥的算法。
 * DES加密算法出自IBM的研究，后来被美国政府正式采用，之后开始广泛流传，
 * 但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，24小时内即可被破解。
 * 虽然如此，在某些简单应用中，我们还是可以使用DES加密算法。
 * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月10日 下午5:20:22
 * @version: 1.0
 * @since: JDK1.7
 */
public class DESUtil {

	public DESUtil() {}

	/**
	 * 加密
	 * @param datasource 	待加密字符串
	 * @param password		加密私钥，长度不能够小于8位
	 * @return
	 */
	public static String encrypt(String datasource, String password) {
		if(datasource == null) {
			return null;
		}
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(dks);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return new String(cipher.doFinal(datasource.getBytes()));
			//byte[] bytes = cipher.doFinal(datasource.getBytes());
			//return byte2String(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解密
	 * @param datasource 	待解密字符串
	 * @param password		解密私钥，长度不能够小于8位
	 * @return
	 */
	public static String decrypt(String datasource, String password) {
		if(datasource == null) {
			return null;
		}
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(dks);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作
			return new String(cipher.doFinal(datasource.getBytes()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 二行制转字符串
	 * @param by
	 * @return
	 */
    @SuppressWarnings("unused")
	private static String byte2String(byte[] by) {
		StringBuilder sb = new StringBuilder();
		String stmp = null;
		for (int i = 0; by!=null && i < by.length; i++) {
			stmp = Integer.toHexString(by[i] & 0XFF);
			if (stmp.length() == 1) {
				sb.append('0');
			} else {
				sb.append(stmp);
			}
		}
		return sb.toString().toUpperCase();
	}
    /*public static void main(String[] args) {
    	String pwd = "9588028820109132570743325311898426347857298773549468758875018579537757772163084478873699447306034466200616411960574122434059469100235892702736860872901247123456";
    	System.out.println(DESUtil.decrypt("12312312", pwd));
	}*/
}
