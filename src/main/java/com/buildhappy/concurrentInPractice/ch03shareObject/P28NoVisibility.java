package com.buildhappy.concurrentInPractice.ch03shareObject;

public class P28NoVisibility {
	private static boolean ready = false;
	private static int number = 0;
	
	private static class ReaderThread extends Thread{
		public void run(){
			while(!ready)
				Thread.yield();
			System.out.println(number);
		}
	}
	public static void main(String[] args) {
		new ReaderThread().start();
		number = 42;
		ready = true;
	}
}
