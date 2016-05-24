package com.buildhappy.iooperation.nio.fileOpt;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/**
 * 逐行读取文件，并按行写入
 * @author buildhappy
 *
 */
public class ReadFileByRow {

	public static void main(String args[]) throws Exception {

		// String infile = "D:\\workspace\\test\\usagetracking.log";
		// FileInputStream fin= new FileInputStream(infile);
		// FileChannel fcin = fin.getChannel();

		int bufSize = 100;
		File fin = new File("task1_127.txt");
		File fout = new File("E:\\task1_127.txt");

		FileChannel fcin = new RandomAccessFile(fin, "r").getChannel();
		ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);

		FileChannel fcout = new RandomAccessFile(fout, "rws").getChannel();
		//MappedByteBuffer wBuffer;// 
		//wBuffer = fcin.map(FileChannel.MapMode.READ_ONLY, 0,bufSize);
		ByteBuffer wBuffer= ByteBuffer.allocateDirect(bufSize);
		long start = System.nanoTime();
		readFileByLine(bufSize, fcin, rBuffer, fcout, wBuffer);
		System.out.println(System.nanoTime() - start);
		System.out.print("OK!!!");
	}

	public static void readFileByLine(int bufSize, FileChannel fcin,
			ByteBuffer rBuffer, FileChannel fcout, ByteBuffer wBuffer) {
		String enterStr = "\n";
		try {
			byte[] bs = new byte[bufSize];

			int size = 0;
			int counter = 0;
			StringBuffer strBuf = new StringBuffer("");
			// while((size = fcin.read(buffer)) != -1){
			int fcinPositon = 0;
			while((fcinPositon = fcin.read(rBuffer)) != -1) {
				//System.out.println("fcinPositon:" + fcinPositon);
				int rSize = rBuffer.position();//rsize=50
				rBuffer.rewind();
				rBuffer.get(bs);
				rBuffer.clear();
				String tempString = new String(bs, 0, rSize);
				// System.out.print(tempString);
				// System.out.print("<200>");

				int fromIndex = 0;
				int endIndex = 0;
				while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {
					String line = tempString.substring(fromIndex, endIndex);
					line = new String(strBuf.toString() + line);
					// System.out.print(line);
					// System.out.print("</over/>");
					// write to anthone file
					//writeFileByLine(fcout, wBuffer, line);

					strBuf.delete(0, strBuf.length());
					fromIndex = endIndex + 1;
					counter++;
				}
				
				/*
				String lastRow = tempString.substring(fromIndex, tempString.length());
				if(lastRow != null && lastRow.length() > 0){
					//writeFileByLine(fcout, wBuffer, lastRow);
				}
				*/
				if (rSize > tempString.length()) {
					strBuf.append(tempString.substring(fromIndex,
							tempString.length()));
				} else {
					strBuf.append(tempString.substring(fromIndex, rSize));
				}
				
			}
			System.out.println(counter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeFileByLine(FileChannel fcout, ByteBuffer wBuffer,
			String line) {
		try {
			// write on file head
			// fcout.write(wBuffer.wrap(line.getBytes()));
			// wirte append file on foot
			System.out.println("in write:" + line);
			fcout.write(wBuffer.wrap((line).getBytes()), fcout.size());
			wBuffer.clear();
			fcout.write(wBuffer.wrap(("\n").getBytes()), fcout.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}