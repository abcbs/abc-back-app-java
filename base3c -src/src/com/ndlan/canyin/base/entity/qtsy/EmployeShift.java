 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.EmployeShiftIncome;

 import java.io.Serializable;
 import java.math.BigDecimal;
 import java.util.Date;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_employe_shift")
 public class EmployeShift extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="ces_id", unique=true, nullable=false, length=36)
   private String cesId;
 
   @Column(name="current_cash")
   private BigDecimal currentCash;
 
   @Column(name="current_handoff_cash")
   private BigDecimal currentHandoffCash;
 
   @Column(name="current_handon_cash")
   private BigDecimal currentHandonCash;
 
   @Column(name="last_balance_cash")
   private BigDecimal lastBalanceCash;
 
   @Column(name="current_cash_balance")
   private BigDecimal currentCashBalance;
 
   @Column(name="is_print", length=1)
   private String isPrint;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @OneToMany(mappedBy="employeShift")
   private List<EmployeShiftIncome> cmEmploeeShiftIncomes;
 
   @Transient
   private Date previousTime;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="last_shift_time")
   private Date lastShiftTime;
 
   @Column(name="people_num")
   private Integer peopleNum;
 
   @Column(name="service_money_sum")
   private BigDecimal serviceMoneySum;
 
   @Column(name="tuicai_money_sum")
   private BigDecimal tuicaiMoneySum;
 
   @Column(name="zengcai_money_sum")
   private BigDecimal zengcaiMoneySum;
 
   @Column(name="shift_payment_detail", length=5000)
   private String shiftPaymentDetail;
 
   @Column(name="odd_change_sum")
   private BigDecimal oddChangeSum;
 
   @Column(name="moling_sum")
   private BigDecimal molingSum;
 
   @Column(name="discount_money_sum")
   private BigDecimal discountMoneySum;
 
   @Column(name="force_pay_sum")
   private BigDecimal forcePaySum;
 
   @Column(name="card_payment_detail", length=5000)
   private String cardPaymentDetail;
 
   @Column(name="prepay_payment_detail", length=5000)
   private String prepayPaymentDetail;
 
   @Column(name="un_paybillcost_sum")
   private BigDecimal unPaybillcostSum;
 
   @Transient
   private BigDecimal realCost;
 
   public Date getPreviousTime()
   {
     return this.previousTime;
   }
 
   public void setPreviousTime(Date previousTime) {
     this.previousTime = previousTime;
   }
 
   public String getCesId()
   {
     return this.cesId;
   }
 
   public void setCesId(String cesId) {
     this.cesId = cesId;
   }
 
   public BigDecimal getCurrentCash() {
     return this.currentCash;
   }
 
   public void setCurrentCash(BigDecimal currentCash) {
     this.currentCash = currentCash;
   }
 
   public BigDecimal getCurrentHandoffCash() {
     return this.currentHandoffCash;
   }
 
   public void setCurrentHandoffCash(BigDecimal currentHandoffCash) {
     this.currentHandoffCash = currentHandoffCash;
   }
 
   public BigDecimal getCurrentHandonCash() {
     return this.currentHandonCash;
   }
 
   public void setCurrentHandonCash(BigDecimal currentHandonCash) {
     this.currentHandonCash = currentHandonCash;
   }
 
   public String getIsPrint() {
     return this.isPrint;
   }
 
   public void setIsPrint(String isPrint) {
     this.isPrint = isPrint;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public List<EmployeShiftIncome> getCmEmploeeShiftIncomes() {
     return this.cmEmploeeShiftIncomes;
   }
 
   public void setCmEmploeeShiftIncomes(List<EmployeShiftIncome> cmEmploeeShiftIncomes)
   {
     this.cmEmploeeShiftIncomes = cmEmploeeShiftIncomes;
   }
 
   public BigDecimal getLastBalanceCash() {
     return this.lastBalanceCash;
   }
 
   public void setLastBalanceCash(BigDecimal lastBalanceCash) {
     this.lastBalanceCash = lastBalanceCash;
   }
 
   public Integer getPeopleNum() {
     return this.peopleNum;
   }
 
   public void setPeopleNum(Integer peopleNum) {
     this.peopleNum = peopleNum;
   }
 
   public BigDecimal getServiceMoneySum() {
     return this.serviceMoneySum;
   }
 
   public void setServiceMoneySum(BigDecimal serviceMoneySum) {
     this.serviceMoneySum = serviceMoneySum;
   }
 
   public BigDecimal getTuicaiMoneySum() {
     return this.tuicaiMoneySum;
   }
 
   public void setTuicaiMoneySum(BigDecimal tuicaiMoneySum) {
     this.tuicaiMoneySum = tuicaiMoneySum;
   }
 
   public BigDecimal getZengcaiMoneySum() {
     return this.zengcaiMoneySum;
   }
 
   public void setZengcaiMoneySum(BigDecimal zengcaiMoneySum) {
     this.zengcaiMoneySum = zengcaiMoneySum;
   }
 
   public String getShiftPaymentDetail() {
     return this.shiftPaymentDetail;
   }
 
   public void setShiftPaymentDetail(String shiftPaymentDetail) {
     this.shiftPaymentDetail = shiftPaymentDetail;
   }
 
   public BigDecimal getOddChangeSum() {
     return this.oddChangeSum;
   }
 
   public void setOddChangeSum(BigDecimal oddChangeSum) {
     this.oddChangeSum = oddChangeSum;
   }
 
   public BigDecimal getMolingSum() {
     return this.molingSum;
   }
 
   public void setMolingSum(BigDecimal molingSum) {
     this.molingSum = molingSum;
   }
 
   public BigDecimal getDiscountMoneySum() {
     return this.discountMoneySum;
   }
 
   public void setDiscountMoneySum(BigDecimal discountMoneySum) {
     this.discountMoneySum = discountMoneySum;
   }
 
   public BigDecimal getForcePaySum() {
     return this.forcePaySum;
   }
 
   public void setForcePaySum(BigDecimal forcePaySum) {
     this.forcePaySum = forcePaySum;
   }
 
   public String getCardPaymentDetail() {
     return this.cardPaymentDetail;
   }
 
   public void setCardPaymentDetail(String cardPaymentDetail) {
     this.cardPaymentDetail = cardPaymentDetail;
   }
 
   public String getPrepayPaymentDetail() {
     return this.prepayPaymentDetail;
   }
 
   public void setPrepayPaymentDetail(String prepayPaymentDetail) {
     this.prepayPaymentDetail = prepayPaymentDetail;
   }
 
   public BigDecimal getUnPaybillcostSum() {
     return this.unPaybillcostSum;
   }
 
   public void setUnPaybillcostSum(BigDecimal unPaybillcostSum) {
     this.unPaybillcostSum = unPaybillcostSum;
   }
 
   public Date getLastShiftTime() {
     return this.lastShiftTime;
   }
 
   public void setLastShiftTime(Date lastShiftTime) {
     this.lastShiftTime = lastShiftTime;
   }
 
   public BigDecimal getCurrentCashBalance() {
     return this.currentCashBalance;
   }
 
   public void setCurrentCashBalance(BigDecimal currentCashBalance) {
     this.currentCashBalance = currentCashBalance;
   }
 
   public BigDecimal getRealCost() {
     return this.realCost;
   }
 
   public void setRealCost(BigDecimal realCost) {
     this.realCost = realCost;
   }
 }

