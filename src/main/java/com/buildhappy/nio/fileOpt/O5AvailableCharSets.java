package com.buildhappy.nio.fileOpt;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * 获取所有可用的编码
 * P555
 * @author buildhappy
 *
 */
public class O5AvailableCharSets {

	public static void main(String[] args) {
		SortedMap<String , Charset> charSets = Charset.availableCharsets();
		Iterator<String> keys = charSets.keySet().iterator();
		while(keys.hasNext()){
			String csName = keys.next();
			System.out.println(csName);
			Iterator aliases = charSets.get(csName).aliases().iterator();
			if(aliases.hasNext())
				System.out.print(":");
			while(aliases.hasNext()){
				System.out.print(aliases.next());
				if(aliases.hasNext())
					System.out.print(",");
			}
		}
		System.out.println();
		
	}

}
