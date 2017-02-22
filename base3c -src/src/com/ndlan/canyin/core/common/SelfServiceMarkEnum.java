 package com.ndlan.canyin.core.common;

import com.ndlan.canyin.core.common.SelfServiceMarkEnum;
 
 public enum SelfServiceMarkEnum
 {
   CALLORDER("0", "下单"), 
   ADDTABLEWARE("1", "添加餐具"), 
   CHECKOUT("2", "结账"), 
   CUSTOMER("3", "客户自定义"), 
   URGEDISH("4", "催菜"), 
   CALLSERVCIE("5", "服务员现场服务"), 
   OTHER("6", "其他"), 
   CALLERIDDISPLAY("7", "来电显示");
 
   public static final String enumCode = "SelfServiceMarkEnum";
   public static final String enumName = "自助服务的标示";
   private String code;
   private String desc;
 
   private SelfServiceMarkEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (SelfServiceMarkEnum status : values()) {
       if (status.getCode().equalsIgnoreCase(code)) {
         return status.getDesc();
       }
     }
     return "未知";
   }
 
   public static SelfServiceMarkEnum getEnumByCode(String code) {
     for (SelfServiceMarkEnum status : values()) {
       if (status.getCode().equalsIgnoreCase(code)) {
         return status;
       }
     }
     return OTHER;
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

