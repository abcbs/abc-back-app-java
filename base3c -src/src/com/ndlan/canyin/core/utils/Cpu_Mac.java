 package com.ndlan.canyin.core.utils;
 
 import java.io.PrintStream;
 import java.net.InetAddress;
 import java.net.NetworkInterface;
 import java.net.SocketException;
 import java.net.UnknownHostException;
 
 public class Cpu_Mac
 {
   public static void main(String[] args)
     throws SocketException, UnknownHostException
   {
     System.out.println(System.getProperty("os.name"));
 
     System.out.println(NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress());
   }
 }

