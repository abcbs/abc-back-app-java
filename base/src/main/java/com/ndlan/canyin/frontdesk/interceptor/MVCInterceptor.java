package com.ndlan.canyin.frontdesk.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ndlan.canyin.frontdesk.dto3c.MobileRspResult;
import com.ndlan.canyin.core.common.StatusCodeEnum;

public class MVCInterceptor implements HandlerInterceptor{
	private static final Logger logger = LoggerFactory.getLogger(MVCInterceptor.class);
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		     throws Exception
		   {
		     return true;
		   }
		 
		   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
		     throws Exception
		   {
			   
//		     request.setAttribute("cacheVersion", "2.3.0");
			   String returnJson =  (String) request.getParameter("returnJson");
			   if(returnJson != null && returnJson != "" ){
				   if(modelAndView!= null){
					   ModelMap modelMap = modelAndView.getModelMap();
					   Set entries = modelMap.entrySet();
					   if(entries != null){
						   Iterator iterator = entries.iterator();
						   Map map = new HashMap();
						   while(iterator.hasNext()){
							   Map.Entry entry = (Entry) iterator.next();
							   String key =  (String) entry.getKey();
							   Object object = entry.getValue();
							   if(object != null)
							   if(! (object instanceof BeanPropertyBindingResult))
							   map.put(key, object);
						   }
						   MobileRspResult mobileRspResult = new MobileRspResult(StatusCodeEnum.SUCCESS.getCode(), "", map);
//						   System.out.println("json:"+mobileRspResult.getObject());
						   modelAndView.clear();
						   /*	response.setCharacterEncoding("UTF-8");  
						    response.setContentType("application/json; charset=utf-8");
						    response.getWriter().write(mobileRspResult.getObject().toString());*/
						    
						    response.setCharacterEncoding("UTF-8");  
						    response.setContentType("application/json; charset=utf-8");
							PrintWriter out = null;
							try {
								out = response.getWriter();
								out.print(mobileRspResult.getObject().toString());
								out.flush();
							} catch (IOException e) {
								logger.error("输出失败"+e.getMessage());
							}finally{
								if(out!=null){
									out.close();
								}
							}
					   }
				   }
			   }
		   }
		 
		   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
		     throws Exception
		   {
		   }
}
