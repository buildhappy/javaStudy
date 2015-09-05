package com.buildhappy.nettyDefinitiveGuide.p30NioSocket.nioServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO 时间服务器
 * Created by buildhappy on 15/9/4.
 */
public class MultiplexerTimeServer implements Runnable {
    private Selector selector;
    private ServerSocketChannel servChannel;
    private volatile boolean stop;

    public MultiplexerTimeServer(int port) {
        try{
            selector = Selector.open();
            servChannel = ServerSocketChannel.open();
            //异步非阻塞模式
            servChannel.configureBlocking(false);
            servChannel.socket().bind(new InetSocketAddress(port), 1024);//requested maximum length of the queue of incoming connections.
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port:" + port);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop(){
        this.stop = true;
    }

    public void run() {
        while(!stop){
            try{
                selector.select(1000);//无论是否有读写等事件发生，selector每隔1秒钟被唤醒一次
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while(it.hasNext()){
                    key = it.next();
                    it.remove();
                    try{
                        handleInput(key);
                    }catch (Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null)
                                key.channel().close();
                        }
                    }
                }
            }catch (Throwable t){
                t.printStackTrace();
            }
        }
        //多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if(selector != null){
            try{
                selector.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理请求
     * @param key
     */
    public void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            //处理新接入的请求消息
            if(key.isAcceptable()){
                //accept the new connection
                ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //add the new connection to the selector
                sc.register(selector , SelectionKey.OP_READ);//将socket事件注册到selector中
            }
            if(key.isReadable()){
                //read the data
                SocketChannel sc = (SocketChannel)key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);//1M的缓存
                int readBytes = sc.read(readBuffer);
                //返回值>0：读到了字节，队字节进行编码解码
                //返回值<0：没有读取到字节，属于正常场景，直接忽略
                //返回为－1：链路已经关闭，需要关闭SocketChannel，释放资源
                if(readBytes > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes , "UTF-8");
                    System.out.println("The time server receive order:" + body);
                    String currentTime = "QUERY TIME ORDER".equals(body) ?
                                        new java.util.Date(System.currentTimeMillis()).toString():
                                        "BAD ORDER";
                    doWrite(sc , currentTime);//将时间返回给client
                }else if(readBytes < 0){
                    //对端链路关闭
                    key.cancel();
                    sc.close();
                }else{
                    //读到0字节，直接忽略
                }
            }
        }
    }

    /**
     * 向客户端返回应答，以异步的方式
     * @param channel
     * @param response
     * @throws IOException
     */
    private void doWrite(SocketChannel channel , String response) throws IOException {
        if(response != null && response.trim().length() > 0){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}
