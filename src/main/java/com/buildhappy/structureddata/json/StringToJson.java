package com.buildhappy.structureddata.json;

/**
 * Created by buildhappy on 15/10/22.
 */
public class StringToJson {
    public static void main(String[] args){
//        String s = null;
//        org.json.JSONObject jsonObject = new org.json.JSONObject(s);
        String s = "940230人已领";
        System.out.println(s.substring(0 , s.lastIndexOf("人")));


    }
}
