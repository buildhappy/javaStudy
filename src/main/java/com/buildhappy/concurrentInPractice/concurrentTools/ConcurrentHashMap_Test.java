package com.buildhappy.concurrentInPractice.concurrentTools;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 并发HashMap
 * @author buildhappy
 *
 */
public class ConcurrentHashMap_Test {

	public static void main(String[] args) {
		ConcurrentHashMap<String , String> conMap = new ConcurrentHashMap<String , String>();
		conMap.put("key1", "value1");
		conMap.get("key1");
		int NCPU = Runtime.getRuntime().availableProcessors();
		System.out.println(NCPU);
		//Executors.n
	}
}
