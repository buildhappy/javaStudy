package com.buildhappy.zzothers.unzip;

/**
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class ZipUtilsTest {
	
	public static void main(String[] args) throws Exception{
		// 解压到指定目录
		//ZipUtils.decompress("d:\\f.txt.zip", "d:\\ff");
		// 解压到当前目录
		UnZipUtils.decompressAndDelete("G:\\aaa.zip");
		//ZipUtils.decompress("G:\\aaa.zip");
		System.out.println("done");
		//System.out.println(File.separator);		
	}
}