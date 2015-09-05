package com.buildhappy.nio.fileOpt;

import java.nio.ByteBuffer;

public class O7ByteBufferTest {

	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//System.out.println("mark:" + buffer.mark());
		System.out.println("position::" + buffer.position());
		System.out.println("capacity:" + buffer.capacity());
		System.out.println("limit:" + buffer.limit());
		buffer.put("test".getBytes());
		//System.out.println("mark:" + buffer.mark());
		System.out.println("position::" + buffer.position());
		System.out.println("capacity:" + buffer.capacity());
		System.out.println("limit:" + buffer.limit());
	}

}
