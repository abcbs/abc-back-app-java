 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.core.common.BillFromEnum;
 import java.io.Serializable;
 import java.math.BigDecimal;
 import java.util.Date;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.OneToOne;
 import javax.persistence.Table;
 import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
 
 @Entity
 @Table(name="cm_takeout")
 public class Takeout extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="t_id", unique=true, nullable=false, length=36)
   private String tId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @JsonIgnore
   @OneToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="bill_id")
   private DinerBill dinerBill;
 
   @Column(name="online_takeout_id", length=36)
   private String onlineTakeoutId;
 
   @Column(name="ccd_id", length=36)
   private String ccdId;
 
   @Column(name="online_takeout_no", length=64)
   private String onlineTakeoutNo;
 
   @Column(name="online_payment_type", length=36)
   private String onlinePaymentType;
 
   @Column(name="online_user_mobile", length=32)
   private String onlineUserMobile;
 
   @Column(name="bill_from", length=32)
   private String billFrom;
 
   @Column(name="mc_id", length=36)
   private String mcId;
 
   @Column(name="bill_no", length=64)
   private String billNo;
 
   @Column(name="send_address", length=512)
   private String sendAddress;
 
   @Column(name="contact_name", length=128)
   private String contactName;
 
   @Column(name="telephone", length=64)
   private String telephone;
 
   @Column(name="mobile", length=11)
   private String mobile;
 
   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
   @Column(name="send_time")
   private Date sendTime;
 
   @Column(name="deliver_cost")
   private BigDecimal deliverCost;
 
   @Column(name="total_cost")
   private BigDecimal totalCost;
 
   @Column(name="sender_by", length=36)
   private String senderBy;
 
   @Column(name="sender_name", length=64)
   private String senderName;
 
   @Column(name="send_at_once", length=1)
   private String sendAtOnce;
 
   @Column(name="is_canceled", length=1)
   private String isCanceled;
 
   @Column(name="invoice_title", length=128)
   private String invoiceTitle;
 
   @Column(name="custom_note", length=2048)
   private String customNote;
 
   @Column(name="notes", length=1024)
   private String notes;
   //配送员
   @Column(name="takes_id", length=36)
   private String takeId;
   
   //编号
   @Column(name="takes_number", length=36)
   private String takesNumber;
 


public String getTakesNumber() {
	return takesNumber;
}

public void setTakesNumber(String takesNumber) {
	this.takesNumber = takesNumber;
}

public String getTakeId() {
	return takeId;
}

public void setTakeId(String takeId) {
	this.takeId = takeId;
}

public String gettId()
   {
     return this.tId;
   }
 
   public void settId(String tId) {
     this.tId = tId;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public DinerBill getDinerBill() {
     return this.dinerBill;
   }
 
   public void setDinerBill(DinerBill dinerBill) {
     this.dinerBill = dinerBill;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
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
 
   public Date getSendTime() {
     return this.sendTime;
   }
 
   public void setSendTime(Date sendTime) {
     this.sendTime = sendTime;
   }
 
   public BigDecimal getDeliverCost() {
     return this.deliverCost;
   }
 
   public void setDeliverCost(BigDecimal deliverCost) {
     this.deliverCost = deliverCost;
   }
 
   public String getSenderName() {
     return this.senderName;
   }
 
   public void setSenderName(String senderName) {
     this.senderName = senderName;
   }
 
   public String getCustomNote() {
     return this.customNote;
   }
 
   public void setCustomNote(String customNote) {
     this.customNote = customNote;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public BigDecimal getTotalCost() {
     return this.totalCost;
   }
 
   public void setTotalCost(BigDecimal totalCost) {
     this.totalCost = totalCost;
   }
 
   public String getMcId() {
     return this.mcId;
   }
 
   public void setMcId(String mcId) {
     this.mcId = mcId;
   }
 
   public String getInvoiceTitle() {
     return this.invoiceTitle;
   }
 
   public void setInvoiceTitle(String invoiceTitle) {
     this.invoiceTitle = invoiceTitle;
   }
 
   public String getSenderBy() {
     return this.senderBy;
   }
 
   public void setSenderBy(String senderBy) {
     this.senderBy = senderBy;
   }
 
   public String getSendAtOnce() {
     return this.sendAtOnce;
   }
 
   public void setSendAtOnce(String sendAtOnce) {
     this.sendAtOnce = sendAtOnce;
   }
 
   public String getOnlineTakeoutId() {
     return this.onlineTakeoutId;
   }
 
   public void setOnlineTakeoutId(String onlineTakeoutId) {
     this.onlineTakeoutId = onlineTakeoutId;
   }
 
   public String getOnlineTakeoutNo() {
     return this.onlineTakeoutNo;
   }
 
   public void setOnlineTakeoutNo(String onlineTakeoutNo) {
     this.onlineTakeoutNo = onlineTakeoutNo;
   }
 
   public String getIsCanceled() {
     return this.isCanceled;
   }
 
   public void setIsCanceled(String isCanceled) {
     this.isCanceled = isCanceled;
   }
 
   public String getOnlinePaymentType() {
     return this.onlinePaymentType;
   }
 
   public void setOnlinePaymentType(String onlinePaymentType) {
     this.onlinePaymentType = onlinePaymentType;
   }
 
   public String getCcdId() {
     return this.ccdId;
   }
 
   public void setCcdId(String ccdId) {
     this.ccdId = ccdId;
   }
 
   public String getBillFrom() {
     return this.billFrom;
   }
 
   public void setBillFrom(String billFrom) {
     this.billFrom = billFrom;
   }
 
   public String getBillFromDesc() {
     return BillFromEnum.getDesc(this.billFrom);
   }
 
   public String getOnlineUserMobile() {
     return this.onlineUserMobile;
   }
 
   public void setOnlineUserMobile(String onlineUserMobile) {
     this.onlineUserMobile = onlineUserMobile;
   }
 }

