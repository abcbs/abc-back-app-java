 package com.ndlan.canyin.core.common;

import com.ndlan.canyin.core.common.CloudPaymentTypeEnum;
 
 public enum CloudPaymentTypeEnum
 {
   COD("1", "货到付款"), 
   TRP("2", "到店支付"), 
   MCP("3", "在线会员卡支付");
 
   public static final String enumCode = "OnlinePaymentType";
   public static final String enumName = "在线支付方式";
   private String code;
   private String desc;
 
   private CloudPaymentTypeEnum(String code, String desc) { this.code = code;
     this.desc = desc;
   }
 
   public static CloudPaymentTypeEnum[] getOrderPaymentType()
   {
     return new CloudPaymentTypeEnum[] { TRP, MCP };
   }
 
   public static CloudPaymentTypeEnum[] getTakeOutPaymentType()
   {
     return new CloudPaymentTypeEnum[] { COD, MCP };
   }
 
   public static String getDesc(String code)
   {
     for (CloudPaymentTypeEnum status : values()) {
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

