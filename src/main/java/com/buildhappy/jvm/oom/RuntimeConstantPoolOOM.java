package com.buildhappy.jvm.oom;

import java.util.LinkedList;
import java.util.List;

/**
 * 运行时常量池属于方法区的一部分，保存程序运行时的常量数据
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author buildhappy
 *
 */
public class RuntimeConstantPoolOOM {
	
	public static void main(String[] args) {
		List<String> list = new LinkedList<String>();
		int i = 0;
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}

}
