package com.buildhappy.structureddata.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ObjectToJson {
	public static void main(String[] args){
		listToJson();
	}
	
	/**
	 * 将List转化成json数据
	 */
	public static void listToJson(){
		List<Apk> list = new ArrayList<Apk>();
		
		Apk user = new Apk("222" , "343",null,null,null);
		list.add(user);
		//list.add(user);
		//list.add(user);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(list);
		System.out.println(jsonArray);

	}
	
	/**
	 * 将对象转换成json数据
	 */
	public static void objectToJson(){
		Apk apk;
		apk = new Apk("222" , "343",null,null,null);
		Apk apk2 = new Apk("22245" , "343",null,null,null);
		JSONArray jsonArray = JSONArray.fromObject(apk);
		jsonArray.add(apk2);
		System.out.println(jsonArray);
	}
	
	/**
	 * 将map转换成json数据
	 */
	public static void mapToJson(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", 1001);
		map.put("userName", "张三");
		map.put("userSex", "男");
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject);
	}

}
