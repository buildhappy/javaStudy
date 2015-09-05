package com.buildhappy.httprequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.xalan.xsltc.compiler.util.TestGenerator;

public class SendPostRequest {
	public static void main(String[] args) throws Exception, URISyntaxException, IOException, InterruptedException{
		//for(int i = 138; i < 180;i++){
		/*
		for(int i = 140; i < 176;i++){
			System.out.println("channelId:" + i);
			testMyCrawler(21);//168(360)  59(腾讯) 21(百度)
			Thread.sleep(1000 * 60);
		}
		*/
//		testMyCrawler(168);
//		testGet();

		testPost();
	}
	
	/**
	 * start test of crawler job
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void testMyCrawler(int i) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//HttpPost httppost = new HttpPost("http://asec.buptnsrc.com:3050/subtask/1001");//10.108.122.150:3050//localhost:9090
		
		HttpPost httppost = new HttpPost("http://localhost:8077/subtask/1001");//10.108.122.150:3050//localhost:9090
		
		//远程tomcat
		//HttpPost httppost = new HttpPost("http://asec.buptnsrc.com:8090/subtask/1001");
		
		//笔记本
		//HttpPost httppost = new HttpPost("http://10.108.115.17:9090/subtask/1001");
		
		
		StringEntity myEntity = new StringEntity("{\"channelId\":\"" + i + "\",\"keyword\":\"" + "魂斗罗" + "\"," + "\"version\":\"" + "5.4.1" + "\"}",//{channelId:101 102 , keyword:qq}
				ContentType.create("application/json", "UTF-8"));
		httppost.setEntity(myEntity);
		CloseableHttpResponse response = httpClient.execute(httppost);
		try{
			HttpEntity entity = response.getEntity();
			if(entity != null){
				System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));  
			}
		}finally{
			response.close();
		}
	}
	
	public static void testBaiduWangpan() throws URISyntaxException, ClientProtocolException, IOException{
		String shareid = "3820482417";
		String web = "1";
		String uk = "638431586";
		String clienttype = "0";
		String timestamp = "1429616328";
		String channel = "chunlei";
		String sign = "ac772fde8e5c376a55dace02bb1820d9e8e08828";
		String bdstoken = "b0b6cab1413a15787bff9235bd09d62e";
		String appId = "250528";
		String url = "http://pan.baidu.com/share/download";
		
		//url = "250528";
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append("http://pan.baidu.com/api/sharedownload?sign=");
		urlBuilder.append(sign);
		urlBuilder.append("&timestamp=");
		urlBuilder.append(timestamp);
		urlBuilder.append("&bdstoken=");
		urlBuilder.append(bdstoken);
		urlBuilder.append("&channel=");
		urlBuilder.append(channel);
		urlBuilder.append("&clienttype=");
		urlBuilder.append(clienttype);
		urlBuilder.append("&web=");
		urlBuilder.append(web);
		urlBuilder.append("&app_id=");
		urlBuilder.append(appId);
		
		url = urlBuilder.toString();
		
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost= new HttpPost(url);
		
		//create the http header
//		RequestBuilder requestBuilder = RequestBuilder.post();
//        HttpUriRequest httpUriRequest = requestBuilder.build();
        //防止反爬虫
//        httpUriRequest.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//        //httpUriRequest.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
//        httpUriRequest.addHeader("Content-Encoding", "text/html");
//        httpUriRequest.addHeader("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2");
//        httpUriRequest.addHeader("Content-Type", "application/x-www-form-urlencoded");
//        httpUriRequest.addHeader("Referer", "http://pan.baidu.com");
//        httpUriRequest.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.77 Safari/537.36");
//        
        
        httpPost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpPost.addHeader("Content-Encoding", "text/html");
        httpPost.addHeader("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Referer", "http://pan.baidu.com");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.77 Safari/537.36");
        
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		NameValuePair pair1 = new BasicNameValuePair("web", "1");
		NameValuePair pair2 = new BasicNameValuePair("shareid", "3820482417");
		NameValuePair pair3 = new BasicNameValuePair("uk", "638431586");
		NameValuePair pair4 = new BasicNameValuePair("clienttype", "0");
		NameValuePair pair5 = new BasicNameValuePair("timestamp", "1429616328");
		NameValuePair pair6 = new BasicNameValuePair("channel", "chunlei");
		NameValuePair pair7 = new BasicNameValuePair("sign", "ac772fde8e5c376a55dace02bb1820d9e8e08828");
		pairs.add(pair1);
		pairs.add(pair2);
		pairs.add(pair3);
		pairs.add(pair4);
		pairs.add(pair5);
		pairs.add(pair6);
		pairs.add(pair7);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
//		httpClient.getParams().setParameter("web", "1");
//		httpClient.getParams().setParameter("shareid", "3820482417");
//		httpClient.getParams().setParameter("clienttype", "0");
//		httpClient.getParams().setParameter("timestamp", "1429616328");
//		httpClient.getParams().setParameter("clienttype", "0");
//		httpClient.getParams().setParameter("timestamp", "1429616328");
//		httpClient.getParams().setParameter("channel", "chunlei");
//		httpClient.getParams().setParameter("sign", "ac772fde8e5c376a55dace02bb1820d9e8e08828");
		
		StringEntity myEntity = new StringEntity("{\"channelId\":\"" + "12" + "\",\"keyword\":\"" + "qq" + "\"," + "\"version\":\"" + "5.4.1" + "\"}",//{channelId:101 102 , keyword:qq}
				ContentType.create("application/json", "UTF-8"));
		
		//httpPost.setEntity(myEntity);
		//StringEntity entity = new StringEntity("test");
		//httpPost.setEntity(entity);
		//httpPost.addHeader(header);
		
		//提交form表单数据
		  // 创建参数队列
		  List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		  formparams.add(new BasicNameValuePair("fid_list","343383322233283"));
		  //formparams.add(new BasicNameValuePair("password", "admin"));
		  UrlEncodedFormEntity uefEntity;
		  try {
		   uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
		   httpPost.setEntity(uefEntity);
		   System.out.println("executing request " + httpPost.getURI());
		   HttpResponse response;
		   response = httpClient.execute(httpPost);
		   HttpEntity entity = response.getEntity();
		   if (entity != null) {
		    System.out.println("--------------------------------------");
		    System.out.println("Response content: "
		      + EntityUtils.toString(entity, "UTF-8"));
		    System.out.println("--------------------------------------");
		    
		   }
		  } catch (ClientProtocolException e) {
		   e.printStackTrace();
		  } catch (UnsupportedEncodingException e1) {
		   e1.printStackTrace();
		  } catch (IOException e) {
		   e.printStackTrace();
		  } finally {
		   // 关闭连接,释放资源
		   httpClient.getConnectionManager().shutdown();
		  }
		
//		CloseableHttpResponse response = httpClient.execute(httpPost);
//		
//		HttpEntity responseEntity = response.getEntity();
//		System.out.println(EntityUtils.toString(responseEntity, "UTF-8"));
	}
	
	
	public static void testGet() throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://dlc2.pconline.com.cn/dltoken/61068_genLink.js");
		httpGet.addHeader("Cache-Control", "max-age=0");
		httpGet.addHeader("Connection", "keep-alive");
		httpGet.addHeader("Host", "dlc2.pconline.com.cn");
		httpGet.addHeader("Referer", "http://dl.pconline.com.cn/download/61068.html");
		httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.76 Safari/537.36");
		//httpGet.addHeader("Connection", "keep-alive");
		CloseableHttpResponse response = httpClient.execute(httpGet);
		try{
			HttpEntity entity = response.getEntity();
			if(entity != null){
				System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));  
			}
		}finally{
			response.close();
		}
	}

	public static void testPost() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://114.80.88.44:9090/Boss/riguke/tableWeekData.do");
		httpPost.addHeader("Accept", "*/*");
		httpPost.addHeader("Accept-Encoding", "mgzip, deflate");
		httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpPost.addHeader("Host", "114.80.88.44:9090");
		httpPost.addHeader("Cookie", "JSESSIONID=4E7D816E61FA2978F3A9081171FE7A9B");
		httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
		httpPost.addHeader("Referer", "http://114.80.88.44:9090/Boss/riguke/getRiGkId.do?brandId=56&shopId=0&date=2015-07-12");
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.addHeader("Origin", "http://114.80.88.44:9090");
		httpPost.addHeader("Connection", "keep-alive");
		//httpPost.addHeader("Content-Length", "45");
		httpPost.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36");

		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		NameValuePair pair1 = new BasicNameValuePair("data", "2015-07-12");
		NameValuePair pair2 = new BasicNameValuePair("brandId", "56");
		NameValuePair pair3 = new BasicNameValuePair("restaurantname", "56");
		pairs.add(pair1);
		pairs.add(pair2);
		pairs.add(pair3);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
		Header[] message = httpPost.getAllHeaders();
		for(Header header : message)
			System.out.println(header);
		//System.out.println(httpPost.getEntity().getContent());
		CloseableHttpResponse response = httpClient.execute(httpPost);
		try{

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				System.out.println("--------------------------------------");
				System.out.println("Response content: "
						+ EntityUtils.toString(entity, "UTF-8"));
				System.out.println("--------------------------------------");

			}
		}catch (Exception e){

		}
	}
}
