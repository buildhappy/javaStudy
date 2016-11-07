package com.buildhappy.test;

import com.google.common.collect.Lists;
import it.sauronsoftware.cron4j.Scheduler;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by caijianfu02 on 16/5/3.
 */
public class TimerTest {
    //private static Timer timer = new Timer();
    public static void main(String[] args){
        Pattern pattern = Pattern.compile("已领[0-9]*");
//        Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
        Matcher matcher = pattern.matcher("已领23");
        println(matcher.matches());
        matcher = pattern.matcher("23");
        println(matcher.matches());
        matcher = pattern.matcher("qwrg12");
        println(matcher.matches());
        matcher = pattern.matcher("123fsfd");
        println(matcher.matches());

        String drawInfo = "已享122";
        println("原始:" + drawInfo);
        String salesMsg = drawInfo.substring(2 , drawInfo.length());
        println("salesMsg:" + salesMsg);
        //转换成旧版支付宝的销量信息格式
        drawInfo = new StringBuffer(salesMsg).append("人").append(drawInfo.substring(0 , 2)).toString();
        println(drawInfo);
    }

    public static void println(Object o){
        System.out.println(o);
    }
}
