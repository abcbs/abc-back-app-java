 package com.ndlan.canyin.core.vo;
 
 import java.math.BigDecimal;
 import java.util.Date;
 
 public class MemberCardRechargeVo
 {
   private String restName;
   private String cardNo;
   private Date rechargeTime;
   private String operator;
   private BigDecimal rechargeCash;
   private BigDecimal paidinCash;
   private BigDecimal giveCash;
   private BigDecimal cashPledge;
   private BigDecimal addMemberIntegral;
   private BigDecimal balance;
   private BigDecimal memberIntegral;
   private String isDrawBill;
 
   public String getRestName()
   {
     return this.restName;
   }
 
   public void setRestName(String restName) {
     this.restName = restName;
   }
 
   public String getCardNo() {
     return this.cardNo;
   }
 
   public void setCardNo(String cardNo) {
     this.cardNo = cardNo;
   }
 
   public Date getRechargeTime() {
     return this.rechargeTime;
   }
 
   public void setRechargeTime(Date rechargeTime) {
     this.rechargeTime = rechargeTime;
   }
 
   public String getOperator() {
     return this.operator;
   }
 
   public void setOperator(String operator) {
     this.operator = operator;
   }
 
   public BigDecimal getRechargeCash() {
     return this.rechargeCash;
   }
 
   public void setRechargeCash(BigDecimal rechargeCash) {
     this.rechargeCash = rechargeCash;
   }
 
   public BigDecimal getPaidinCash() {
     return this.paidinCash;
   }
 
   public void setPaidinCash(BigDecimal paidinCash) {
     this.paidinCash = paidinCash;
   }
 
   public BigDecimal getGiveCash() {
     return this.giveCash;
   }
 
   public void setGiveCash(BigDecimal giveCash) {
     this.giveCash = giveCash;
   }
 
   public BigDecimal getCashPledge() {
     return this.cashPledge;
   }
 
   public void setCashPledge(BigDecimal cashPledge) {
     this.cashPledge = cashPledge;
   }
 
   public BigDecimal getAddMemberIntegral() {
     return this.addMemberIntegral;
   }
 
   public void setAddMemberIntegral(BigDecimal addMemberIntegral) {
     this.addMemberIntegral = addMemberIntegral;
   }
 
   public BigDecimal getBalance() {
     return this.balance;
   }
 
   public void setBalance(BigDecimal balance) {
     this.balance = balance;
   }
 
   public BigDecimal getMemberIntegral() {
     return this.memberIntegral;
   }
 
   public void setMemberIntegral(BigDecimal memberIntegral) {
     this.memberIntegral = memberIntegral;
   }
 
   public String getIsDrawBill() {
     return this.isDrawBill;
   }
 
   public void setIsDrawBill(String isDrawBill) {
     this.isDrawBill = isDrawBill;
   }
 }

