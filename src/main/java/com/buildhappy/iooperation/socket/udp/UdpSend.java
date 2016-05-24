package com.buildhappy.iooperation.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class  UdpSend{
	public static void main(String[] args) throws Exception{
		//1,创建udp服务。通过DatagramSocket对象
		DatagramSocket ds = new DatagramSocket(8888);

		//2,确定数据，并封装成数据包
		//DatagramPacket(byte[] buf, int length, InetAddress address, int port) 
		byte[] buf = "udp ge men lai le ".getBytes();
		DatagramPacket dp = 
			new DatagramPacket(buf , buf.length ,InetAddress.getByName("localhost") , 10000);
		//3,通过socket服务，将已有的数据包发送出去。通过send方法
		ds.send(dp);
		//4,关闭资源
		ds.close();
	}
}