package com.buildhappy.concurrent.concurrentitcast.cn.itcast.heima2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition类完成进程间的通信 对TraditionalThreadCommunication.java的改进。
 * 子线程运行10次，接着主线程运行20次，接着又回到子线程运行10次，又主线程运行20次，如此往复5次
 * 
 * @author buildhappy
 *
 */
public class ThreadCommunication_Condition {
	public static void main(String[] args){
		final Business business = new Business();
		//创建的子线程负责完成子线程的任务
		new Thread(
			new Runnable() {
				public void run() {
					for(int i=1;i<=5;i++){
						business.sub(i);
					}
				}
			}
		).start();
		
		//主线程完成主线程的任务
		for(int i=1;i<=5;i++){
			business.main(i);
		}
	}
	
	
	static class Business{
		private Lock lock = new ReentrantLock();//保证互斥
		private Condition conditon = lock.newCondition();//完成线程的通信
		private boolean subCanRun = false;
		
		//子线程
		public void sub(int i){
			lock.lock();
			try{
				while(!subCanRun){
					try {
						conditon.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for(int j=1;j<=10;j++){
					System.out.println("sub thread sequence of " + j + ", loop of " + i);
				}
				subCanRun = false;
				conditon.signal();
			}finally{
				lock.unlock();
			}
		}
		
		//主线程
		public void main(int i){
			//System.out.println("main");
			lock.lock();
			try{
				while(subCanRun){
					try{
						conditon.await();
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				for(int j=1;j<=20;j++){
					System.out.println("main thread sequence of " + j + ", loop of " + i);
				}
				subCanRun = true;
				conditon.signal();
			}finally{
				lock.unlock();
			}
		}
	}
}


