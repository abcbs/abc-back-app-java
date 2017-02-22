 package com.ndlan.canyin.core.defines;
 
 import java.io.PrintStream;
 
 public class SeparatorDefine
 {
   public static final String separator_cloud = "┬";
   public static final String separator_response_L1 = "┍";
   public static final String separator_response_L2 = "┎";
   public static final String separator_response_L3 = "┏";
   public static final String separator_response_L4 = "┐";
   public static final String separator_response_L5 = "┑";
   public static final String separator_request_L1 = "┍";
   public static final String separator_request_L2 = "┎";
   public static final String separator_request_L3 = "┏";
   public static final String separator_request_L4 = "┐";
   public static final String separator_request_L5 = "┑";
 
   public static void main(String[] args)
   {
     String a = "a┬b";
     System.out.println(a);
     System.out.println(a.split("┬")[0]);
   }
 }

