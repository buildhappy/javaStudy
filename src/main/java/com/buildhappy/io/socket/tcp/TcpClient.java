package com.buildhappy.io.socket.tcp;
/**
演示tcp传输
1,tcp分客户端和服务端。
2,客户端对应的对象是Socket。
	服务端对应的对象是ServerSocket。

客户端，
通过查阅socket对象，发现在该对象建立时，就可以去连接指定主机。
因为tcp是面向连接的。所以在建立socket服务时，
就要有服务端存在，并连接成功。形成通路后，在该通道进行数据的传输。

需求：给服务端发送给一个文本数据。

步骤：
1，创建Socket服务,并指定要连接的主机和端口
*/
import java.io.*;
import java.net.*;

class  TcpClient{
	public static void main(String[] args) throws Exception{
		//创建客户端的socket服务。指定目的主机和端口
		Socket s = new Socket("localhost" , 10003);
		
		//为了发送数据，应该获取socket流中的输出流。(socket既有输出流也有输入流)
		OutputStream out = s.getOutputStream();

		out.write("tcp ge men lai le ".getBytes());

		s.close();
	}
}