 package com.ndlan.canyin.core.vo;
 
 import com.ndlan.canyin.core.utils.BigDecimalUtil;

import org.apache.commons.lang3.StringUtils;
 
 public class PrintMemberCardInfoDatasVo
 {
   private String title = "";
 
   private String cardNo = "";
   private String balance;
   private String cardStatusName;
   private String memberIntegral;
   private String cardClassName = "";
   private String cardIssueTime;
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
 
   public String getCardStatusName() {
     return this.cardStatusName;
   }
 
   public void setCardStatusName(String cardStatusName) {
     this.cardStatusName = cardStatusName;
   }
 
   public String getCardClassName() {
     return this.cardClassName;
   }
 
   public void setCardClassName(String cardClassName) {
     this.cardClassName = cardClassName;
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
 
   public String getCardIssueTime() {
     return this.cardIssueTime;
   }
 
   public void setCardIssueTime(String cardIssueTime) {
     this.cardIssueTime = cardIssueTime;
   }
 
   public String getMemberIntegral() {
     return StringUtils.isNotEmpty(this.memberIntegral) ? BigDecimalUtil.formatFloat(Float.valueOf(Float.parseFloat(this.memberIntegral))).toString() : "0";
   }
 
   public void setMemberIntegral(String memberIntegral) {
     this.memberIntegral = memberIntegral;
   }
 
   public String getTitle() {
     return this.title;
   }
 
   public void setTitle(String title) {
     this.title = title;
   }
 }

