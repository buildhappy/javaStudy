package com.buildhappy.trafficControl;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * Created by caijianfu02 on 16/9/19.
 */
public class GuavaTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
//        RateLimiter limiter = RateLimiter.create(3);
//        Thread.sleep(2000);
//        for(int i = 0; i < 10; i++){
//            System.out.println(limiter.acquire());
//        }
//        System.out.println(limiter.acquire());

        RateLimiter limiter = RateLimiter.create(3, 2000, TimeUnit.MILLISECONDS);
        System.out.println(limiter.acquire(3));
        Thread.sleep(1000 * 2);
        System.out.println(limiter.acquire(3));
        System.out.println(limiter.acquire(3));
        System.out.println(limiter.acquire(3));
        System.out.println(limiter.acquire(3));

    }
}
