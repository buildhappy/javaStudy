package com.buildhappy.concurrent.concurrentInPractice.ch07.taskCancel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 任务的中断
 * @author buildhappy
 *
 */
public class P112PrimeGenerator {
	//具体用法
	public static void main(String[] args) {
		/*方法一的测试：
		PrimeGenerator_1 primeG = new PrimeGenerator_1();
		new Thread(primeG).start();
		
		try{
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			primeG.cancel();
			
		}
		*/
		/*方法二的测试：*/
		PrimeGenerator_2 primeG = new PrimeGenerator_2();
		primeG.start();
		try{
			TimeUnit.NANOSECONDS.sleep(1000);
			TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			primeG.cancel();
		}
		
		List<BigInteger> results = primeG.get();
		for(BigInteger bigInt : results){
			System.out.println(bigInt.intValue());
		}
	}

}

/**
 * 使用自定义的中断机制来取消任务
 * 弊端：当调用的方法被阻塞时，线程将无法中断
 */
class PrimeGenerator_1 implements Runnable{
	private final List<BigInteger> primes = new ArrayList<BigInteger>();
	private volatile boolean cancelled;
	
	public void run() {
		BigInteger p = BigInteger.ONE;
		while(!cancelled){
			p = p.nextProbablePrime();
			synchronized(this){
				primes.add(p);
			}
		}
	}
	
	public void cancel(){
		this.cancelled = true;
	}
	
	public synchronized List<BigInteger> get(){
		return new ArrayList<BigInteger>(primes);
	}
}

/**
 * 
 * @author Administrator
 *
 */
class PrimeGenerator_2 extends Thread{
	private final BlockingQueue<BigInteger> queue = new LinkedBlockingQueue<BigInteger>(1000);
	
	public void run(){
		BigInteger p = BigInteger.ONE;
		try{
			while(!Thread.currentThread().interrupted()){
				queue.put(p = p.nextProbablePrime());
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void cancel(){
		interrupt();
	}
	public synchronized List<BigInteger> get(){
		return new ArrayList<BigInteger>(queue);
	}
}