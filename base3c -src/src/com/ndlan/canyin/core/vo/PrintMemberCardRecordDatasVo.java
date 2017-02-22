 package com.ndlan.canyin.core.vo;
 
 public class PrintMemberCardRecordDatasVo
 {
   private String title = "";
 
   private String billNo = "";
 
   private String cardNo = "";
 
   private String payMoney = "";
 
   private String addMemberIntegral = "";
   private String balance;
   private String memberIntegral;
   private String operatorName = "";
 
   private String operatorTime = "";
 
   public String getOperatorName()
   {
     return this.operatorName;
   }
 
   public void setOperatorName(String operatorName) {
     this.operatorName = operatorName;
   }
 
   public String getCardNo() {
     return this.cardNo;
   }
 
   public void setCardNo(String cardNo) {
     this.cardNo = cardNo;
   }
 
   public String getOperatorTime() {
     return this.operatorTime;
   }
 
   public void setOperatorTime(String operatorTime) {
     this.operatorTime = operatorTime;
   }
 
   public String getBalance() {
     return this.balance;
   }
 
   public void setBalance(String balance) {
     this.balance = balance;
   }
 
   public String getMemberIntegral()
   {
     return this.memberIntegral;
   }
 
   public void setMemberIntegral(String memberIntegral) {
     this.memberIntegral = memberIntegral;
   }
 
   public String getPayMoney() {
     return this.payMoney;
   }
 
   public void setPayMoney(String payMoney) {
     this.payMoney = payMoney;
   }
 
   public String getAddMemberIntegral() {
     return this.addMemberIntegral;
   }
 
   public void setAddMemberIntegral(String addMemberIntegral) {
     this.addMemberIntegral = addMemberIntegral;
   }
 
   public String getTitle() {
     return this.title;
   }
 
   public void setTitle(String title) {
     this.title = title;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 }

