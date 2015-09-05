package com.buildhappy.nio.fileOpt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 使用ByteBuffer的toCharBuffer()将二进制转化成字符
 * 缓冲器容纳的是普通的字节，为了把它们转移成字符，我们要么在输入的时候对其进行编码，要么在读取时对其进行编码。
 * 《编程思想》P554
 * @author buildhappy
 *
 */
public class O4BufferToText {
	private static final int BSIZE = 1024;
	public static void main(String[] args) throws IOException {
		FileChannel in = new FileInputStream("data.txt").getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		in.read(buffer);
		
		//does not work
		//System.out.println(buffer.asCharBuffer());
		
		//decode using this system's default Charset
		buffer.rewind();
		String encoding = System.getProperty("file.encoding");
		System.out.println("Decoding using " + encoding + ":\n" + 
							Charset.forName(encoding).decode(buffer));
		
		//或者在写入数据时，对数据编码
		System.out.println("\n写入数据时进行编码，那么在读数据时，可以直接调ByteBuffer的asCharBuffer:");
		FileChannel out = new FileOutputStream("endcodeData.txt").getChannel();
		out.write(ByteBuffer.wrap("encode data test".getBytes("UTF-16BE")));
		out.close();
		//read again
		in = new FileInputStream("endcodeData.txt").getChannel();
		buffer.clear();
		in.read(buffer);
		buffer.flip();
		System.out.println(buffer.asCharBuffer());//乱码
		
		//使用CharBuffer进行操作
		System.out.println("\n使用CharBuffer进行数据操作：");
		out = new FileOutputStream("charBuffer.txt").getChannel();
		buffer.asCharBuffer().put("CharBuffer data");
		out.write(buffer);
		out.close();
		//read data
		in = new FileInputStream("charBuffer.txt").getChannel();
		buffer.clear();
		in.read(buffer);
		buffer.flip();
		System.out.println(buffer.asCharBuffer());
	}

}
