package com.buildhappy.concurrent.concurrentitcast.cn.itcast.heima2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//ExecutorService threadPool = Executors.newFixedThreadPool(3);
		//ExecutorService threadPool = Executors.newCachedThreadPool();
		ExecutorService threadPool = Executors.newSingleThreadExecutor();//单线程是线程池，但是在线程死了后，线程池会自动启动线程
		for(int i=1;i<=10;i++){
			final int task = i;
			threadPool.execute(new Runnable(){
				public void run() {
					for(int j=1;j<=5;j++){
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for  task of " + task);
					}
				}
			});
		}
		System.out.println("all of 10 tasks have committed! ");
		threadPool.shutdown();//shutdown和shutdownNow的区别
		
		//定时线程池
		/*
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(
				new Runnable(){
				public void run() {
					System.out.println("bombing!");
					
				}},
				6,
				2,
				TimeUnit.SECONDS);//没过2秒触发一次任务
		*/
	}

}
