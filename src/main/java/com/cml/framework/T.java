package com.cml.framework;

import java.util.Calendar;

public class T {
	public static void main(String[] args) {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.HOUR_OF_DAY, 0);
		// date.add(Calendar.DATE, 1);
		date.add(Calendar.DATE, 6);
		System.out.println(date.getTime());
	}
}
