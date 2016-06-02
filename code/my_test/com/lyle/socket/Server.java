package com.lyle.socket;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws Exception {
		int port=8080;
		ServerSocket server=new ServerSocket(port);
		//自定端口，创建服务端Socket
		System.out.println("开始监听，端口号："+port);
		while(true){
            Socket client=server.accept();//监听获取客户端请求socket，accept方法是一个阻塞方法，在客户端和服务端之间建立联系之前一直等待阻塞
			System.out.println(client.getInetAddress());
			//返回客户端地址
            byte requestbuffer[]=new byte[4096];//准备读取客户端请求的数据，读取数据保存在buffer数组
			
			String requestString=new String(requestbuffer,"UTF-8");
			System.out.println(requestString);//返回请求数据
			
			OutputStream out=client.getOutputStream();
			
			String statusLine = "HTTP/1.1 200 OK\r\n";
			byte[] statusLineBytes = statusLine.getBytes("UTF-8");
			String responseBody= "<html>"
					+ "<head>"
					+ "	<title>"
					+ "From Socket Server"
					+ "</title>"
					+ "</head>"
					+ "<body>"
					+ "<h1>"
					+ "Hello, world."
					+ "</h1>"
					+ "</body>"
					+ "</html>";
            byte[] responseBodyBytes = responseBody.getBytes("UTF-8");
            
            // 回应的头部
            String responseHeader ="Content-Type: text/html; charset=UTF-8\r\nContent-Length: "+responseBody.length()+"\r\n";
            
            byte[] responseHeaderBytes = responseHeader.getBytes("UTF-8");
         // 向客户端发送状态信息
			out.write(statusLineBytes);
			// 向客户端发送回应头
			out.write(responseHeaderBytes);
			// 头部与内容的分隔行
			out.write(new byte[] { 13, 10 });
			// 向客户端发送内容部分
			out.write(responseBodyBytes);
			// 断开与客户端的连接
			client.close();
            
	}
}
}