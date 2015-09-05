package com.buildhappy.concurrentInPractice.ch14synTool;

import java.util.concurrent.LinkedBlockingQueue;

public class Test {
	private final int i = 0;
	LinkedBlockingQueue q;
	//PseudoRandom p;
	public void setI(int i){
		//this.i = i;
	}
	public static void main(String[] args)throws InterruptedException{
		while(true){
			System.out.println(Class.class.getClassLoader());
			Thread.sleep(1000);
		}
	}
}
