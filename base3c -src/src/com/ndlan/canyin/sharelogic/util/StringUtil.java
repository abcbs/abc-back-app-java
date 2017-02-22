 package com.ndlan.canyin.sharelogic.util;
 
 import java.io.UnsupportedEncodingException;
 import java.util.ArrayList;
 
 public class StringUtil
 {
   public ArrayList<String> splitAsLength(String str, int length, String encoding)
     throws UnsupportedEncodingException
   {
     ArrayList list_str = new ArrayList();
 
     if (str == null) {
       return null;
     }
 
     if (str.getBytes(encoding).length <= length) {
       list_str.add(str);
       return list_str;
     }
 
     int lens = 0;
     int startInd = 0;
 
     for (int i = 0; i < str.length(); )
     {
       byte[] arr_b = String.valueOf(str.charAt(i)).getBytes(encoding);
 
       if (arr_b.length >= length) {
         if (lens != 0) {
           list_str.add(str.substring(startInd, i));
           lens = 0;
         }
 
         list_str.add(str.substring(i, i + 1));
         i++;
         startInd = i;
       }
       else {
         lens += arr_b.length;
 
         if (lens >= length)
         {
           int endInd;
//           int endInd;
           if (lens == length) {
             i++; endInd = i;
           } else {
             endInd = i;
           }
           list_str.add(str.substring(startInd, endInd));
           lens = 0;
           startInd = i;
         } else {
           i++;
         }
       }
     }
     if (startInd < str.length()) {
       list_str.add(str.substring(startInd));
     }
     return list_str;
   }
 
   public static void main(String[] args)
   {
   }
 }

