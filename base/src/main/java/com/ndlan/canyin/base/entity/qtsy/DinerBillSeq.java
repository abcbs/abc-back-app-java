 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;

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
 @Table(name="cm_diner_bill_seq")
 @JsonIgnoreProperties(ignoreUnknown=true)
 public class DinerBillSeq extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="seq_id", unique=true, nullable=false, length=36)
   private String seqId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="bill_seq_type", length=32)
   private String billSeqType;
 
   @Column(name="bill_seq")
   private int billSeq;
 
   @Column(name="bill_no", length=32)
   private String billNo;
 
   @Temporal(TemporalType.DATE)
   @Column(name="bill_date")
   private Date billDate;
 
   public String getSeqId()
   {
     return this.seqId;
   }
 
   public void setSeqId(String seqId) {
     this.seqId = seqId;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public int getBillSeq() {
     return this.billSeq;
   }
 
   public void setBillSeq(int billSeq) {
     this.billSeq = billSeq;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public String getBillSeqType() {
     return this.billSeqType;
   }
 
   public void setBillSeqType(String billSeqType) {
     this.billSeqType = billSeqType;
   }
 
   public Date getBillDate() {
     return this.billDate;
   }
 
   public void setBillDate(Date billDate) {
     this.billDate = billDate;
   }
 }

