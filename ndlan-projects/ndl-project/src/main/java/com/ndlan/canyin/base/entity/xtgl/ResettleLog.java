 package com.ndlan.canyin.base.entity.xtgl;
 
 import com.ndlan.canyin.base.entity.BaseEntity;

 import java.io.Serializable;
 import java.math.BigDecimal;
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
 @Table(name="cm_resettle_log")
 public class ResettleLog extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="log_id", unique=true, nullable=false, length=36)
   private String logId;
 
   @Column(name="bill_id", length=36)
   private String billId;
 
   @Column(name="bill_no", length=32)
   private String billNo;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="log_time")
   private Date logTime;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="pay_time")
   private Date payTime;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="resettle_time")
   private Date resettleTime;
 
   @Column(name="rea_id", length=128)
   private String reaId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="payable_cost")
   private BigDecimal payableCost;
 
   @Column(name="resettle_cost")
   private BigDecimal resettleCost;
 
   @Column(name="resettle_reason")
   private String resettleReason;
 
   @Column(name="notes", length=1024)
   private String notes;
 
   public String getResettleReason()
   {
     return this.resettleReason;
   }
 
   public void setResettleReason(String resettleReason) {
     this.resettleReason = resettleReason;
   }
 
   public String getReplaceNotes()
   {
     return this.notes.replaceAll("<em class=\"red-star\">*</em>", "");
   }
 
   public String getLogId()
   {
     return this.logId;
   }
 
   public void setLogId(String logId) {
     this.logId = logId;
   }
 
   public String getBillId() {
     return this.billId;
   }
 
   public void setBillId(String billId) {
     this.billId = billId;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public Date getLogTime() {
     return this.logTime;
   }
 
   public void setLogTime(Date logTime) {
     this.logTime = logTime;
   }
 
   public Date getPayTime() {
     return this.payTime;
   }
 
   public void setPayTime(Date payTime) {
     this.payTime = payTime;
   }
 
   public Date getResettleTime() {
     return this.resettleTime;
   }
 
   public void setResettleTime(Date resettleTime) {
     this.resettleTime = resettleTime;
   }
 
   public String getReaId() {
     return this.reaId;
   }
 
   public void setReaId(String reaId) {
     this.reaId = reaId;
   }
 
   public BigDecimal getPayableCost() {
     return this.payableCost;
   }
 
   public void setPayableCost(BigDecimal payableCost) {
     this.payableCost = payableCost;
   }
 
   public BigDecimal getResettleCost() {
     return this.resettleCost;
   }
 
   public void setResettleCost(BigDecimal resettleCost) {
     this.resettleCost = resettleCost;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 }

