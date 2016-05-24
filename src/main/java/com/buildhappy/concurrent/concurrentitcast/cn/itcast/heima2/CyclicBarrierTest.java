package com.buildhappy.concurrent.concurrentitcast.cn.itcast.heima2;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * CyclicBarrier类，当达到指定数目的线程时，某事件才会发生。
 * 生活中，大家一起出游到指定集合地点分批出发，比如3人一台车，当来了3人后就发车。。
 * @author buildhappy
 *
 */
public class CyclicBarrierTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CyclicBarrier cb = new CyclicBarrier(3);
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 10000));
						System.out
								.println("线程"
										+ Thread.currentThread().getName()
										+ "即将到达集合点1，当前已有"
										+ (cb.getNumberWaiting() + 1)
										+ "线程到达，"
										+ (cb.getNumberWaiting() == 2 ? "�������ˣ������߰�"
												: "正在等候"));
						cb.await();

						Thread.sleep((long) (Math.random() * 10000));
						System.out
								.println("线程"
										+ Thread.currentThread().getName()
										+ "即将到达集合点2，当前已有"
										+ (cb.getNumberWaiting() + 1)
										+ "线程到达，"
										+ (cb.getNumberWaiting() == 2 ? "都到齐了，走起"
												: "正在等候"));
						cb.await();
						Thread.sleep((long) (Math.random() * 10000));
						System.out
								.println("线程"
										+ Thread.currentThread().getName()
										+ "即将到达集合点3，当前已有"
										+ (cb.getNumberWaiting() + 1)
										+ "线程到达，"
										+ (cb.getNumberWaiting() == 2 ? "都到齐了，走起"
												: "正在等候"));
						cb.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
}
