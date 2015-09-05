package com.buildhappy.nio.fileOpt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 两个通道相互交流数据
 * 在一系列通道读取或放置操作之后，调用flip为write操作做准备。同理必须调用clear为read做准备
 * 《编程思想》P553
 * @author buildhappy
 *
 */
public class O2ChannelCopy {
	private static final int BSIZE = 1024;
	public static void main(String[] args) throws IOException {
		FileChannel in = new FileInputStream("data.txt").getChannel(); 
		FileChannel out = new FileOutputStream("out.txt").getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		//-1表示已经到达输入末尾
		while(in.read(buffer) != -1){
			//在一系列通道读取或放置操作之后，调用flip为write操作做准备。同理必须调用clear为read做准备
			buffer.flip();
			out.write(buffer);
			buffer.clear();
		}
	}
}
