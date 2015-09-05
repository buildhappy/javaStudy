package com.buildhappy.nettyDefinitiveGuide.p46AioSocket.aioServer;

/**
 * 服务端的启动类
 * Created by buildhappy on 15/9/4.
 */
public class TimeServer {
    public static void main(String[] args){
        int port = 8080;
        new Thread(new AsynTimeServerHandler(port)).start();
    }
}
