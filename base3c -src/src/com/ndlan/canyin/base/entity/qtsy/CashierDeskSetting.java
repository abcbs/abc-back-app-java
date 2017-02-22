 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.ndlan.canyin.base.entity.BaseEntity;

 import java.io.Serializable;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_cashier_desk_setting")
 public class CashierDeskSetting extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="ccds_id", unique=true, nullable=false, length=36)
   private String ccdsId;
 
   @Column(name="is_pay_enter_desk", length=1)
   private String isPayEnterDesk;
 
   @Column(name="is_show_payed_bill", length=1)
   private String isShowPayedBill;
 
   @Column(name="is_start_enter_order", length=1)
   private String isStartEnterOrder;
 
   @Column(name="is_unplace_bill_can_paying", length=1)
   private String isUnplaceBillCanPaying;
 
   @Column(name="is_order_enter_desk", length=1)
   private String isOrderEnterDesk;
 
   @Column(name="is_auto_qingtai", length=1)
   private String isAutoQingtai;
 
   @Column(name="lock_screen_time")
   private int lockScreenTime;
 
   @Column(name="order_lock_time")
   private int orderLockTime;
 
   @Column(name="message_tel", length=11)
   private String messageTel;
 
   @Column(name="order_expire_time")
   private int orderExpireTime;
 
   @Column(name="order_hint_time")
   private int orderHintTime;
 
   @Column(name="order_warn_time")
   private int orderWarnTime;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="bill_place_enter_desk_or_pay")
   private String billPlaceEnterDeskOrPay;
 
   @Column(name="is_show_place_bill_confirm_window")
   private String isShowPlaceBillConfirmWindow;
 
   @Column(name="is_fastfood_bill_print")
   private String isFastfoodBillPrint;
 
   @Column(name="emp_id")
   private String empId;
 
   @Column(name="printer_id")
   private String printerId;
 
   @Column(name="is_show_module_desk")
   private String isShowModuleDesk;
 
   @Column(name="is_show_module_fastfood")
   private String isShowModuleFastfood;
 
   @Column(name="is_show_module_bill")
   private String isShowModuleBill;
 
   @Column(name="is_show_module_member")
   private String isShowModuleMember;
 
   @Column(name="is_show_module_order")
   private String isShowModuleOrder;
 
   @Column(name="is_show_module_guqing")
   private String isShowModuleGuqing;
 
   @Column(name="is_show_module_waimai")
   private String isShowModuleWaimai;
 
   @Column(name="leave_time")
   private Integer leaveTime;
 
   public String getCcdsId()
   {
     return this.ccdsId;
   }
 
   public void setCcdsId(String ccdsId) {
     this.ccdsId = ccdsId;
   }
 
   public String getIsPayEnterDesk()
   {
     return this.isPayEnterDesk;
   }
 
   public void setIsPayEnterDesk(String isPayEnterDesk) {
     this.isPayEnterDesk = isPayEnterDesk;
   }
 
   public String getIsShowPayedBill() {
     return this.isShowPayedBill;
   }
 
   public void setIsShowPayedBill(String isShowPayedBill) {
     this.isShowPayedBill = isShowPayedBill;
   }
 
   public String getIsStartEnterOrder() {
     return this.isStartEnterOrder;
   }
 
   public void setIsStartEnterOrder(String isStartEnterOrder) {
     this.isStartEnterOrder = isStartEnterOrder;
   }
 
   public String getIsUnplaceBillCanPaying() {
     return this.isUnplaceBillCanPaying;
   }
 
   public void setIsUnplaceBillCanPaying(String isUnplaceBillCanPaying) {
     this.isUnplaceBillCanPaying = isUnplaceBillCanPaying;
   }
 
   public int getLockScreenTime() {
     return this.lockScreenTime;
   }
 
   public void setLockScreenTime(int lockScreenTime) {
     this.lockScreenTime = lockScreenTime;
   }
 
   public String getMessageTel() {
     return this.messageTel;
   }
 
   public void setMessageTel(String messageTel) {
     this.messageTel = messageTel;
   }
 
   public int getOrderExpireTime() {
     return this.orderExpireTime;
   }
 
   public void setOrderExpireTime(int orderExpireTime) {
     this.orderExpireTime = orderExpireTime;
   }
 
   public int getOrderHintTime() {
     return this.orderHintTime;
   }
 
   public void setOrderHintTime(int orderHintTime) {
     this.orderHintTime = orderHintTime;
   }
 
   public int getOrderWarnTime() {
     return this.orderWarnTime;
   }
 
   public void setOrderWarnTime(int orderWarnTime) {
     this.orderWarnTime = orderWarnTime;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getIsOrderEnterDesk() {
     return this.isOrderEnterDesk;
   }
 
   public void setIsOrderEnterDesk(String isOrderEnterDesk) {
     this.isOrderEnterDesk = isOrderEnterDesk;
   }
 
   public int getOrderLockTime() {
     return this.orderLockTime;
   }
 
   public void setOrderLockTime(int orderLockTime) {
     this.orderLockTime = orderLockTime;
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
 
   public Integer getLeaveTime() {
     return this.leaveTime;
   }
 
   public void setLeaveTime(Integer leaveTime) {
     this.leaveTime = leaveTime;
   }
 
   public String getIsShowPlaceBillConfirmWindow() {
     return this.isShowPlaceBillConfirmWindow;
   }
 
   public void setIsShowPlaceBillConfirmWindow(String isShowPlaceBillConfirmWindow) {
     this.isShowPlaceBillConfirmWindow = isShowPlaceBillConfirmWindow;
   }
 
   public String getIsFastfoodBillPrint() {
     return this.isFastfoodBillPrint;
   }
 
   public void setIsFastfoodBillPrint(String isFastfoodBillPrint) {
     this.isFastfoodBillPrint = isFastfoodBillPrint;
   }
 
   public String getEmpId() {
     return this.empId;
   }
 
   public void setEmpId(String empId) {
     this.empId = empId;
   }
 
   public String getPrinterId() {
     return this.printerId;
   }
 
   public void setPrinterId(String printerId) {
     this.printerId = printerId;
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
 
   public String getIsShowModuleWaimai() {
     return this.isShowModuleWaimai;
   }
 
   public void setIsShowModuleWaimai(String isShowModuleWaimai) {
     this.isShowModuleWaimai = isShowModuleWaimai;
   }
 }

