package com.buildhappy.concurrent.concurrentInPractice.ch14synTool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock实现信号量
 * @author buildhappy
 *
 */
public class P253SemaphoreOnLock {
	//信号量的值
	private int permits;
	Lock lock = new ReentrantLock();
	//条件谓词：permitsAvailable(permits > 0)
	Condition permitsAvailable = lock.newCondition();
	
	P253SemaphoreOnLock(int initPermits){
		this.permits = initPermits;
	}
	
	//阻塞直到：permitsAvailable
	public void acquire()throws InterruptedException{
		lock.lock();
		try{
			while(permits <= 0)
				permitsAvailable.await();
			permits--;
		}finally{
			lock.unlock();
		}
	}
	
	public void release(){
		lock.lock();
		try{
			++permits;
			permitsAvailable.notify();
		}finally{
			lock.unlock();
		}
	}
	
}





