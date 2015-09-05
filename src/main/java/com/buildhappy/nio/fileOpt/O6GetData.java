package com.buildhappy.nio.fileOpt;

import java.nio.ByteBuffer;

/**
 * 从ByteBuffer中获取基本数据类型
 * @author buildhappy
 */
public class O6GetData {
	private static final int BSIZE = 1024;
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		
		System.out.println("分配的空间，，默认用0填充");
		int i = 0;
		while(i++ < buffer.limit()){
			if(buffer.get() != 0)
				System.out.println("nonzero");
		}
		System.out.println("buffer size:" + i);
		System.out.println();
		buffer.rewind();//返回到数据的开始部分
		
		System.out.println("保存和读取char数组");
		buffer.asCharBuffer().put("test");
		char c;
		while((c = buffer.getChar()) != 0)
			System.out.print(c);
		System.out.println();
		buffer.rewind();
		//buffer.compact();
		
		System.out.println("\n保存和读取short");
		buffer.asShortBuffer().put((short)471142);//需要进行类型转换，其他的不用
		System.out.println(buffer.getShort());
		System.out.println();
		buffer.rewind();
		
		System.out.println("保存和读取int");
		buffer.asIntBuffer().put(99471142);
		System.out.println(buffer.getInt());
		System.out.println();
		buffer.rewind();
		
	}

}
