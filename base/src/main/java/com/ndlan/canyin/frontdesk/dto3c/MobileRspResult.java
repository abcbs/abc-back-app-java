 package com.ndlan.canyin.frontdesk.dto3c;
 
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;

import com.fasterxml.jackson.core.JsonProcessingException;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import com.ndlan.canyin.base.entity.BaseEntity;
 import java.util.List;
import java.util.Map;
 
 public class MobileRspResult
 {
   private String statusCode = "200";
   private String message = "操作成功";
   private Object object;
 
   public MobileRspResult(String statusCode, String message, Object object)
   {
     this.statusCode = statusCode;
     this.message = message;
     turnObject(object);
   }
 
   private void turnObject(Object object)
   {
     if (((object instanceof List)) || ((object instanceof Map)) || ((object instanceof BaseEntity)))
     {
       ObjectMapper mapper = new ObjectMapper();
//       mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
       try {
         this.object = mapper.writeValueAsString(object);
       } catch (JsonProcessingException e) {
         e.printStackTrace();
         this.object = "转换数据到JSON出错";
       }
     }
     else
     {
       this.object = object;
     }
   }
   public MobileRspResult() {
   }
 
   public String getStatusCode() {
     return this.statusCode;
   }
   public void setStatusCode(String statusCode) {
     this.statusCode = statusCode;
   }
   public String getMessage() {
     return this.message;
   }
   public void setMessage(String message) {
     this.message = message;
   }
   public Object getObject() {
     return this.object;
   }
   public void setObject(Object object) {
     turnObject(object);
   }
 }

