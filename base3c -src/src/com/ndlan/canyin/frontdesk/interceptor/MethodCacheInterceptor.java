 package com.ndlan.canyin.frontdesk.interceptor;
 
 import java.io.Serializable;
 import java.lang.reflect.Method;
 import java.util.Map;
 import net.sf.ehcache.Cache;
 import net.sf.ehcache.Element;
 import org.aopalliance.intercept.MethodInterceptor;
 import org.aopalliance.intercept.MethodInvocation;
 import org.springframework.beans.factory.InitializingBean;
 
 public class MethodCacheInterceptor
   implements MethodInterceptor, InitializingBean
 {
   private Cache cache;
 
   public void setCache(Cache cache)
   {
     this.cache = cache;
   }
 
   public void afterPropertiesSet() throws Exception
   {
   }
 
   public Object invoke(MethodInvocation invocation) throws Throwable {
     String targetName = invocation.getThis().getClass().getName();
     String methodName = invocation.getMethod().getName();
     Object[] arguments = invocation.getArguments();
 
     String cacheKey = getCacheKey(targetName, methodName, arguments);
     Element element = null;
     synchronized (this) {
       element = this.cache.get(cacheKey);
       if (element == null)
       {
         Object result = invocation.proceed();
         element = new Element(cacheKey, (Serializable)result);
         this.cache.put(element);
       }
 
     }
 
     return element.getObjectValue();
   }
 
   private String getCacheKey(String targetName, String methodName, Object[] arguments)
   {
     StringBuffer sb = new StringBuffer();
     sb.append(targetName).append(".").append(methodName);
     if ((arguments != null) && (arguments.length != 0)) {
       for (int i = 0; i < arguments.length; i++) {
         Object arg = arguments[i];
         if ((arg instanceof Map))
         {
           Map<String,String> keyMap = (Map)arg;
           StringBuffer mapCacheKey = new StringBuffer();
           for (String key : keyMap.keySet()) {
             mapCacheKey.append(".").append("key= " + key + " and value= " + keyMap.get(key));
           }
           sb.append(".").append(mapCacheKey);
         }
         else if ((arg instanceof String[]))
         {
           StringBuffer mapCacheKey = new StringBuffer();
           String[] args = (String[])arg;
           for (String key : args) {
             mapCacheKey.append(".").append(key);
           }
           sb.append(".").append(mapCacheKey);
         }
         else
         {
           sb.append(".").append(arg);
         }
       }
     }
 
     return sb.toString();
   }
 }

