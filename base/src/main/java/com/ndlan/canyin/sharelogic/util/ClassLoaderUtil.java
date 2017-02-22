 package com.ndlan.canyin.sharelogic.util;
 
 import java.io.IOException;
 import java.io.InputStream;
 import java.net.MalformedURLException;
 import java.net.URL;
 import java.util.Properties;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ndlan.canyin.sharelogic.util.ClassLoaderUtil;
 
 public class ClassLoaderUtil
 {
   private static Logger log = LoggerFactory.getLogger(ClassLoaderUtil.class);
 
   public static Class loadClass(String className)
   {
     try
     {
       return getClassLoader().loadClass(className); 
       }
     catch (ClassNotFoundException e) {
     }
//     throw new RuntimeException("class not found '" + className + "'", e);
     return null;
   }
 
   public static ClassLoader getClassLoader()
   {
     return ClassLoaderUtil.class.getClassLoader();
   }
 
   public static InputStream getStream(String relativePath)
     throws MalformedURLException, IOException
   {
     if (!relativePath.contains("../")) {
       return getClassLoader().getResourceAsStream(relativePath);
     }
 
     return getStreamByExtendResource(relativePath);
   }
 
   public static InputStream getStream(URL url)
     throws IOException
   {
     if (url != null) {
       return url.openStream();
     }
     return null;
   }
 
   public static InputStream getStreamByExtendResource(String relativePath)
     throws MalformedURLException, IOException
   {
     return getStream(getExtendResource(relativePath));
   }
 
   public static Properties getProperties(String resource)
   {
     Properties properties = new Properties();
     try {
       properties.load(getStream(resource));
     } catch (IOException e) {
       throw new RuntimeException("couldn't load properties file '" + resource + "'", e);
     }
     return properties;
   }
 
   public static String getAbsolutePathOfClassLoaderClassPath()
   {
     log.info(getClassLoader().getResource("").toString());
     return getClassLoader().getResource("").toString();
   }
 
   public static URL getExtendResource(String relativePath)
     throws MalformedURLException
   {
     log.info("传入的相对路径：" + relativePath);
 
     if (!relativePath.contains("../")) {
       return getResource(relativePath);
     }
 
     String classPathAbsolutePath = getAbsolutePathOfClassLoaderClassPath();
     if (relativePath.substring(0, 1).equals("/")) {
       relativePath = relativePath.substring(1);
     }
     log.info(""+Integer.valueOf(relativePath.lastIndexOf("../")));
 
     String wildcardString = relativePath.substring(0, relativePath.lastIndexOf("../") + 3);
     relativePath = relativePath.substring(relativePath.lastIndexOf("../") + 3);
     int containSum = containSum(wildcardString, "../");
     classPathAbsolutePath = cutLastString(classPathAbsolutePath, "/", containSum);
     String resourceAbsolutePath = classPathAbsolutePath + relativePath;
     log.info("绝对路径：" + resourceAbsolutePath);
     URL resourceAbsoluteURL = new URL(resourceAbsolutePath);
     return resourceAbsoluteURL;
   }
 
   private static int containSum(String source, String dest)
   {
     int containSum = 0;
     int destLength = dest.length();
     while (source.contains(dest)) {
       containSum++;
       source = source.substring(destLength);
     }
     return containSum;
   }
 
   private static String cutLastString(String source, String dest, int num)
   {
     for (int i = 0; i < num; i++) {
       source = source.substring(0, source.lastIndexOf(dest, source.length() - 2) + 1);
     }
     return source;
   }
 
   public static URL getResource(String resource)
   {
     log.info("传入的相对于classpath的路径：" + resource);
     return getClassLoader().getResource(resource);
   }
 
   public static void main(String[] args)
     throws MalformedURLException
   {
   }
 }

