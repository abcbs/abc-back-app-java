 package com.ndlan.canyin.core.utils;
 
 import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.ndlan.canyin.core.utils.PropertyConfig;
 
 public class PropertyConfig
 {
   public Properties mConfig;
   public Set<Map.Entry<Object, Object>> tblMap = null;
 
   public PropertyConfig(String propertiesName) { InputStream fis = PropertyConfig.class.getClassLoader().getResourceAsStream(propertiesName);
     try {
       this.mConfig = new Properties();
       this.tblMap = this.mConfig.entrySet();
       this.mConfig.load(fis);
     }
     catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public String getProperty(String key)
   {
     return this.mConfig.getProperty(key);
   }
 
   public String getProperty(String key, String defaultValue) {
     String value = this.mConfig.getProperty(key);
     if (value == null) {
       return defaultValue;
     }
     return value;
   }
 
   public boolean getBooleanProperty(String name, boolean defaultValue) {
     String value = getProperty(name);
 
     if (value == null) {
       return defaultValue;
     }
     return new Boolean(value).booleanValue();
   }
 
   public int getIntProperty(String name) {
     return getIntProperty(name, 0);
   }
 
   public int getIntProperty(String name, int defaultValue) {
     String value = getProperty(name);
 
     if (value == null) {
       return defaultValue;
     }
     return new Integer(value).intValue();
   }
 }

