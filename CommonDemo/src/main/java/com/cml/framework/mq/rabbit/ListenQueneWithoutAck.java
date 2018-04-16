package com.cml.framework.mq.rabbit;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * 手动确认消息
 * 
 * @author cml
 *
 */
public class ListenQueneWithoutAck {
	private final static String QUEUE_NAME = "hello2";

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.99.100");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// 消息持久化
		boolean durable = true;
		channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
		channel.basicQos(1);// 同时只允许一条消息进入

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
				System.out.println("handleShutdownSignal");
			}

			@Override
			public void handleCancelOk(String consumerTag) {
				System.out.println("===handleCancelOk>>>" + consumerTag);
			}

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
				System.out.println(" [x] Received '" + message + "'" + properties.getMessageId());

			}
		};

		String tag = channel.basicConsume(QUEUE_NAME, false, consumer);

		System.out.println("监听 tag:" + tag);
	}
}
