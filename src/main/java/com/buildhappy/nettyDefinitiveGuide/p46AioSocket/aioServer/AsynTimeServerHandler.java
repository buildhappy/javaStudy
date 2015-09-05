package com.buildhappy.nettyDefinitiveGuide.p46AioSocket.aioServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * Created by buildhappy on 15/9/4.
 */
public class AsynTimeServerHandler implements Runnable{
    private int port;
    CountDownLatch latch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AsynTimeServerHandler(int port){
        this.port = port;
        try{
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void run() {
        latch = new CountDownLatch(1);
        doAccept();
        try{
            //完成一组正在执行的操作之前，允许当前的线程一直阻塞
            //让线程在此阻塞防止服务执行完成退出
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void doAccept(){
        asynchronousServerSocketChannel.accept(this , new AcceptCompletionHandler());
    }
}
