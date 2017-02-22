 package com.ndlan.canyin.base.entity.xtgl;
 
 import com.ndlan.canyin.base.entity.BaseEntity;
 import com.ndlan.canyin.core.common.BillOpTypeEnum;
 import java.io.Serializable;
 import java.util.Date;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_busi_log")
 public class BusiLog extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="log_id", unique=true, nullable=false, length=36)
   private String logId;
 
   @Column(name="bill_id")
   private String billId;
 
   @Column(name="bill_no", length=32)
   private String billNo;
 
   @Column(name="order_id", length=36)
   private String orderId;
 
   @Column(name="order_no", length=64)
   private String orderNo;
 
   @Column(name="buss_log_type", length=32)
   private String bussLogType;
 
   @Column(name="op_type", length=32)
   private String opType;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="tab_no", length=64)
   private String tabNo;
 
   @Column(name="tab_id")
   private String tabId;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="pay_time")
   private Date payTime;
 
   @Column(name="notes", length=1024)
   private String notes;
 
   public String getOpTypeName()
   {
     return BillOpTypeEnum.getDesc(getOpType());
   }
 
   public String getLogId()
   {
     return this.logId;
   }
 
   public void setLogId(String logId) {
     this.logId = logId;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public String getBussLogType() {
     return this.bussLogType;
   }
 
   public void setBussLogType(String bussLogType) {
     this.bussLogType = bussLogType;
   }
 
   public String getOpType() {
     return this.opType;
   }
 
   public void setOpType(String opType) {
     this.opType = opType;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getTabNo() {
     return this.tabNo;
   }
 
   public void setTabNo(String tabNo) {
     this.tabNo = tabNo;
   }
 
   public String getTabId() {
     return this.tabId;
   }
 
   public void setTabId(String tabId) {
     this.tabId = tabId;
   }
 
   public String getBillId() {
     return this.billId;
   }
 
   public void setBillId(String billId) {
     this.billId = billId;
   }
 
   public String getOrderId() {
     return this.orderId;
   }
 
   public void setOrderId(String orderId) {
     this.orderId = orderId;
   }
 
   public String getOrderNo() {
     return this.orderNo;
   }
 
   public void setOrderNo(String orderNo) {
     this.orderNo = orderNo;
   }
 
   public Date getPayTime() {
     return this.payTime;
   }
 
   public void setPayTime(Date payTime) {
     this.payTime = payTime;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 }

