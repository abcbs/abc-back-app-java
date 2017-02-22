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
import com.ndlan.ieream.model.HotelsGetItemBean;
import com.ndlan.ieream.model.UsersGetItemBean;

public class UsersGetItemBeanController {
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
	
	public  UsersGetItemBean getitem(Integer  id){
		final String path=url+"users.php?action=getitem";
		//http://www.ieream.com/mapi/1/users.php?action=getitem&uid=102
		FutureTask< UsersGetItemBean > future = new FutureTask< UsersGetItemBean >(new Callable<  UsersGetItemBean >() {
			public  UsersGetItemBean  call() {
				try{
					ResponseEntity< UsersGetItemBean > response = 
					restTemplate.getForEntity(path+"&uid=102",  UsersGetItemBean .class);
					
					return response.getBody();
				}catch(Exception e){
					e.printStackTrace();
					return new  UsersGetItemBean ();
				}
			}
		});
		executorService.execute(future);
		UsersGetItemBean  result = null;
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
