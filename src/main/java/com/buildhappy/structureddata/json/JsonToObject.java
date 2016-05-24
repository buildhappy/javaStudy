package com.buildhappy.structureddata.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonToObject {

	public static void main(String[] args) {
		SimpleUser simpleUser = (SimpleUser) JSON2SimpleBean();
		//Object obj = JSON2SimpleBean();
		System.out.println(simpleUser.getAge());
	}
	/** 
	 * 将json格式封装的简单实体类型数据转换成简单类型的javabean 
	 * @return 
	 */  
	public static Object JSON2SimpleBean() {  
	    String jsonStr = "{\"age\":23,\"id\":123,\"name\":\"tt_2009\"," +  
	            "\"province\":\"上海\",\"sex\":\"男\"}";  
	    JSONObject jsonBean = JSONObject.fromObject(jsonStr);  
	    return JSONObject.toBean(jsonBean, SimpleUser.class);  
	}
	
	/** 
	 * 将json格式封装的列表数据转换成java的List数据 
	 * @return 
	 */  
	public static Object JSON2List() {  
	    String jsonArray =  
	            "[{\"age\":23,\"id\":123,\"name\":\"tt_2009_0\",\"province\":\"上海\",\"sex\":\"男\"}," +  
	            "{\"age\":24,\"id\":123,\"name\":\"tt_2009_1\",\"province\":\"上海\",\"sex\":\"男\"}," +  
	            "{\"age\":32,\"id\":123,\"name\":\"tt_2009_9\",\"province\":\"上海\",\"sex\":\"男\"}]";  
	  
	    return JSONArray.toList(JSONArray.fromObject(jsonArray), new SimpleUser(), new JsonConfig());  
	}  
	
}
