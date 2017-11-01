package com.cml.framework.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Test {
	public static void main(String[] args) {
		System.out.println(encode(
				"我这是从中文来的，哈哈哈哈哈sddfsdfsd我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈sddfsdfsd我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈sddfsdfsd我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈我这是从中文来的，哈哈哈哈哈",
				"MD5"));
	}

	private static String encode(String str, String method) {
		MessageDigest md = null;
		String dstr = null;
		try {
			md = MessageDigest.getInstance(method);
			md.update(str.getBytes());
			dstr = new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return dstr;
	}

}
