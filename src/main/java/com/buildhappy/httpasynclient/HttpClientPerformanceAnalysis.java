package com.buildhappy.httpasynclient;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.concurrent.*;

/**
 * 500qps，测试HttpAsynClient和HttpClient的性能
 * @author caijianfu02@meituan.com
 * @date 2015年10月09日11:23:45
 */
public class HttpClientPerformanceAnalysis {
    private final static Logger LOG = LoggerFactory.getLogger(HttpClientPerformanceAnalysis.class);
    private final static String URL = "http://cis.sankuai.com/open/home";
    private final static RateLimiter RATE_LIMITER = RateLimiter.create(50);
    private final static ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    private static int completeCounter = 0;
    private static int failedCounter = 0;

    public void testHttpAsynClient() throws Exception {
        new Thread(new HttpAsynClientTask(10000)).start();
    }

    public void testHttpClient() {
        long start = System.currentTimeMillis();
        int taskNum = 100;
        CountDownLatch latch = new CountDownLatch(taskNum);

//        while(true) {
//            RATE_LIMITER.acquire();
//            EXECUTOR_SERVICE.submit(new HttpClientTask());
//        }
        for(int i = 0; i < 100; i++){
            EXECUTOR_SERVICE.submit(new HttpClientTask(latch , URL));
        }
        try{
            latch.await();
            LOG.info("HttpClient consume:" + (System.currentTimeMillis() - start));
        }catch (Exception e){

        }

    }

    private static class HttpAsynClientTask implements Runnable{
        private int taskNum = 100;
        public HttpAsynClientTask(int taskNum){
            this.taskNum = taskNum;
        }
        public void run() {
            long start = System.currentTimeMillis();
            CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
            httpClient.start();
            //CountDownLatch:A synchronization aid that allows one or more threads to wait until
            //a set of operations being performed in other threads completes.
            final CountDownLatch latch = new CountDownLatch(taskNum);
            for(int i = 0; i < taskNum; i++){
                HttpGet request = new HttpGet(URL);
                httpClient.execute(request, new FutureCallback<HttpResponse>() {
                    public void completed(final HttpResponse response) {
                        latch.countDown();
                        //System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
                        LOG.info("Finish num:" + ++completeCounter);
                    }
                    public void failed(final Exception ex) {
                        latch.countDown();
                        //System.out.println(request.getRequestLine() + "->" + ex);
                        LOG.info("Failed num:" + ++failedCounter , ex);
                    }

                    public void cancelled() {
                        latch.countDown();
                        //System.out.println(request.getRequestLine() + " cancelled");
                    }
                });
            }
            try {
                latch.await();
                LOG.info("HttpAsynClient Consume time:" + (System.currentTimeMillis() - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try{
                httpClient.close();
            }catch (Exception e){
                LOG.error("Release resources error:" , e);
            }
        }
    }

    static class TaskCallback implements FutureCallback<HttpResponse>{
        private static int completeCounter = 0;
        private static int failedCounter = 0;
        public void completed(final HttpResponse response) {
           // latch.countDown();
            //System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
            System.out.println("Finish num:" + ++completeCounter);
            LOG.info("Finish num:" + ++completeCounter);
        }
        public void failed(final Exception ex) {
            LOG.info("Failed num:" + ++failedCounter);
            //latch.countDown();
            //System.out.println(request.getRequestLine() + "->" + ex);
        }

        public void cancelled() {
            //latch.countDown();
            //System.out.println(request.getRequestLine() + " cancelled");
        }
    }

    /**
     * 回调类，处理请求后的Response
     */
    static class ResponseConsumer extends AsyncCharConsumer<Boolean> {
        private static int counter = 0;
        @Override
        protected void onCharReceived(CharBuffer charBuffer, IOControl ioControl) throws IOException {
            counter++;
            StringBuilder builder = new StringBuilder();
            while(charBuffer.hasRemaining()){
                builder.append(charBuffer.get());
            }
            LOG.info("Finished request:" + counter);
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

    /**
     * HttpClient的执行线程
     */
    private static class HttpClientTask implements Runnable {
        private String url;
        private final CountDownLatch latch;
        public HttpClientTask(CountDownLatch latch , String url){
            this.url = url;
            this.latch = latch;
        }
        public void run() {
            //long start = System.currentTimeMillis();
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url);
            try {
                HttpResponse resp = client.execute(httpget);
                EntityUtils.toString(resp.getEntity());
                client.close();
            } catch (IOException e) {
                LOG.error("Failed", e);
            }finally {
                latch.countDown();
            }
            //LOG.info("Cousume time:" + (System.currentTimeMillis() - start));
        }
    }

    public static void main (String[] args) throws Exception {
        HttpClientPerformanceAnalysis test = new HttpClientPerformanceAnalysis();

        /*
        if("httpasynclient".equalsIgnoreCase(args[args.length - 1])) {
            LOG.info("starting httpasynclient test");
            test.testHttpAsynClient();
        } else if ("httpclient".equalsIgnoreCase(args[args.length - 1])) {
            LOG.info("starting httpclient test");
            test.testHttpClient();
        } else {
            LOG.error("invalid arguments, exit");
            return;
        }
        */
        LOG.info("starting httpasynclient test");
        test.testHttpAsynClient();
//        LOG.info("starting httpclient test");
//        test.testHttpClient();
    }
}
