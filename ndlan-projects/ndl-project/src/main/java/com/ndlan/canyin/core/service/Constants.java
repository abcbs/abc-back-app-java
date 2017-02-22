 package com.ndlan.canyin.core.service;
 
 public class Constants
 {
   public static final int DESCRIBE = 1;
   public static final int RE_DESCRIBE = 2;
   public static String SERVER_IP = "www.xxxx.com";
 
   public static String SERVER_PORT = "8080";
   public static final String SERVER_CONTEXT_PATH = "/m";
   public static String BASE_SERVIER_URL = "http://" + SERVER_IP + ":" + SERVER_PORT + "/m" + "/";
 
   public static void resetServerIp(String serverIp) {
     if ("".equals(serverIp)) {
       return;
     }
     SERVER_IP = serverIp;
     BASE_SERVIER_URL = "http://" + SERVER_IP + ":" + SERVER_PORT + "/m" + "/";
   }
   public static void resetServerPort(String serverPort) {
     if ("".equals(serverPort)) {
       return;
     }
     SERVER_PORT = serverPort;
     BASE_SERVIER_URL = "http://" + SERVER_IP + ":" + SERVER_PORT + "/m" + "/";
   }
 }

