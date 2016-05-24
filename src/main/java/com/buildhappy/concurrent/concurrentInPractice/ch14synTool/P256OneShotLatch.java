package com.buildhappy.concurrent.concurrentInPractice.ch14synTool;

import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 用AbstractQueuedSynchronizer实现二元闭锁
 * @author buildhappy
 *
 */
public class P256OneShotLatch {
	private ReentrantLock lock;
	private ReentrantReadWriteLock readWriteLock;
	private FutureTask futrue;
	//Unsafe unsafe;
	
	private final Sync sync = new Sync();
	public void signal(){
		sync.releaseShared(0);
	}
	
	public void await() throws InterruptedException{
		/*
		 * Acquires in shared mode,aborting if interrupted.
		 * Implemented by first checking interrupt status, 
		 * then invoking at least once tryAcquireShared, returning on success. 
		 * Otherwise the thread is queued, possibly repeatedly blocking and unblocking,
	     * invoking tryAcquireShared until success or the thread
	     * is interrupted.
	     * @param arg the acquire argument.
	     * This value is conveyed to tryAcquireShared but is
	     * otherwise uninterpreted and can represent anything
	     * you like.
	     * */
		sync.acquireSharedInterruptibly(0);
	}
	
	private class Sync extends AbstractQueuedSynchronizer{
		//如果闭锁是开的(getState() == 1)，操作成功，否则失败
		protected int tryAcquireShared(int ignored){
			return (getState() == 1) ? 1 : -1; 
		}
		
		protected boolean tryRelease(){
			setState(1);
			return true;
		}
	}
}
