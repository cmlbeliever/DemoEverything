package com.cml.framework.erm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ErmBugfix {
	public static void main(String[] args) throws Exception {
		File file = new File("D:\\svn\\keleqiu\\200_设计\\230_详细设计\\DB设计\\keleqiu.erm");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		String preLine = "";

		File newFile = new File("D:\\svn\\keleqiu\\200_设计\\230_详细设计\\DB设计\\keleqiu-new.erm");
		BufferedWriter write = new BufferedWriter(new FileWriter(newFile));

		int dumplicateCount = 0;

		while (null != (line = reader.readLine())) {
			if (preLine.equals(line)) {
				System.out.println(preLine);
				dumplicateCount++;
				continue;
			}
			write.write(line);
			write.newLine();
			preLine = line;
		}

		reader.close();
		write.close();
		System.out.println("去除重复成功！！共有重复行：" + dumplicateCount);
	}

}
