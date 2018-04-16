package com.cml.framework.mq.rabbit;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.client.AMQP.BasicProperties;

public class ListenQuene {
	private final static String QUEUE_NAME = "hello1";

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.99.100");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.basicQos(1);

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
				super.handleShutdownSignal(consumerTag, sig);
				System.out.println("handleShutdownSignal");
			}

			@Override
			public void handleCancelOk(String consumerTag) {
				super.handleCancelOk(consumerTag);
				System.out.println("===handleCancelOk>>>" + consumerTag);
			}

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
				super.handleDelivery(consumerTag, envelope, properties, body);
				String message = new String(body, "UTF-8");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(" [x] Received '" + message + "'" + properties.getMessageId());
			}
		};

		String tag = channel.basicConsume(QUEUE_NAME, true, consumer);

		System.out.println("监听 tag:" + tag);
	}
}
