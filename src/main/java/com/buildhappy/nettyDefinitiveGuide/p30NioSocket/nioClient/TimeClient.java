package com.buildhappy.nettyDefinitiveGuide.p30NioSocket.nioClient;

/**
 * Created by buildhappy on 15/9/4.
 */
public class TimeClient {
    public static void main(String[] args){
        new Thread(new TimeClientHandle("127.0.0.1" , 8080) , "TimeClient001");
    }
}
