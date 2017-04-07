package com.stroe.weixin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import com.stroe.weixin.constant.Constant;
/**
 * 机密校验工具栏
 * @author zengjintao
 * 2017年4月七日上午8:05
 */
public class EncryptUtil {

	/**
	 * 参数校验
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public  static String checkSignature(String timestamp,String nonce){
		String str[]={Constant.TOKEN, timestamp, nonce};
		Arrays.sort(str);
		StringBuilder sb=new StringBuilder();
		for(String s:str){
			sb.append(s);
		}
		return getSHA1(sb.toString());
	}
	/**
	 * SHA加密算法
	 * @param key
	 * @return
	 */
	public static String getSHA1(String key){
		try {
	      MessageDigest digest=MessageDigest.getInstance("SHA");
	      byte[] bytes=digest.digest(key.getBytes());
	      //将字节数组转换成16进制字符串
	      return org.apache.commons.codec.binary.Hex.encodeHexString(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * md5加密算法
	 * @param key
	 * @return
	 */
	public static String getMD5(String key){
		try {
		    MessageDigest digest=MessageDigest.getInstance("MD5");
		    byte[] bytes=digest.digest(key.getBytes());
		      //将字节数组转换成16进制字符串
	        return org.apache.commons.codec.binary.Hex.encodeHexString(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
