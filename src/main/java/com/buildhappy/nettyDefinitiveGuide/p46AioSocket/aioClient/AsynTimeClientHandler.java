package com.buildhappy.nettyDefinitiveGuide.p46AioSocket.aioClient;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created by buildhappy on 15/9/5.
 */
public class AsynTimeClientHandler implements Runnable , CompletionHandler<Void , AsynTimeClientHandler> {
    private AsynchronousSocketChannel client;
    private String host;
    private int port;
    private CountDownLatch latch;

    public AsynTimeClientHandler(String host , int port){
        this.host = host;
        this.port = port;
        try{
            this.client = AsynchronousSocketChannel.open();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void completed(Void result, AsynTimeClientHandler attachment) {
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        client.write(writeBuffer , writeBuffer , new CompletionHandler<Integer , ByteBuffer>(){
            public void completed(Integer result , final ByteBuffer buffer){
                if(buffer.hasRemaining()){
                    client.write(buffer , buffer , this);
                }else{
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    client.read(readBuffer, readBuffer,
                            new CompletionHandler<Integer, ByteBuffer>() {
                                public void completed(Integer result, ByteBuffer attachment) {
                                    buffer.flip();
                                    byte[] bytes = new byte[buffer.remaining()];
                                    buffer.get(bytes);
                                    String body;
                                    try {
                                        body = new String(bytes , "UTF-8");
                                        System.out.println("Now is:" + body);
                                        latch.countDown();
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }

                                public void failed(Throwable exc, ByteBuffer attachment) {

                                }
                            });
                }
            }
            public void failed(Throwable exc , ByteBuffer attachment){
                try{
                    client.close();
                    latch.countDown();
                }catch (IOException e){

                }
            }
        });
    }

    public void failed(Throwable exc, AsynTimeClientHandler attachment) {

    }

    public void run() {
        latch = new CountDownLatch(1);
        client.connect(new InetSocketAddress(host , port) , this , this);

        try{
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        try {
            client.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
