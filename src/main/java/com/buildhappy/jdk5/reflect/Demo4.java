package com.buildhappy.jdk5.reflect;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Demo4 {

	/**
	 * 反射类的字段/属性（用getField()、getDeclaredField()方法）
	 * @throws Exception
	 */
	//public String name;   //字段
	@Test
	public void test1() throws Exception{
		
		Person p = new Person();
		Class clazz = Class.forName("com.buildhappy.jdk5.reflect.Person");
		Field f = clazz.getField("name");
		f.set(p, "zxx");
		System.out.println(p.name);
	} 
	
	//private List list;
	@Test
	public void test2() throws Exception{
		
		Person p = new Person();
		Class clazz = Class.forName("com.buildhappy.jdk5.reflect.Person");
		Field f = clazz.getDeclaredField("list");
		f.setAccessible(true);
		f.set(p, new ArrayList());
		System.out.println(p.getList());
	}
	
	//private static String address;
	@Test
	public void test3() throws Exception{
		
		Class clazz = Class.forName("com.buildhappy.jdk5.reflect.Person");
		Field f = clazz.getDeclaredField("address");
		f.setAccessible(true);
		f.set(null, "shanghai");
		System.out.println(Person.getAddress());
	}

}
