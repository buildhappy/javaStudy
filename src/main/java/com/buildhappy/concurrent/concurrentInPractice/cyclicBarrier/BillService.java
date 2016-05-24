package com.buildhappy.concurrent.concurrentInPractice.cyclicBarrier;
/**
 * @author guangbo
 * 
 */
public interface BillService {

	/**
	 * 各省计费
	 * 
	 * @param code
	 *            省编码
	 */
	public void bill(String code);

}

