package com.buildhappy.iooperation.nio.fileOpt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用NIO对文件进行操作
 * 《java编程思想》P552
 * @author buildhappy
 *
 */
public class O1GetChannel {
	private static final int BSIZE = 1024;
	public static void main(String[] args) throws IOException {
		//Write a file
		FileChannel fChannel = new FileOutputStream("data.txt").getChannel();
		fChannel.write(ByteBuffer.wrap("test data to file \n".getBytes()));
		fChannel.close();
		
		//append data to the end of the file
		fChannel = new RandomAccessFile("data.txt" , "rw").getChannel();
		fChannel.position(fChannel.size());
		fChannel.write(ByteBuffer.wrap("write more".getBytes()));
		fChannel.close();
		
		//read the file
		fChannel = new FileInputStream("data.txt").getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		fChannel.read(buffer);
		//反转此缓冲区。在一系列通道读取或放置操作之后，调用此方法为write操作做准备。同理必须调用clear为read做准备
		buffer.flip();
		
		while(buffer.hasRemaining()){
			System.out.print((char)buffer.get());
		}
	}
}
