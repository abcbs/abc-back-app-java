 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;

 import java.io.Serializable;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_bill_combine")
 public class BillCombine extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="cbc_id", unique=true, nullable=false, length=36)
   private String cbcId;
 
   @Column(name="bill_no", length=32)
   private String billNo;
 
   @Column(name="cbill_id", length=36)
   private String cbillId;
 
   @Column(name="cbill_no", length=32)
   private String cbillNo;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="bill_id")
   private DinerBill dinerBill;
 
   public String getCbcId()
   {
     return this.cbcId;
   }
 
   public void setCbcId(String cbcId) {
     this.cbcId = cbcId;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public String getCbillId() {
     return this.cbillId;
   }
 
   public void setCbillId(String cbillId) {
     this.cbillId = cbillId;
   }
 
   public String getCbillNo() {
     return this.cbillNo;
   }
 
   public void setCbillNo(String cbillNo) {
     this.cbillNo = cbillNo;
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
 }

