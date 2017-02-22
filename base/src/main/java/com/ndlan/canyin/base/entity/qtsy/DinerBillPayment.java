 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.sygl.PaymentType;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillCreditPayment;
 import com.ndlan.canyin.core.common.CreditStatusEnum;
 import java.io.Serializable;
 import java.math.BigDecimal;
 import java.util.Date;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_diner_bill_payment")
 public class DinerBillPayment extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="dbp_id", unique=true, nullable=false, length=36)
   private String dbpId;
 
   @Column(name="bill_no", length=32)
   private String billNo;
   
   @Column(name="bill_status", length=32)
   private int billStatus=0; //����״̬ 0��ʾ������ 1��ʾ�Ѹ���
 
   @Column(name="credit_payed_money")
   private BigDecimal creditPayedMoney;
 
   @Column(name="credit_status", length=32)
   private String creditStatus;
 
   @Transient
   private String creditStatusDesc;
 
   @Column(name="money")
   private BigDecimal money;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="pay_time")
   private Date payTime;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @JsonIgnore
   @OneToMany(mappedBy="dinerBillPayment")
   private List<DinerBillCreditPayment> dinerBillCreditPayments;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="bill_id")
   private DinerBill dinerBill;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="cpt_id")
   private PaymentType paymentType;
 
   public String getDbpId()
   {
     return this.dbpId;
   }
 
   public void setDbpId(String dbpId) {
     this.dbpId = dbpId;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public BigDecimal getCreditPayedMoney() {
     return this.creditPayedMoney;
   }
 
   public void setCreditPayedMoney(BigDecimal creditPayedMoney) {
     this.creditPayedMoney = creditPayedMoney;
   }
 
   public String getCreditStatus() {
     return this.creditStatus;
   }
 
   public void setCreditStatus(String creditStatus) {
     this.creditStatus = creditStatus;
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
 
   public List<DinerBillCreditPayment> getDinerBillCreditPayments() {
     return this.dinerBillCreditPayments;
   }
 
   public void setDinerBillCreditPayments(List<DinerBillCreditPayment> dinerBillCreditPayments)
   {
     this.dinerBillCreditPayments = dinerBillCreditPayments;
   }
 
   public DinerBill getDinerBill() {
     return this.dinerBill;
   }
 
   public void setDinerBill(DinerBill dinerBill) {
     this.dinerBill = dinerBill;
   }
 
   public PaymentType getPaymentType() {
     return this.paymentType;
   }
 
   public void setPaymentType(PaymentType paymentType) {
     this.paymentType = paymentType;
   }
 
   public String getCreditStatusDesc() {
     this.creditStatusDesc = CreditStatusEnum.getDesc(this.creditStatus);
     return this.creditStatusDesc;
   }
 
   public void setCreditStatusDesc(String creditStatusDesc) {
     this.creditStatusDesc = creditStatusDesc;
   }

public int getBillStatus() {
	return billStatus;
}

public void setBillStatus(int billStatus) {
	this.billStatus = billStatus;
}

   
   
 }

