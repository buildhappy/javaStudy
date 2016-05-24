package com.buildhappy.concurrent.concurrentInPractice.deadLock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * java线程饥饿死锁thread starvation lock
 * @author buildhappy
 *
 */
public class ThreadStarvationLock {
	//注意exe是单线程  
	static final ExecutorService exe = Executors.newSingleThreadExecutor();
	
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		//模拟中间人，存放任务结果  
		StringBuilder sb = new StringBuilder();
		
		TaskOne taskOne = new TaskOne(sb);
		TaskTwo taskTwo = new TaskTwo(sb);
		
		//先提交 taskOne， taskOne要等待taskTwo的运行结果  
		Future<String> one = exe.submit(taskOne);
		//再把taskTwo放入线程池运行  
		Future<String> two = exe.submit(taskTwo);
		
        // 获取taskOne结果，但如果exe是个单线程的线程池  
        // taskOne等待taskTwo的结果，可taskTwo却要等到taskOne运行结束  
        // 才可以运行，造成了死锁，taskTwo永远不能获得cpu的运行,成饥饿状态 
		System.out.println(one.get());
		System.out.println(two.get());
		
		
	}
}

class TaskOne implements Callable<String>{
	// 模拟中间人，存放任务结果  
	private StringBuilder sb;
	public TaskOne(StringBuilder sb){
		this.sb = sb;
	}
	
	public String call() throws InterruptedException{
		synchronized(sb){
            //这个任务要等另一个任务完成 
            //中间人没有获取到另一个任务的结果的时候,此任务要一直等待  
			while(sb.length() == 0){
				//Thread.currentThread().wait();
				sb.wait();
			}
			sb.append("哈哈，task one终于等到task two运行完成了~~ task one结束");
		}
		return sb.toString();
	}
}

class TaskTwo implements Callable<String>{
	private StringBuilder sb;
	public TaskTwo(StringBuilder sb){
		this.sb = sb;
	}
	
	public String call(){
		synchronized(sb){
			sb.append("task two result");
			sb.notify();
		}
		return sb.toString();
	}
}












