package com.cml.framework.spring.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

public class MessageReceiver implements ChannelAwareMessageListener {

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		String msg = new String(message.getBody());

		try {
			System.out.println("MessageReceiver.onReceiveMessage:" + msg);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 确认消息
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		}
	}

}
