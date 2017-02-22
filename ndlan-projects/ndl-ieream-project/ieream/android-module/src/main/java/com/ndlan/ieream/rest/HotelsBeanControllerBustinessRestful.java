package com.ndlan.ieream.rest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.ndlan.ieream.model.GuidesGetListBean;
import com.ndlan.ieream.model.HotelsGetItemBean;
import com.ndlan.ieream.model.HotelsGetRellistBean;
import com.ndlan.ieream.model.HotelsSearchBean;

public class HotelsBeanControllerBustinessRestful {
	private static final String url = BaseAndroidRestfulConfig.moduleServicePath;
	MediaType mediaType = MediaType.APPLICATION_JSON;

	private static final ExecutorService executorService = Executors.newCachedThreadPool();
	private final ObjectMapper mapper ;
	{
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(SerializationFeature.WRAP_EXCEPTIONS, false);

	}
	private static final RestTemplate restTemplate = new RestTemplate();

	{
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}

	/**
获取霞客列表
**/
	public  HotelsSearchBean search(  Integer  p ,   Integer  dataType){
		final String path=url+"hotels.php?action=search";
	
		FutureTask< HotelsSearchBean > future = new FutureTask< HotelsSearchBean >(new Callable<  HotelsSearchBean >() {
			public  HotelsSearchBean  call() {
				try{
					ResponseEntity< HotelsSearchBean > response = 
					restTemplate.getForEntity(path+"&filter=locations:330000;tags:9;keywords:民宿",  HotelsSearchBean.class);
					HotelsSearchBean rest=response.getBody();
					return rest;
				}catch(Exception e){
					e.printStackTrace();
					return new  HotelsSearchBean ();
				}
			}
		});
		executorService.execute(future);
		HotelsSearchBean  result = null;
		try {
			result = future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return result;

	}
	public  GuidesGetListBean getList(  Integer  p ,   String  dataOrder){
		final String path=url+"hotels.php?action=getList";
	
		FutureTask< GuidesGetListBean > future = new FutureTask< GuidesGetListBean >(new Callable<  GuidesGetListBean >() {
			public  GuidesGetListBean  call() {
				try{
					ResponseEntity< GuidesGetListBean > response = 
					restTemplate.getForEntity(path,  GuidesGetListBean.class);
					GuidesGetListBean rest=response.getBody();
					return rest;
				}catch(Exception e){
					e.printStackTrace();
					return new  GuidesGetListBean ();
				}
			}
		});
		executorService.execute(future);
		GuidesGetListBean  result = null;
		try {
			result = future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return result;

	}
	
	/**
获取霞客相关列表信息(合伙霞客列表)
**/
	public  HotelsGetRellistBean getRelList(  Integer  uid ,   Integer  dataType){
		final String path=url+"hotels.php?action=getRelList";
	
		FutureTask< HotelsGetRellistBean > future = new FutureTask< HotelsGetRellistBean >(new Callable<  HotelsGetRellistBean >() {
			public  HotelsGetRellistBean  call() {
				try{
					ResponseEntity< HotelsGetRellistBean > response = 
					restTemplate.getForEntity(path+"&id=6",  HotelsGetRellistBean.class);
					
					return response.getBody();
				}catch(Exception e){
					e.printStackTrace();
					return new  HotelsGetRellistBean ();
				}
			}
		});
		executorService.execute(future);
		HotelsGetRellistBean  result = null;
		try {
			result = future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return result;

	}
	
	/**
获取霞客信
**/
	public  HotelsGetItemBean getitem(Integer  id){
		final String path=url+"hotels.php?action=getitem";
	//http://www.ieream.com/mapi/1/hotels.php?action=getitem&id=1
		FutureTask< HotelsGetItemBean > future = new FutureTask< HotelsGetItemBean >(new Callable<  HotelsGetItemBean >() {
			public  HotelsGetItemBean  call() {
				try{
					ResponseEntity< HotelsGetItemBean > response = 
					restTemplate.getForEntity(path+"&id=1",  HotelsGetItemBean .class);
					
					return response.getBody();
				}catch(Exception e){
					e.printStackTrace();
					return new  HotelsGetItemBean ();
				}
			}
		});
		executorService.execute(future);
		HotelsGetItemBean  result = null;
		try {
			result = future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return result;

	}
}
