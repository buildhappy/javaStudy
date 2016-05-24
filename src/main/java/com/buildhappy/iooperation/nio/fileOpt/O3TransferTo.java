package com.buildhappy.iooperation.nio.fileOpt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 使用transferTo和transferFrom允许将一个通道和另一个通道直接相连
 * 《编程思想》P553
 * @author buildhappy
 *
 */
public class O3TransferTo {

	public static void main(String[] args) throws IOException {
		FileChannel in = new FileInputStream("data.txt").getChannel();
		FileChannel out = new FileOutputStream("out.txt").getChannel();
		in.transferTo(0, in.size(), out);
		//或者
		//out.transferFrom(in , 0 , in.size());
	}

}
