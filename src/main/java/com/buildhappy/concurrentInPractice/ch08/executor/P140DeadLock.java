package com.buildhappy.concurrentInPractice.ch08.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class P140DeadLock {
	private ExecutorService exec = Executors.newSingleThreadExecutor();
	
	public class RenderPageTask implements Callable<String>{
		public String call() throws Exception {
			Future<String> head , foot;
			head = exec.submit(new LoadFileTask("header.html"));
			foot = exec.submit(new LoadFileTask("foot.html"));
			//在此发生死锁--由于任务在等待子任务的结果
			return head.get() + foot.get();
		}
	}
	private class LoadFileTask implements Callable<String>{
		String content;
		public LoadFileTask(String content){
			this.content = content;
		}
		public String call() throws Exception {
			return content;
		}
		
	}
	public static void main(String[] args) {
		
	}

}
