package com.buildhappy.concurrentInPractice.ch14synTool;

/**
 * 使用简单阻塞实现有界队列
 * 原理：不断查看运行条件(队列是否空，是否满)，如果条件满足运行代码，否则睡眠
 * @author buildhappy
 *
 * @param <V>
 */
public class P241SleepyBoundedBuffer<V> extends P239BaseBoundedBuffer {

	protected P241SleepyBoundedBuffer(int capacity){
		super(capacity);
	}
	public void put(V v) throws InterruptedException{
		while(true){
			synchronized(this){
				if(!isFull()){
					doPut(v);
					return;
				}
			}
			Thread.sleep(1000);
		}
	}
	
	public V take()throws InterruptedException{
		while(true){
			synchronized(this){
				if(!isEmpty()){
					return (V) doTake();
				}
			}
			Thread.sleep(1000);
		}
	}
}
