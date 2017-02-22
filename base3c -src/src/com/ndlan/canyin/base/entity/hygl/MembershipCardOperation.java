 package com.ndlan.canyin.base.entity.hygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.sygl.PaymentType;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
 import com.ndlan.canyin.core.common.CardOperationTypeEnum;
 import java.io.Serializable;
 import java.math.BigDecimal;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.OneToOne;
 import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_membership_card_operation")
 public class MembershipCardOperation extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="cmco_id", unique=true, nullable=false, length=36)
   private String cmcoId;
 
   @Column(name="add_integral")
   private BigDecimal addIntegral;
   
   @JsonIgnore
   @OneToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="bill_id")
   private DinerBill dinerBill;
 
   @Column(name="bill_no", length=32)
   private String billNo;
 
   @Column(name="card_operation_type", length=32)
   private String cardOperationType;
 
   @Column(name="recharge_cash")
   private BigDecimal rechargeCash;
 
   @Column(name="consume_cash")
   private BigDecimal consumeCash;
 
   @Column(name="cash_pledge")
   private BigDecimal cashPledge;
 
   @Column(name="balance")
   private BigDecimal balance;
   
   @Column(name="paidin_cash")
   private BigDecimal paidinCash;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="total_integral")
   private BigDecimal totalIntegral;
 
   @Column(name="is_draw_bill")
   private String isDrawBill;
 
   @Column(name="draw_bill_amount")
   private BigDecimal drawBillAmount;
 
   @Column(name="remarks")
   private String remarks;
 
   @JsonIgnore
   @ManyToOne
   @JoinColumn(name="cpt_id")
   private PaymentType paymentType;

   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="mc_id")
   private MembershipCard membershipCard;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="mb_id")
   private RestMemberInfo restMemberInfo;
   
   @Transient
   private String iosName;
 
   public BigDecimal getCashPledge()
   {
     return this.cashPledge;
   }
 
   public void setCashPledge(BigDecimal cashPledge) {
     this.cashPledge = cashPledge;
   }
 
   public BigDecimal getBalance() {
     return this.balance;
   }
 
   public void setBalance(BigDecimal balance) {
     this.balance = balance;
   }
   
   @JsonIgnore
   public BigDecimal getPreferentialCash()
   {
     return this.rechargeCash.subtract(this.paidinCash);
   }
 
   public RestMemberInfo getRestMemberInfo()
   {
     return this.restMemberInfo;
   }
 
   public void setRestMemberInfo(RestMemberInfo restMemberInfo) {
     this.restMemberInfo = restMemberInfo;
   }
 
   public String getCmcoId()
   {
     return this.cmcoId;
   }
 
   public void setCmcoId(String cmcoId) {
     this.cmcoId = cmcoId;
   }
 
   public BigDecimal getAddIntegral() {
     return this.addIntegral;
   }
 
   public void setAddIntegral(BigDecimal addIntegral) {
     this.addIntegral = addIntegral;
   }
 
   public BigDecimal getPaidinCash() {
     return this.paidinCash;
   }
 
   public void setPaidinCash(BigDecimal paidinCash) {
     this.paidinCash = paidinCash;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public String getCardOperationType() {
     return this.cardOperationType;
   }
 
   public void setCardOperationType(String cardOperationType) {
     this.cardOperationType = cardOperationType;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public BigDecimal getTotalIntegral() {
     return this.totalIntegral;
   }
 
   public void setTotalIntegral(BigDecimal totalIntegral) {
     this.totalIntegral = totalIntegral;
   }
 
   public MembershipCard getMembershipCard() {
     return this.membershipCard;
   }
 
   public void setMembershipCard(MembershipCard membershipCard) {
     this.membershipCard = membershipCard;
   }
 
   public BigDecimal getRechargeCash() {
     return this.rechargeCash;
   }
 
   public void setRechargeCash(BigDecimal rechargeCash) {
     this.rechargeCash = rechargeCash;
   }
 
   public String getIsDrawBill() {
     return this.isDrawBill;
   }
 
   public void setIsDrawBill(String isDrawBill) {
     this.isDrawBill = isDrawBill;
   }
 
   public BigDecimal getDrawBillAmount() {
     return this.drawBillAmount;
   }
 
   public void setDrawBillAmount(BigDecimal drawBillAmount) {
     this.drawBillAmount = drawBillAmount;
   }
 
   public PaymentType getPaymentType() {
     return this.paymentType;
   }
 
   public void setPaymentType(PaymentType paymentType) {
     this.paymentType = paymentType;
   }
 
   public String getRemarks() {
     return this.remarks;
   }
 
   public void setRemarks(String remarks) {
     this.remarks = remarks;
   }
 
   public BigDecimal getConsumeCash() {
     return this.consumeCash;
   }
 
   public void setConsumeCash(BigDecimal consumeCash) {
     this.consumeCash = consumeCash;
   }
 
   public DinerBill getDinerBill() {
     return this.dinerBill;
   }
 
   public void setDinerBill(DinerBill dinerBill) {
     this.dinerBill = dinerBill;
   }
 
   public String getCardOperationTypeDesc()
   {
     return CardOperationTypeEnum.getDesc(this.cardOperationType);
   }
	
	public String getIosName() {
		return iosName;
	}
	
	public void setIosName(String iosName) {
		this.iosName = iosName;
	}
   
   
   
 }

