package com.buildhappy.jvm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HashMapTest {

	public static void main(String[] args){
		HashMap<String , String> hashMap = new HashMap<String , String>(20);
		hashMap.put("1", "one");
		Iterator<String> it = hashMap.keySet().iterator();
		while(it.hasNext()){
			
		}
	}
}
