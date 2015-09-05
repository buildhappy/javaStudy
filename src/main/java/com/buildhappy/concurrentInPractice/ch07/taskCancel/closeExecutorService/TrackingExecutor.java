package com.buildhappy.concurrentInPractice.ch07.taskCancel.closeExecutorService;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * P130
 * ExecutorService的shutdownNow存在一个局限性，调用该函数会尝试取消正在执行的任务，
 * 并且返回所有已提交尚未开始的任务。
 * 构造该类的目的是为了保存那些已开始但未结束的任务。
 * 二者构成了所有未完成的任务集合。
 * @author buildhappy
 *
 */
public class TrackingExecutor extends AbstractExecutorService{
	private ExecutorService exec;
	//存放已开始，但是未结束的任务
	private final Set<Runnable> tasksCancelledAtShutdown = 
			Collections.synchronizedSet(new HashSet<Runnable>());
	public TrackingExecutor(ExecutorService service){
		this.exec = service;
	}
	//获取没有被执行的任务
	public List<Runnable> getCancelledTasks(){
		if(!exec.isTerminated()){
			throw new IllegalStateException();
		}
		return new ArrayList<Runnable>(tasksCancelledAtShutdown);
	}
	public void shutdown() {
		exec.shutdown();		
	}

	public List<Runnable> shutdownNow() {
		return exec.shutdownNow();
	}

	public boolean isShutdown() {
		return exec.isShutdown();
	}

	public boolean isTerminated() {
		return exec.isTerminated();
	}

	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		
		return exec.awaitTermination(timeout, unit);
	}

	public void execute(final Runnable command) {
		exec.execute(new Runnable(){
			public void run(){
				try{
					command.run();
				}finally{
					if(isShutdown() && Thread.currentThread().isInterrupted()){
						tasksCancelledAtShutdown.add(command);
					}
				}
			}
		});
	}
	
}
