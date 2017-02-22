 package com.ndlan.canyin.core.vo;
 
 import java.math.BigDecimal;
import java.util.List;

import com.ndlan.canyin.core.vo.DinerBillDisheVo;
 
 public class DinerBillDisheVo
 {
   private String dishesName;
   private BigDecimal unitPrice;
   private Float unitNum;
   private String unitName;
   private String discountType;
   private Integer discount;
   private BigDecimal realCost;
   private BigDecimal oriCost;
   private String notes;
   private String isSet;
   private String dsDishesDesc;
   private String replaseDisheInfo;
   private List<DinerBillDisheVo> dishesSetDishesList;
   private Float cancleNum;
 
   public String getDishesName()
   {
     return this.dishesName;
   }
 
   public void setDishesName(String dishesName) {
     this.dishesName = dishesName;
   }
 
   public BigDecimal getUnitPrice() {
     return this.unitPrice;
   }
 
   public void setUnitPrice(BigDecimal unitPrice) {
     this.unitPrice = unitPrice;
   }
 
   public Float getUnitNum() {
     return this.unitNum;
   }
 
   public void setUnitNum(Float unitNum) {
     this.unitNum = unitNum;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public String getUnitName() {
     return this.unitName;
   }
 
   public void setUnitName(String unitName) {
     this.unitName = unitName;
   }
 
   public String getDiscountType() {
     return this.discountType;
   }
 
   public void setDiscountType(String discountType) {
     this.discountType = discountType;
   }
 
   public Integer getDiscount() {
     return this.discount;
   }
 
   public void setDiscount(Integer discount) {
     this.discount = discount;
   }
 
   public BigDecimal getRealCost() {
     return this.realCost;
   }
 
   public void setRealCost(BigDecimal realCost) {
     this.realCost = realCost;
   }
 
   public BigDecimal getOriCost() {
     return this.oriCost;
   }
 
   public void setOriCost(BigDecimal oriCost) {
     this.oriCost = oriCost;
   }
 
   public String getReplaseDisheInfo() {
     return this.replaseDisheInfo;
   }
 
   public void setReplaseDisheInfo(String replaseDisheInfo) {
     this.replaseDisheInfo = replaseDisheInfo;
   }
 
   public String getDsDishesDesc() {
     return this.dsDishesDesc;
   }
 
   public void setDsDishesDesc(String dsDishesDesc) {
     this.dsDishesDesc = dsDishesDesc;
   }
 
   public String getIsSet() {
     return this.isSet;
   }
 
   public void setIsSet(String isSet) {
     this.isSet = isSet;
   }
 
   public List<DinerBillDisheVo> getDishesSetDishesList() {
     return this.dishesSetDishesList;
   }
 
   public void setDishesSetDishesList(List<DinerBillDisheVo> dishesSetDishesList) {
     this.dishesSetDishesList = dishesSetDishesList;
   }
 
   public Float getCancleNum() {
     return this.cancleNum;
   }
 
   public void setCancleNum(Float cancleNum) {
     this.cancleNum = cancleNum;
   }
 }

