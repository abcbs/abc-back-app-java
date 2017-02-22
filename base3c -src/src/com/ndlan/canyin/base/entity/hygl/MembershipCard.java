 package com.ndlan.canyin.base.entity.hygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.hygl.MembershipCardClass;
import com.ndlan.canyin.base.entity.hygl.MembershipCardOperation;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
 import com.ndlan.canyin.core.common.MemberCardStatusEnum;
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
 import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_membership_card")
 public class MembershipCard extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="mc_id", unique=true, nullable=false, length=36)
   private String mcId;
 
   @Column(name="balance")
   private BigDecimal balance;
 
   @Column(name="card_no", length=32)
   private String cardNo;
 
   @Column(name="card_password", length=32)
   private String cardPassword;
 
   @Column(name="card_status", length=32)
   private String cardStatus;
 
   @Transient
   private String cardStatusName;
 
   @Column(name="belong_org", length=32)
   private String belongOrg;
 
   @Transient
   private Boolean isEmptyPassWord;
 
   @Column(name="cash_pledge")
   private BigDecimal cashPledge;
 
   @Column(name="member_integral")
   private BigDecimal memberIntegral;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="mcclass_id")
   private MembershipCardClass membershipCardClass;
 
   @Transient
   private String membershipCardClassName;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="mb_id")
   private RestMemberInfo restMemberInfo;
 
   @JsonIgnore
   @OneToMany(mappedBy="membershipCard")
   private List<MembershipCardOperation> membershipCardOperations;
 
   @JsonIgnore
   @OneToMany(mappedBy="membershipCard")
   private List<DinerBill> DinerBills;
 
   @Transient
   private BigDecimal rechargeCash;
 
   @Transient
   private BigDecimal paidinCash;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="card_issue_time")
   private Date cardIssueTime;
 
   @Column(name="active_status", length=36)
   private String activeStatus;
 
   public String getCardStatusName()
   {
     return MemberCardStatusEnum.getDesc(getCardStatus());
   }
 
   public void setCardStatusName(String cardStatusName) {
     this.cardStatusName = cardStatusName;
   }
 
   public String getMcId()
   {
     return this.mcId;
   }
 
   public void setMcId(String mcId) {
     this.mcId = mcId;
   }
 
   public String getCardNo() {
     return this.cardNo;
   }
 
   public void setCardNo(String cardNo) {
     this.cardNo = cardNo;
   }
 
   public String getCardPassword() {
     return this.cardPassword;
   }
 
   public void setCardPassword(String cardPassword) {
     this.cardPassword = cardPassword;
   }
 
   public String getCardStatus() {
     return this.cardStatus;
   }
 
   public void setCardStatus(String cardStatus) {
     this.cardStatus = cardStatus;
   }
 
   public BigDecimal getBalance() {
     return this.balance;
   }
 
   public void setBalance(BigDecimal balance) {
     this.balance = balance;
   }
 
   public BigDecimal getCashPledge() {
     return this.cashPledge;
   }
 
   public void setCashPledge(BigDecimal cashPledge) {
     this.cashPledge = cashPledge;
   }
 
   public BigDecimal getMemberIntegral() {
     return this.memberIntegral;
   }
 
   public void setMemberIntegral(BigDecimal memberIntegral) {
     this.memberIntegral = memberIntegral;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public MembershipCardClass getMembershipCardClass() {
     return this.membershipCardClass;
   }
 
   public void setMembershipCardClass(MembershipCardClass membershipCardClass) {
     this.membershipCardClass = membershipCardClass;
   }
 
   public RestMemberInfo getRestMemberInfo() {
     return this.restMemberInfo;
   }
 
   public void setRestMemberInfo(RestMemberInfo restMemberInfo) {
     this.restMemberInfo = restMemberInfo;
   }
 
   public List<MembershipCardOperation> getMembershipCardOperations() {
     return this.membershipCardOperations;
   }
 
   public void setMembershipCardOperations(List<MembershipCardOperation> membershipCardOperations)
   {
     this.membershipCardOperations = membershipCardOperations;
   }
 
   public List<DinerBill> getDinerBills() {
     return this.DinerBills;
   }
 
   public void setDinerBills(List<DinerBill> dinerBills) {
     this.DinerBills = dinerBills;
   }
 
   public String getMembershipCardClassName() {
     return this.membershipCardClassName;
   }
 
   public void setMembershipCardClassName(String membershipCardClassName) {
     this.membershipCardClassName = membershipCardClassName;
   }
 
   public BigDecimal getRechargeCash() {
     return this.rechargeCash;
   }
 
   public void setRechargeCash(BigDecimal rechargeCash) {
     this.rechargeCash = rechargeCash;
   }
 
   public BigDecimal getPaidinCash() {
     return this.paidinCash;
   }
 
   public void setPaidinCash(BigDecimal paidinCash) {
     this.paidinCash = paidinCash;
   }
 
   public Boolean getIsEmptyPassWord() {
     if (StringUtils.isEmpty(getCardPassword()))
       this.isEmptyPassWord = Boolean.valueOf(true);
     else {
       this.isEmptyPassWord = Boolean.valueOf(false);
     }
     return this.isEmptyPassWord;
   }
 
   public void setIsEmptyPassWord(Boolean isEmptyPassWord) {
     this.isEmptyPassWord = isEmptyPassWord;
   }
 
   public Date getCardIssueTime() {
     return this.cardIssueTime;
   }
 
   public void setCardIssueTime(Date cardIssueTime) {
     this.cardIssueTime = cardIssueTime;
   }
 
   public String getActiveStatus() {
     return this.activeStatus;
   }
 
   public void setActiveStatus(String activeStatus) {
     this.activeStatus = activeStatus;
   }
 
   public String getBelongOrg() {
     return this.belongOrg;
   }
 
   public void setBelongOrg(String belongOrg) {
     this.belongOrg = belongOrg;
   }
 }

