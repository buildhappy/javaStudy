package com.buildhappy.concurrent.concurrentInPractice.ch06.taskExecutor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {

	public static void main(String[] args) {
		Executor exe = Executors.newFixedThreadPool(5);
		//exe.execute();
		ExecutorService service = Executors.newFixedThreadPool(5);
		List<TaskExample> tasks = Collections.synchronizedList(new LinkedList<TaskExample>());
		for(int i = 0; i < 10; i++){
			tasks.add(new TaskExample());
		}
		while(!tasks.isEmpty()){
			TaskExample task = tasks.get(0);
			tasks.remove(0);
			service.execute(task);
			System.out.println("distribute task");
		}
		System.out.println("service isShutdown:" + service.isShutdown());
		if(service.isTerminated()){
			System.out.println("service Terminated");
			service.shutdown();
		}
		try {
			System.out.println("await:" + service.awaitTermination(5000, TimeUnit.MILLISECONDS));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(service.isShutdown()){
			System.out.println("service Terminated");
			service.shutdown();
		}
		service.shutdown();
		//service.shutdownNow();
		
	}

}

class TaskExample implements Runnable{
	public void run() {
		System.out.println("Perform task");
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}