package com.buildhappy.concurrentInPractice.ch05;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 用FutureTask来提前加载用户需要的数据
 * 为什么对外提供一个start方法：
 * 由于在构造函数或静态初试方法中启动线程不是一个好的方法，因此单独提供start方法来启动线程。
 * @author buildhappy
 *
 */
public class P81FutureTask_Preloader {
	private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(
			new Callable<ProductInfo>(){
				public ProductInfo call() throws Exception {
					ProductInfo productInfo = new ProductInfo();
					return productInfo;//从数据库或其他地方获取信息(耗时操作)
				}
			});
	private final Thread thread = new Thread(future);//FutureTask可以放在Thread的运行，可以放在Executor中运行
	public void start(){
		thread.start();
	}
	
	public ProductInfo get() throws DataLoadException, InterruptedException{
		try{
			return future.get();
		}catch(ExecutionException e){
			
			Throwable cause = e.getCause();
			if(cause instanceof DataLoadException){
				throw (DataLoadException)cause;
			}else{
				//throw (InterruptedException)cause;
			}
			return null;
		}
	}
	public static void main(String[] args) {
		
	}

}

class ProductInfo{
	public String id;
	public String name;
}

class DataLoadException extends Throwable{
	
}
