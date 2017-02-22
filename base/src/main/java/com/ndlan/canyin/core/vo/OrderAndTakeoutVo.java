 package com.ndlan.canyin.core.vo;
 
 import com.ndlan.canyin.core.common.BillFromEnum;

 import java.io.Serializable;
import java.math.BigDecimal;
 
 public class OrderAndTakeoutVo
   implements Serializable
 {
   private static final long serialVersionUID = -20366845123375689L;
   private String billFrom;
   private String billFromDesc;
   private String billType;
   private String status;
   private String isVip;
   private String eatTime;
   private String mobile;
   private BigDecimal totalCost;
   private String peopleName;
   private String address;
   private String billNo;
   private String billId;
 
   public String getBillId()
   {
     return this.billId;
   }
 
   public void setBillId(String billId) {
     this.billId = billId;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public String getBillFrom() {
     return this.billFrom;
   }
 
   public void setBillFrom(String billFrom) {
     this.billFrom = billFrom;
   }
 
   public String getBillType() {
     return this.billType;
   }
 
   public void setBillType(String billType) {
     this.billType = billType;
   }
 
   public String getStatus() {
     return this.status;
   }
 
   public void setStatus(String status) {
     this.status = status;
   }
 
   public String getIsVip() {
     return this.isVip;
   }
 
   public void setIsVip(String isVip) {
     this.isVip = isVip;
   }
 
   public String getEatTime() {
     return this.eatTime;
   }
 
   public void setEatTime(String eatTime) {
     this.eatTime = eatTime;
   }
 
   public String getMobile() {
     return this.mobile;
   }
 
   public void setMobile(String mobile) {
     this.mobile = mobile;
   }
 
   public BigDecimal getTotalCost() {
     return this.totalCost;
   }
 
   public void setTotalCost(BigDecimal totalCost) {
     this.totalCost = totalCost;
   }
 
   public String getPeopleName() {
     return this.peopleName;
   }
 
   public void setPeopleName(String peopleName) {
     this.peopleName = peopleName;
   }
 
   public String getAddress() {
     return this.address;
   }
 
   public void setAddress(String address) {
     this.address = address;
   }
 
   public String getBillFromDesc() {
     this.billFromDesc = BillFromEnum.getDesc(getBillFrom());
     return this.billFromDesc;
   }
 }

