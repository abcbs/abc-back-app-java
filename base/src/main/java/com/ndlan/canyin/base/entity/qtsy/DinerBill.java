 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 import com.google.common.collect.ImmutableList;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.TableBillRelation;
import com.ndlan.canyin.base.entity.hygl.MembershipCard;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
import com.ndlan.canyin.base.entity.sygl.CashDiscount;
import com.ndlan.canyin.base.entity.sygl.CashSetting;
import com.ndlan.canyin.base.entity.qtsy.BillCombine;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishesSet;
import com.ndlan.canyin.base.entity.qtsy.DinerBillLog;
import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;
import com.ndlan.canyin.base.entity.qtsy.Takeout;
 import com.ndlan.canyin.core.common.BillFromEnum;
 import com.ndlan.canyin.core.common.BillStatusEnum;
 import com.ndlan.canyin.core.common.BillTypeEnum;
 import com.ndlan.canyin.core.common.DishesStatusEnum;
 import com.ndlan.canyin.core.common.MolingModeEnum;
 import com.ndlan.canyin.core.common.PungentLevelEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.utils.Collections3;
 import java.io.Serializable;
 import java.math.BigDecimal;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import java.util.Set;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.OneToMany;
 import javax.persistence.OneToOne;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
 import javax.persistence.Transient;
 import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @javax.persistence.Table(name="cm_diner_bill")
 @JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
 public class DinerBill extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="bill_id", unique=true, nullable=false, length=36)
   private String billId;
 
   @Column(name="before_pay")
   private BigDecimal beforePay;
 
   @Column(name="bill_no", length=32)
   private String billNo;
 
   @Column(name="bill_status", length=32)
   private String billStatus;
 
   @Column(name="bill_type", length=32)
   private String billType;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="bill_time")
   private Date billTime;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="resettle_time")
   private Date resettleTime;
 
   @Column(name="bill_from", length=32)
   private String billFrom;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="ccd_id")
   private CashDiscount cashDiscount;
 
   @Column(name="discount_name", length=128)
   private String discountName;
 
   @Column(name="dishes_type_discount_desc", length=255)
   private String dishesTypeDiscountDesc;
 
   @Column(name="moling_mode", length=32)
   private String molingMode;
 
   @Transient
   private String molingModeDesc;
   
   @Transient
   private String createEmployeeName;   //ios所需开单人名称
   
   @Transient
   private String cashierEmployeeName;   //ios所需收银员名称
   
   @Transient
   private String tableId;   //ios餐台id
   
   @Transient
   private String tableName;   //ios餐台名称
   
   
   @Column(name="discount")
   private Integer discount;
 
   @Column(name="is_custom_discount", length=1)
   private String isCustomDiscount;
 
   @Column(name="is_valid", length=1)
   private String isValid;
 
   @Column(name="is_shift", length=1)
   private String isShift;
 
   @Column(name="is_force_pay", length=1)
   private String isForcePay = TrueFalseEnum.FALSE.getCode();
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="force_pay_operator")
   private Employee forcePayOperator;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="force_pay_time")
   private Date forcePayTime;
 
   @Column(name="ces_id", length=36)
   private String cesId;
 
   @Column(name="online_bill_id", length=36)
   private String onlineBillId;
 
   @Column(name="online_bill_no", length=36)
   private String onlineBillNo;
 
   @Column(name="draw_bill_amount")
   private BigDecimal drawBillAmount;
 
   @Column(name="is_draw_bill", length=1)
   private String isDrawBill;
 
   @Column(name="company", length=256)
   private String company;
 
   @Column(name="is_in_kitchen", length=1)
   private String isInKitchen;
 
   @Column(name="is_moling", length=1)
   private String isMoling;
 
   @Column(name="moling_mode_cost")
   private BigDecimal molingModeCost;
 
   @Column(name="is_round", length=1)
   private String isRound;
 
   @Column(name="is_wait_call", length=1)
   private String isWaitCall;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="last_urge_time")
   private Date lastUrgeTime;
 
   @Column(name="losse_gain")
   private BigDecimal losseGain;
 
   @Column(name="notes", length=1024)
   private String notes;
 
   @Column(name="card_notes", length=1024)
   private String cardNotes;
 
   @Transient
   private Boolean isAddDishes = Boolean.FALSE;
 
   @Column(name="bill_notes", length=1024)
   private String billNotes;
 
   @Column(name="rea_id", length=36)
   private String reaId;
 
   @Column(name="cancel_bill_reason", length=128)
   private String cancelBillReason;
 
   @Transient
   private String isEnterDiancaiPage;
 
   @Column(name="odd_change")
   private BigDecimal oddChange;
 
   @Column(name="order_id", length=36)
   private String orderId;
 
   @Column(name="ori_cost")
   private BigDecimal oriCost;
 
   @Column(name="other_discount", length=1024)
   private String otherDiscount;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="pay_time")
   private Date payTime;
 
   @Transient
   private String attributionDay;
 
   @Column(name="membercard_pay_type", length=36)
   private String membercardPayType;
 
   @Column(name="people_num")
   private Integer peopleNum;
 
   @Column(name="real_cost")
   private BigDecimal realCost;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="rest_id")
   private Restaurant restaurant;
 
   @Column(name="service_charge_money")
   private BigDecimal serviceChargeMoney;
 
   @Column(name="sevice_charge_num")
   private BigDecimal seviceChargeNum;
 
   @Column(name="deliver_cost")
   private BigDecimal deliverCost;
 
   @Column(name="consume_cost")
   private BigDecimal consumeCost;
 
   @Column(name="save_cost")
   private BigDecimal saveCost;
 
   @Column(name="rate_cost")
   private BigDecimal rateCost;
 
   @Column(name="service_charge_type", length=32)
   private String serviceChargeType;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="tab_id")
   private com.ndlan.canyin.base.entity.ctzh.Table table;
 
   @Transient
   private BigDecimal tableConsumeLow;
 
   @Column(name="tab_no", length=32)
   private String tabNo;
 
   @Column(name="taste_id_array", length=1024)
   private String tasteIdArray;
 
   @Column(name="taste_name_array", length=1024)
   private String tasteNameArray;
 
   @Column(name="avoidfood_id_array", length=1024)
   private String avoidfoodIdArray;
 
   @Column(name="avoidfood_name_array", length=1024)
   private String avoidfoodNameArray;
 
   @Column(name="pungent_level")
   private int pungentLevel = 0;
 
   @Transient
   @JsonIgnore
   private List<String> tasteIdList;
 
   @Transient
   @JsonIgnore
   private List<String> tasteNameList;
 
   @Transient
   @JsonIgnore
   private List<String> avoidfoodIdList;
 
   @Transient
   @JsonIgnore
   private List<String> avoidfoodNameList;
 
   @Column(name="payable_cost", length=36)
   private BigDecimal payableCost;
 
   @Transient
   private BigDecimal needMoney;
 
   @Transient
   private String payments;
 
   @Transient
   private BigDecimal consumeLow;
 
   @Column(name="urge_num")
   private int urgeNum;
 
   @Column(name="add_integral", length=36)
   private BigDecimal addIntegral;
 
   @Column(name="membercard_cost", length=36)
   private BigDecimal membercardCost;
 
   @JsonIgnore
   @OneToMany(mappedBy="dinerBill")
   private Set<BillCombine> billCombines;
 
   @JsonIgnore
   @OneToMany(mappedBy="dinerBill")
   private List<DinerBillDishe> dinerBillDishes;
 
   @JsonIgnore
   @OneToMany(mappedBy="dinerBill")
   private List<DinerBillDishesSet> dinerBillDishesSets;
 
   @JsonIgnore
   @OneToMany(mappedBy="dinerBill")
   private Set<DinerBillLog> dinerBillLogs;
 
   @JsonIgnore
   @OneToMany(mappedBy="dinerBill")
   private List<DinerBillPayment> dinerBillPayments;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="mb_id")
   private RestMemberInfo restMemberInfo;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="mc_id")
   private MembershipCard membershipCard;
 
   @JsonIgnore
   @OneToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="st_id")
   private CashSetting cashSetting;
 
   @JsonIgnore
   @OneToOne(mappedBy="dinerBill")
   private Takeout takeout;
   
   @JsonIgnore
   @OneToOne(mappedBy="dinerBill")
   private TableBillRelation tableBillRelation;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="cashier_id")
   private Employee cashierEmployee;
 
   @Column(name="cashier_name")
   private String cashierName;
 
   @Column(name="waiter_id")
   private String waiterId;
 
   @Column(name="waiter_name")
   private String waiterName;
 
   @Column(name="salesman_id")
   private String salesmanId;
 
   @Column(name="salesman_name")
   private String salesmanName;
 
   @Transient
   private String oldTabId;
 
   @Transient
   private Boolean intraday;
 
   @Transient
   private String isChedan;
   
   @Column(name="bill_number", length=36)
   private String billNumber;
   
   @Column(name="bill_money" )
   private BigDecimal billMoney;
 


