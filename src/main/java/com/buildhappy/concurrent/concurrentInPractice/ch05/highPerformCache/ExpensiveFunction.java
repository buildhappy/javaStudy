package com.buildhappy.concurrent.concurrentInPractice.ch05.highPerformCache;

import java.math.BigInteger;

/**
 * Computable的实现，compute非常耗时
 * @author buildhappy
 *
 */
public class ExpensiveFunction implements Computable<String , BigInteger>{

	public BigInteger compute(String arg) throws InterruptedException {
		return new BigInteger(arg);
	}

}
