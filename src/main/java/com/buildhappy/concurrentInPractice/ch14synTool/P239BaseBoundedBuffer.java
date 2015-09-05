package com.buildhappy.concurrentInPractice.ch14synTool;

/**
 * 有界缓存的基类
 * @author buildhappy
 *
 * @param <V>
 */
public abstract class P239BaseBoundedBuffer<V> {
	//@GuardedBy("this")
	private final  V[] buf;
	//@GuardedBy("this")
	private int tail;
	private int head;
	private int count;
	
	protected P239BaseBoundedBuffer(int capacity){
		this.buf = (V[]) new Object[capacity];
	}
	
	protected synchronized final void doPut(V v){
		buf[tail] = v;
		if(++tail == buf.length){
			tail = 0;
		}
		++count;
	}
	
	protected synchronized final V doTake(){
		V v = buf[head];
		buf[head] = null;
		if(++head == count)
			head = 0;
		--count;
		return v;
	}
	
	public synchronized final boolean isFull(){
		return count == buf.length;
	}
	
	public synchronized final boolean isEmpty(){
		return count == 0;
	}
}

