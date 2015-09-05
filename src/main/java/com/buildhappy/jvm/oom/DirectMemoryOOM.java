package com.buildhappy.jvm.oom;

import java.lang.reflect.Field;

/**
 * 直接内存溢出P59
 * @author buildhappy
 *
 */
public class DirectMemoryOOM {

	public static void main(String[] args) {
		Field unsafeField = DirectMemoryOOM.class.getDeclaredFields()[0];
		
	}

}
