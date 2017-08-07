package com.cml.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class FileMd5 {
	public static void main(String[] args) {
		try {

			long startTime = System.currentTimeMillis();
			File file = new File("E:\\MAX三维外观视频.mp4");
			FileInputStream fis = new FileInputStream(file);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = fis.read(buffer, 0, 1024)) != -1) {
				md.update(buffer, 0, length);
			}
			BigInteger bigInt = new BigInteger(1, md.digest());
			System.out.println("文件md5值：" + bigInt.toString(16));
			System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
			fis.close();
			startTime = System.currentTimeMillis();
			System.out.println(getSHA(file));
			System.out.println("origin:TsbxhTKjEYpKMBrcUXxrRRVwCF8=");
			System.out.println("耗时：" + (System.currentTimeMillis() - startTime));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到文件的SHA码,用于校验
	 * 
	 * @param file
	 * @return
	 */
	public static String getSHA(File file) {
		FileInputStream fis = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}
			return Base64.getEncoder().encodeToString(md.digest());
		} catch (IOException ex) {
			return null;
		} catch (NoSuchAlgorithmException ex) {
			return null;
		} finally {
			try {
				fis.close();
			} catch (IOException ex) {
			}
		}
	}

}
