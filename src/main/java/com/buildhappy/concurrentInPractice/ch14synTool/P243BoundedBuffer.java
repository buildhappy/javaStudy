package com.buildhappy.concurrentInPractice.ch14synTool;

/**
 * 使用条件队列，实现有界缓存
 * 原理：不断查看运行条件(队列是否空，是否满)，如果条件满足运行代码，否则阻塞；当条件满足后被唤醒
 * @author buildhappy
 *
 * @param <V>
 */
public class P243BoundedBuffer<V> extends P239BaseBoundedBuffer  {

	protected P243BoundedBuffer(int capacity) {
		super(capacity);
	}
	
	public synchronized void put(V v) throws InterruptedException{
		while(isFull())//isFull()为条件谓词
			wait();
		doPut(v);
		notifyAll();
	}
	
	public synchronized V take() throws InterruptedException{
		while(isEmpty())//isEmpty()为条件谓词
			wait();
		V v = (V) doTake();
		notifyAll();
		return v;
	}
}
