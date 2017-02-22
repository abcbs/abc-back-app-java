 package com.ndlan.canyin.core.vo;
 
 import java.util.Date;
 
 public class PrintMemberCardInfoParamentsVo
 {
   private String restName;
   private String restId = "";
 
   private String operatorName = "";
 
   private Date operatorTime = new Date();
 
   private String printerId = "";
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getOperatorName() {
     return this.operatorName;
   }
 
   public void setOperatorName(String operatorName) {
     this.operatorName = operatorName;
   }
 
   public String getPrinterId() {
     return this.printerId;
   }
 
   public void setPrinterId(String printerId) {
     this.printerId = printerId;
   }
 
   public Date getOperatorTime() {
     return this.operatorTime;
   }
 
   public void setOperatorTime(Date operatorTime) {
     this.operatorTime = operatorTime;
   }
 
   public String getRestName() {
     return this.restName;
   }
 
   public void setRestName(String restName) {
     this.restName = restName;
   }
 }

