 package com.ndlan.canyin.core.common;
 
 import java.io.PrintStream;
 
 public abstract class Constants
 {
   public static final String cloudPasswordKey = "cdad908ca78811e4944e5453edb8d7a8";
   public static final String currentVersion = "230";
   public static final String currentVersionStr = "2.3.0";
   public static final int apkCurrentVersionNum = 27;
   public static final String UNKNOWN = "未知";
   public static final String ENCODE = "utf-8";
   public static int PAGE_SIZE = 20;
 
   public static int PAGE_SIZE_ALL = 999999999;
 
   public static int THREAD_SECONDS = 300000;
 
   public static String OPEN_REPEAT_REQUEST_REDIRECT_URL = "/mxbapi/bldcb/other/repeatrequest";
 
   public static String env_test = "test";
 
   public static String env_production = "production";
 
   public static String env_development = "development";
 
   public static String currentEnv = env_test;
 
   public static String cloudServerAppName = "cloud-portal";
   public static final String env_production_cloudServerIp = "http://www.xxx.com";
   public static final String env_production_cloudUploadIp = "http://upload.xxx.cn:8080";
   public static final String env_production_cloudDownloadIp = "http://download.xxx.cn";
   public static final String env_production_cloudDataIp = "http://data.xxx.cn";
   public static final String env_production_cloudPullDataIp = "http://pulldata.xxx.cn/synch/recieveData";
   public static final String env_production_cloudSocketServerIp = "socket.xxx.cn";
   public static final String env_production_cloudPingServerIp = "http://ping.xxx.cn/ping";
   public static final String env_test_cloudServerIp = "http://114.215.128.45";
   public static final String env_test_cloudUploadIp = "http://114.215.128.45:8080";
   public static final String env_test_cloudDownloadIp = "http://114.215.128.45";
   public static final String env_test_cloudDataIp = "http://114.215.128.45";
   public static final String env_test_cloudPullDataIp = "http://114.215.128.45/synch/recieveData";
   public static final String env_test_cloudSocketServerIp = "114.215.128.45";
   public static final String env_test_cloudPingServerIp = "http://114.215.128.45/ping";
   public static final String env_development_cloudServerIp = "http://localhost:8082";
   public static final String env_development_cloudUploadIp = "http://localhost:8082";
   public static final String env_development_cloudDownloadIp = "http://localhost:8082";
   public static final String env_development_cloudDataIp = "http://localhost:8082";
   public static final String env_development_cloudPullDataIp = "synch/recieveData";
   public static final String env_development_cloudSocketServerIp = "localhost";
   public static final String env_development_cloudPingServerIp = "http://localhost:8082/ping";
   public static final int uploadType_dish = 1;
   public static final int uploadType_employee = 2;
   public static final int uploadType_printer = 3;
   public static final int uploadType_restaurant = 4;
   public static final int uploadType_table = 5;
   public static final int uploadType_restNews = 6;
   public static final String dishUploadPath_dish = "/upload/dish";
   public static final String dishUploadPath_employee = "/upload/employee";
   public static final String dishUploadPath_printer = "/upload/printer";
   public static final String dishUploadPath_restaurant = "/upload/restaurant";
   public static final String dishUploadPath_table = "/upload/table";
   public static final String dishUploadPath_restNews = "/upload/restNews";
 
   public static final String getServer()
   {
     String result = "http://www.xxx.com";
     if (currentEnv.equals(env_test))
     {
       result = "http://114.215.128.45";
     }
     if (currentEnv.equals(env_production))
     {
       result = "http://www.xxx.com";
     }
     if (currentEnv.equals(env_development))
     {
       result = "http://localhost:8082/" + cloudServerAppName;
     }
     System.out.println("getServer:" + result);
     return result;
   }
 
   public static final String getUploadServer()
   {
     String result = "http://upload.xxx.cn:8080";
     if (currentEnv.equals(env_test))
     {
       result = "http://114.215.128.45:8080";
     }
     if (currentEnv.equals(env_production))
     {
       result = "http://upload.xxx.cn:8080";
     }
     if (currentEnv.equals(env_development))
     {
       result = "http://localhost:8082/" + cloudServerAppName;
     }
     System.out.println("getUploadServer:" + result);
     return result;
   }
 
   public static final String getDownloadServer()
   {
     String result = "http://download.xxx.cn";
     if (currentEnv.equals(env_test))
     {
       result = "http://114.215.128.45";
     }
     if (currentEnv.equals(env_production))
     {
       result = "http://download.xxx.cn";
     }
     if (currentEnv.equals(env_development))
     {
       result = "http://localhost:8082/" + cloudServerAppName;
     }
     System.out.println("getDownloadServer:" + result);
     return result;
   }
 
   public static final String getDataServer()
   {
     String result = "http://data.xxx.cn";
     if (currentEnv.equals(env_test))
     {
       result = "http://114.215.128.45";
     }
     if (currentEnv.equals(env_production))
     {
       result = "http://data.xxx.cn";
     }
     if (currentEnv.equals(env_development))
     {
       result = "http://localhost:8082/" + cloudServerAppName;
     }
     System.out.println("getDataServer:" + result);
     return result;
   }
 
   public static final String getPullDataServer()
   {
     String result = "http://pulldata.xxx.cn/synch/recieveData";
     if (currentEnv.equals(env_test))
     {
       result = "http://114.215.128.45/synch/recieveData";
     }
     if (currentEnv.equals(env_production))
     {
       result = "http://pulldata.xxx.cn/synch/recieveData";
     }
     if (currentEnv.equals(env_development))
     {
       result = "http://localhost:8082/" + cloudServerAppName + "/" + "synch/recieveData";
     }
     System.out.println("getPullDataServer:" + result);
     return result;
   }
 
   public static final String getSocketServer()
   {
     String result = "socket.xxx.cn";
     if (currentEnv.equals(env_test))
     {
       result = "114.215.128.45";
     }
     if (currentEnv.equals(env_production))
     {
       result = "socket.xxx.cn";
     }
     if (currentEnv.equals(env_development))
     {
       result = "localhost";
     }
     System.out.println("getSocketServer:" + result);
     return result;
   }
 
   public static final String getPingServer()
   {
     String result = "http://ping.xxx.cn/ping";
     if (currentEnv.equals(env_test))
     {
       result = "http://114.215.128.45/ping";
     }
     if (currentEnv.equals(env_production))
     {
       result = "http://ping.xxx.cn/ping";
     }
     if (currentEnv.equals(env_development))
     {
       result = "http://localhost:8082/ping";
     }
     System.out.println("getPingServer:" + result);
     return result;
   }
 
   public static void main(String[] args)
   {
     getPullDataServer();
   }
 }

