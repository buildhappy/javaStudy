package com.buildhappy.jvm;

public class ClassInitTest1 {
	public static void main(String[] args){
		System.out.println("Parent.a:" + Parent.a);
		System.out.println(Child.b);
//		ClassLoader clazz = new ClassLoader();
	}
}

class Parent{
	public static  int a = 1;
	static{
		System.out.println("in parent");
	}
}

class Child extends Parent{
	public static final int b = 0;
	static{
		System.out.println("in child");
	}
}