package com.buildhappy.concurrentInPractice.ch05;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 闭锁的应用场景二：
 * 将一个问题分成N个部分，用执行每个部分并让锁存器倒计数的Runnable来描述每个部分，
 * 然后将所有Runnable加入到Executor队列。当所有的子部分完成后，协调线程就能够通过await。
 * (当线程必须用这种方法反复倒计数时，可改为使用CyclicBarrier)
 * 
 * @author buildhappy
 *
 */
public class P80CountDonwLatch_2 {
	public static void main(String[] args) throws InterruptedException{
		int count = 5;
		CountDownLatch latch = new CountDownLatch(count);
		Executor executor = Executors.newFixedThreadPool(count);
		for(int i = 0; i < count; i++){
			executor.execute(new Worker(latch , i));
		}
		latch.await();
		System.out.println("work done");
	}
}

class Worker implements Runnable{
	private final int workerNum;
	private final CountDownLatch latch;
	public Worker(CountDownLatch latch , int workerNum){
		this.workerNum = workerNum;
		this.latch = latch;
	}
	public void run() {
		System.out.println("working on task" + workerNum);
		latch.countDown();
	}
	
}


