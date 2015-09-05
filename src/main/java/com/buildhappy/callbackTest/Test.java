package com.buildhappy.callbackTest;

public class Test {
	public static void main(String[] args) {
		FooBar foo = new FooBar();
		foo.setCallBack(new ICallBack() {
			public void postExec() {
				System.out.println("在Test类中实现但不能被Test的对象引用,而由FooBar对象调用");
			}
		});
	}
}