package com.cml.framework.guava;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

public class FileTest {
	public static void main(String[] args) throws Exception {
		File file = new File("E:/test.txt");

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 100; i++) {
			sb.append("dataline " + i).append("\n");
		}
		Files.write(sb.toString().getBytes(), file);
		
		//读取文件每行数据，返回最终结果
		String result = Files.asCharSource(file, Charset.forName("utf-8")).readLines(new LineProcessor<String>() {

			private int lineCount;

			@Override
			public boolean processLine(String line) throws IOException {
				System.out.println("line:" + line);
				lineCount++;
				return true;
			}

			@Override
			public String getResult() {
				return "success:" + lineCount;
			}
		});
		System.out.println("result:" + result);
	}
}
