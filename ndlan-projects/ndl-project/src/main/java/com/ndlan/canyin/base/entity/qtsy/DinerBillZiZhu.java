 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.DinerBillZiZhuDishe;
 import com.ndlan.canyin.core.common.BillStatusEnum;
 import com.ndlan.canyin.core.common.BillTypeEnum;
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
 @Table(name="cm_diner_bill_zizhu")
 @JsonIgnoreProperties(ignoreUnknown=true)
 public class DinerBillZiZhu extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="bill_id", unique=true, nullable=false, length=36)
   private String billId;
 
   @Column(name="cbs_id", length=36)
   private String cbsId;
 
   @Column(name="bill_no", length=32)
   private String billNo;
 
   @Column(name="bill_status", length=32)
   private String billStatus;
 
   @Column(name="bill_type", length=32)
   private String billType;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="bill_time")
   private Date billTime;
 
   @Column(name="is_valid", length=1)
   private String isValid;
 
   @Column(name="ori_cost")
   private BigDecimal oriCost;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="pay_time")
   private Date payTime;
 
   @Column(name="people_num")
   private Integer peopleNum;
 
   @Column(name="print_times")
   private Integer printTimes;
 
   @Column(name="real_cost")
   private BigDecimal realCost;
 
   @Column(name="pay_desc")
   private String payDesc;
 
   @Column(name="rest_id")
   private String restId;
 
   @Column(name="save_cost")
   private BigDecimal saveCost;
 
   @Column(name="mb_id")
   private String mbId;
 
   @Column(name="mc_id")
   private String mcId;
 
   @Column(name="bank_card_no")
   private String bankCardNo;
 
   @OneToMany(mappedBy="dinerBillZiZhu")
   private List<DinerBillZiZhuDishe> dinerBillZiZhuDishe;
 
   @Transient
   private String dinerBillZiZhuDisheStr;
 
   public String getBillStatusDesc()
   {
     return BillStatusEnum.getDesc(this.billStatus);
   }
 
   public String getBillTypeDesc()
   {
     return BillTypeEnum.getDesc(this.billType);
   }
 
   public String getBillId()
   {
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
 
   public String getBillStatus() {
     return this.billStatus;
   }
 
   public void setBillStatus(String billStatus) {
     this.billStatus = billStatus;
   }
 
   public String getBillType() {
     return this.billType;
   }
 
   public void setBillType(String billType) {
     this.billType = billType;
   }
 
   public Date getBillTime() {
     return this.billTime;
   }
 
   public void setBillTime(Date billTime) {
     this.billTime = billTime;
   }
 
   public String getIsValid() {
     return this.isValid;
   }
 
   public void setIsValid(String isValid) {
     this.isValid = isValid;
   }
 
   public BigDecimal getOriCost() {
     return this.oriCost;
   }
 
   public void setOriCost(BigDecimal oriCost) {
     this.oriCost = oriCost;
   }
 
   public Date getPayTime() {
     return this.payTime;
   }
 
   public void setPayTime(Date payTime) {
     this.payTime = payTime;
   }
 
   public Integer getPeopleNum() {
     return this.peopleNum;
   }
 
   public void setPeopleNum(Integer peopleNum) {
     this.peopleNum = peopleNum;
   }
 
   public BigDecimal getRealCost() {
     return this.realCost;
   }
 
   public void setRealCost(BigDecimal realCost) {
     this.realCost = realCost;
   }
 
   public String getPayDesc() {
     return this.payDesc;
   }
 
   public void setPayDesc(String payDesc) {
     this.payDesc = payDesc;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public BigDecimal getSaveCost() {
     return this.saveCost;
   }
 
   public void setSaveCost(BigDecimal saveCost) {
     this.saveCost = saveCost;
   }
 
   public String getMbId() {
     return this.mbId;
   }
 
   public void setMbId(String mbId) {
     this.mbId = mbId;
   }
 
   public String getMcId() {
     return this.mcId;
   }
 
   public void setMcId(String mcId) {
     this.mcId = mcId;
   }
 
   public String getBankCardNo() {
     return this.bankCardNo;
   }
 
   public void setBankCardNo(String bankCardNo) {
     this.bankCardNo = bankCardNo;
   }
 
   public String getCbsId() {
     return this.cbsId;
   }
 
   public void setCbsId(String cbsId) {
     this.cbsId = cbsId;
   }
 
   public List<DinerBillZiZhuDishe> getDinerBillZiZhuDishe() {
     return this.dinerBillZiZhuDishe;
   }
 
   public void setDinerBillZiZhuDishe(List<DinerBillZiZhuDishe> dinerBillZiZhuDishe) {
     this.dinerBillZiZhuDishe = dinerBillZiZhuDishe;
   }
 
   public String getDinerBillZiZhuDisheStr() {
     return this.dinerBillZiZhuDisheStr;
   }
 
   public void setDinerBillZiZhuDisheStr(String dinerBillZiZhuDisheStr) {
     this.dinerBillZiZhuDisheStr = dinerBillZiZhuDisheStr;
   }
 
   public Integer getPrintTimes() {
     return this.printTimes;
   }
 
   public void setPrintTimes(Integer printTimes) {
     this.printTimes = printTimes;
   }
 }

