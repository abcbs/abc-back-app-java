 package com.ndlan.canyin.core.common;

import com.ndlan.canyin.core.common.OperationTypeEnum;
 
 public enum OperationTypeEnum
 {
   CREATE("1", "增"), 
   DELETE("2", "删"), 
   UPDATE("3", "改"), 
   DELETE_ID("4", "通过ID删"), 
   BATCH_DELETE("5", "批量删"), 
   SQL("9", "运行sql"), 
   UPLOAD("10", "上传文件"), 
   PROC("11", "运行存储过程"), 
   MOBILE_MESSAGE("12", "短信"), 
   METHOD("13", "云餐厅方法");
 
   public static final String enumCode = "OperationTypeEnum";
   public static final String enumName = "操作类型";
   private String code;
   private String desc;
 
   private OperationTypeEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (OperationTypeEnum status : values()) {
       if (status.getCode().equalsIgnoreCase(code)) {
         return status.getDesc();
       }
     }
     return "未知";
   }
 
   public static OperationTypeEnum getEnumByCode(String code) {
     for (OperationTypeEnum status : values()) {
       if (status.getCode().equalsIgnoreCase(code)) {
         return status;
       }
     }
     return UPDATE;
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
 
   public static void main(String[] args)
   {
     generateSQL();
   }
 
   public static void generateSQL()
   {
   }
 }

