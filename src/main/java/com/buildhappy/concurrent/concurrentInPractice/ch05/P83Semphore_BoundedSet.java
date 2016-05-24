package com.buildhappy.concurrent.concurrentInPractice.ch05;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Semaphore用来控制同时访问某个特定资源的操作数量，或者同时执行否个特定操作的数量。
 * 
 * 此处的应用：限制容器的容量
 * @author buildhappy
 *
 */
public class P83Semphore_BoundedSet<T> {
	private final Set<T> set;
	private final Semaphore sem;

	public P83Semphore_BoundedSet(int bound){
		set = Collections.synchronizedSet(new HashSet<T>());
		sem = new Semaphore(bound);
	}
	
	public boolean add(T o) throws InterruptedException{
		sem.acquire();//信号量加1
		boolean wasAdd = false;
		try{
			wasAdd = set.add(o);
			return wasAdd;
		}finally{
			if(!wasAdd)
				sem.release();
		}
	}
	public boolean remove(T o){
		boolean flag = false;
		flag = set.remove(o);
		if(flag)
			sem.release();
		return flag;
	}
	public static void main(String[] args) {
		
	}

}
