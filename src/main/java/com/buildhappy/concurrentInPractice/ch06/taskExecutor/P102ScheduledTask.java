package com.buildhappy.concurrentInPractice.ch06.taskExecutor;

import java.util.concurrent.*;

public class P102ScheduledTask {

	public static void main(String[] args) {
		//创建方式
		ScheduledThreadPoolExecutor exe = new ScheduledThreadPoolExecutor(1);
		
		ExecutorService schedulService = Executors.newScheduledThreadPool(1);
		DelayQueue queue;
		FutureTask futureTask = new FutureTask(null);
		
	}

}
