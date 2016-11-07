package com.buildhappy.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by caijianfu02 on 16/6/29.
 */
public class ThreadLocalTest {


    public static void main(String[] args) throws InterruptedException {
        System.out.println(new DataFormatForThread().formatIt(new Date()));
        new Thread(){
            public void run() {
                System.out.println(new DataFormatForThread().formatIt(new Date()));
            }
        }.start();
        new Thread(){
            public void run() {
                //System.out.println("Thread name:" + Thread.currentThread().getName());
                System.out.println(new DataFormatForThread().formatIt(new Date()));
            }
        }.start();
        Thread.sleep(1000);
    }

    /**
     * 将ThreadLocal封装在一个类中,保存SimpleDateFormat数据,每一个访问的线程都单独拥有一个自己的副本
     */
    private static class DataFormatForThread{
        private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>(){
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat("yyyyMMdd HHmm");
            }
        };
        public String formatIt(Date date) {
            System.out.println("Thread name:" + Thread.currentThread().getName());
            return formatter.get().format(date);
        }
    }
}
