package com.buildhappy.iooperation.nio.fileOpt.bigFile;

import java.io.File;
import java.util.List;
/**
 * 这是文件被处理的过程。
 * 每一个List包含的元素都从一个单独的buffer中解码，每一个元素都是被TextRowDecoder定义的byte二维数组。
 * @author buildhappy
 *
 */
public class Test {

	public static void main(String[] args) {
		byte comma = 10;//",".getBytes();
		TextRowDecoder decoder = new TextRowDecoder(4, comma);
		File[] file = {new File("data.txt")};
		//List<File> file = new LinkedList<File>();
		FileReader<byte[][]> reader = FileReader.create(decoder, file);//file.listFiles());
		for (List<byte[][]> chunk : reader) {
			
			byte[][] line = chunk.get(0);
			
			byte[] b = line[0];
			
			System.out.println(new String(b));
		}
		byte t = (byte)'\n';
		//System.out.println(t);
		
	}

}
