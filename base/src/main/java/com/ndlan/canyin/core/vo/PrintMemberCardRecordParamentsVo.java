 package com.ndlan.canyin.core.vo;
 
 import java.math.BigDecimal;
 import java.util.Date;
 
 public class PrintMemberCardRecordParamentsVo
 {
   private String restName;
   private String billNo = "";
 
   private String operatorName = "";
 
   private Date operatorTime = new Date();
   private BigDecimal membercardCost;
   private BigDecimal addIntegral;
 
   public String getOperatorName()
   {
     return this.operatorName;
   }
 
   public void setOperatorName(String operatorName) {
     this.operatorName = operatorName;
   }
 
   public Date getOperatorTime() {
     return this.operatorTime;
   }
 
   public void setOperatorTime(Date operatorTime) {
     this.operatorTime = operatorTime;
   }
 
   public BigDecimal getMembercardCost() {
     return this.membercardCost;
   }
 
   public void setMembercardCost(BigDecimal membercardCost) {
     this.membercardCost = membercardCost;
   }
 
   public BigDecimal getAddIntegral() {
     return this.addIntegral;
   }
 
   public void setAddIntegral(BigDecimal addIntegral) {
     this.addIntegral = addIntegral;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public String getRestName() {
     return this.restName;
   }
 
   public void setRestName(String restName) {
     this.restName = restName;
   }
 }

