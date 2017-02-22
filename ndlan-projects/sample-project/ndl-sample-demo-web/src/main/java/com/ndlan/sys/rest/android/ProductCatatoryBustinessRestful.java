package com.ndlan.sys.rest.android;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.ndlan.framework.core.web.domain.Result;
import com.ndlan.framework.demo.domain.SysDictionary;

public class ProductCatatoryBustinessRestful {

	private static final String url = "http://localhost:8080/ndl-sample-demo-web/business/sys/dictionary";
	MediaType mediaType = MediaType.APPLICATION_JSON;

	private static final RestTemplate restTemplate = new RestTemplate();

	{
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}

	public Result selectAll() {
		ResponseEntity<Result> response = restTemplate.getForEntity(url, Result.class);
		Result result = response.getBody();
		return result;
	}

	public Result deleteList(String[] ids) {
		ResponseEntity<Result> response = restTemplate.postForEntity(url + "/deleteList", ids, Result.class);
		return response.getBody();
	}

	public Result deleteOne(String defualtId) {

		ResponseEntity<Result> response = restTemplate.getForEntity(url + "/deleteOne/" + defualtId, Result.class);
		return response.getBody();
	}

	// @Override
	public Result addOne(SysDictionary entity) {
		// url=deleteOne
		ResponseEntity<Result> response = restTemplate.postForEntity(url + "/addOne", 
				entity, Result.class);
		return response.getBody();
	}

	// @Override
	public Result selectList(SysDictionary query) {
		ResponseEntity<Result> response = restTemplate.postForEntity(url + "/selectList", 
				query, Result.class);
		return response.getBody();
	}

	// @Override
	public Result viewOne(String defualtId) {
		ResponseEntity<Result> response = restTemplate.getForEntity(url + "/viewOne/"+defualtId, 
				 Result.class);
		return response.getBody();
	}

	// @Override
	public Result editOne(SysDictionary entity) {
		ResponseEntity<Result> response = restTemplate.postForEntity(url + "/editView", entity,
				 Result.class);
		return response.getBody();
	}

}
