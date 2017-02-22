package com.ndlan.cwwarm.rest;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ndlan.cwwarm.model.CwWorkJobBean;
import com.ndlan.framework.core.web.domain.Result;

public class FileUploadRestful {

	private static final String url = BaseAndroidRestfulConfig.moduleServicePath+"cw/imageUpload/";
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
		//restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}

	public Result selectAll() {
		
		FutureTask<Result> future = new FutureTask<Result>(new Callable<Result>() {
			public Result call() {
				try{
				    MultiValueMap<String, Object> formData;
					Resource resource = new ClassPathResource("spring09_logo.png");

					// populate the data to post
					formData = new LinkedMultiValueMap<String, Object>();
					//formData.add("description", "Spring logo");
					formData.add("file", resource);
					
					HttpHeaders requestHeaders = new HttpHeaders();

					// Sending multipart/form-data
					requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

					// Populate the MultiValueMap being serialized and headers in an HttpEntity object to use for the request
					HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(
							formData, requestHeaders);

					// Create a new RestTemplate instance
					//RestTemplate restTemplate = new RestTemplate(true);

					// Make the network request, posting the message, expecting a String in response from the server
					ResponseEntity<Result> response;
					
					 response = restTemplate.exchange(url+"postformdata", HttpMethod.POST, requestEntity,
							 Result.class);
						 
					response = restTemplate.postForEntity(url+"uploadPic", requestEntity,Result.class);
					Result result=response.getBody();
					return result;
				}catch(Exception e){
					e.printStackTrace();
					return new Result(Result.Status.ERROR,e.getMessage());
				}
			}
		});
		executorService.execute(future);
		Result result = null;
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
