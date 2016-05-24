package com.buildhappy.concurrent.concurrentInPractice.ch10avoidLivenessHazards;

public class Test {
	public static void main(String[] args) throws InterruptedException{
		int j = 0;
		for(int i = 0; i < 100; i++){
			j = j++;
		}
		System.out.println(j);
		
		Integer inte = new Integer(2);
		System.out.println(inte.doubleValue());
		
		//0打头的代表八进制
		int t = 012;
		System.out.println(t);
		t = 034;
		System.out.println(t);
		t = 056;
		System.out.println(t);
		
		short s = 1;
		//s = s + 1;
		System.out.println();
		
		String s1 = "abc";
		String s2 = "abc";
		String s3 = new String("abc");
		System.out.println("s1 == s2? " + s1 == s2);
		System.out.println("s1 == s3? " + s1 == s3);
	}

}
