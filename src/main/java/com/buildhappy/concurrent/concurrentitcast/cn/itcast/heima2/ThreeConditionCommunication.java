package com.buildhappy.concurrent.concurrentitcast.cn.itcast.heima2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用多路Condition类完成三个进程间的通信 对ThreadCommunication_Condition.java的改进。
 * main运行20次后，sub2运行10次，接着sub3运行20次；又main运行20次后，sub2运行10次，接着sub3运行20次；如此往复5次
 * 
 * @author buildhappy
 *
 */

public class ThreeConditionCommunication {

	public static void main(String[] args) {
		final Business business = new Business();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 1; i <= 5; i++) {
					business.sub2(i);
				}

			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				for (int i = 1; i <= 5; i++) {
					business.sub3(i);
				}

			}
		}).start();

		for (int i = 1; i <= 5; i++) {
			business.main(i);
		}

	}

	static class Business {
		Lock lock = new ReentrantLock();
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Condition condition3 = lock.newCondition();
		private int shouldSub = 1;

		//sub2运行完后sub3运行
		public void sub2(int i) {
			lock.lock();
			try {
				while(shouldSub != 2) {
					try{
						condition2.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub2 thread sequence of " + j
							+ ",loop of " + i);
				}
				shouldSub = 3;
				condition3.signal();
			} finally {
				lock.unlock();
			}
		}
		//sub3运行完后main运行
		public void sub3(int i) {
			lock.lock();
			try {
				while (shouldSub != 3) {
					try {
						condition3.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 20; j++) {
					System.out.println("sub3 thread sequence of " + j
							+ ",loop of " + i);
				}
				shouldSub = 1;
				condition1.signal();
			} finally {
				lock.unlock();
			}
		}
		//main运行完后sub2运行
		public void main(int i) {
			lock.lock();
			try {
				while (shouldSub != 1) {
					try {
						condition1.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("main thread sequence of " + j
							+ ",loop of " + i);
				}
				shouldSub = 2;
				condition2.signal();
			} finally {
				lock.unlock();
			}
		}

	}
}
