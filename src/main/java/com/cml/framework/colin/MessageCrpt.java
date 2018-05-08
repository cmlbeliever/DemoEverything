package com.cml.framework.colin;

public class MessageCrpt {
	// public static void main(String[] args) {
	// String message = "{\"SID\":\"qttech\",\"PWD\":\"1234567890\"}";
	//
	// String sb1 = encrpt(message);
	// System.out.println(sb1.toString());
	// String msg = sb1.toString();
	// String sb = decrpt(msg);
	// System.out.println(sb.toString());
	//
	// }

	/**
	 * 解密数据
	 * 
	 * @param msg
	 * @return
	 */
	private static String decrpt(String msg) {
		int offset = 1;
		StringBuffer sb = new StringBuffer();
		String[] msgArray = msg.split(" ");
		for (int i = 0; i < msgArray.length; i++) {
			char v = (char) (Integer.parseInt(msgArray[i], 16) + offset);
			sb.append(String.valueOf(v));
			offset++;
			if (offset > 30) {
				offset = 1;
			}
		}
		return sb.toString();
	}

	/**
	 * 数据加密
	 * 
	 * @param message
	 * @return
	 */
	private static String encrpt(String message) {
		int offset = 1;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < message.length(); i++) {
			char v = message.charAt(i);
			int asciiV = Integer.valueOf(v - offset);
			String realV = Integer.toHexString(asciiV);
			if (realV.length() == 1) {
				realV = "0" + realV;
			}
			sb.append(realV).append(" ");
			offset++;
			if (offset > 30) {
				offset = 1;
			}
		}
		return sb.toString();
	}
}
