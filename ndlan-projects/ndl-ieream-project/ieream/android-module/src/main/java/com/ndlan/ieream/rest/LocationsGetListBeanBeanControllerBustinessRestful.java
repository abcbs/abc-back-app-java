package com.ndlan.ieream.rest;
/**
*Controller Type:LocationsGetListBean
 获 获取位置列表
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

public class LocationsGetListBeanBeanControllerBustinessRestful {

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
获 获取位置列表
**/
	public  LocationsGetListBean getlist(  Integer  p ,   Integer  psize ,   String  loc_type ,   String  loc_order ,   String  data_type){
		final String path=url+"locations.php?action=getlist";
		
		
		
			loc_type="ream";
		
			loc_order="asc";
		
			data_type="exact";
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
		
		if(StringUtils.isNotEmpty(loc_type)){
			query.append("&");
			query.append("loc_type");
			query.append("=");
			query.append(loc_type);
		}
		
		if(StringUtils.isNotEmpty(loc_order)){
			query.append("&");
			query.append("loc_order");
			query.append("=");
			query.append(loc_order);
		}
		
		if(StringUtils.isNotEmpty(data_type)){
			query.append("&");
			query.append("data_type");
			query.append("=");
			query.append(data_type);
		}
		
		FutureTask< LocationsGetListBean > future = new FutureTask< LocationsGetListBean >(new Callable<  LocationsGetListBean >() {
			public  LocationsGetListBean  call() {
				try{
					String currentPath=path+query.toString();
					ResponseEntity< LocationsGetListBean > response = 
					restTemplate.getForEntity(currentPath,  LocationsGetListBean .class);
					
					return response.getBody();
				}catch(Exception e){
					e.printStackTrace();
					return new  LocationsGetListBean ();
				}
			}
		});
		executorService.execute(future);
	         LocationsGetListBean  result = null;
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


