package com.buildhappy.concurrent.concurrentitcast.forkAndjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import static junit.framework.Assert.assertEquals;

/**
 * Author: zhongxin
 * Created at: 2010-4-14 13:40:58
 */
public class CalculatorTest { 

    //@Test
    public void run() throws Exception{
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> result = forkJoinPool.submit(new Calculator(0, 10000));
        assertEquals(new Integer(49995000), result.get());
    }


}



