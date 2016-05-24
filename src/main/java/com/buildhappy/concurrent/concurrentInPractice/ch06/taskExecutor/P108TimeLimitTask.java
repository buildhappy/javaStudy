package com.buildhappy.concurrent.concurrentInPractice.ch06.taskExecutor;

import java.util.concurrent.*;

/**
 * 场景：
 * 限制的获取广告信息，当规定时间内没有获取，则加载默认的广告
 * 
 * @author buildhappy
 *
 */
public class P108TimeLimitTask {
	static final long TIME_BUGET = 1000;//超时时间1秒
	ExecutorService executor = Executors.newFixedThreadPool(4);
	//在页面中加入广告
	public Page renderPageWithAd(){
		long endTime = System.nanoTime() + TIME_BUGET;
		Future<AD> future = executor.submit(new FetchADTask());
		Page page = renderPageBody();
		AD ad = null;
		long timeLeft = endTime - System.nanoTime();
		try{
			future.get(timeLeft, TimeUnit.MILLISECONDS);
		}catch(TimeoutException e){
			ad = null;
			future.cancel(true);//超时后就不加载广告,并且任务取消
		}catch(ExecutionException e ){
			ad = null;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		page.setAd(ad);
		return null;
	}
	
	//加载页面的主体部分
	private Page renderPageBody() {
		return null;
	}
}

/**
 * 带有广告的页面
 *
 */
class Page{
	String text;
	AD ad;
	//向Page中添加广告
	public void setAd(AD ad2) {
		
	}
}

/**
 * 广告类
 *
 */
class AD{
	String text;
	String url;
}

/**
 * 获取AD
 *
 */
class FetchADTask implements Callable<AD>{

	public AD call() throws Exception {
		return null;
	}
	
}