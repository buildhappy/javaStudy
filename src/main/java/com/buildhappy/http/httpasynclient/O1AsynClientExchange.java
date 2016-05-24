package com.buildhappy.http.httpasynclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.util.concurrent.Future;

/**
 * Asynchronous HTTP exchange
 * This example demonstrates a basic asynchronous HTTP request/response exchange. Response content is buffered in memory for simplicity.
 * @author caijianfu02@meituan.com
 * @date 2015-10-09 上午10:10:51
 */
public class O1AsynClientExchange {
    public static void main(String[] args) throws Exception {
        CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
        try{
            httpClient.start();
            HttpGet request = new HttpGet("http://www.bupt.edu.cn");
            Future<HttpResponse> future = httpClient.execute(request , null);
            HttpResponse response = future.get();
            System.out.println("Response:" + response.getStatusLine());
        }finally {
            httpClient.close();
        }
    }
}
