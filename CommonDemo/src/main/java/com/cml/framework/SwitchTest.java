package com.cml.framework;

import java.io.IOException;

public class SwitchTest {
	public static void main(String[] args) throws Exception {
		System.out.println(0.4 - 0.3);
		try {
			throw new Exception("1");
		} catch (IOException e) {
			throw new Exception("2");
		} catch (Exception e) {
			throw new Exception("3");
		} finally {
			throw new Exception("4");
		}
		// int type = 4;
		// switch (type) {
		//
		// case 1:
		// System.out.println(1);
		// case 2:
		// System.out.println(2);
		// default:
		// System.out.println(4);
		// case 3:
		// System.out.println(3);
		// }
	}
}
