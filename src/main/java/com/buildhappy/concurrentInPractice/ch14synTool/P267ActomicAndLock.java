package com.buildhappy.concurrentInPractice.ch14synTool;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class P267ActomicAndLock {

	public static void main(String[] args) {
		
	}
}

/**
 * 伪随机数生成器
 * @author buildhappy
 *
 */
class PseudoRandom{
	Random rand = new Random();
	//int seed;
	public int calculateNext(int seed){
		return rand.nextInt(seed);
	}
}

/**
 * 使用ReentrantLock实现线程安全的伪随机数生成器
 * @author buildhappy
 *
 */
class ReentrantLockPseudoRandom extends PseudoRandom{
	private int seed;
	private ReentrantLock lock = new ReentrantLock(false);
	public ReentrantLockPseudoRandom(int seed){
		this.seed = seed;
	}
	//在每次迭代中将生成一个随机数字(该过程将读取和修改共享的seed状态)
	//并执行一些仅在线程本地数据上执行的繁重迭代
	public int nextInt(int n){
		lock.lock();
		try{
			int curSeed = seed;
			seed = calculateNext(curSeed);
			int remainder = curSeed % n;
			return remainder > 0 ? remainder : remainder + n;
		}finally{
			lock.unlock();
		}
	}
}

class AcotmicPseudoRandom extends PseudoRandom{
	private AtomicInteger seed;
	public AcotmicPseudoRandom(int seed){
		this.seed = new AtomicInteger(seed);
	}
	
	//while(true)实现非阻塞等待
	public int nextInt(int n){
		while(true){
			int s = seed.get();
			int nextSeed = calculateNext(s);
			if(seed.compareAndSet(s, nextSeed)){
				int remainder = s % n;
				return remainder > 0 ? remainder : remainder + n;
			}
		}

		
		//return seed.get();
	}
}


