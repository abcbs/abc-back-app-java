 package com.ndlan.canyin.core.utils;
 
 import java.io.BufferedInputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.net.URL;
 import java.util.Properties;
 
 public class PropertiesUtil
 {
   public Properties getProperties(String fileName)
     throws IOException
   {
     Properties prop = new Properties();
     String filePath = getClass().getResource("/").getPath();
     InputStream stream = new BufferedInputStream(new FileInputStream(new File(filePath + fileName)));
     prop.load(stream);
     stream.close();
     return prop;
   }
 }

