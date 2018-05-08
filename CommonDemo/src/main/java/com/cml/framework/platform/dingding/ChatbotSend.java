package com.cml.framework.platform.dingding;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ChatbotSend {

	public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=c1a1a2cc9730a88de769e2fc85a0591f762dda81073bfc4a334244adf4f736a5a";

	public static void main(String args[]) throws Exception {

		HttpClient httpclient = HttpClients.createDefault();

		HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
		httppost.addHeader("Content-Type", "application/json; charset=utf-8");

		String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"哈哈哈哈 我是自己发送的消息，的烟火\"}}";
		StringEntity se = new StringEntity(textMsg, "utf-8");
		httppost.setEntity(se);

		HttpResponse response = httpclient.execute(httppost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			System.out.println(result);
		}
	}
}