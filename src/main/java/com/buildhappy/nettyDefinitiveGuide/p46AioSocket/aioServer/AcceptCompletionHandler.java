package com.buildhappy.nettyDefinitiveGuide.p46AioSocket.aioServer;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * ComplementHandler is a handler for consuming the result of an asynchronous I/O operation.
 *   V – The result type of the I/O operation
 *   A – The type of the object attached to the I/O operation
 * Created by buildhappy on 15/9/4.
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel , AsynTimeServerHandler> {

    public void completed(AsynchronousSocketChannel result, AsynTimeServerHandler attachment) {
        attachment.asynchronousServerSocketChannel.accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //使用read方法进行异步的读取，参数说明：
        //参数1:接受缓冲区，用于从异步Channel中读取数据包
        //参数2:异步channel携带的附件，通过回调的时候作为入参使用
        //参数3:接受通知回调的业务Handler
        result.read(buffer , buffer , new ReadCompletionHandler(result));
    }

    public void failed(Throwable exc, AsynTimeServerHandler attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}
