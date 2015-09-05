package com.buildhappy.jvm;

public class Test {

	public static void main(String[] args) {
		String[] infos = new String[]{"faf" , "name"};
		String url = null;
		String name = null;
		set(infos , url , name);
		System.out.println(url);
		
		
	}
	public static String[] set(String[] infos , String url , String name){
		url = infos[0];
		name = infos[1];
		return null;
	}
}
