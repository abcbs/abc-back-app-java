 package com.ndlan.canyin.core.common;

import com.ndlan.canyin.core.common.BackUpTypeEnum;
 
 public enum BackUpTypeEnum
 {
   LOCAL("local", "本地备份"), 
   CLOUD("cloud", "云备份");
 
   public static final String enumCode = "BackUpType";
   public static final String enumName = "备份文件类型";
   private String code;
   private String desc;
 
   private BackUpTypeEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (BackUpTypeEnum status : values()) {
       if (status.getCode().equalsIgnoreCase(code)) {
         return status.getDesc();
       }
     }
     return "未知";
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
 }

 