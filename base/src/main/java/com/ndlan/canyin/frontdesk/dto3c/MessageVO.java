 package com.ndlan.canyin.frontdesk.dto3c;
 
 import java.io.Serializable;
 
 public class MessageVO
   implements Serializable
 {
   private static final long serialVersionUID = -8497467011269087264L;
   private int id;
   private int code;
   private int type;
   private String title;
   private String name;
   private String content;
   private String desc;
   private String from;
   private String to;
   private String time;
   private String status;
   private String serialNo;
   private int notificationId;
   private String callType;
 
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
 
   public int getType() {
     return this.type;
   }
 
   public void setType(int type) {
     this.type = type;
   }
 
   public String getTitle() {
     return this.title;
   }
 
   public void setTitle(String title) {
     this.title = title;
   }
 
   public String getContent() {
     return this.content;
   }
 
   public void setContent(String content) {
     this.content = content;
   }
 
   public String getFrom() {
     return this.from;
   }
 
   public void setFrom(String from) {
     this.from = from;
   }
 
   public String getTo() {
     return this.to;
   }
 
   public void setTo(String to) {
     this.to = to;
   }
 
   public String getTime() {
     return this.time;
   }
 
   public void setTime(String time) {
     this.time = time;
   }
 
   public String getStatus() {
     return this.status;
   }
 
   public void setStatus(String status) {
     this.status = status;
   }
 
   public static long getSerialversionuid() {
     return -8497467011269087264L;
   }
 
   public String getCallType() {
     return this.callType;
   }
 
   public void setCallType(String callType) {
     this.callType = callType;
   }
 
   public int getId() {
     return this.id;
   }
 
   public void setId(int id) {
     this.id = id;
   }
 
   public String getSerialNo() {
     return this.serialNo;
   }
 
   public void setSerialNo(String serialNo) {
     this.serialNo = serialNo;
   }
 
   public int getNotificationId() {
     return this.notificationId;
   }
 
   public void setNotificationId(int notificationId) {
     this.notificationId = notificationId;
   }
 }

