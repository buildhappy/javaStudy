package com.buildhappy.iooperation.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 利用Nio实现文件的读取操作
 * @author buildhappy
 *
 */
public class ReadFile {
	public static void main(String[] args) throws Exception {
		RandomAccessFile randFile = new RandomAccessFile("data/nio-data.txt" , "rw");
		FileChannel channel = randFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(8);//UTF-8编码是变长编码，通常汉字占三个字节
		int bytesRead = channel.read(buf);
		int count = 0;
		while(bytesRead != -1){
			System.out.println("Read " + bytesRead);
			//flip()的调用，首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据
			//将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值
			buf.flip();
			/*
			while(buf.hasRemaining()){
				System.out.print((char)buf.get());
			}
			*/
			synchronized(ReadFile.class){
				System.out.println(new String(buf.array()) + " " + count++);
			}
			buf.clear();
			bytesRead = channel.read(buf);
		}
		randFile.close();
	}

}
