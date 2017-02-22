 package com.ndlan.canyin.core.utils;
 
 import java.io.IOException;
 import java.io.InputStream;
 
 public class BatchUtils
 {
   public static void exeBatch(String cmd, boolean sftc)
   {
     if (sftc)
       cmd = "cmd /c start " + cmd;
     try
     {
       Process ps = Runtime.getRuntime().exec(cmd);
       InputStream in = ps.getInputStream();
       int c;
       while ((c = in.read()) != -1);
       in.close();
       ps.waitFor();
     } catch (IOException ioe) {
       ioe.printStackTrace();
     }
     catch (InterruptedException e) {
       e.printStackTrace();
     }
   }
 
   public static void main(String[] args)
   {
   }
 }

