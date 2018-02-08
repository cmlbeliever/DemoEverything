package com.cml.framework.aes;

public class AESTest {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String pass = "dfudsfhisdfu@##@$$#%^&^12313jjf";
		for (int i = 0; i < 1000; i++) {
			pass = pass + i;
			String s = "hello,所发生的返回了是等待反馈是返回哈是的方收款上打开收费的但是返回换符文的方式的开发您好" + i;
			String s1 = AESUtil.encrypt(s, pass);
			System.out.println("encrypt s1:" + s1);
			System.out.println("decrypt s2:" + AESUtil.decrypt(s1, pass));
		}
		System.out.println(System.currentTimeMillis() - start);
	}

}
