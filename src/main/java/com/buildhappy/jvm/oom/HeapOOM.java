package com.buildhappy.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * java的堆溢出《java虚拟机》P51
 * 只要不断的创建对象，并且保证GC Roots到对象之间有可达路径来避免垃圾回收机制清除这些对象，
 * 那么当对象数量到达最大堆的容量限制后会产生内存溢出。
 * jvm参数：
 * -Xms20M -Xmn10M -XX:+HeapDumpOnOutOfMemoryError
 * 两种情况：
 * 
 * @author buildhappy
 *
 */
public class HeapOOM {
	static class OOMObject{
		
	}
	
	public static void main(String[] args){
		List<OOMObject> list = new ArrayList<OOMObject>();
		while(true)
			list.add(new OOMObject());
	}
}
