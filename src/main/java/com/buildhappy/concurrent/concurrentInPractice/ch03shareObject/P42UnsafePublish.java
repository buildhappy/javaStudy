package com.buildhappy.concurrent.concurrentInPractice.ch03shareObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 不正确的发布对象
 * @author buildhappy
 *
 */
public class P42UnsafePublish {

	public static void main(String[] args) {
		//Holder holder = new PublishHolder().
		Collections.synchronizedList(new ArrayList());
	}

}

//不安全的发布对象
class PublishHolder{
	public Holder holder;
	public void initialize(){
		this.holder = new Holder(42);
	}
}

class Holder{
	private int n;
	public Holder(int n){
		this.n = n;
	}
	public void assertSanity() throws Exception{
		if(n != n){
			throw new Exception("This statement is false");
		}
	}
}
