package com.buildhappy.jvm;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class MakeFileHash {
	private static char hexChar[] = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public MakeFileHash() {
	}

	public static String getFileMD5(String filename) {
		String str = "";
		try {
			str = getHash(filename, "MD5");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String getFileSHA1(String filename) {
		String str = "";
		try {
			str = getHash(filename, "SHA1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String getFileSHA256(String filename) {
		String str = "";
		try {
			str = getHash(filename, "SHA-256");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String getFileSHA384(String filename) {
		String str = "";
		try {
			str = getHash(filename, "SHA-384");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String getFileSHA512(String filename) {
		String str = "";
		try {
			str = getHash(filename, "SHA-512");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * Secure one-way hash function;
	 * Input the whole fileName(with the file path) and the hashType,
	 * output a fixed-length hash value
	 * 
	 * @param fileName:the whole fileName(with the file path)
	 * @param hashType:the functionality of a message digest algorithm, such as MD5,SHA-1,SHA-256,SHA-512,SHA-384
	 * @return
	 * @throws Exception
	 */
	private static String getHash(String fileName, String hashType)
			throws Exception {
		InputStream fis = new FileInputStream(fileName);
		byte buffer[] = new byte[1024];
		MessageDigest md5 = MessageDigest.getInstance(hashType);
		for (int numRead = 0; (numRead = fis.read(buffer)) > 0;) {
			md5.update(buffer, 0, numRead);
		}
		fis.close();
		return toHexString(md5.digest());
	}

	private static String toHexString(byte b[]) {
		//System.out.println(b.length);
		StringBuilder sb = new StringBuilder(b.length);
		for (int i = 0; i < b.length; i++) {
			sb.append(Integer.toHexString(b[i]));
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		System.out.println(MakeFileHash.getFileMD5("G://aaaa.txt"));
		System.out.println(MakeFileHash.getFileMD5("G://aaaa.txt").equals("44ffffffe4ffffffc01e7d1c62ffffffef1affffff8f3176ffffffa8ffffffa3ffffffa178"));
		//System.out.println("ffffffc41684327ffffff913f6b6a8ffffffb42cffffff85ffffffe667ffffff83".length());
		//System.out.println(66 * 16);
		
		String s = "hello";
		char[] ss = new char[]{'h','e','l','l','o'};
		System.out.println(s.equals(ss));
	}
}