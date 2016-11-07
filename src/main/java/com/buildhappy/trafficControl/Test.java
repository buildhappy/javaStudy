package com.buildhappy.trafficControl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;

import java.util.*;
import java.util.concurrent.*;

import static java.util.Arrays.asList;

/**
 * Created by caijianfu02 on 16/6/15.
 */
public class Test {
    public static void main(String[] args){
        final ArrayBlockingQueue<Runnable> runnableTaskQueue = new ArrayBlockingQueue<Runnable>(100);
        ThreadPoolExecutor executorService =
                new ThreadPoolExecutor(0, 10, 60L,
                        TimeUnit.SECONDS, runnableTaskQueue, new RejectedExecutionHandlerImpl());
        executorService.submit(new Runnable() {
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                        println("runnableTaskQueue size:" + runnableTaskQueue.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
//        while(true){
//            executorService.submit(new Runnable() {
//                public void run() {
//                    while(true){
//                        try {
//                            Thread.sleep(1000000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            });
//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        for(int i = 0; i < 10000000; i++){
            executorService.submit(new Runnable() {
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(100000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void test(){
        //Executors.newCachedThreadPool()
        new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        Queue taskQueue = new SynchronousQueue<Runnable>();
        taskQueue.size();
    }

    public static void println(Object o){
        System.out.println(o);
    }

    static class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(r.toString() + " is rejected");
        }

    }
}