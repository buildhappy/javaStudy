package com.buildhappy.concurrentInPractice.ch06.taskExecutor;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 使用Timer类来执行定时任务
 * @author buildhappy
 *
 */
public class P102TimerTest {

	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();
		timer.schedule(new Task(), 1);
		Thread.sleep(1000);
		timer.schedule(new Task(), 1);
		Thread.sleep(5000);
	}
}

class Task extends TimerTask{
	@Override
	public void run() {
		throw new RuntimeException();
	}
	
}