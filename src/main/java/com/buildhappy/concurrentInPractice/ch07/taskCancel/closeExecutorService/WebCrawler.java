package com.buildhappy.concurrentInPractice.ch07.taskCancel.closeExecutorService;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * P131
 * 该类中将使用TrackingExecutor来执行任务，并且在执行结束后保存未完成的任务以备后续执行
 * @author buildhappy
 *
 */
public abstract class WebCrawler {
	private volatile TrackingExecutor exec;
	private final Set<URL> urlsToCrawler = new HashSet<URL>();
	
	public synchronized void start(){
		exec = new TrackingExecutor(Executors.newCachedThreadPool());
		for(URL url : urlsToCrawler) submitCrawlerTask(url);
		urlsToCrawler.clear();
	}
	
	public synchronized void stop() throws InterruptedException{
		try{
			saveUnCrawled(exec.shutdownNow());//获取已提交但是未执行的任务
			if(exec.awaitTermination(1000, TimeUnit.MILLISECONDS)){
				saveUnCrawled(exec.getCancelledTasks());//获取已执行但未结束的任务
			}
		}finally{
			exec = null;
		}
	}
	
	public abstract List<URL> processPage(URL url);
	
	private void saveUnCrawled(List<Runnable> uncrawled){
		for(Runnable runnable : uncrawled){
			urlsToCrawler.add(((CrawlerTask)runnable).getPageUrl());
		}
	}
	private void submitCrawlerTask(URL u){
		exec.execute(new CrawlerTask(u));
	}
	
	//爬虫任务类
	private class CrawlerTask implements Runnable{
		private final URL url;
		public CrawlerTask(URL url){
			this.url = url;
		}
		public void run(){
			for(URL link : processPage(url)){
				if(Thread.currentThread().isInterrupted())
					return;
				submitCrawlerTask(link);
			}
		}
		public URL getPageUrl(){
			return url;
		}
	}
	
}
