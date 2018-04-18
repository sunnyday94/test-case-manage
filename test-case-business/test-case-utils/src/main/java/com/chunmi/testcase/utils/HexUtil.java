/**
 * This class was created by sunny. It's distributed as
 * part of the chunmiuc Mod.
 *
 * 版权所有(C) 上海纯米电子科技有限公司 2014-2023
 * Copyright 2014-2023 CHUNMI TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * CHUNMI Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with CHUNMI.
 *
 * File Created @ [2018年3月30日, 下午16:43:24 (CST)]
 */
package com.chunmi.testcase.utils;

public class HexUtil {

	private static final String HEX_CHARS = "0123456789abcdef";

	private HexUtil() {
	}
	

	/**
	 * Converts a byte array to hex string.
	 * @description: <p class="detail"></p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年3月30日-下午4:46:42
	 * @param @param b
	 * @param @return
	 * @return String
	 */
	public static String toHexString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append(HexUtil.HEX_CHARS.charAt(b[i] >>> 4 & 0x0F));
			sb.append(HexUtil.HEX_CHARS.charAt(b[i] & 0x0F));
		}
		return sb.toString();
	}

	/**
	 * Converts a hex string into a byte array.
	 *
	 * @param s
	 *            - string to be converted
	 * @return byte array converted from s
	 */
	public static byte[] toByteArray(String s) {
		byte[] buf = new byte[s.length() / 2];
		int j = 0;
		for (int i = 0; i < buf.length; i++) {
			buf[i] = (byte) ((Character.digit(s.charAt(j++), 16) << 4) | Character.digit(s.charAt(j++), 16));
		}
		return buf;
	}

}
