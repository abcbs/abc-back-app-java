 package com.ndlan.canyin.core.vo;
 
 public class TakingDishesVo
 {
   private String restId;
   private String billId;
   private String dishId;
   private String dishCode;
   private String dishName;
   private float dishNum;
   private String dishAvoidIds;
   private String dishTasteIds;
   private String dishCustomNotes;
   private String dishPungentLevelCode;
   private String billTasteIds;
   private String billPungentLevelCode;
   private String billAvoidIds;
   private String billCustomNotes;
   private boolean isUserDefined = false;
 
   public boolean isUserDefined() {
     return this.isUserDefined;
   }
 
   public void setUserDefined(boolean isUserDefined)
   {
     this.isUserDefined = isUserDefined;
   }
 
   public String getRestId()
   {
     return this.restId;
   }
 
   public void setRestId(String restId)
   {
     this.restId = restId;
   }
 
   public String getBillId()
   {
     return this.billId;
   }
 
   public void setBillId(String billId)
   {
     this.billId = billId;
   }
 
   public String getDishId()
   {
     return this.dishId;
   }
 
   public void setDishId(String dishId)
   {
     this.dishId = dishId;
   }
 
   public String getDishCode()
   {
     return this.dishCode;
   }
 
   public void setDishCode(String dishCode)
   {
     this.dishCode = dishCode;
   }
 
   public String getDishName()
   {
     return this.dishName;
   }
 
   public void setDishName(String dishName)
   {
     this.dishName = dishName;
   }
 
   public float getDishNum()
   {
     return this.dishNum;
   }
 
   public void setDishNum(float dishNum)
   {
     this.dishNum = dishNum;
   }
 
   public String getDishAvoidIds()
   {
     return this.dishAvoidIds;
   }
 
   public void setDishAvoidIds(String dishAvoidIds)
   {
     this.dishAvoidIds = dishAvoidIds;
   }
 
   public String getDishTasteIds()
   {
     return this.dishTasteIds;
   }
 
   public void setDishTasteIds(String dishTasteIds)
   {
     this.dishTasteIds = dishTasteIds;
   }
 
   public String getDishCustomNotes()
   {
     return this.dishCustomNotes;
   }
 
   public void setDishCustomNotes(String dishCustomNotes)
   {
     this.dishCustomNotes = dishCustomNotes;
   }
 
   public String getBillTasteIds()
   {
     return this.billTasteIds;
   }
 
   public void setBillTasteIds(String billTasteIds)
   {
     this.billTasteIds = billTasteIds;
   }
 
   public String getBillAvoidIds() {
     return this.billAvoidIds;
   }
 
   public void setBillAvoidIds(String billAvoidIds)
   {
     this.billAvoidIds = billAvoidIds;
   }
 
   public String getBillCustomNotes()
   {
     return this.billCustomNotes;
   }
 
   public void setBillCustomNotes(String billCustomNotes)
   {
     this.billCustomNotes = billCustomNotes;
   }
 
   public String getDishPungentLevelCode()
   {
     return this.dishPungentLevelCode;
   }
 
   public void setDishPungentLevelCode(String dishPungentLevelCode)
   {
     this.dishPungentLevelCode = dishPungentLevelCode;
   }
 
   public String getBillPungentLevelCode()
   {
     return this.billPungentLevelCode;
   }
 
   public void setBillPungentLevelCode(String billPungentLevelCode)
   {
     this.billPungentLevelCode = billPungentLevelCode;
   }
 
   public static void main(String[] args)
   {
   }
 }

