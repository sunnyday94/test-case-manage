package com.chunmi.testcase.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	private static final BigInteger PRIVATE_D = new BigInteger(
			"3206586642942415709865087389521403230384599658161226562177807849299468150139");
	private static final BigInteger N = new BigInteger(
			"7318321375709168120463791861978437703461807315898125152257493378072925281977");

	private MD5Util() {
	}

	/**
	 * Returns a MessageDigest for the given <code>algorithm</code>.
	 *
	 * The MessageDigest algorithm name.
	 * 
	 * @return An MD5 digest instance.
	 * @throws RuntimeException
	 *             when a {@link NoSuchAlgorithmException} is caught
	 */

	static MessageDigest getDigest() {
		try {
			return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 16 element
	 * <code>byte[]</code>.
	 * 
	 * @param data
	 *            Data to digest
	 * @return MD5 digest
	 */
	public static byte[] md5(byte[] data) {
		return getDigest().digest(data);
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 16 element
	 * <code>byte[]</code>.
	 * 
	 * @param data
	 *            Data to digest
	 * @return MD5 digest
	 */
	public static byte[] md5(String data, String charset) {
		if (charset == null) {
			return md5(data.getBytes());
		} else {
			try {
				return md5(data.getBytes(charset));
			} catch (UnsupportedEncodingException e) {

			}

			return null;
		}
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 32 character hex
	 * string.
	 * 
	 * @param data
	 *            Data to digest
	 * @return MD5 digest as a hex string
	 */
	public static String md5Hex(byte[] data) {
		return HexUtil.toHexString(md5(data));
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 32 character hex
	 * string.
	 * 
	 * @param data
	 *            Data to digest
	 * @return MD5 digest as a hex string
	 */
	public static String md5Hex(String data) {
		return HexUtil.toHexString(md5(data, null));
	}

	/**
	 * 指定编码加密
	 * 
	 * @author lihe 2013-7-4 下午5:27:07
	 * @param data
	 * @param charset
	 * @return
	 * @see
	 * @since
	 */
	public static String md5Hex(String data, String charset) {
		return HexUtil.toHexString(md5(data, charset));
	}

	/**
	 * 解密登录密码
	 * 
	 * @author lihe 2013-7-4 下午5:27:24
	 * @param str
	 * @return
	 * @see
	 * @since
	 */
	public static String getDecryptLoginPassword(String str) {
		byte ptext[] = HexUtil.toByteArray(str);
		BigInteger encryC = new BigInteger(ptext);

		BigInteger variable = encryC.modPow(PRIVATE_D, N);
		// 计算明文对应的字符串
		byte[] mt = variable.toByteArray();
		StringBuffer buffer = new StringBuffer();
		for (int i = mt.length - 1; i > -1; i--) {
			buffer.append((char) mt[i]);
		}

		return buffer.substring(0, buffer.length() - 10).toString();
	}
	
	/***
	 * 
	 * @description: <p class="detail">32位大写加�?</p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">Kimmyzhao</a>
	 * @date: 2017�?3�?3�?-下午4:59:30
	 * @param @param s
	 * @param @return
	 * @return String
	 */
	public static String MD5Encryption(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法�? MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘�?
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
