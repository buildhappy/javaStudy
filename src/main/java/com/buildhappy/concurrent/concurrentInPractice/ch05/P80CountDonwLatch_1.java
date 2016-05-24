package com.buildhappy.concurrent.concurrentInPractice.ch05;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁的应用场景一：
 * 协调一组线程的工作顺序。
 * 每个线程首先要在起始门上等待，确保所有的线程都就绪后才开始执行。
 * 而每个线程要做的最后一件事是调用countdown方法减1，这使主线程高效地等待所有工作线程都执行完成，
 * 因此可以统计工作时间。
 * @author buildhappy
 *
 */
public class P80CountDonwLatch_1 {
	public static long countTime(int threadNum , final Runnable task) throws InterruptedException{
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch stopGate = new CountDownLatch(threadNum);
		for(int i = 0; i < threadNum; i++){
			new Thread(){
				public void run(){
					try {
						startGate.await();//确保主线程准备就绪
						try{
							task.run();
						}finally{
							stopGate.countDown();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		long startTime = System.nanoTime();
		startGate.countDown();
		stopGate.await();
		return System.nanoTime() - startTime;
	}
	
	public static void main(String[] args) throws InterruptedException{
		System.out.println(countTime(5 , new Task()));
	}
}
//29133

class Task implements Runnable{
	public void run() {
		System.out.println("Thread:" + Thread.currentThread().getName());
	}
}
