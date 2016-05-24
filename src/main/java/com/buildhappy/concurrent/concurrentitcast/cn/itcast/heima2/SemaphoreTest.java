package com.buildhappy.concurrent.concurrentitcast.cn.itcast.heima2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 * Semaphore信号灯，一个资源可以定义自己的信号灯的个数n，当同时访问的线程个数小于n时，线程可以访问资源，否则等待
 * @author Administrator
 *
 */
public class SemaphoreTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Semaphore sp = new Semaphore(3);
		//开启10个线程
		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						sp.acquire();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName()
							+ "进入，当前已有" + (3 - sp.availablePermits())
							+ "个并发");
					try {
						Thread.sleep((long) (Math.random() * 10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName()
							+ "即将离开");
					sp.release();
					//
					System.out.println("线程" + Thread.currentThread().getName()
							+ "已离开，当前已有" + (3 - sp.availablePermits())
							+ "������");
				}
			};
			service.execute(runnable);
		}
	}

}
