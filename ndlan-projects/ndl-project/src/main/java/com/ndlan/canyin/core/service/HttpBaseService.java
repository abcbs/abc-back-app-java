 package com.ndlan.canyin.core.service;
 
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import org.springframework.http.HttpEntity;
 import org.springframework.http.HttpHeaders;
 import org.springframework.http.HttpMethod;
 import org.springframework.http.MediaType;
 import org.springframework.http.ResponseEntity;
 import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
 import org.springframework.http.converter.FormHttpMessageConverter;
 import org.springframework.http.converter.StringHttpMessageConverter;
 import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
 import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ndlan.canyin.core.service.Constants;
import com.ndlan.canyin.core.service.HttpBaseService;
 
 public class HttpBaseService
 {
   protected static final String TAG = HttpBaseService.class.getSimpleName();
   public static String COOKIE_STR;
 
   protected static <T> ResponseEntity<T> post(String relativeUrl, MultiValueMap<String, Object> formData, Class<T> responseType)
   {
     HttpHeaders requestHeaders = new HttpHeaders();
     List acceptableMediaTypes = new ArrayList();
     acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
     requestHeaders.setAccept(acceptableMediaTypes);
     requestHeaders.add("Cookie", COOKIE_STR);
 
     requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
 
     HttpEntity requestEntity = new HttpEntity(formData, requestHeaders);
 
     RestTemplate restTemplate = new RestTemplate();
     restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
 
     restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
 
     ResponseEntity response = null;
     try {
       String url = Constants.BASE_SERVIER_URL + relativeUrl;
       System.out.println("read from ------------" + url);
       response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType, new Object[0]);
     } catch (Exception e) {
       e.printStackTrace();
       System.out.println("post: Network is unreachable? ------------" + response + e);
     }
     return response;
   }
 
   public static <T> T postObject(String relativeUrl, MultiValueMap<String, String> formData, Class<T> responseType) {
     RestTemplate restTemplate = new RestTemplate();
     HttpComponentsClientHttpRequestFactory componentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
     componentsClientHttpRequestFactory.setConnectTimeout(3000);
     componentsClientHttpRequestFactory.setReadTimeout(3000);
     restTemplate.setRequestFactory(componentsClientHttpRequestFactory);
     restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
     restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
     restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
     Object response = null;
     try {
       String url = Constants.BASE_SERVIER_URL + relativeUrl;
       System.out.println("read from ------------" + url);
 
       response = restTemplate.postForObject(url, formData, responseType, new Object[0]);
       if (response != null) response.toString();
     }
     catch (Exception e)
     {
       e.printStackTrace();
       System.out.println("postEntity: Network is unreachable? ------------" + response + e);
     }
 
     return (T)response;
   }
 
   protected static <E, T> ResponseEntity<T> postEntity(String relativeUrl, Class<E> reqType, Object obj, Class<T> responseType) {
     RestTemplate restTemplate = new RestTemplate();
 
     restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
     ResponseEntity response = null;
 
     HttpHeaders requestHeaders = new HttpHeaders();
     requestHeaders.add("Cookie", COOKIE_STR);
     try {
       String url = Constants.BASE_SERVIER_URL + relativeUrl;
       System.out.println("read from ------------" + url);
 
       HttpEntity entity = new HttpEntity(obj, requestHeaders);
       response = restTemplate.postForEntity(url, entity, responseType, new Object[0]);
     }
     catch (Exception e) {
       e.printStackTrace();
       System.out.println("postEntity: Network is unreachable? ------------" + response + e);
     }
     return response;
   }
 
   protected static <T> ResponseEntity<T> get(String relativeUrl, Class<T> responseType, Map<String, ?> params) {
     HttpHeaders requestHeaders = new HttpHeaders();
     List acceptableMediaTypes = new ArrayList();
     acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
     requestHeaders.setAccept(acceptableMediaTypes);
     requestHeaders.add("Cookie", COOKIE_STR);
 
     String suffix = "?";
     if (params != null) {
       Set<String> keys = params.keySet();
       for (String key : keys) {
         Object value = params.get(key);
         suffix = suffix + key + "=" + value + "&";
       }
     }
     RestTemplate restTemplate = new RestTemplate();
     restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
     HttpEntity requestEntity = new HttpEntity(requestHeaders);
     ResponseEntity response = null;
     try {
       String url = Constants.BASE_SERVIER_URL + relativeUrl + suffix;
       System.out.println("read from ------------" + url);
       response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType, new Object[0]);
     } catch (Exception e) {
       e.printStackTrace();
       System.out.println("Network is unreachable? ------------" + response + e);
     }
     return response;
   }
 }

