  package com.ndlan.canyin.frontdesk.util; 
 import java.io.File;
 import java.io.PrintStream;
 import java.security.MessageDigest;
 import java.security.NoSuchAlgorithmException;
 
 public class ImageMD5
 {
   public static String MD5(byte[] s)
   {
     char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
     try
     {
       byte[] strTemp = s;
       MessageDigest mdTemp = MessageDigest.getInstance("MD5");
       mdTemp.update(strTemp);
       byte[] md = mdTemp.digest();
       int j = md.length;
       char[] str = new char[j * 2];
       int k = 0;
 
       for (int i = 0; i < j; i++)
       {
         byte byte0 = md[i];
         str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
         str[(k++)] = hexDigits[(byte0 & 0xF)];
       }
       return new String(str);
     }
     catch (Exception e) {
     }
     return null;
   }
 
   public static void main(String[] args)
     throws NoSuchAlgorithmException
   {
     File file = new File("f://28163151673.jpg");
     byte[] b = new byte[(int)file.length()];
     System.out.println(MD5(b));
   }
 }

