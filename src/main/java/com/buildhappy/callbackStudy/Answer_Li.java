package com.buildhappy.callbackStudy;

/**
 * Created by buildhappy on 15/9/10.
 */
public class Answer_Li {
    public void executeMessage(CallBack callBack , String question) {
        System.out.println("The question:" + question);
        try{
            Thread.sleep(1000);//长时间的运算
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        String result = "Anwser=2";
        /**
         * 于是就打电话告诉小王，调用小王中的方法
         * 这就相当于B类反过来调用A的方法D
         */
        callBack.solve(result);
    }
}
