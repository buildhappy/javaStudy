package com.buildhappy.nio.selector;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Selector（选择器）是Java NIO中能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。
 * 这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。
 * 
 * @author buildhappy
 *
 */
public class Selector_Test {

	public static void main(String[] args) throws IOException {
		Selector selector = Selector.open();
		
		//DatagramChannel channel = DatagramChannel.open();
		SocketChannel channel = SocketChannel.open();
		
		channel.socket().connect(new InetSocketAddress("localhost" , 10010));
		//为了将Channel和Selector配合使用，必须将channel注册到selector上。
		//通过SelectableChannel.register()方法来实现
		channel.configureBlocking(false);
		SelectionKey keys = channel.register(selector, SelectionKey.OP_READ);
		
		//与Selector一起使用时，Channel必须处于非阻塞模式下。
		//这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以
		//FileChannel fileChannel = new RandomAccessFile(new File("data.txt"), "rw").getChannel();
		//fileChannel.configureBlocking(false);
		
		while(true){
			
			int readyChannels = selector.select();//阻塞到至少有一个通道在你注册的事件上就绪了。 
			if(readyChannels == 0) continue;
			Set<SelectionKey> selectorKeys = selector.selectedKeys();
			Iterator<SelectionKey> it = selectorKeys.iterator();
			
			while(it.hasNext()){
				SelectionKey key = it.next();
				if(key.isAcceptable()){
					System.out.println("isAcceptable");
				}else if(key.isConnectable()){
					System.out.println("isConnectable");
				}else if(key.isReadable()){
					System.out.println("isReadable");
				}else{
					System.out.println("fafas");
				}
			}
		}
		
	}

}
