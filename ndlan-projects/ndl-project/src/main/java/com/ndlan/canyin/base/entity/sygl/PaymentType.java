 package com.ndlan.canyin.base.entity.sygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.PaymentTypeEnum;
 import java.io.Serializable;
 import java.math.BigDecimal;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_payment_type")
 @JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
 public class PaymentType extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="cpt_id", unique=true, nullable=false, length=36)
   private String cptId;
 
   
   @JsonIgnore
   @Column(name="is_included_sales", length=1)
   private String isIncludedSales;
 
   
   @JsonIgnore
   @Column(name="is_show", length=1)
   private String isShow;
 
   @JsonIgnore
   @Column(name="notes", length=1024)
   private String notes;
 
   @Column(name="payment_name", length=32)
   private String paymentName;
   
   //???????????   1:?? 0??
   @JsonIgnore
   @Column(name="enable_status", length=32)   
   private String enableStatus;
 
   @JsonIgnore
   @Column(name="rest_id", length=36)
   private String restId;
 
   @JsonIgnore
   @Column(name="sysdata_type", length=32)
   private String sysdataType;
 
   @JsonIgnore
   @Column(name="show_seq", length=32)
   private String showSeq;
 
   //支付方式英文代码
   @Column(name="payment_type", length=32)
   private String paymentType;
   
   //支付方式数字代码
   @Column(name="pay_code", length=32)
   private String payCode;
   
   //支付状态
   @Column(name="pay_status", length=32)
   private String payStatus;
 
   @JsonIgnore
   @Transient
   private String subType;
 
   @JsonIgnore
   @Transient
   private BigDecimal money;
 
   @JsonIgnore
   @Transient
   String paymentTypeDesc;
 
   @JsonIgnore
   @OneToMany(mappedBy="paymentType")
   private List<DinerBillPayment> dinerBillPayments;
 
   @JsonIgnore
   @OneToMany(mappedBy="paymentType")
   private List<TableOrder> tableOrders;
 
   @JsonIgnore
   public String getEnableStatusName()
   {
     return EnableStatusEnum.getDesc(this.enableStatus);
   }
 
   public String getShowSeq()
   {
     return this.showSeq;
   }
 
   public void setShowSeq(String showSeq) {
     this.showSeq = showSeq;
   }
 
   public String getRestId()
   {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getCptId() {
     return this.cptId;
   }
 
   public void setCptId(String cptId) {
     this.cptId = cptId;
   }
 
   public String getIsIncludedSales() {
     return this.isIncludedSales;
   }
 
   public void setIsIncludedSales(String isIncludedSales) {
     this.isIncludedSales = isIncludedSales;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public String getPaymentName() {
     return this.paymentName;
   }
 
   public void setPaymentName(String paymentName) {
     this.paymentName = paymentName;
   }
 
   public String getEnableStatus()
   {
     return this.enableStatus;
   }
 
   public void setEnableStatus(String enableStatus) {
     this.enableStatus = enableStatus;
   }
 
   public String getSysdataType() {
     return this.sysdataType;
   }
 
   public void setSysdataType(String sysdataType) {
     this.sysdataType = sysdataType;
   }
 
   public String getPaymentType() {
     return this.paymentType;
   }
 
   public void setPaymentType(String paymentType) {
     this.paymentType = paymentType;
   }
 
   public List<DinerBillPayment> getDinerBillPayments() {
     return this.dinerBillPayments;
   }
 
   public void setDinerBillPayments(List<DinerBillPayment> dinerBillPayments) {
     this.dinerBillPayments = dinerBillPayments;
   }
 
   public List<TableOrder> getTableOrders() {
     return this.tableOrders;
   }
 
   public void setTableOrders(List<TableOrder> tableOrders) {
     this.tableOrders = tableOrders;
   }
 
   public String getSubType()
   {
     if ((PaymentTypeEnum.CASH.getCode().equalsIgnoreCase(this.paymentType)) || 
       (PaymentTypeEnum.CARD.getCode().equalsIgnoreCase(this.paymentType)) || 
       (PaymentTypeEnum.CHEQUE.getCode().equalsIgnoreCase(this.paymentType)) || 
       (PaymentTypeEnum.COUPON.getCode().equalsIgnoreCase(this.paymentType)))
       this.subType = "MONEY";
     else if ((PaymentTypeEnum.HOTEL_CREDIT.getCode().equalsIgnoreCase(this.paymentType)) || 
       (PaymentTypeEnum.TEAM_CREDIT.getCode().equalsIgnoreCase(this.paymentType)) || 
       (PaymentTypeEnum.RESTAURANT_CREDIT.getCode().equalsIgnoreCase(this.paymentType)) || 
       (PaymentTypeEnum.MEMBER_CARD.getCode().equalsIgnoreCase(this.paymentType)))
       this.subType = "CARD";
     else if (PaymentTypeEnum.WEB_SITE_MEMBER.getCode().equalsIgnoreCase(this.paymentType))
       this.subType = "WEBSITE";
     else {
       this.subType = "OTHER";
     }
     return this.subType;
   }
 
   public void setSubType(String subType) {
     this.subType = subType;
   }
 
   public BigDecimal getMoney() {
     return this.money;
   }
 
   public void setMoney(BigDecimal money) {
     this.money = money;
   }
 
   public String getPaymentTypeDesc() {
     this.paymentTypeDesc = PaymentTypeEnum.getDesc(getPaymentType());
     return this.paymentTypeDesc;
   }
 
   public void setPaymentTypeDesc(String paymentTypeDesc) {
     this.paymentTypeDesc = paymentTypeDesc;
   }
 
   public String getIsShow() {
     return this.isShow;
   }
 
   public void setIsShow(String isShow) {
     this.isShow = isShow;
   }

public String getPayCode() {
	return payCode;
}

public void setPayCode(String payCode) {
	this.payCode = payCode;
}

public String getPayStatus() {
	return payStatus;
}

public void setPayStatus(String payStatus) {
	this.payStatus = payStatus;
}
   
   
 }

