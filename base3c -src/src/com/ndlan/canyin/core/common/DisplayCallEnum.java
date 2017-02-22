 package com.ndlan.canyin.core.common;

import com.ndlan.canyin.core.common.DisplayCallEnum;
 
 public enum DisplayCallEnum
 {
   UNTREATED("0", "未处理"), 
   TAKEOUT("1", "外卖"), 
   ORDER("2", "预订"), 
   CANCEL("3", "取消");
 
   public static final String enumCode = "DisplayCallEnum";
   public static final String enumName = "来电显示处理状态";
   private String code;
   private String desc;
 
   private DisplayCallEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (DisplayCallEnum status : values()) {
       if (status.getCode().equalsIgnoreCase(code)) {
         return status.getDesc();
       }
     }
     return "未知";
   }
 
   public static DisplayCallEnum getEnumByCode(String code) {
     for (DisplayCallEnum status : values()) {
       if (status.getCode().equalsIgnoreCase(code)) {
         return status;
       }
     }
     return UNTREATED;
   }
 
   public String getDesc() {
     return this.desc;
   }
 
   public void setDesc(String desc) {
     this.desc = desc;
   }
 
   public String getCode() {
     return this.code;
   }
 
   public void setCode(String code) {
     this.code = code;
   }
 
   public static void main(String[] args) {
     generateSQL();
   }
 
   public static void generateSQL()
   {
   }
 }

