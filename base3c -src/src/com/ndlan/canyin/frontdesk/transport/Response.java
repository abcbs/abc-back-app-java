 package com.ndlan.canyin.frontdesk.transport;
 
 public class Response
 {
   private int sign;
   private String restId;
   private String serialNO;
   private String content;
 
   public int getSign()
   {
     return this.sign;
   }
 
   public void setSign(int sign) {
     this.sign = sign;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getSerialNO() {
     return this.serialNO;
   }
 
   public void setSerialNO(String serialNO) {
     this.serialNO = serialNO;
   }
 
   public String getContent() {
     return this.content;
   }
 
   public void setContent(String content) {
     this.content = content;
   }
 }

