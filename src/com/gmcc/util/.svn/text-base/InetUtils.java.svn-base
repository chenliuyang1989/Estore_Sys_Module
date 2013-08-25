package com.gmcc.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class InetUtils {

	public static void main(String[] args) {
		try{
			System.out.println(InetUtils.getLocalIp());
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
	}
	
	public static String getLocalIp() throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		return addr.getHostAddress();
	}
	public static  String getRemortIP() {  
		HttpServletRequest request=ServletActionContext.getRequest();
//	    if (request.getHeader("x-forwarded-for") == null) {  
//	        return ServletActionContext.getRequest().getRemoteAddr();  
//	    }  else{
	    	String ip = request.getHeader("x-forwarded-for");  
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("Proxy-Client-IP");  
	        }  
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	        }  
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getRemoteAddr();  
	        }  
	        return ip;  
//	    }  
	}  
}
