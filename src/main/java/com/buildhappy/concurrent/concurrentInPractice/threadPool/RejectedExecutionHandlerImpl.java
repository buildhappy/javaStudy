package com.buildhappy.concurrent.concurrentInPractice.threadPool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 我们可以指定创建 ThreadPoolExecutor 实例时活跃的线程数，并且可以限制线程池的大小，
 * 还可以创建自己的 RejectedExecutionHandler 实现来处理不适合放在工作队列里的任务。
 * @author Administrator
 *
 */
public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {
 
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(r.toString() + " is rejected");
    }
 
}