 package com.ndlan.canyin.core.common;
 
 public enum EnvelopeSignEnum
 {
   CUSTOMER(-1, "自定义"), 
   BOOK(1, "预订"), 
   TAKEOUT(2, "外卖"), 
   X(3, "sf"), 
   SYNDATA(4, "同步数据"), 
   HEARTBEAT(100, "心跳包"), 
   JOIN(101, "连接登录");
 
   public static final String enumCode = "EnvelopeSignEnum";
   public static final String enumName = "数据流通的指令";
   private int code;
   private String desc;
 
   private EnvelopeSignEnum(int code, String desc) { this.code = code;
     this.desc = desc;
   }
 
   public String getDesc()
   {
     return this.desc;
   }
 
   public void setDesc(String desc) {
     this.desc = desc;
   }
 
   public static void main(String[] args)
   {
     generateSQL();
   }
 
   public static void generateSQL()
   {
   }
 
   public int getCode()
   {
     return this.code;
   }
 
   public void setCode(int code) {
     this.code = code;
   }
 }

