package com.lyle.web.ex01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
	
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	
	private boolean shutdown = false;
	
	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		System.out.println(WEB_ROOT);
		server.await();
	}
	
	public void await() { 
		
		ServerSocket server = null;
		int port = 8080;
		try{
			server = new ServerSocket(port,1,InetAddress.getByName("127.0.0.1"));
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		//loop(循环) waiting for a request
		while(!shutdown){
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				System.out.println("accept之前");
				socket = server.accept();
				System.out.println("accept之后");
				input = socket.getInputStream();
				output = socket.getOutputStream();
				//create request object and parse
				Request request = new Request(input);
				request.parse();
				//create response object
				Response response = new Response(output);
				response.setRequest(request);
				response.sendStaticResource();
				//close the socket
				socket.close();
				//check if the previous URI is a shutdown command 
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
				System.out.println("服务器关闭");
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}
}
