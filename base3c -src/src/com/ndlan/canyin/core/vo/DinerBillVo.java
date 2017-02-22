 package com.ndlan.canyin.core.vo;
 
 import java.math.BigDecimal;
 import java.util.Date;
import java.util.List;

import com.ndlan.canyin.core.vo.DinerBillDisheVo;
 
 public class DinerBillVo
 {
   private String restName;
   private String billNo;
   private String oldBillNo;
   private String cardNo;
   private String cardBalance;
   private BigDecimal memberIntegral;
   private Date payTime;
   private String cashierName;
   private List<DinerBillDisheVo> dinerBillDishes;
   private List<DinerBillDisheVo> dinerBillDishesSet;
   private BigDecimal oriCost;
   private BigDecimal serviceChargeMoney;
   private BigDecimal saveCost;
   private BigDecimal molingModeCost;
   private BigDecimal payableCost;
   private BigDecimal oddChange;
   private String payments;
   private String oldTabName;
   private String tabName;
   private String oldTabNo;
   private String waiterName;
   private String operatorName = "";
   private Date orderTime;
   private String urgeDishes;
   private Date urgeTime;
   private String notes;
   private String isDrawBill;
   private DinerBillDisheVo dinerBillDisheVo;
   private String message;
   private BigDecimal realCost;
   private String billType;
   private String billFrom;
   private Date cancleTime;
   private String sendAddress;
   private String contactName;
   private String telephone;
   private String mobile;
   private String senderName;
   private BigDecimal deliverCost;
   private Date resettleTime;
 
   public String getBillType()
   {
     return this.billType;
   }
 
   public void setBillType(String billType) {
     this.billType = billType;
   }
 
   public String getBillFrom() {
     return this.billFrom;
   }
 
   public void setBillFrom(String billFrom) {
     this.billFrom = billFrom;
   }
 
   public BigDecimal getRealCost() {
     return this.realCost;
   }
 
   public void setRealCost(BigDecimal realCost) {
     this.realCost = realCost;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public String getCardNo() {
     return this.cardNo;
   }
 
   public void setCardNo(String cardNo) {
     this.cardNo = cardNo;
   }
 
   public BigDecimal getMemberIntegral() {
     return this.memberIntegral;
   }
 
   public void setMemberIntegral(BigDecimal memberIntegral) {
     this.memberIntegral = memberIntegral;
   }
 
   public Date getPayTime() {
     return this.payTime;
   }
 
   public void setPayTime(Date payTime) {
     this.payTime = payTime;
   }
 
   public String getCashierName() {
     return this.cashierName;
   }
 
   public void setCashierName(String cashierName) {
     this.cashierName = cashierName;
   }
 
   public List<DinerBillDisheVo> getDinerBillDishes() {
     return this.dinerBillDishes;
   }
 
   public void setDinerBillDishes(List<DinerBillDisheVo> dinerBillDishes) {
     this.dinerBillDishes = dinerBillDishes;
   }
 
   public BigDecimal getOriCost() {
     return this.oriCost;
   }
 
   public void setOriCost(BigDecimal oriCost) {
     this.oriCost = oriCost;
   }
 
   public BigDecimal getServiceChargeMoney() {
     return this.serviceChargeMoney;
   }
 
   public void setServiceChargeMoney(BigDecimal serviceChargeMoney) {
     this.serviceChargeMoney = serviceChargeMoney;
   }
 
   public BigDecimal getSaveCost() {
     return this.saveCost;
   }
 
   public void setSaveCost(BigDecimal saveCost) {
     this.saveCost = saveCost;
   }
 
   public BigDecimal getMolingModeCost() {
     return this.molingModeCost;
   }
 
   public void setMolingModeCost(BigDecimal molingModeCost) {
     this.molingModeCost = molingModeCost;
   }
 
   public BigDecimal getPayableCost() {
     return this.payableCost;
   }
 
   public void setPayableCost(BigDecimal payableCost) {
     this.payableCost = payableCost;
   }
 
   public BigDecimal getOddChange() {
     return this.oddChange;
   }
 
   public void setOddChange(BigDecimal oddChange) {
     this.oddChange = oddChange;
   }
 
   public String getPayments() {
     return this.payments;
   }
 
   public void setPayments(String payments) {
     this.payments = payments;
   }
 
   public String getRestName() {
     return this.restName;
   }
 
   public void setRestName(String restName) {
     this.restName = restName;
   }
 
   public String getTabName() {
     return this.tabName;
   }
 
   public void setTabName(String tabName) {
     this.tabName = tabName;
   }
 
   public String getWaiterName() {
     return this.waiterName;
   }
 
   public void setWaiterName(String waiterName) {
     this.waiterName = waiterName;
   }
 
   public Date getOrderTime() {
     return this.orderTime;
   }
 
   public void setOrderTime(Date orderTime) {
     this.orderTime = orderTime;
   }
 
   public String getUrgeDishes() {
     return this.urgeDishes;
   }
 
   public void setUrgeDishes(String urgeDishes) {
     this.urgeDishes = urgeDishes;
   }
 
   public Date getUrgeTime() {
     return this.urgeTime;
   }
 
   public void setUrgeTime(Date urgeTime) {
     this.urgeTime = urgeTime;
   }
 
   public DinerBillDisheVo getDinerBillDisheVo() {
     return this.dinerBillDisheVo;
   }
 
   public void setDinerBillDisheVo(DinerBillDisheVo dinerBillDisheVo) {
     this.dinerBillDisheVo = dinerBillDisheVo;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public String getOldTabNo() {
     return this.oldTabNo;
   }
 
   public void setOldTabNo(String oldTabNo) {
     this.oldTabNo = oldTabNo;
   }
 
   public String getOldBillNo() {
     return this.oldBillNo;
   }
 
   public void setOldBillNo(String oldBillNo) {
     this.oldBillNo = oldBillNo;
   }
 
   public String getIsDrawBill() {
     return this.isDrawBill;
   }
 
   public void setIsDrawBill(String isDrawBill) {
     this.isDrawBill = isDrawBill;
   }
 
   public String getMessage() {
     return this.message;
   }
 
   public void setMessage(String message) {
     this.message = message;
   }
 
   public List<DinerBillDisheVo> getDinerBillDishesSet() {
     return this.dinerBillDishesSet;
   }
 
   public void setDinerBillDishesSet(List<DinerBillDisheVo> dinerBillDishesSet) {
     this.dinerBillDishesSet = dinerBillDishesSet;
   }
 
   public String getCardBalance() {
     return this.cardBalance;
   }
 
   public void setCardBalance(String cardBalance) {
     this.cardBalance = cardBalance;
   }
 
   public Date getCancleTime() {
     return this.cancleTime;
   }
 
   public void setCancleTime(Date cancleTime) {
     this.cancleTime = cancleTime;
   }
 
   public String getSenderName() {
     return this.senderName;
   }
 
   public void setSenderName(String senderName) {
     this.senderName = senderName;
   }
 
   public BigDecimal getDeliverCost() {
     return this.deliverCost;
   }
 
   public void setDeliverCost(BigDecimal deliverCost) {
     this.deliverCost = deliverCost;
   }
 
   public String getSendAddress() {
     return this.sendAddress;
   }
 
   public void setSendAddress(String sendAddress) {
     this.sendAddress = sendAddress;
   }
 
   public String getContactName() {
     return this.contactName;
   }
 
   public void setContactName(String contactName) {
     this.contactName = contactName;
   }
 
   public String getTelephone() {
     return this.telephone;
   }
 
   public void setTelephone(String telephone) {
     this.telephone = telephone;
   }
 
   public String getMobile() {
     return this.mobile;
   }
 
   public void setMobile(String mobile) {
     this.mobile = mobile;
   }
 
   public Date getResettleTime() {
     return this.resettleTime;
   }
 
   public void setResettleTime(Date resettleTime) {
     this.resettleTime = resettleTime;
   }
 
   public String getOperatorName() {
     return this.operatorName;
   }
 
   public void setOperatorName(String operatorName) {
     this.operatorName = operatorName;
   }
 
   public String getOldTabName() {
     return this.oldTabName;
   }
 
   public void setOldTabName(String oldTabName) {
     this.oldTabName = oldTabName;
   }
 }

