package com.sankuai.confhub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * 从confhub上获取配置信息
 * Created by caijianfu02 on 16/5/3.
 */
public class GetConfhubInfo {
    private static final String CONFHUB_URL
            //= "http://data.sankuai.com/confhub/api/c/cis/resource_manager";
    ="http://data.sankuai.com/confhub/api/c/cis/villarreal/access_control";


    public static void main(String[] args){
        getInfoFromConfhub();
    }
    public static void getInfoFromConfhub(){
        JSONArray appList = null;
        try{
            String json = httpGet(CONFHUB_URL   + "?limit=1000");
            //JSON.parseArray(json);
            Map jsonObj = JSON.parseObject(json);
            Object v = jsonObj.get("auth");
            System.out.println((Map) v);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        if(appList != null){
            for(Object appObj:appList){
                Map<String , String> appInfo = (Map<String , String>) appObj;
                println(appInfo);
            }
        }
    }


    public static String httpGet(String url) {
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        try {
            return EntityUtils.toString(client.execute(get).getEntity());
        } catch (IOException e) {
            //
        }
        return null;
    }
    public static void println(Object o){
        System.out.println(o);
    }
}
