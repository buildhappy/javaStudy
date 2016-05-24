package com.buildhappy.concurrent.concurrentInPractice.ch05.highPerformCache;
/**
 * 计算任务的接口声明，输入参数A返回结果V
 * @author Administrator
 *
 * @param <A>
 * @param <V>
 */
public interface Computable<A , V> {
	V compute(A arg) throws InterruptedException;
}
