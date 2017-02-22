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
import com.ndlan.ieream.model.LocationsGetListBean;
import com.ndlan.ieream.model.TagsGetListBean;

public class TagsBeanControllerBusinessRestful {
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
	
	public  TagsGetListBean getList(Integer  id){
		final String path=url+"tags.php?action=getlist";
		//http://www.ieream.com/mapi/1/tags.php?action=getlist&psize=30
		FutureTask< TagsGetListBean > future = new FutureTask< TagsGetListBean >(new Callable<  TagsGetListBean >() {
			public  TagsGetListBean  call() {
				try{
					ResponseEntity< TagsGetListBean > response = 
					restTemplate.getForEntity(path+"&psize=30",  TagsGetListBean .class);
					
					return response.getBody();
				}catch(Exception e){
					e.printStackTrace();
					return new  TagsGetListBean ();
				}
			}
		});
		executorService.execute(future);
		TagsGetListBean  result = null;
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
