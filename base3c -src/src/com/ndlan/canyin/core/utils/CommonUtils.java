 package com.ndlan.canyin.core.utils;
 
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class CommonUtils
 {
   public static boolean ifHanZi(String str)
   {
     boolean hz = false;
     if (str != null) {
       String regEx = "[\\u4e00-\\u9fa5]";
       Pattern p = Pattern.compile(regEx);
       Matcher m = p.matcher(str);
       if (m.find()) {
         hz = true;
       }
     }
 
     return hz;
   }
 }

