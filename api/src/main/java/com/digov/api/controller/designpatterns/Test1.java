package com.digov.api.controller.designpatterns;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Data;

@Data
public class Test1 {

	public static String num1="1";
	public static String num2="2";
	public static String num3="3";

	static {Test1.num1 = "2";}
	public static void main(String[] args) {

		System.out.println(num1);

	}

}
