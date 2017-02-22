 package com.ndlan.canyin.core.vo;
 
 import java.math.BigDecimal;
 
 public class PaymentTypeVO
 {
   private String paymentType;
   private String paymentName;
   private String cptId;
   private BigDecimal money;
   private String enableStatus;
   private String peopleNum;  //就餐人数
   private String realCost; //实收金额
   private String molingModeCost;//抹零金额
 
   public String getPeopleNum() {
	return peopleNum;
}

public void setPeopleNum(String peopleNum) {
	this.peopleNum = peopleNum;
}

public String getRealCost() {
	return realCost;
}

public void setRealCost(String realCost) {
	this.realCost = realCost;
}



public String getMolingModeCost() {
	return molingModeCost;
}

public void setMolingModeCost(String molingModeCost) {
	this.molingModeCost = molingModeCost;
}

public String getPaymentType()
   {
     return this.paymentType;
   }
 
   public void setPaymentType(String paymentType) {
     this.paymentType = paymentType;
   }
 
   public String getPaymentName() {
     return this.paymentName;
   }
 
   public void setPaymentName(String paymentName) {
     this.paymentName = paymentName;
   }
 
   public BigDecimal getMoney() {
     return this.money;
   }
 
   public void setMoney(BigDecimal money) {
     this.money = money;
   }
 
   public String getCptId() {
     return this.cptId;
   }
 
   public void setCptId(String cptId) {
     this.cptId = cptId;
   }
 
   public String getEnableStatus() {
     return this.enableStatus;
   }
 
   public void setEnableStatus(String enableStatus) {
     this.enableStatus = enableStatus;
   }
 }

