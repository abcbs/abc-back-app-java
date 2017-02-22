package com.ndlan.ieream.rest;
/**
*Controller Type:UsersGetupdateinfoBean
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

public class UsersGetupdateinfoBeanBeanControllerBustinessRestful {

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
获取APP版本更新信息
**/
	public  UsersGetupdateinfoBean getupdateinfo(  String  version_type ,   Integer  version_id){
		final String path=url+"helpers.php?action=getupdateinfo";
		final StringBuffer query=new StringBuffer();
		query.append("");
		if(StringUtils.isNotEmpty(version_type)){
			query.append("&");
			query.append("version_type");
			query.append("=");
			query.append(version_type);
		}
		if(version_id!=null){
			query.append("&");
			query.append("version_id");
			query.append("=");
			query.append(version_id);
		}
		
		FutureTask< UsersGetupdateinfoBean > future = new FutureTask< UsersGetupdateinfoBean >(new Callable<  UsersGetupdateinfoBean >() {
			public  UsersGetupdateinfoBean  call() {
				try{
					ResponseEntity< UsersGetupdateinfoBean > response = 
					restTemplate.getForEntity(path+query.toString(),  UsersGetupdateinfoBean .class);
					
					return response.getBody();
				}catch(Exception e){
					e.printStackTrace();
					return new  UsersGetupdateinfoBean ();
				}
			}
		});
		executorService.execute(future);
	         UsersGetupdateinfoBean  result = null;
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


