 package com.ndlan.canyin.core.utils;
 
 import java.io.IOException;
 import java.io.PrintStream;
 import java.net.Inet4Address;
 import java.net.InetAddress;
 import java.net.NetworkInterface;
 import java.net.SocketException;
 import java.net.UnknownHostException;
 import java.util.ArrayList;
 import java.util.Enumeration;
 import java.util.List;
 
 public class NetUtil
 {
   public static void main(String[] args)
   {
     System.out.println(getLocalMac());
   }
   public static String getLocalMac() {
     try {
       for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
         NetworkInterface intf = (NetworkInterface)en.nextElement();
         for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
           InetAddress inetAddress = (InetAddress)enumIpAddr.nextElement();
           if (!inetAddress.isLoopbackAddress()) {
             String ip = inetAddress.getHostAddress().toString();
             return ip;
           }
         }
       }
     } catch (SocketException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public static String getLocalHostByNetworkCard() {
     try {
       for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
         NetworkInterface intf = (NetworkInterface)en.nextElement();
         for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
           InetAddress inetAddress = (InetAddress)enumIpAddr.nextElement();
           if ((!inetAddress.isLoopbackAddress()) && (inetAddress.getHostAddress().indexOf(":") == -1)) {
             String ip = inetAddress.getHostAddress().toString();
             return ip;
           }
         }
       }
     } catch (SocketException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public static String getLocalIp()
   {
     String ip = "127.0.0.1";
     try
     {
       InetAddress addr = InetAddress.getLocalHost();
       ip = addr.getHostAddress();
     } catch (UnknownHostException e) {
       e.printStackTrace();
     }
     return ip;
   }
 
   public static List<String> getLocalIPList() {
     List ipList = new ArrayList();
     try {
       Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
 
       while (networkInterfaces.hasMoreElements()) {
         NetworkInterface networkInterface = (NetworkInterface)networkInterfaces.nextElement();
         Enumeration inetAddresses = networkInterface.getInetAddresses();
         while (inetAddresses.hasMoreElements()) {
           InetAddress inetAddress = (InetAddress)inetAddresses.nextElement();
           if ((inetAddress != null) && ((inetAddress instanceof Inet4Address))) {
             String ip = inetAddress.getHostAddress();
             System.out.println(ip);
             ipList.add(ip);
           }
         }
       }
     } catch (SocketException e) {
       e.printStackTrace();
     }
     return ipList;
   }
 }

