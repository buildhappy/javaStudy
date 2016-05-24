package com.buildhappy.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

/**
 * Created by caijianfu02 on 16/5/3.
 */
public class TimerTest {
    private static Timer timer = new Timer();
    private static final long SYNC_LOOP_MILLIS = 10000L;

    private static final long START_DELAY_MILLIS = 1000L;
    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(1);
        //ThreadLocal threadLocal = new ThreadLocal();
        Map<String , Object> ops = new HashMap<String , Object>();
        ops.put("status", "1");
        ops.put("countdownlatch" , latch);
        println(ops.get("status"));
        println(ops.get("countdownlatch"));
    }

    public static void println(Object o){
        System.out.println(o);
    }

    static class Thread1 implements Runnable{
        Integer i = new Integer(2);
        public void run() {
            new Thread(new Thread2(i)).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            println(i);
        }
    }

    static class Thread2 implements Runnable{
        Integer i;
        public Thread2(Integer i){
            this.i = i;
        }

        public void run() {
            println("thread2:" + i);
            i = new Integer(3);
        }
    }
}
