 package com.ndlan.canyin.core.common;

import com.ndlan.canyin.core.common.SynResultStatusEnum;
 
 public enum SynResultStatusEnum
 {
   SUCCESS("1", "同步成功"), 
   CLOUD_NO_DATA("2", "云端无此餐厅数据"), 
   VERSION_DIFFERENT("3", "版本不一致"), 
   ERROR("4", "程序异常"), 
   CHECK_ERROR("5", "数字签名不一致"), 
   RETRY("6", "重试");
 
   public static final String enumCode = "SynResultStatus";
   public static final String enumName = "数据同步结果状态";
   private String code;
   private String desc;
 
   private SynResultStatusEnum(String code, String desc) { this.code = code;
     this.desc = desc; }
 
   public static String getDesc(String code)
   {
     for (SynResultStatusEnum status : values()) {
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