public BigDecimal getBillMoney() {
	return billMoney;
}

public void setBillMoney(BigDecimal billMoney) {
	this.billMoney = billMoney;
}

public String getBillNumber() {
	return billNumber;
}

public void setBillNumber(String billNumber) {
	this.billNumber = billNumber;
}

public String getBillStatusDesc()
   {
     return BillStatusEnum.getDesc(getBillStatus());
   }
 
   public String getBillTypeDesc()
   {
     return BillTypeEnum.getDesc(this.billType);
   }
 
   public String getBillFromDesc()
   {
     return BillFromEnum.getDesc(this.billFrom);
   }
 
   public String getZizhuNotes()
   {
     if (!StringUtils.isEmpty(this.cardNotes)) {
       String[] a = this.cardNotes.split(",");
       if ((a != null) && (a.length == 8))
       {
         String hideString = a[2].substring(6, a[2].length() - 4);
         return "银行卡号：" + a[2].replace(hideString, "*******") + ",金额：" + a[3] + ",参考号：" + a[4] + ",流水号：" + a[5] + ",批次号：" + a[6] + ",有效期：" + a[7];
       }
     }
     if (!StringUtils.isEmpty(this.notes)) {
       String[] a = this.notes.split(",");
       if ((a != null) && (a.length == 5))
       {
         String hideString = a[1].substring(6, a[1].length() - 4);
         return "银行卡号：" + a[1].replace(hideString, "*******") + ",金额：" + a[2] + ",参考号：" + a[3] + ",流水号：" + a[4];
       }
       if ((a != null) && (a.length == 7))
       {
         String hideString = a[1].substring(6, a[1].length() - 4);
         return "银行卡号：" + a[1].replace(hideString, "*******") + ",金额：" + a[2] + ",参考号：" + a[3] + ",流水号：" + a[4] + ",批次号：" + a[5] + ",有效期：" + a[6];
       }
     }
     return "";
   }
 
   public String getAllNotes()
   {
     String cookingNotes = "";
     String avoidfoodNameArray = (getAvoidfoodNameArray() != null) && (!getAvoidfoodNameArray().isEmpty()) ? "忌口：" + getAvoidfoodNameArray() : "";
     String tasteNameArray = (getTasteNameArray() != null) && (!getTasteNameArray().isEmpty()) ? " 口味：" + getTasteNameArray() : "";
     int pungentLevel = getPungentLevel();
     String pungentLevelDesc = " 辣度：" + PungentLevelEnum.getDesc(String.valueOf(pungentLevel)) + " ";
     String notes = getNotes() == null ? "" : getNotes();
 
     cookingNotes = avoidfoodNameArray + tasteNameArray + pungentLevelDesc + " " + notes;
     return cookingNotes;
   }
 
   public BigDecimal getTableConsumeLow()
   {
     return this.tableConsumeLow;
   }
 
   public void setTableConsumeLow(BigDecimal tableConsumeLow)
   {
     this.tableConsumeLow = tableConsumeLow;
   }
 
   public void setTasteIdList(List<String> tasteIdList)
   {
     this.tasteIdArray = Collections3.convertToString(tasteIdList, ",");
     this.tasteIdList = tasteIdList;
   }
 
   public List<String> getTasteIdList()
   {
     if (this.tasteIdArray != null)
     {
       this.tasteIdList = ImmutableList.copyOf(StringUtils.split(this.tasteIdArray, ","));
     }
     return this.tasteIdList;
   }
 
   public void setTasteNameList(List<String> tasteNameList) {
     this.tasteNameArray = Collections3.convertToString(tasteNameList, ",");
     this.tasteNameList = tasteNameList;
   }
 
   public List<String> getTasteNameList() {
     if (this.tasteNameArray != null)
     {
       this.tasteNameList = ImmutableList.copyOf(StringUtils.split(this.tasteNameArray, ","));
     }
     return this.tasteNameList;
   }
 
   public void setAvoidfoodIdList(List<String> avoidfoodIdList)
   {
     this.avoidfoodIdArray = Collections3.convertToString(avoidfoodIdList, ",");
     this.avoidfoodIdList = avoidfoodIdList;
   }
 
   public List<String> getAvoidfoodIdList()
   {
     if (this.avoidfoodIdArray != null)
     {
       this.avoidfoodIdList = ImmutableList.copyOf(StringUtils.split(this.avoidfoodIdArray, ","));
     }
     return this.avoidfoodIdList;
   }
 
   public void setAvoidfoodNameList(List<String> avoidfoodNameList)
   {
     this.avoidfoodNameArray = Collections3.convertToString(avoidfoodNameList, ",");
     this.avoidfoodNameList = avoidfoodNameList;
   }
 
   public List<String> getAvoidfoodNameList() {
     if (this.avoidfoodNameArray != null)
     {
       this.avoidfoodNameList = ImmutableList.copyOf(StringUtils.split(this.avoidfoodNameArray, ","));
     }
     return this.avoidfoodNameList;
   }
 
   public BigDecimal getMolingModeCost()
   {
     return this.molingModeCost;
   }
 
   public void setMolingModeCost(BigDecimal molingModeCost) {
     this.molingModeCost = molingModeCost;
   }
 
   public String getDiscountName() {
     return this.discountName;
   }
 
   public void setDiscountName(String discountName) {
     this.discountName = discountName;
   }
 
   public String getMolingMode() {
     return this.molingMode;
   }
 
   public void setMolingMode(String molingMode) {
     this.molingMode = molingMode;
   }
 
   public String getBillId() {
     return this.billId;
   }
 
   public void setBillId(String billId) {
     this.billId = billId;
   }
 
   public BigDecimal getBeforePay() {
     return this.beforePay;
   }
 
   public void setBeforePay(BigDecimal beforePay) {
     this.beforePay = beforePay;
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
 
   public Date getBillTime()
   {
     return this.billTime;
   }
 
   public void setBillTime(Date billTime) {
     this.billTime = billTime;
   }
 
   public CashDiscount getCashDiscount()
   {
     return this.cashDiscount;
   }
 
   public void setCashDiscount(CashDiscount cashDiscount) {
     this.cashDiscount = cashDiscount;
   }
 
   public Integer getDiscount() {
     return this.discount;
   }
 
   public void setDiscount(Integer discount) {
     this.discount = discount;
   }
 
   public BigDecimal getDrawBillAmount() {
     return this.drawBillAmount;
   }
 
   public void setDrawBillAmount(BigDecimal drawBillAmount) {
     this.drawBillAmount = drawBillAmount;
   }
 
   public String getIsDrawBill() {
     return this.isDrawBill;
   }
 
   public void setIsDrawBill(String isDrawBill) {
     this.isDrawBill = isDrawBill;
   }
 
   public String getIsInKitchen() {
     return this.isInKitchen;
   }
 
   public void setIsInKitchen(String isInKitchen) {
     this.isInKitchen = isInKitchen;
   }
 
   public String getIsMoling() {
     return this.isMoling;
   }
 
   public void setIsMoling(String isMoling) {
     this.isMoling = isMoling;
   }
 
   public String getIsWaitCall() {
     return this.isWaitCall;
   }
 
   public void setIsWaitCall(String isWaitCall) {
     this.isWaitCall = isWaitCall;
   }
 
   public Date getLastUrgeTime() {
     return this.lastUrgeTime;
   }
 
   public void setLastUrgeTime(Date lastUrgeTime) {
     this.lastUrgeTime = lastUrgeTime;
   }
 
   public BigDecimal getLosseGain() {
     return this.losseGain;
   }
 
   public void setLosseGain(BigDecimal losseGain) {
     this.losseGain = losseGain;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public BigDecimal getOddChange() {
     return this.oddChange;
   }
 
   public void setOddChange(BigDecimal oddChange) {
     this.oddChange = oddChange;
   }
 
   public String getOrderId() {
     return this.orderId;
   }
 
   public void setOrderId(String orderId) {
     this.orderId = orderId;
   }
 
   public BigDecimal getOriCost() {
     return this.oriCost;
   }
 
   public void setOriCost(BigDecimal oriCost) {
     this.oriCost = oriCost;
   }
 
   public String getOtherDiscount() {
     return this.otherDiscount;
   }
 
   public void setOtherDiscount(String otherDiscount) {
     this.otherDiscount = otherDiscount;
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
 
   public Restaurant getRestaurant() {
     return this.restaurant;
   }
 
   public void setRestaurant(Restaurant restaurant) {
     this.restaurant = restaurant;
   }
 
   public BigDecimal getServiceChargeMoney() {
     return this.serviceChargeMoney;
   }
 
   public void setServiceChargeMoney(BigDecimal serviceChargeMoney) {
     this.serviceChargeMoney = serviceChargeMoney;
   }
 
   public String getServiceChargeType() {
     return this.serviceChargeType;
   }
 
   public void setServiceChargeType(String serviceChargeType) {
     this.serviceChargeType = serviceChargeType;
   }
 
   public com.ndlan.canyin.base.entity.ctzh.Table getTable() {
     return this.table;
   }
 
   public void setTable(com.ndlan.canyin.base.entity.ctzh.Table table) {
     this.table = table;
   }
 
   public String getTabNo() {
     return this.tabNo;
   }
 
   public void setTabNo(String tabNo) {
     this.tabNo = tabNo;
   }
 
   public int getUrgeNum() {
     return this.urgeNum;
   }
 
   public void setUrgeNum(int urgeNum) {
     this.urgeNum = urgeNum;
   }
 
   public Set<BillCombine> getBillCombines() {
     return this.billCombines;
   }
 
   public void setBillCombines(Set<BillCombine> billCombines) {
     this.billCombines = billCombines;
   }
 
   public List<DinerBillDishe> getDinerBillDishes() {
     return this.dinerBillDishes;
   }
 
   public void setDinerBillDishes(List<DinerBillDishe> dinerBillDishes) {
     this.dinerBillDishes = dinerBillDishes;
   }
 
   public Set<DinerBillLog> getDinerBillLogs() {
     return this.dinerBillLogs;
   }
 
   public void setMolingModeDesc(String molingModeDesc) {
	this.molingModeDesc = molingModeDesc;
}

public void setDinerBillLogs(Set<DinerBillLog> dinerBillLogs) {
     this.dinerBillLogs = dinerBillLogs;
   }
 
   public List<DinerBillPayment> getDinerBillPayments() {
     return this.dinerBillPayments;
   }
 
   public void setDinerBillPayments(List<DinerBillPayment> dinerBillPayments) {
     this.dinerBillPayments = dinerBillPayments;
   }
 
   public RestMemberInfo getRestMemberInfo() {
     return this.restMemberInfo;
   }
 
   public void setRestMemberInfo(RestMemberInfo restMemberInfo) {
     this.restMemberInfo = restMemberInfo;
   }
 
   public BigDecimal getPayableCost() {
     return this.payableCost;
   }
 
   public void setPayableCost(BigDecimal payableCost) {
     this.payableCost = payableCost;
   }
 
   public MembershipCard getMembershipCard() {
     return this.membershipCard;
   }
 
   public void setMembershipCard(MembershipCard membershipCard) {
     this.membershipCard = membershipCard;
   }
 
   public CashSetting getCashSetting() {
     return this.cashSetting;
   }
 
   public void setCashSetting(CashSetting cashSetting) {
     this.cashSetting = cashSetting;
   }
 
   public TableBillRelation getTableBillRelation() {
     return this.tableBillRelation;
   }
 
   public void setTableBillRelation(TableBillRelation tableBillRelation) {
     this.tableBillRelation = tableBillRelation;
   }
 
   public Employee getCashierEmployee() {
     return this.cashierEmployee;
   }
 
   public void setCashierEmployee(Employee cashierEmployee) {
     this.cashierEmployee = cashierEmployee;
   }
 
   public BigDecimal getConsumeCost() {
     return this.consumeCost;
   }
 
   public void setConsumeCost(BigDecimal consumeCost) {
     this.consumeCost = consumeCost;
   }
 
   public BigDecimal getSaveCost() {
     return this.saveCost;
   }
 
   public void setSaveCost(BigDecimal saveCost) {
     this.saveCost = saveCost;
   }
 
   public BigDecimal getRateCost() {
     return this.rateCost;
   }
 
   public void setRateCost(BigDecimal rateCost) {
     this.rateCost = rateCost;
   }
 
   public String getIsEnterDiancaiPage()
   {
     return this.isEnterDiancaiPage;
   }
 
   public void setIsEnterDiancaiPage(String isEnterDiancaiPage) {
     this.isEnterDiancaiPage = isEnterDiancaiPage;
   }
 
   public BigDecimal getNeedMoney() {
     return this.needMoney;
   }
 
   public void setNeedMoney(BigDecimal needMoney) {
     this.needMoney = needMoney;
   }
 
   public BigDecimal getConsumeLow()
   {
     return this.consumeLow;
   }
 
   public void setConsumeLow(BigDecimal consumeLow) {
     this.consumeLow = consumeLow;
   }
 
   public BigDecimal getAddIntegral() {
     return this.addIntegral;
   }
 
   public void setAddIntegral(BigDecimal addIntegral) {
     this.addIntegral = addIntegral;
   }
 
   public String getCashierName() {
     return this.cashierName;
   }
 
   public void setCashierName(String cashierName) {
     this.cashierName = cashierName;
   }
 
   public String getWaiterId() {
     return this.waiterId;
   }
 
   public void setWaiterId(String waiterId) {
     this.waiterId = waiterId;
   }
 
   public String getWaiterName() {
     return this.waiterName;
   }
 
   public void setWaiterName(String waiterName) {
     this.waiterName = waiterName;
   }
 
   public String getSalesmanId() {
     return this.salesmanId;
   }
 
   public void setSalesmanId(String salesmanId) {
     this.salesmanId = salesmanId;
   }
 
   public String getSalesmanName() {
     return this.salesmanName;
   }
 
   public void setSalesmanName(String salesmanName) {
     this.salesmanName = salesmanName;
   }
 
   public String getOldTabId() {
     return this.oldTabId;
   }
 
   public void setOldTabId(String oldTabId) {
     this.oldTabId = oldTabId;
   }
 
   public String getCancelBillReason() {
     return this.cancelBillReason;
   }
 
   public void setCancelBillReason(String cancelBillReason) {
     this.cancelBillReason = cancelBillReason;
   }
 
   public String getReaId() {
     return this.reaId;
   }
 
   public void setReaId(String reaId) {
     this.reaId = reaId;
   }
 
   public String getCompany() {
     return this.company;
   }
 
   public void setCompany(String company) {
     this.company = company;
   }
 
   public String getTasteIdArray() {
     return this.tasteIdArray;
   }
 
   public void setTasteIdArray(String tasteIdArray) {
     this.tasteIdArray = tasteIdArray;
   }
 
   public String getAvoidfoodIdArray() {
     return this.avoidfoodIdArray;
   }
 
   public void setAvoidfoodIdArray(String avoidfoodIdArray) {
     this.avoidfoodIdArray = avoidfoodIdArray;
   }
 
   public int getPungentLevel() {
     return this.pungentLevel;
   }
 
   public void setPungentLevel(int pungentLevel) {
     this.pungentLevel = pungentLevel;
   }
 
   public String getTasteNameArray() {
     return this.tasteNameArray;
   }
 
   public void setTasteNameArray(String tasteNameArray) {
     this.tasteNameArray = tasteNameArray;
   }
 
   public String getAvoidfoodNameArray() {
     return this.avoidfoodNameArray;
   }
 
   public void setAvoidfoodNameArray(String avoidfoodNameArray) {
     this.avoidfoodNameArray = avoidfoodNameArray;
   }
 
   public String getPayments() {
     return this.payments;
   }
 
   public void setPayments(String payments) {
     this.payments = payments;
   }
 
   public String getBillType() {
     return this.billType;
   }
 
   public void setBillType(String billType) {
     this.billType = billType;
   }
 
   public String getBillNotes() {
     return this.billNotes;
   }
 
   public void setBillNotes(String billNotes)
   {
     this.billNotes = billNotes;
   }
 
   public Boolean getIntraday()
   {
     if (getPayTime() != null) {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       String nowStr = sdf.format(new Date());
       String payStr = sdf.format(getPayTime());
       this.intraday = Boolean.valueOf(nowStr.equals(payStr));
     }
     return this.intraday;
   }
 
   public void setIntraday(Boolean intraday) {
     this.intraday = intraday;
   }
 
   public String getIsCustomDiscount() {
     return this.isCustomDiscount;
   }
 
   public void setIsCustomDiscount(String isCustomDiscount) {
     this.isCustomDiscount = isCustomDiscount;
   }
 
   public BigDecimal getSeviceChargeNum() {
     return this.seviceChargeNum;
   }
 
   public void setSeviceChargeNum(BigDecimal seviceChargeNum) {
     this.seviceChargeNum = seviceChargeNum;
   }
 
   public String getDishesTypeDiscountDesc() {
     return this.dishesTypeDiscountDesc;
   }
 
   public void setDishesTypeDiscountDesc(String dishesTypeDiscountDesc) {
     this.dishesTypeDiscountDesc = dishesTypeDiscountDesc;
   }
 
   public String getIsChedan()
   {
     this.isChedan = TrueFalseEnum.FALSE.getCode();
 
     if (BillStatusEnum.NOT_PLACE_ORDER.getCode().equals(this.billStatus)) {
       this.isChedan = TrueFalseEnum.TRUE.getCode();
     } else {
       int dishe_cancel = 0;
       int set_cancel = 0;
       int totalNum = 0;
 
       if (this.dinerBillDishes != null)
       {
         for (DinerBillDishe dinerBillDishe : this.dinerBillDishes) {
           if ((DishesStatusEnum.UNSERVE_CANCEL.getCode().equals(dinerBillDishe.getDishesStatus())) || (DishesStatusEnum.SERVED_CANCEL.getCode().equals(dinerBillDishe.getDishesStatus()))) {
             dishe_cancel++;
           }
         }
         totalNum += this.dinerBillDishes.size();
       }
       if (this.dinerBillDishesSets != null)
       {
         for (DinerBillDishesSet dinerBillDishesSet : this.dinerBillDishesSets) {
           if ((DishesStatusEnum.UNSERVE_CANCEL.getCode().equals(dinerBillDishesSet.getDsStatus())) || (DishesStatusEnum.SERVED_CANCEL.getCode().equals(dinerBillDishesSet.getDsStatus()))) {
             set_cancel++;
           }
         }
         totalNum += this.dinerBillDishesSets.size();
       }
       if (dishe_cancel + set_cancel == totalNum)
       {
         this.isChedan = TrueFalseEnum.TRUE.getCode();
       }
     }
     return this.isChedan;
   }
 
   public String getIsValid() {
     return this.isValid;
   }
 
   public void setIsValid(String isValid) {
     this.isValid = isValid;
   }
 
   public void setIsChedan(String isChedan) {
     this.isChedan = isChedan;
   }
 
   public List<DinerBillDishesSet> getDinerBillDishesSets() {
     return this.dinerBillDishesSets;
   }
 
   public void setDinerBillDishesSets(List<DinerBillDishesSet> dinerBillDishesSets) {
     this.dinerBillDishesSets = dinerBillDishesSets;
   }
 
   public String getMembercardPayType() {
     return this.membercardPayType;
   }
 
   public void setMembercardPayType(String membercardPayType) {
     this.membercardPayType = membercardPayType;
   }
 
   public String getIsShift() {
     return this.isShift;
   }
 
   public void setIsShift(String isShift) {
     this.isShift = isShift;
   }
 
   public String getCesId() {
     return this.cesId;
   }
 
   public void setCesId(String cesId) {
     this.cesId = cesId;
   }
 
   public String getIsForcePay() {
     return this.isForcePay;
   }
 
   public void setIsForcePay(String isForcePay) {
     this.isForcePay = isForcePay;
   }
 
   public Employee getForcePayOperator() {
     return this.forcePayOperator;
   }
 
   public void setForcePayOperator(Employee forcePayOperator) {
     this.forcePayOperator = forcePayOperator;
   }
 
   public Date getForcePayTime() {
     return this.forcePayTime;
   }
 
   public void setForcePayTime(Date forcePayTime) {
     this.forcePayTime = forcePayTime;
   }
 
   public String getCardNotes() {
     return this.cardNotes;
   }
 
   public void setCardNotes(String cardNotes) {
     this.cardNotes = cardNotes;
   }
 
   public String getBillFrom() {
     return this.billFrom;
   }
 
   public void setBillFrom(String billFrom) {
     this.billFrom = billFrom;
   }
 
   public BigDecimal getDeliverCost() {
     return this.deliverCost;
   }
 
   public void setDeliverCost(BigDecimal deliverCost) {
     this.deliverCost = deliverCost;
   }
 
   public Takeout getTakeout() {
     return this.takeout;
   }
 
   public void setTakeout(Takeout takeout) {
     this.takeout = takeout;
   }
 
   public BigDecimal getMembercardCost() {
     return this.membercardCost;
   }
 
   public void setMembercardCost(BigDecimal membercardCost) {
     this.membercardCost = membercardCost;
   }
 
   public String getOnlineBillId() {
     return this.onlineBillId;
   }
 
   public void setOnlineBillId(String onlineBillId) {
     this.onlineBillId = onlineBillId;
   }
 
   public String getIsRound() {
     return this.isRound;
   }
 
   public void setIsRound(String isRound) {
     this.isRound = isRound;
   }
 
   public String getMolingModeDesc() {
     this.molingModeDesc = MolingModeEnum.getDesc(this.molingMode);
     return this.molingModeDesc;
   }
 
   public Date getResettleTime() {
     return this.resettleTime;
   }
 
   public void setResettleTime(Date resettleTime) {
     this.resettleTime = resettleTime;
   }
 
   public Boolean getIsAddDishes() {
     return this.isAddDishes;
   }
 
   public void setIsAddDishes(Boolean isAddDishes) {
     this.isAddDishes = isAddDishes;
   }
 
   public String getAttributionDay() {
     return this.attributionDay;
   }
 
   public void setAttributionDay(String attributionDay) {
     this.attributionDay = attributionDay;
   }
 
   public String getOnlineBillNo() {
     return this.onlineBillNo;
   }
 
   public void setOnlineBillNo(String onlineBillNo) {
     this.onlineBillNo = onlineBillNo;
   }
   
	public String getCreateEmployeeName() {
		return createEmployeeName;
	}
	
	public void setCreateEmployeeName(String createEmployeeName) {
		this.createEmployeeName = createEmployeeName;
	}

public String getCashierEmployeeName() {
	return cashierEmployeeName;
}

public void setCashierEmployeeName(String cashierEmployeeName) {
	this.cashierEmployeeName = cashierEmployeeName;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

public String getTableId() {
	return tableId;
}

public void setTableId(String tableId) {
	this.tableId = tableId;
}

public String getTableName() {
	return tableName;
}

public void setTableName(String tableName) {
	this.tableName = tableName;
}
   
 }
 

