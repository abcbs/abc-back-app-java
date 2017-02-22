 package com.ndlan.canyin.core.vo;
 
 public class PrintSalesVolumeDetailDatasVo
 {
   private String dishName = "";
   private String salesVolume;
   private String sumMoney;
 
   public String getSalesVolume()
   {
     return this.salesVolume;
   }
 
   public void setSalesVolume(String salesVolume) {
     this.salesVolume = salesVolume;
   }
 
   public String getSumMoney() {
     return this.sumMoney;
   }
 
   public void setSumMoney(String sumMoney) {
     this.sumMoney = sumMoney;
   }
 
   public String getDishName() {
     return this.dishName;
   }
 
   public void setDishName(String dishName) {
     this.dishName = dishName;
   }
 }

