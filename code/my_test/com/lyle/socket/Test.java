package com.lyle.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress host = InetAddress.getByName("127.0.0.1");
		System.out.println(host);//        /127.0.0.1
		System.out.println(System.getProperty("user.dir"));//D:\workspace\tomcat7
	}
}
