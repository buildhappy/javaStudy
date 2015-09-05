package com.buildhappy.concurrentInPractice.ch14synTool;
/**
 * 当不满足条件时，有界缓存不执行相应的操作
 * 简单
 * @author buildhappy
 *
 */
public class P240GrumpyBoundedBuffer<V> extends P239BaseBoundedBuffer {
	protected P240GrumpyBoundedBuffer(int capacity) {
		super(capacity);
	}
		
	/**
	 * 简单的put和take方法
	 * 缺点用户要捕获异常
	 * @param v
	 * @throws Exception
	 */
	public synchronized void put(V v) throws Exception{
		if(isFull()){
			throw new Exception();
		}
		doPut(v);
	}
	public synchronized V take() throws Exception{
		if(isEmpty()){
			throw new Exception();
		}
		return (V) doTake();
	}
	
	/**
	 * 对take方法的调用
	 * 用户要捕获队列为空的异常，其实队列为空不能算作异常
	 * @throws InterruptedException 
	 */
	public void test() throws InterruptedException{
		while(true){
			try{
				V item = take();
				//对item的使用
				break;
			}catch(Exception e){
				Thread.sleep(1000);
			}
		}
	}
}
