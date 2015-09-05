package com.buildhappy.concurrentInPractice.ch14synTool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用显示条件变量的有界缓存
 * @author buildhappy
 *
 */
public class P252ConditionBoundedBuffer<T> {
	private final Lock lock = new ReentrantLock();
	//条件谓词：notFull
	private final Condition notFull = lock.newCondition();
	//条件谓词：notEmpty
	private final Condition notEmpty = lock.newCondition();
	private final T[] items = (T[]) new Object[100];
	private int count , head , tail;
	
	/**
	 * 阻塞并直到：notFull
	 * @param x
	 * @throws InterruptedException
	 */
	public void put(T x) throws InterruptedException{
		lock.lock();
		try{
			while(count == items.length)
				notFull.await();
			items[tail] = x;
			if(++tail == items.length)
				tail = 0;
			count++;
			notEmpty.signal();
		}finally{
			lock.unlock();
		}
	}
	/**
	 * 等待直到：notEmpty
	 * @return
	 * @throws InterruptedException 
	 */
	public T take() throws InterruptedException{
		T t = null;
		lock.lock();
		try{
			while(count == 0)
				notEmpty.await();
			t = items[head];
			if(++head == items.length)
				head = 0;
			--count;
			notFull.signal();
			return t;
		}finally{
			lock.unlock();
		}
	}
}






