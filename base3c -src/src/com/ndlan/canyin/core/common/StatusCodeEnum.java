 package com.ndlan.canyin.core.common;

import com.ndlan.canyin.core.common.StatusCodeEnum;
 
 public enum StatusCodeEnum
 {
   SUCCESS("200", "成功"), 
   CHECK_ERROR("300", "校验错误"), 
   LOGIC_ERROR("400", "逻辑错误"), 
   ROLE_ERROR("401", "权限错误"), 
   ERROR("500", "异常错误"), 
   UNLOGIN("600", "未登录"), 
   YANZHENGSUCCESS("800", "验证成功"), 
   CONNECT_ERROR("1000", "网络异常"), 
   NODATA("1100", "没有数据");
 
   public static final String enumCode = "StatusCode";
   public static final String enumName = "状态编码";
   private String code;
   private String desc;
 
   private StatusCodeEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (StatusCodeEnum status : values()) {
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

