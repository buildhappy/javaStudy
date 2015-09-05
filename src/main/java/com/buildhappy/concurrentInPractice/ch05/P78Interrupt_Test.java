package com.buildhappy.concurrentInPractice.ch05;

import java.lang.String;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class P78Interrupt_Test {
	private static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
	public static void main(String[] args) {
		Thread thread = new Thread(){
			public void run(){
				try {
					queue.put("fafa");
					//queue.put("fafa");
					Thread.currentThread().setName("hahaha");
					Thread.currentThread().interrupt();//唯一能将线程中断的方法
					Set<Thread> set = Thread.getAllStackTraces().keySet();
					for(Thread thread : set){
						System.out.println(thread.getName() + ":" + thread.getState().name());
						//thread.interrupt();
					}
					System.out.println(Thread.currentThread().interrupted());
					//静态interrupted方法，测试当前线程是否已经中断。线程的中断状态 由该方法清除。即如果连续两次调用该方法，
					//则第二次调用将返回false（在第一次调用已清除了其中断状态之后，
					//且第二次调用检验完中断状态前，当前线程再次中断的情况除外）
					//而isInterrupted方法只是取回线程当前状态
					System.out.println(Thread.currentThread().interrupted());
				} catch (InterruptedException e) {
					e.printStackTrace();
					//System.out.println(Thread.currentThread().interrupted());
				}
				
				//System.out.println(queue.poll());
			}
		};
		thread.start();
	}

}
