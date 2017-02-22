package com.ndlan.ieream.rest;
/**
*Controller Type:GuidesBean
 霞客
*/
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.ndlan.framework.core.web.domain.Result;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.ndlan.ieream.model.*;
import com.ndlan.ieream.query.*;

public class GuidesBeanBeanControllerBustinessRestful {

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
	public  GuidesGetRellistBean getList(  Integer  p ,   Integer  psize ,   String  data_order){
		final String path=url+"guides.php?action=getList";
		final StringBuffer query=new StringBuffer();
		query.append("");
		if(p!=null){
			query.append("&");
			query.append("p");
			query.append("=");
			query.append(p);
		}
		if(psize!=null){
			query.append("&");
			query.append("psize");
			query.append("=");
			query.append(psize);
		}
		if(StringUtils.isNotEmpty(data_order)){
			query.append("&");
			query.append("data_order");
			query.append("=");
			query.append(data_order);
		}
		
		FutureTask< GuidesGetRellistBean > future = new FutureTask< GuidesGetRellistBean >(new Callable<  GuidesGetRellistBean >() {
			public  GuidesGetRellistBean  call() {
				try{
					ResponseEntity< GuidesGetRellistBean > response = 
					restTemplate.getForEntity(path+query.toString(),  GuidesGetRellistBean .class);
					
					return response.getBody();
				}catch(Exception e){
					e.printStackTrace();
					return new  GuidesGetRellistBean ();
				}
			}
		});
		executorService.execute(future);
	         GuidesGetRellistBean  result = null;
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
	public  GuidesGetItemBean getItemById(  Integer  id){
		final String path=url+"guides.php?action=getItemById";
		final StringBuffer query=new StringBuffer();
		query.append("");
		if(id!=null){
			query.append("&");
			query.append("id");
			query.append("=");
			query.append(id);
		}
		
		FutureTask< GuidesGetItemBean > future = new FutureTask< GuidesGetItemBean >(new Callable<  GuidesGetItemBean >() {
			public  GuidesGetItemBean  call() {
				try{
					ResponseEntity< GuidesGetItemBean > response = 
					restTemplate.getForEntity(path+query.toString(),  GuidesGetItemBean .class);
					
					return response.getBody();
				}catch(Exception e){
					e.printStackTrace();
					return new  GuidesGetItemBean ();
				}
			}
		});
		executorService.execute(future);
	         GuidesGetItemBean  result = null;
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
	public  GuidesGetRellistBean getRelList(  Integer  uid ,   Integer  p ,   Integer  psize){
		final String path=url+"guides.php?action=getRelList";
		final StringBuffer query=new StringBuffer();
		query.append("");
		if(uid!=null){
			query.append("&");
			query.append("uid");
			query.append("=");
			query.append(uid);
		}
		if(p!=null){
			query.append("&");
			query.append("p");
			query.append("=");
			query.append(p);
		}
		if(psize!=null){
			query.append("&");
			query.append("psize");
			query.append("=");
			query.append(psize);
		}
		
		FutureTask< GuidesGetRellistBean > future = new FutureTask< GuidesGetRellistBean >(new Callable<  GuidesGetRellistBean >() {
			public  GuidesGetRellistBean  call() {
				try{
					ResponseEntity< GuidesGetRellistBean > response = 
					restTemplate.getForEntity(path+query.toString(),  GuidesGetRellistBean .class);
					
					return response.getBody();
				}catch(Exception e){
					e.printStackTrace();
					return new  GuidesGetRellistBean ();
				}
			}
		});
		executorService.execute(future);
	         GuidesGetRellistBean  result = null;
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


