package com.buildhappy.iooperation.nio.fileOpt;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用内存映射文件，我们可以假定整个
 * @author buildhappy
 *
 */
public class O8LargeFile {
	private static int length = 0X8FFFFFF;//128MB
	public static void main(String[] args) throws IOException {
		MappedByteBuffer out = new RandomAccessFile("largeFile.txt","rw").
				getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
		for(int i = 0; i < length; i++)
			out.put((byte)'x');
		System.out.println("finish writing");
		
		
	}

}
