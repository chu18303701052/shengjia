package com.digov.api.controller.mq.two;

import java.util.Scanner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Test1 {
	public static void main(String[] args) throws Exception {
		ConnectionFactory f = new ConnectionFactory();
		f.setHost("127.0.0.1");
		f.setPort(5672);
		f.setUsername("guest");
		f.setPassword("guest");
		
		Connection c = f.newConnection();
		Channel ch = c.createChannel();
		//参数:queue,durable,exclusive,autoDelete,arguments
		ch.queueDeclare("helloworld", false,false,false,null);

		while (true) {
		    //控制台输入的消息发送到rabbitmq
			System.out.print("输入消息: ");
			String msg = new Scanner(System.in).nextLine();
			//如果输入的是"exit"则结束生产者进程
			if ("exit".equals(msg)) {
				break;
			}
			//参数:exchage,routingKey,props,body
			ch.basicPublish("", "helloworld", null, msg.getBytes());
			System.out.println("消息已发送: "+msg);
		}

		c.close();
	}
}
