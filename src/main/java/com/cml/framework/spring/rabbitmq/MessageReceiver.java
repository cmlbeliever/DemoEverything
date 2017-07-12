package com.cml.framework.spring.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MessageReceiver implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("MessageReceiver.onReceiveMessage:" + new String(message.getBody()));
	}

}
