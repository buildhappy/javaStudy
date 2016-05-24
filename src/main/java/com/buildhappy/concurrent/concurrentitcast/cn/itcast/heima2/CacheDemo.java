package com.buildhappy.concurrent.concurrentitcast.cn.itcast.heima2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存系统伪代码
 * @author buildhappy
 *
 */
public class CacheDemo {

	private Map<String, Object> cache = new HashMap<String, Object>();
	public static void main(String[] args) {
		
	}

	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	
	//用户提交key从本地容器(cache)中查找，如果存在则直接返回，否则从内存中获取
	public Object getData(String key){
		rwl.readLock().lock();
		Object value = null;
		try{
			value = cache.get(key);
			if(value == null){
				rwl.readLock().unlock();//如果缓存中没有该数据，释放读锁，供其他线程使用。
				rwl.writeLock().lock();//获取写锁，因为缓存会从内存中获取该数据，在将该数据返回的同时写入到缓存中，因此要用写锁。
				try{
					if(value==null){
						value = "aaaa";//实际是去查询内存
					}
				}finally{
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		}finally{
			rwl.readLock().unlock();
		}
		return value;
	}
}
