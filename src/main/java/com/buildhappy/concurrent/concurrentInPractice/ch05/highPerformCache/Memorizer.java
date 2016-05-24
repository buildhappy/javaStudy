package com.buildhappy.concurrent.concurrentInPractice.ch05.highPerformCache;

import java.util.concurrent.*;

/**
 * 高效缓存的实现
 * 优点一：线程安全
 * 优点二：能够保证两个线程不重复计算相同的任务(先检测再执行，若没有则添加)
 * 
 * @author buildhappy
 *
 */
public class Memorizer<A , V> implements Computable<A , V>{
	private final ConcurrentHashMap<A , FutureTask<V>> cache = 
			new ConcurrentHashMap<A , FutureTask<V>>();
	private final Computable<A , V> c;
	public Memorizer(Computable<A , V> c){
		this.c = c;
	}
	public V compute(final A arg) throws InterruptedException {
		while(true){
			FutureTask<V> f = cache.get(arg);
			if(f == null){
				FutureTask<V> ft = new FutureTask<V>(
						new Callable<V>(){
							public V call() throws InterruptedException {
								return c.compute(arg);
							}
						});
				//If the specified key is not already associated with a value, 
				//associate it with the given value
				//return:the previous value associated with the specified key,
				//or null if there was no mapping for the key
				f = cache.putIfAbsent(arg, ft);
				//双重检测，防止
				if(f == null){
					f = ft;
					ft.run();
				}
			}
			try{
				return f.get();
			}catch(CancellationException e){
				cache.remove(arg , f);
			}catch(ExecutionException e){
				e.printStackTrace();
			}
		}
	}

	
}
