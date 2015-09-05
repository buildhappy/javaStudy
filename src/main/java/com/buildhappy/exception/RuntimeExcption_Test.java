package com.buildhappy.exception;
/**
 * RuntimeException是可以不进行处理，可以由虚拟机自动捕获和抛出
 * @author buildhappy
 *
 */
public class RuntimeExcption_Test {
	//如果RuntimeException没有被捕获而直接到达main()，
	//那么程序退出时会调用printStackTrace()方法。
	public static void main(String[] args) {
		throwException();
	}
	public static void throwException(){
		throw new RuntimeException("RuntimeException test");
	}
}
