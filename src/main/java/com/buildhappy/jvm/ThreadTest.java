package com.buildhappy.jvm;

public class ThreadTest{
	public static void main(String[] args){
		Thread thread1 = new Thread(new MyThread2(new MyClass(0)));
		Thread thread2 = new Thread(new MyThread2(new MyClass(1)));
		Thread thread3 = new Thread(new MyThread2(new MyClass(2)));
		thread1.start();
		thread2.start();
		thread3.start();
		//System.out.println(Thread.currentThread().getName());
	}
}

class MyThread1 implements Runnable{
	private final int num;
	public MyThread1(int num){
		this.num = num;
	}
	
	public void run(){
		System.out.println(Thread.currentThread().getName() + ":" + num);
	}
}

class MyThread2 implements Runnable{
	private final MyClass myClass;
	public MyThread2(MyClass myClass){
		this.myClass = myClass;
		Thread.currentThread().setName("Thread" + myClass.getNum()+1);
	}
	
	public void run(){
		//synchronized(MyThread2.class){//试试换成this锁
			System.out.println(Thread.currentThread().getName());
			myClass.show();
		//}
	}
}

class MyClass{
	private int num;
	public MyClass(int num){
		this.num = num;
	}
	
	public void show(){
		System.out.println(Thread.currentThread().getName() + ":" + num);
	}
	public int getNum(){
		return num;
	}
}