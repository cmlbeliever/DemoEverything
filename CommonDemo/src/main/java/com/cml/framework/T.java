package com.cml.framework;

import org.apache.log4j.DailyRollingFileAppender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class T {
	public static void main(String[] args) {
		new ArrayList<>().forEach(t -> {
			System.out.println(t);
		});
		System.out.println("===========结束=====");
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		String data1 = "EE 04 01 7A 20 4F 41 4F 1C 33 1A 27 18 21 16 40 45 38 12 EF";
		String data2 = "EE 04 02 29 10 30 5B 59 51 5B 49 5B 5B 51 45 57 4B 6E 6C EF";
		String data3 = "EE 04 03 1E 1C 52 63 26 3E 60 16 5E 67 13 61 5C 1E 11 1A EF";
		String data4 = "EE 04 04 0F 39 2C 2D 0B 22 09 16 15 14 13 12 2F 20 7A EF";
		T t = new T();

		System.out.println(t.retrieveData(data1));
		System.out.println(t.retrieveData(data2));
		System.out.println(t.retrieveData(data3));
		// System.out.println(t.retrieveData(data4));
		String realData = t.retrieveData(data4);
		System.out.println(t.decrpt(realData));

	}

	public String decrpt(String msg) {
		int offset = 1;
		StringBuffer sb = new StringBuffer();
		String[] msgArray = msg.split(" ");
		for (int i = 0; i < msgArray.length; i++) {
			int value = Integer.parseInt(msgArray[i], 16);
			char v = (char) (value + offset);
			sb.append(v);
			offset++;
			if (offset > 30) {
				offset = 1;
			}
		}
		return sb.toString();
	}

	private String lastMsgBuilder = "";

	private String retrieveData(String msgs) {
		try {
			if (null == msgs) {
				return null;
			}
			List<String> msg = Arrays.asList(msgs.toUpperCase().split(" "));

			if (msg.size() < 3) {
				return null;
			}

			int dataCount = Integer.parseInt(msg.get(1));
			int currentCount = Integer.parseInt(msg.get(2));

			// msg = msg.subList(3, msg.size() - 1);

			if (lastMsgBuilder.length() >= 0) {
				lastMsgBuilder += " ";
			}
			// 最后一条报文
			if (dataCount == currentCount) {
				String result = lastMsgBuilder + msgs.substring(9, msgs.length() - 3);
				lastMsgBuilder = "";
				return result.trim();
			} else {
				lastMsgBuilder += msgs.substring(9, msgs.length() - 3);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
