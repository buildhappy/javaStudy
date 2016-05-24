package com.buildhappy.iooperation.nio.fileOpt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用映射文件能够非常显著地提高效率
 * @author buildhappy
 *
 */
public class O9MappedIO {
	private static int numOfInts = 4000000;
	private static int numOfUbuffInts = 200000;
	private static Tester[] tests = {
		new Tester("Stream Writer"){
			public void test() throws IOException{
				DataOutputStream dos = new DataOutputStream(
						new BufferedOutputStream(new FileOutputStream(new File("temp.tmp"))));
				for(int i = 0; i < numOfInts; i++)
					dos.writeInt(i);
				dos.close();
			}
		},
		new Tester("Mapped Writer"){
			public void test() throws IOException{
				FileChannel fc = new RandomAccessFile("temp.tmp" , "rw").getChannel();
				IntBuffer buffer = fc.map(FileChannel.MapMode.READ_WRITE , 0 , fc.size()).asIntBuffer();
				for(int i = 0; i < numOfInts; i++)
					buffer.put(i);
				fc.close();
			}
		},
		new Tester("Stream Reader"){
			public void test() throws IOException{
				DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("temp.tmp")));
				for(int i = 0; i < numOfInts; i++)
					in.readInt();
				in.close();
			}
		},
		new Tester("Mapped Reader"){
			public void test() throws IOException{
				FileChannel fc = new FileInputStream("temp.tmp").getChannel();
				System.out.println("fc.size:" + fc.size());
				IntBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY , 0 , fc.size()).asIntBuffer();
				while(buffer.hasRemaining())
					buffer.get();
				fc.close();
			}
		},
		new Tester("Mapped Read/Write"){
			public void test() throws IOException{
				FileChannel fc = new RandomAccessFile(new File("temp.tmp"),"rw").getChannel();
				IntBuffer buffer = fc.map(FileChannel.MapMode.READ_WRITE , 0 , fc.size()).asIntBuffer();
				buffer.put(0);
				System.out.println(buffer.limit());
				for(int i = 1; i < numOfUbuffInts; i++){
					buffer.put(buffer.get(i - 1));
				}
				fc.close();
			}
		}
	};
	
	public static void main(String[] args) {
		for(Tester tester : tests){
			tester.runTest();
		}
	}

}

abstract class Tester{
	private String name;
	public Tester(String name){
		this.name = name;
	}
	public void runTest(){
		System.out.println(name + ":");
		try{
			long start = System.nanoTime();
			test();
			long duration = System.nanoTime() - start;
			System.out.println(duration);
		}catch(Exception e){
			throw new RuntimeException();
		}
	}
	public abstract void test()throws IOException;
}


