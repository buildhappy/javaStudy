package com.buildhappy.exception;

public class Finally_Test {

	public static void main(String[] args) {
		System.out.println(testFinally());
	}
	
	public static int testFinally(){
		try{
			throw new Exception();
		}catch(Exception e){
			e.printStackTrace();
			return 1;
		}finally{
			return 0;
		}
	}

}
