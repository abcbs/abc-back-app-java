 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;

 import java.io.Serializable;
 import java.math.BigDecimal;
 import java.util.Date;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_diner_bill_credit_payment")
 public class DinerBillCreditPayment extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="cdbcp_id", unique=true, nullable=false, length=36)
   private String cdbcpId;
 
   @Column(name="bill_id", length=36)
   private String billId;
 
   @Column(name="bill_no", length=32)
   private String billNo;
 
   @Column(name="money")
   private BigDecimal money;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="pay_time")
   private Date payTime;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="dbp_id")
   private DinerBillPayment dinerBillPayment;
 
   public String getCdbcpId()
   {
     return this.cdbcpId;
   }
 
   public void setCdbcpId(String cdbcpId) {
     this.cdbcpId = cdbcpId;
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
 
   public BigDecimal getMoney() {
     return this.money;
   }
 
   public void setMoney(BigDecimal money) {
     this.money = money;
   }
 
   public Date getPayTime() {
     return this.payTime;
   }
 
   public void setPayTime(Date payTime) {
     this.payTime = payTime;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public DinerBillPayment getDinerBillPayment() {
     return this.dinerBillPayment;
   }
 
   public void setDinerBillPayment(DinerBillPayment dinerBillPayment) {
     this.dinerBillPayment = dinerBillPayment;
   }
 }

