 package com.ndlan.canyin.core.vo;
 
 import java.math.BigDecimal;
import java.util.List;

import com.ndlan.canyin.core.vo.DinerBillDisheVo;
import com.ndlan.canyin.core.vo.PaymentTypeVO;
 
 public class EmployeShiftVo
 {
   private String shiftEmpName;
   private String lastShiftTime;
   private String currentShiftTime;
   private List<PaymentTypeVO> currentPaymentTypeVOs;
   private List<PaymentTypeVO> memberPaymentTypeVOs;
   private List<PaymentTypeVO> orderPaymentTypeVOs;
   private BigDecimal orderForegiftSum;
   private BigDecimal currentHandonCash;
   private BigDecimal currentHandoffCash;
   private BigDecimal currentCash;
   private BigDecimal lastBalanceCash;
   private String currentCashBalance;
   private BigDecimal currentMoneySum;
   private BigDecimal memberMoneySum;
   private Long peopleNum;
   private BigDecimal molingSum;
   private BigDecimal discountMoneySum;
   private BigDecimal serviceMoneySum;
   private BigDecimal tuicaiMoneySum;
   private BigDecimal zengcaiMoneySum;
   private BigDecimal currentUnPayMoneySum;
   private BigDecimal oddChangeSum;
   private BigDecimal forceMoneySum;
   private List<DinerBillDisheVo> dinerBillDishes;
 
   public String getShiftEmpName()
   {
     return this.shiftEmpName;
   }
 
   public void setShiftEmpName(String shiftEmpName) {
     this.shiftEmpName = shiftEmpName;
   }
 
   public String getLastShiftTime() {
     return this.lastShiftTime;
   }
 
   public void setLastShiftTime(String lastShiftTime) {
     this.lastShiftTime = lastShiftTime;
   }
 
   public String getCurrentShiftTime() {
     return this.currentShiftTime;
   }
 
   public void setCurrentShiftTime(String currentShiftTime) {
     this.currentShiftTime = currentShiftTime;
   }
 
   public List<PaymentTypeVO> getCurrentPaymentTypeVOs() {
     return this.currentPaymentTypeVOs;
   }
 
   public void setCurrentPaymentTypeVOs(List<PaymentTypeVO> currentPaymentTypeVOs) {
     this.currentPaymentTypeVOs = currentPaymentTypeVOs;
   }
 
   public List<PaymentTypeVO> getMemberPaymentTypeVOs() {
     return this.memberPaymentTypeVOs;
   }
 
   public void setMemberPaymentTypeVOs(List<PaymentTypeVO> memberPaymentTypeVOs) {
     this.memberPaymentTypeVOs = memberPaymentTypeVOs;
   }
 
   public BigDecimal getCurrentHandonCash() {
     return this.currentHandonCash;
   }
 
   public void setCurrentHandonCash(BigDecimal currentHandonCash) {
     this.currentHandonCash = currentHandonCash;
   }
 
   public BigDecimal getCurrentHandoffCash() {
     return this.currentHandoffCash;
   }
 
   public void setCurrentHandoffCash(BigDecimal currentHandoffCash) {
     this.currentHandoffCash = currentHandoffCash;
   }
 
   public BigDecimal getCurrentCash() {
     return this.currentCash;
   }
 
   public void setCurrentCash(BigDecimal currentCash) {
     this.currentCash = currentCash;
   }
 
   public BigDecimal getLastBalanceCash() {
     return this.lastBalanceCash;
   }
 
   public void setLastBalanceCash(BigDecimal lastBalanceCash) {
     this.lastBalanceCash = lastBalanceCash;
   }
 
   public String getCurrentCashBalance() {
     return this.currentCashBalance;
   }
 
   public void setCurrentCashBalance(String currentCashBalance) {
     this.currentCashBalance = currentCashBalance;
   }
 
   public BigDecimal getCurrentMoneySum() {
     return this.currentMoneySum;
   }
 
   public void setCurrentMoneySum(BigDecimal currentMoneySum) {
     this.currentMoneySum = currentMoneySum;
   }
 
   public BigDecimal getMemberMoneySum() {
     return this.memberMoneySum;
   }
 
   public void setMemberMoneySum(BigDecimal memberMoneySum) {
     this.memberMoneySum = memberMoneySum;
   }
 
   public Long getPeopleNum() {
     return this.peopleNum;
   }
 
   public void setPeopleNum(Long peopleNum) {
     this.peopleNum = peopleNum;
   }
 
   public BigDecimal getMolingSum() {
     return this.molingSum;
   }
 
   public void setMolingSum(BigDecimal molingSum) {
     this.molingSum = molingSum;
   }
 
   public BigDecimal getDiscountMoneySum() {
     return this.discountMoneySum;
   }
 
   public void setDiscountMoneySum(BigDecimal discountMoneySum) {
     this.discountMoneySum = discountMoneySum;
   }
 
   public BigDecimal getServiceMoneySum() {
     return this.serviceMoneySum;
   }
 
   public void setServiceMoneySum(BigDecimal serviceMoneySum) {
     this.serviceMoneySum = serviceMoneySum;
   }
 
   public BigDecimal getTuicaiMoneySum() {
     return this.tuicaiMoneySum;
   }
 
   public void setTuicaiMoneySum(BigDecimal tuicaiMoneySum) {
     this.tuicaiMoneySum = tuicaiMoneySum;
   }
 
   public BigDecimal getZengcaiMoneySum() {
     return this.zengcaiMoneySum;
   }
 
   public void setZengcaiMoneySum(BigDecimal zengcaiMoneySum) {
     this.zengcaiMoneySum = zengcaiMoneySum;
   }
 
   public BigDecimal getCurrentUnPayMoneySum() {
     return this.currentUnPayMoneySum;
   }
 
   public void setCurrentUnPayMoneySum(BigDecimal currentUnPayMoneySum) {
     this.currentUnPayMoneySum = currentUnPayMoneySum;
   }
 
   public List<DinerBillDisheVo> getDinerBillDishes() {
     return this.dinerBillDishes;
   }
 
   public void setDinerBillDishes(List<DinerBillDisheVo> dinerBillDishes) {
     this.dinerBillDishes = dinerBillDishes;
   }
 
   public List<PaymentTypeVO> getOrderPaymentTypeVOs() {
     return this.orderPaymentTypeVOs;
   }
 
   public void setOrderPaymentTypeVOs(List<PaymentTypeVO> orderPaymentTypeVOs) {
     this.orderPaymentTypeVOs = orderPaymentTypeVOs;
   }
 
   public BigDecimal getOddChangeSum() {
     return this.oddChangeSum;
   }
 
   public void setOddChangeSum(BigDecimal oddChangeSum) {
     this.oddChangeSum = oddChangeSum;
   }
 
   public BigDecimal getForceMoneySum() {
     return this.forceMoneySum;
   }
 
   public void setForceMoneySum(BigDecimal forceMoneySum) {
     this.forceMoneySum = forceMoneySum;
   }
 
   public BigDecimal getOrderForegiftSum() {
     return this.orderForegiftSum;
   }
 
   public void setOrderForegiftSum(BigDecimal orderForegiftSum) {
     this.orderForegiftSum = orderForegiftSum;
   }
 }

