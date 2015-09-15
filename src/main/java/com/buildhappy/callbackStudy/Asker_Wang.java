package com.buildhappy.callbackStudy;


/**
 * 问题发起者
 * 实现了一个回调接口CallBack，相当于----->背景一
 * Created by buildhappy on 15/9/10.
 */
public class Asker_Wang implements CallBack{
    /**
     * 小李对象的引用
     * 相当于----->背景二
     */
    private Answer_Li li;
    /**
     * 小王的构造方法，持有小李的引用
     * @param li
     */
    public Asker_Wang(Answer_Li li){
        this.li = li;
    }

    /**
     * 小王通过这个方法去问小李的问题
     * @param question  就是小王要问的问题,1 + 1 = ?
     */
    public void askQuestion(final String question){
        //此处开启一个线程实现异步
        new Thread(){
            public void run(){
                /**
                 * 小王调用小李中的方法，在这里注册回调接口
                 * 这就相当于A类调用B的方法C
                 */
                li.executeMessage(Asker_Wang.this , question);
            }
        }.start();

    }
    public void play(){
        System.out.println("Outgoing...");
    }
    public void solve(String result) {
        System.out.println("The answer from Answer_Li is:" + result);
    }
}
