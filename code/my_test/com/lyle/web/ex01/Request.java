package com.lyle.web.ex01;

import java.io.IOException;
import java.io.InputStream;

public class Request {

	private InputStream input;
	
	private String uri;

	public Request(InputStream input) {
		this.input = input;
	}
	
	public void parse(){
		//read a set of characters from the socket
		StringBuffer request = new StringBuffer(2048);
		int i;
		byte[] buffer = new byte[2048];
		try {
			i = input.read(buffer);
		} catch (IOException e) {
			i = -1;
			e.printStackTrace();
		}
		//TODO j<i写成i<i导致错误
		for(int j=0;j<i;j++){
			request.append((char)buffer[j]);
		}
		System.out.println(request.toString());

//		GET /index.html HTTP/1.1
//		Host: localhost:8080
//		Connection: keep-alive
//		Cache-Control: max-age=0
//		Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
//		Upgrade-Insecure-Requests: 1
//		User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36
//		Accept-Encoding: gzip, deflate, sdch
//		Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.6
		
		
		uri = parseUri(request.toString());
	}
	
	private String parseUri(String requestString){
		int index1,index2;
		index1 = requestString.indexOf(' ');
		if(index1 != -1){
			index2 = requestString.indexOf(' ', index1+1);
			if(index2>index1){
				return requestString.substring(index1+1, index2);
			}
		}
		return null;
	}
	
	public String getUri(){
		return uri;
	}
	
}
