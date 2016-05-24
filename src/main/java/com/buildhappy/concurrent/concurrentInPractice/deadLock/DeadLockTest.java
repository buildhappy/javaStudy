package com.buildhappy.concurrent.concurrentInPractice.deadLock;

/**
 * 线程的死锁
 * @author Administrator
 *
 */
public class DeadLockTest {
	public static void main(String[]args) throws InterruptedException{
		int a = 1 , b = 2;
		for(int i = 0; i < 1000; i++){
			System.out.println("task:" + i);
			//Thread.sleep(1000);
			//原因看《java虚拟机》P95
			//Intege.valueOf(1)在执行装箱的时候，会检测自己的缓存中是否已有该对象，
			//如果有的话会直接获取缓存对象，否则创建
			new Thread(new AddAB(1 , 2) , "thead_a" + i).start();
			new Thread(new AddAB(2 , 1) , "thead_b" + i).start();
		}
		
		//Integer aa = Integer.valueOf(a);
		//System.out.println(aa == new Integer(a));
		//System.out.println(aa == Integer.valueOf(a));
	}
}

class AddAB implements Runnable{
	private int a;
	private int b;
	public AddAB(int a , int b){
		this.a = a;
		this.b = b;
	}
	public void run() {
		synchronized(Integer.valueOf(a)){
			synchronized(Integer.valueOf(b)){
				System.out.println(Thread.currentThread().getName() + ":" + a + b);
			}
		}
	}
}
