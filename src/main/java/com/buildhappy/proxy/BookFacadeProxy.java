package com.buildhappy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 动态代理类
 * @author buildhappy
 */
public class BookFacadeProxy implements InvocationHandler{
	private Object target;
   
	public BookFacadeProxy(Object target) {
		this.target = target;
	}
	/*
	 * 调用方法
     * Object proxy：指被代理的对象
     * Method method：要调用的方法
     * Object[] args：方法调用时所需要的参数
     */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		
		//添加代理类的功能
		System.out.println("proxy start...");
		result = method.invoke(target, args);
		System.out.println("proxy end...");
		
		return result;
	}
}
