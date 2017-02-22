 package com.ndlan.canyin.frontdesk.dto3c;
 
 import com.ndlan.canyin.base.entity.cygl.Dishe;
 import java.util.List;
 
 public class SelfOrder
 {
   private String tabNo;
   private String notes;
   private List<Dishe> dishes;
   private String dishesStr;
 
   public String getTabNo()
   {
     return this.tabNo;
   }
   public void setTabNo(String tabNo) {
     this.tabNo = tabNo;
   }
   public String getNotes() {
     return this.notes;
   }
   public void setNotes(String notes) {
     this.notes = notes;
   }
   public List<Dishe> getDishes() {
     return this.dishes;
   }
   public void setDishes(List<Dishe> dishes) {
     this.dishes = dishes;
   }
   public String getDishesStr() {
     return this.dishesStr;
   }
   public void setDishesStr(String dishesStr) {
     this.dishesStr = dishesStr;
   }
 }

