package com.ndlan.cwwarm.rest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.ndlan.framework.core.web.domain.Result;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.ndlan.cwwarm.model.CmRoleUserBean;
import com.ndlan.cwwarm.query.CmRoleUserQuery;

public class CmRoleUserBeanBustinessRestful {

	private static final String url = BaseAndroidRestfulConfig.moduleServicePath+"cm/role/user/";
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

	public Result selectAll() {
		
		FutureTask<Result> future = new FutureTask<Result>(new Callable<Result>() {
			public Result call() {
				try{
					ResponseEntity<Result> response = restTemplate.getForEntity(url, Result.class);
						Result result = response.getBody();
					if(result.getStatus()==Result.Status.OK){
						List<?> obj=(List<?>) result.getMessage();
						if(null!=obj){
							CmRoleUserBean[] beans=mapper.convertValue(obj, CmRoleUserBean[].class);
							result.setMessage(beans);
						}
					}
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

	public Result deleteList(final String[] ids) {
		if(ids==null){
			return new Result(Result.Status.ERROR,"delete,pk not null");
		}
		FutureTask<Result> future = new FutureTask<Result>(new Callable<Result>() {
			public Result call() {
				try{
					ResponseEntity<Result> response = restTemplate.postForEntity(url + "deleteList", ids, Result.class);
					return response.getBody();
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

	public Result deleteOne(final String defualtId) {
		if(defualtId==null||defualtId.trim()==""){
			return new Result(Result.Status.ERROR,"delete,pk not null");
		}
		FutureTask<Result> future = new FutureTask<Result>(new Callable<Result>() {
			public Result call() {
				try{
					ResponseEntity<Result> response = restTemplate.getForEntity(url + "deleteOne/" + defualtId, Result.class);
					return response.getBody();
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

	// @Override
	public Result addOne(final CmRoleUserBean entity) {
		
		FutureTask<Result> future = new FutureTask<Result>(new Callable<Result>() {
			public Result call() {
				try{
					ResponseEntity<Result> response = restTemplate.postForEntity(url + "addOne", 
							entity, Result.class);
					Result result = response.getBody();
					if(result.getStatus()==Result.Status.OK){
						Map<?,?> obj=(Map<?,?>) result.getMessage();
						if(null!=obj){
							CmRoleUserBean beans=mapper.convertValue(obj, CmRoleUserBean.class);
							result.setMessage(beans);
						}
					}
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

	// @Override
	public Result selectList(final CmRoleUserQuery query) {
		
		FutureTask<Result> future = new FutureTask<Result>(new Callable<Result>() {
			public Result call() {
				try{
					ResponseEntity<Result> response = restTemplate.postForEntity(url + "selectList", 
							query, Result.class);
					Result result=response.getBody();
					if(result.getStatus()==Result.Status.OK){
						List<?> obj=(List<?>) result.getMessage();
						if(null!=obj){
							CmRoleUserBean[] beans=mapper.convertValue(obj, CmRoleUserBean[].class);
							result.setMessage(beans);
						}
					}
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

	// @Override
	public Result viewOne(final String defualtId) {
		if(defualtId==null||defualtId.trim()==""){
			return new Result(Result.Status.ERROR,"select,pk not null");
		}
		FutureTask<Result> future = new FutureTask<Result>(new Callable<Result>() {
			public Result call() {
				try {
					ResponseEntity<Result> response = restTemplate.getForEntity(url + "viewOne/" + defualtId,
							Result.class);
										Result result=response.getBody();
					if(result.getStatus()==Result.Status.OK){
						Map<?,?> obj=(Map<?,?>) result.getMessage();
						if(null!=obj){
							CmRoleUserBean beans=mapper.convertValue(obj, CmRoleUserBean.class);
							result.setMessage(beans);
						}
					}
					return result;
				} catch (Exception e) {
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

	// @Override
	public Result editOne(final CmRoleUserBean entity) {
		if(entity.getDefaultId()==null||entity.getDefaultId().trim()==""){
			return new Result(Result.Status.ERROR,"update,pk not null");
		}
		FutureTask<Result> future = new FutureTask<Result>(new Callable<Result>() {
			public Result call() {
				try{
					ResponseEntity<Result> response = restTemplate.postForEntity(url + "editView", entity,
							 Result.class);
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

	public Result editSelective(final CmRoleUserBean entity) {
		if(entity.getDefaultId()==null||entity.getDefaultId().trim()==""){
			return new Result(Result.Status.ERROR,"update,pk not null");
		}
		FutureTask<Result> future = new FutureTask<Result>(new Callable<Result>() {
			public Result call() {
				try{
					ResponseEntity<Result> response = restTemplate.postForEntity(url + "editSelective", entity,
							 Result.class);
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


