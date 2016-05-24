package com.buildhappy.http.httprequest;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class ProxyTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		RequestConfig config = RequestConfig.custom().setProxy(new HttpHost("dynamicproxy.buptnsrc.com", 8002, "http")).build();
		HttpGet get = new HttpGet("http://www.baidu.com");
		get.setConfig(config);
		HttpResponse response = client.execute(get);
		
		String entity = EntityUtils.toString(response.getEntity(), "UTF-8");
		System.out.println(entity);		
	}

}
