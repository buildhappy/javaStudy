package com.buildhappy.callbackStudy;

/**
 * 测试启动类
 * Created by buildhappy on 15/9/10.
 */
public class Starter {
    public static void main(String[] args){
        Answer_Li li = new Answer_Li();
        Asker_Wang wang = new Asker_Wang(li);
        wang.askQuestion("1+1=?");
    }
}
