 package com.ndlan.canyin.frontdesk.util;
 
 import com.fasterxml.jackson.core.JsonFactory;
 import com.fasterxml.jackson.core.JsonGenerationException;
 import com.fasterxml.jackson.core.JsonGenerator;
 import com.fasterxml.jackson.core.JsonParseException;
 import com.fasterxml.jackson.databind.JsonMappingException;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import java.io.IOException;
 import java.io.StringWriter;
 
 public class MessageCarrierUtil
 {
   public static String beanToJson(Object obj)
   {
     String json = null;
     try {
       ObjectMapper mapper = new ObjectMapper();
       StringWriter writer = new StringWriter();
       JsonGenerator generator = new JsonFactory().createJsonGenerator(writer);
       mapper.writeValue(generator, obj);
       generator.close();
       json = writer.toString();
       writer.close();
     }
     catch (JsonGenerationException e) {
       e.printStackTrace();
     }
     catch (JsonMappingException e) {
       e.printStackTrace();
     }
     catch (IOException e) {
       e.printStackTrace();
     }
     return json;
   }
 
   public static Object jsonToBean(String json, Class<?> cls) {
     try {
       ObjectMapper mapper = new ObjectMapper();
       Object vo = mapper.readValue(json, cls);
       return vo;
     }
     catch (JsonParseException e) {
       e.printStackTrace();
     }
     catch (JsonMappingException e) {
       e.printStackTrace();
     }
     catch (IOException e) {
       e.printStackTrace();
     }
     return null;
   }
 }

