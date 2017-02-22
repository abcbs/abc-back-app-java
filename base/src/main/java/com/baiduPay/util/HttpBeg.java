package com.baiduPay.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpBeg {
	
	
	public Map<Object,Object>  networkRequest(String json){
		Map<Object,Object> productMap=null;
		try {
			URL url = new URL(json);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
	        connection.connect();// 连接会话
	     // 获取输入流
	        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {// 循环读取流
	            sb.append(line);
	        }
	        br.close();// 关闭流
	        connection.disconnect();// 断开连接
	        System.out.println(sb.toString());
	        ObjectMapper mapper = new ObjectMapper();  
	        productMap= mapper.readValue(sb.toString(), Map.class);//转成map
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
        
		return productMap;
	}
	
	
	public String  networkXml(String json){
		Map<Object,Object> productMap=null;
		try {
			URL url = new URL(json);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
	        connection.connect();// 连接会话
	     // 获取输入流
	        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {// 循环读取流
	            sb.append(line);
	        }
	        br.close();// 关闭流
	        connection.disconnect();// 断开连接
	        System.out.println(sb.toString());
	        return sb.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
        return "";
	}

}
