package com.buildhappy.concurrentInPractice.ch07.taskCancel;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * 通常，中断的使用场景有以下几个：
 * 点击某个桌面应用中的取消按钮时；
 * 某个操作超过了一定的执行时间限制需要中止时；
 * 多个线程做相同的事情，只要一个线程成功其它线程都可以取消时；
 * 一组线程中的一个或多个出现错误导致整组都无法继续时；
 * 当一个应用或服务需要停止时。
 * 
 * @author buildhappy
 *
 */
public class UsageOfInterrupt {
	
	public static void main(String[] args) {
		final Thread fileReadThread = new Thread(){
			public void run(){
				try{
					FileScanner.listFile(new File("c:\\"));
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		};
		new Thread(){
			public void run(){
				while(true){
					String command = FileScanner.readFromConsol();
					System.out.println("command:" + command);
					if("q".equalsIgnoreCase(command)){
						if(fileReadThread.isAlive()){
							fileReadThread.interrupt();
							return;
						}
					}else{
						System.out.println("输入q退出");
					}
				}
			}
		}.start();
		fileReadThread.setName("file reader");
		fileReadThread.start();
	}
}

class FileScanner{
	//扫描文件
	public static void listFile(File f) throws InterruptedException{
		if(f == null) throw new IllegalArgumentException();
		if(f.isFile()){
			System.out.println(f.getName());
			return;
		}
			
		File[] files = f.listFiles();
		
		//如果是目录的话要检测中断
		if(Thread.interrupted()){
			System.out.println("中断于此：" + f.getName());
			throw new InterruptedException("文件扫描任务被中断");
		}
			
		
		if(files != null){
			for(File file : files)
				listFile(file);
		}

	}
	//读取控制台信息
	public static String readFromConsol(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		try{
			return reader.readLine();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	
}