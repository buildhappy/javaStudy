package com.buildhappy.concurrent.concurrentInPractice.ch06.taskExecutor;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.*;

/**
 * 并行的加载图片和文字：
 * 先加载文字，然后使用ExecutorService获取图片信息，将下载的图片信息保存到CompletionService中，一边下载一边渲染
 * 
 * @author buildhappy
 *
 */
public class P107Render {
	private final ExecutorService executor;// = Executors.newFixedThreadPool(5);
	
	public P107Render(ExecutorService executor){
		this.executor = executor;
	}
	
	void renderPage(CharSequence source){
		List<ImageInfo> info = scanForImage(source);
		//将FutureTask存放到LinkedBlockingQueue中，并且多个CompletionService可以共享一个Executor
		CompletionService<ImageData> completionService = new ExecutorCompletionService(executor);
		for(final ImageInfo imageInfo : info){
			completionService.submit(new Callable<ImageData>(){
				public ImageData call() throws Exception {
					return imageInfo.downloadImage();
				}
			});
		}
		
		renderText(source);
		for(int t = 0, n = info.size(); t < n; t++){
			try {
				Future<ImageData> f = completionService.take();
				ImageData image = f.get();
				renderImage(image);
			} catch (InterruptedException e) {
				//重新设置中断状态
				Thread.currentThread().interrupt();
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	//渲染图片信息
	private void renderImage(ImageData image) {
		return;
	}
	
	//加载文字
	private void renderText(CharSequence source) {
		return;
	}

	//get ImageInfo from source
	private List<ImageInfo> scanForImage(CharSequence source) {
		return null;
	}
}

class ImageInfo{
	String name;
	String url;
	public ImageData downloadImage() {
		return null;
	}
}

class ImageData{
	String name;
	InputStream in;
}

