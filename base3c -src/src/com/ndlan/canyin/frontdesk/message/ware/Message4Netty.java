 package com.ndlan.canyin.frontdesk.message.ware;
 
 import java.io.Serializable;
 
 public class Message4Netty
   implements Serializable
 {
   private static final long serialVersionUID = -8497467011269087264L;
   private int code;
   private String name;
   private String desc;
 
   public int getCode()
   {
     return this.code;
   }
 
   public void setCode(int code)
   {
     this.code = code;
   }
 
   public String getName()
   {
     return this.name;
   }
 
   public void setName(String name)
   {
     this.name = name;
   }
 
   public String getDesc()
   {
     return this.desc;
   }
 
   public void setDesc(String desc)
   {
     this.desc = desc;
   }
 }

