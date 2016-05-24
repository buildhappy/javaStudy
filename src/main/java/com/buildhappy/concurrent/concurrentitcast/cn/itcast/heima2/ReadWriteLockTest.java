package com.buildhappy.concurrent.concurrentitcast.cn.itcast.heima2;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 用ReadWriteLock工具实现读写锁
 * @author buildhappy
 *
 */
public class ReadWriteLockTest {
	public static void main(String[] args) {
		final Queue3 q3 = new Queue3();
		for(int i=0;i<3;i++){
			//创建3个取数据线程
			new Thread(){
				public void run(){
					while(true){
						q3.get();						
					}
				}
			}.start();
			
			//创建3个写数据线程
			new Thread(){
				public void run(){
					while(true){
						q3.put(new Random().nextInt(10000));
					}
				}		
			}.start();
		}
	}
}

class Queue3{
	private Object data = null;//共享数据，只能有一个线程写该数据，但是可以多个线程读该数据
	ReadWriteLock rwl = new ReentrantReadWriteLock();//reentrant可重入的
	//读数据
	public void get(){
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to read data!");
			Thread.sleep((long)(Math.random()*1000));
			System.out.println(Thread.currentThread().getName() + " have read data :" + data);			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.readLock().unlock();
		}
	}
	
	//写数据
	public void put(Object data){
		rwl.writeLock().lock();//只有在写数据整个操作完成后，该线程才会释放资源
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to write data!");					
			Thread.sleep((long)(Math.random()*1000));
			this.data = data;		
			System.out.println(Thread.currentThread().getName() + " have write data: " + data);					
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.writeLock().unlock();
		}
	}
}
