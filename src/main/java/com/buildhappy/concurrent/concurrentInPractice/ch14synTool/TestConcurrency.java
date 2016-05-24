package com.buildhappy.concurrent.concurrentInPractice.ch14synTool;

/**
 * 测试当线程由于同步而等待资源后的反应:
 * 多个竞争线程会交替获取临界资源；没有获取锁的线程执行thread dump时会显示waiting for monitor entry
 * 注：java的字节码中将锁操作的代码块放在monitor entry和monitor exit之间
 * @author buildhappy
 *
 */
public class TestConcurrency {
	public static void main(String[] args){
		//LinkedBlockingQueue
		Thread thread1 = new Thread(new Runnable(){
			public void run() {
				SynSource syn = new SynSource();
				while(true){
					System.out.println(Thread.currentThread().getName() + ":" + syn.getS());

					//notifyAll();
					//System.out.println(Thread.currentThread().getName() + ":" + "waiting");
				}
			}
		});
		Thread thread2 = new Thread(new Runnable(){
			SynSource syn = new SynSource();
			public void run(){
				while(true){
					System.out.println(Thread.currentThread().getName() + ":" + syn.getS());
					//notifyAll();
				}
			}
		});
		thread1.setName("waitingThread");
		thread2.setName("runningThread");
		thread1.start();
		try {
			//wait();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread2.start();
	}
}

class SynSource{
	private final String s = "hahha";
	public synchronized String getS(){
		return s;
	}
}
