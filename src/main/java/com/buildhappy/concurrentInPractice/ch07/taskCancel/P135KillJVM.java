package com.buildhappy.concurrentInPractice.ch07.taskCancel;

public class P135KillJVM {

	public static void main(String[] args) {
		//正常的关闭方法一
		System.exit(1);
		//强行关闭
		//Runtime.getRuntime().halt(1);
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("hahhaa");
	}

}
