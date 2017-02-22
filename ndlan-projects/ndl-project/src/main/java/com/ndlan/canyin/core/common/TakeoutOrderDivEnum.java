 package com.ndlan.canyin.core.common;

import com.ndlan.canyin.core.common.TakeoutOrderDivEnum;
 
 public enum TakeoutOrderDivEnum
 {
   TAKEOUT("1", "外卖"), 
   ORDER("2", "预订");
 
   public static final String enumCode = "takeoutOrderDi";
   public static final String enumName = "外卖预订区分";
   private String code;
   private String desc;
 
   private TakeoutOrderDivEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (TakeoutOrderDivEnum status : values()) {
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

