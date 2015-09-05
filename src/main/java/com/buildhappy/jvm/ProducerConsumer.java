package com.buildhappy.jvm;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ProducerConsumer {

	public static void main(String[] args) {
		//公共的数据缓冲区
		LinkedList<String> info = new LinkedList<String>();
		
		//生产者
		Timer timer = new Timer();
		timer.schedule(new Producer(info), 1000 , 1000);
		
		//消费者
		Thread Consumer = new Thread(new Consumer(info));
		Consumer.start();
	}
	
	

}

class Producer extends TimerTask{
	private LinkedList<String> info;
	static private int  i = 10;
	public Producer(LinkedList<String> info){
		this.info = info;
	}
	public void run(){
		//waitingDowloadedApk.addDownloadTask("app" + i, "http://www.7xdown.com/Download.asp?ID=20931&URL=http://d3.7xdown.com/soft/&file=Xilisoft%20iPhone%20Magic_7xdown.com.rar", null, null);
		Random seed = new Random();
		info.add("String" + seed.nextInt());
		System.out.println("added done");
	}
}

class Consumer implements Runnable{
	private LinkedList<String> info;
	public Consumer(LinkedList<String> info){
		this.info = info;
	}
	public void run(){
		while(true){
			if(info.isEmpty()){
				try {
					Thread.sleep(1000);
					continue;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			System.out.println("info:" + info.poll());
		}
	}
}
