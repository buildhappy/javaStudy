package com.buildhappy.http.httpasynclient;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.concurrent.Future;

/**
 * Asynchronous HTTP exchange with content streaming
 * This example demonstrates an asynchronous HTTP request/response exchange with a full content streaming.
 * @author caijianfu02@meituan.com
 * @date 2015-10-09 上午10:10:51
 */
public class O2AsyncClientHttpExchangeStreaming {
    public static void main(final String[] args) throws Exception {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            httpclient.start();
            Future<Boolean> future = httpclient.execute(
                    HttpAsyncMethods.createGet("http://cis.sankuai.com/open/home"),
                    new MyResponseConsumer(), null);
            Boolean result = future.get();
            if (result != null && result.booleanValue()) {
                System.out.println("Request successfully executed");
            } else {
                System.out.println("Request failed");
            }
            System.out.println("Shutting down");
        } finally {
            httpclient.close();
        }
        System.out.println("Done");
    }
    static class MyResponseConsumer extends AsyncCharConsumer<Boolean>{

        @Override
        protected void onCharReceived(CharBuffer charBuffer, IOControl ioControl) throws IOException {
            while(charBuffer.hasRemaining()){
                System.out.print(charBuffer.get());
            }
        }

        @Override
        protected void onResponseReceived(HttpResponse httpResponse) throws HttpException, IOException {
            System.out.println("Response status:" + httpResponse.getStatusLine());
        }

        @Override
        protected Boolean buildResult(HttpContext httpContext) throws Exception {
            return Boolean.TRUE;
        }
    }
}
