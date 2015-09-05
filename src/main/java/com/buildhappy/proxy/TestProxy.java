package com.buildhappy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestProxy {
	public static void main(String[] args) {
		BookFacadeImpl bookFacade = new BookFacadeImpl();
		InvocationHandler h = new BookFacadeProxy(bookFacade);
		Class<?> cls = bookFacade.getClass();
		/*
		 * ClassLoader loader：类加载器
		 * Class<?>[] interfaces：得到全部的接口
		 * InvocationHandler h：得到InvocationHandler接口的子类实例
		 * return：添加动态代理功能的对象
		 */
		BookFacade bookFacadeProxy = (BookFacade)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), h);
		bookFacadeProxy.addBook();
	}
}
