package com.digov.api.controller.designpatterns;

import lombok.Data;

@Data
public class Test2 {
	public static void main(String[] args) {
		Test1.num1="2";
		System.out.println(Test1.num1);
	}
}
