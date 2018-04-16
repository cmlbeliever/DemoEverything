package com.cml.framework.exec;

import java.io.ByteArrayOutputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

/**
 * 系统命令执行处理
 * 
 * @author cml
 *
 */
public class ExecCommandTest {
	public static void main(String[] args) throws Exception {
		System.out.println("-----------------开始测试执行系统指令----------------------------");

//		CommandLine command = new CommandLine("cmd");
//
//		DefaultExecutor executor = new DefaultExecutor();
//
//		// 设置60秒超时，执行超过60秒后会直接终止
//		ExecuteWatchdog watchdog = new ExecuteWatchdog(60 * 1000);
//		executor.setWatchdog(watchdog);
//
//		DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();
//		executor.execute(command, handler);
//
//		// 命令执行返回前一直阻塞
//		handler.waitFor();

		String result = execCmdWithResult();
		System.out.println("执行返回结果：" + result);

	}

	/**
	 * 带返回结果的命令执行
	 * 
	 * @return
	 */
	private static String execCmdWithResult() {
		try {
			String command = "ping 127.0.0.1";
			// 接收正常结果流
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			// 接收异常结果流
			ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
			CommandLine commandline = CommandLine.parse(command);
			DefaultExecutor exec = new DefaultExecutor();
			exec.setExitValues(null);
			// 设置一分钟超时
			ExecuteWatchdog watchdog = new ExecuteWatchdog(15 * 1000);
			exec.setWatchdog(watchdog);
			PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
			exec.setStreamHandler(streamHandler);
			exec.execute(commandline);
			// 不同操作系统注意编码，否则结果乱码
			String out = outputStream.toString("GBK");
			String error = errorStream.toString("GBK");
			return out + error;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
