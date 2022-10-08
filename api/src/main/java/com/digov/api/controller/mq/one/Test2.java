package com.digov.api.controller.mq.one;

import java.io.IOException;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class Test2 {
	public static void main(String[] args) throws Exception {
		//连接工厂
		ConnectionFactory f = new ConnectionFactory();
		f.setHost("127.0.0.1");
		f.setUsername("guest");
		f.setPassword("guest");
		//建立连接
		Connection c = f.newConnection();
		//建立信道
		Channel ch = c.createChannel();
		//声明队列,如果该队列已经创建过,则不会重复创建
		ch.queueDeclare("helloworld",false,false,false,null);
		System.out.println("等待接收数据");
		
		//收到消息后用来处理消息的回调对象
		DeliverCallback callback = new DeliverCallback() {
			@Override
			public void handle(String consumerTag, Delivery message) throws IOException {
				String msg = new String(message.getBody(), "UTF-8");
				System.out.println("收到: "+msg);
			}
		};
		
		//消费者取消时的回调对象
		CancelCallback cancel = new CancelCallback() {
			@Override
			public void handle(String consumerTag) throws IOException {
			}
		};
		
		ch.basicConsume("helloworld", true, callback, cancel);
	}
}
