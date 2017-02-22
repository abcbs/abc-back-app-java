 package com.ndlan.canyin.frontdesk.util;
 
 import com.ndlan.canyin.core.common.BillPlaceEnterPageEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 
 public class UserSettingCacheSetting
 {
   private String userName = "员工";
 
   private String printerId = TrueFalseEnum.TRUE.getCode();
 
   private String isFastfoodBillPrint = TrueFalseEnum.TRUE.getCode();
 
   private String isShowModuleDesk = TrueFalseEnum.TRUE.getCode();
 
   private String isShowModuleFastfood = TrueFalseEnum.TRUE.getCode();
 
   private String isShowModuleWaimai = TrueFalseEnum.TRUE.getCode();
 
   private String isShowModuleBill = TrueFalseEnum.TRUE.getCode();
 
   private String isShowModuleMember = TrueFalseEnum.TRUE.getCode();
 
   private String isShowModuleOrder = TrueFalseEnum.TRUE.getCode();
 
   private String isShowModuleGuqing = TrueFalseEnum.TRUE.getCode();
 
   private String isStartEnterOrder = TrueFalseEnum.TRUE.getCode();
 
   private String isPayEnterDesk = TrueFalseEnum.TRUE.getCode();
 
   private String isOrderEnterDesk = TrueFalseEnum.TRUE.getCode();
 
   private String isAutoQingtai = TrueFalseEnum.TRUE.getCode();
 
   private String billPlaceEnterDeskOrPay = BillPlaceEnterPageEnum.DIANCAN_PAGE.getCode();
 
   private String isShowPlaceBillConfirmWindow = TrueFalseEnum.TRUE.getCode();
 
   private Integer leaveTime = Integer.valueOf(0);
 
   public String getUserFirstPage()
   {
     if (this.isShowModuleDesk.equals(TrueFalseEnum.TRUE.getCode()))
     {
       return "index";
     }
     if (this.isShowModuleFastfood.equals(TrueFalseEnum.TRUE.getCode()))
     {
       return "fastfood/diancai";
     }
     if (this.isShowModuleWaimai.equals(TrueFalseEnum.TRUE.getCode()))
     {
       return "takeout/list";
     }
     if (this.isShowModuleBill.equals(TrueFalseEnum.TRUE.getCode()))
     {
       return "bill/list";
     }
     if (this.isShowModuleMember.equals(TrueFalseEnum.TRUE.getCode()))
     {
       return "member";
     }
     if (this.isShowModuleOrder.equals(TrueFalseEnum.TRUE.getCode()))
     {
       return "order";
     }
     if (this.isShowModuleGuqing.equals(TrueFalseEnum.TRUE.getCode()))
     {
       return "estimate";
     }
     return "fastfood/diancai";
   }
 
   public String getUserName() {
     return this.userName;
   }
 
   public void setUserName(String userName) {
     this.userName = userName;
   }
 
   public String getPrinterId() {
     return this.printerId;
   }
 
   public void setPrinterId(String printerId) {
     this.printerId = printerId;
   }
 
   public String getIsFastfoodBillPrint() {
     return this.isFastfoodBillPrint;
   }
 
   public void setIsFastfoodBillPrint(String isFastfoodBillPrint) {
     this.isFastfoodBillPrint = isFastfoodBillPrint;
   }
 
   public String getIsShowModuleDesk() {
     return this.isShowModuleDesk;
   }
 
   public void setIsShowModuleDesk(String isShowModuleDesk) {
     this.isShowModuleDesk = isShowModuleDesk;
   }
 
   public String getIsShowModuleFastfood() {
     return this.isShowModuleFastfood;
   }
 
   public void setIsShowModuleFastfood(String isShowModuleFastfood) {
     this.isShowModuleFastfood = isShowModuleFastfood;
   }
 
   public String getIsShowModuleWaimai() {
     return this.isShowModuleWaimai;
   }
 
   public void setIsShowModuleWaimai(String isShowModuleWaimai) {
     this.isShowModuleWaimai = isShowModuleWaimai;
   }
 
   public String getIsShowModuleBill() {
     return this.isShowModuleBill;
   }
 
   public void setIsShowModuleBill(String isShowModuleBill) {
     this.isShowModuleBill = isShowModuleBill;
   }
 
   public String getIsShowModuleMember() {
     return this.isShowModuleMember;
   }
 
   public void setIsShowModuleMember(String isShowModuleMember) {
     this.isShowModuleMember = isShowModuleMember;
   }
 
   public String getIsShowModuleOrder() {
     return this.isShowModuleOrder;
   }
 
   public void setIsShowModuleOrder(String isShowModuleOrder) {
     this.isShowModuleOrder = isShowModuleOrder;
   }
 
   public String getIsShowModuleGuqing() {
     return this.isShowModuleGuqing;
   }
 
   public void setIsShowModuleGuqing(String isShowModuleGuqing) {
     this.isShowModuleGuqing = isShowModuleGuqing;
   }
 
   public String getIsStartEnterOrder() {
     return this.isStartEnterOrder;
   }
 
   public void setIsStartEnterOrder(String isStartEnterOrder) {
     this.isStartEnterOrder = isStartEnterOrder;
   }
 
   public String getIsPayEnterDesk() {
     return this.isPayEnterDesk;
   }
 
   public void setIsPayEnterDesk(String isPayEnterDesk) {
     this.isPayEnterDesk = isPayEnterDesk;
   }
 
   public String getIsOrderEnterDesk() {
     return this.isOrderEnterDesk;
   }
 
   public void setIsOrderEnterDesk(String isOrderEnterDesk) {
     this.isOrderEnterDesk = isOrderEnterDesk;
   }
 
   public String getIsAutoQingtai() {
     return this.isAutoQingtai;
   }
 
   public void setIsAutoQingtai(String isAutoQingtai) {
     this.isAutoQingtai = isAutoQingtai;
   }
 
   public String getBillPlaceEnterDeskOrPay() {
     return this.billPlaceEnterDeskOrPay;
   }
 
   public void setBillPlaceEnterDeskOrPay(String billPlaceEnterDeskOrPay) {
     this.billPlaceEnterDeskOrPay = billPlaceEnterDeskOrPay;
   }
 
   public String getIsShowPlaceBillConfirmWindow() {
     return this.isShowPlaceBillConfirmWindow;
   }
 
   public void setIsShowPlaceBillConfirmWindow(String isShowPlaceBillConfirmWindow) {
     this.isShowPlaceBillConfirmWindow = isShowPlaceBillConfirmWindow;
   }
 
   public Integer getLeaveTime() {
     return this.leaveTime;
   }
 
   public void setLeaveTime(Integer leaveTime) {
     this.leaveTime = leaveTime;
   }
 }

