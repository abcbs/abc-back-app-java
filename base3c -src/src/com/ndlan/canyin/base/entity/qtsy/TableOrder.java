 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
 import com.google.common.collect.ImmutableList;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.TableBillRelation;
import com.ndlan.canyin.base.entity.sygl.PaymentType;
import com.ndlan.canyin.base.entity.qtsy.OrderBillDishe;
import com.ndlan.canyin.base.entity.qtsy.OrderBillDishesSet;
 import com.ndlan.canyin.core.common.BillStatusEnum;
 import com.ndlan.canyin.core.common.OrderStatusEnum;
 import com.ndlan.canyin.core.common.OrderWayEnum;
 import com.ndlan.canyin.core.common.PungentLevelEnum;
 import com.ndlan.canyin.core.utils.Collections3;
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
 import javax.persistence.OneToOne;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
 import javax.persistence.Transient;
 import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @javax.persistence.Table(name="cm_table_order")
 public class TableOrder extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="order_id", unique=true, nullable=false, length=36)
   private String orderId;
   
   @Column(name="bill_id")
   private String billId;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="cpt_id")
   @JsonIgnore
   private PaymentType paymentType;
 
   @Column(name="online_payment_type", length=32)
   private String onlinePaymentType;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="dining_time")
   private Date diningTime;
 
   @Column(name="mb_id", length=36)
   private String mbId;
 
   @Column(name="mc_id", length=36)
   private String mcId;
 
   @Column(name="bill_from", length=32)
   private String billFrom;
 
   @Column(name="taste_id_array", length=1024)
   private String tasteIdArray;
 
   @Column(name="taste_name_array", length=1024)
   private String tasteNameArray;
 
   @Transient
   @JsonIgnore
   private List<String> tasteIdList;
 
   @Transient
   @JsonIgnore
   private List<String> avoidfoodIdList;
 
   @Column(name="avoidfood_id_array", length=1024)
   private String avoidfoodIdArray;
 
   @Column(name="avoidfood_name_array", length=1024)
   private String avoidfoodNameArray;
 
   @Column(name="pungent_level")
   private int pungentLevel;
 
   @Column(name="notes", length=1024)
   private String notes;
 
   @Column(name="order_people_name", length=16)
   private String orderPeopleName;
 
   @Column(name="order_people_name_code", length=16)
   private String orderPeopleNameCode;
 
   @Column(name="order_status", length=32)
   private String orderStatus;
 
   @Transient
   private String orderStatusDesc;
 
   @Transient
   private String tableStatus = "Transient";
   
   
   @Column(name="is_status", length=2)
   private String isStatus;  //ios是否就餐
   
   
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="order_time")
   private Date orderTime;
 
   @Transient
   private String orderTimeStr;
 
   @Column(name="order_way", length=32)
   private String orderWay;
 
   @Transient
   private String orderWayDesc;
 
   @Column(name="people_num")
   private Integer peopleNum;
 
   @Column(name="prepay")
   private BigDecimal prepay;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="sales_mg_id")
   @JsonIgnore
   private Employee salesMg;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="waiter_id")
   @JsonIgnore
   private Employee waiter;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="tab_id")
   @JsonIgnore
   private com.ndlan.canyin.base.entity.ctzh.Table table;
 
   @Transient
   private String tabId;
 
   @Column(name="ccd_id")
   private String ccdId;
 
   @Column(name="tab_no", length=32)
   private String tabNo;
 
   @Column(name="telphone", length=11)
   private String telphone;
 
   @Column(name="order_no", length=64)
   private String orderNo;
 
   @OneToMany(mappedBy="tableOrder")
   @JsonIgnore
   private List<OrderBillDishe> orderBillDishes;
 
   @OneToMany(mappedBy="tableOrder")
   @JsonIgnore
   private List<OrderBillDishesSet> orderBillDishesSets;
 
   @OneToOne(mappedBy="tableOrder", fetch=FetchType.LAZY)
   @JsonIgnore
   private TableBillRelation tableBillRelation;
 
   @Column(name="ori_cost", length=32)
   private BigDecimal oriCost;
 
   @Column(name="is_moling", length=1)
   private String isMoling;
 
   @Column(name="moling_mode_cost")
   private BigDecimal molingModeCost;
 
   @Column(name="gender", length=32)
   private String gender;
 
   @Transient
   private String isEnterDiancaiPage;
 
   @Column(name="cancle_reason")
   private String cancleReason;
 
   @Column(name="online_order_id", length=36)
   private String onlineOrderId;
 
   @Column(name="online_order_no", length=36)
   private String onlineOrderNo;
 
   @Column(name="online_uob_id", length=36)
   private String onlineUobId;
 
   @Column(name="online_area_id", length=36)
   private String onlineAreaId;
 
   @Column(name="online_area_name", length=128)
   private String onlineAreaName;
 
   @Column(name="online_order_people_name", length=16)
   private String onlineOrderPeopleName;
 
   @Column(name="online_gender", length=32)
   private String onlineGender;
 
   @Column(name="online_mobile", length=32)
   private String onlineMobile;
 
   @Column(name="online_user_mobile", length=32)
   private String onlineUserMobile;
 
   @Column(name="online_telphone", length=11)
   private String onlineTelphone;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="online_dining_time")
   private Date onlineDiningTime;
 
   @Column(name="online_people_num")
   private Integer onlinePeopleNum;
 
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
 
   public String getAllNotes()
   {
     String cookingNotes = "";
     String avoidfoodNameArray = (getAvoidfoodNameArray() != null) && (!getAvoidfoodNameArray().isEmpty()) ? "忌口：" + getAvoidfoodNameArray() : "";
     String tasteNameArray = (getTasteNameArray() != null) && (!getTasteNameArray().isEmpty()) ? " 口味：" + getTasteNameArray() : "";
     int pungentLevel = getPungentLevel();
     String pungentLevelDesc = " 辣度：" + PungentLevelEnum.getDesc(String.valueOf(pungentLevel)) + " ";
     String notes = getNotes();
 
     cookingNotes = avoidfoodNameArray + tasteNameArray + pungentLevelDesc + notes;
     return cookingNotes;
   }
 
   public String getCancleReason()
   {
     return this.cancleReason;
   }
 
   public void setCancleReason(String cancleReason) {
     this.cancleReason = cancleReason;
   }
 
   public List<OrderBillDishe> getOrderBillDishes() {
     return this.orderBillDishes;
   }
 
   public void setOrderBillDishes(List<OrderBillDishe> orderBillDishes) {
     this.orderBillDishes = orderBillDishes;
   }
 
   public String getOrderNo() {
     return this.orderNo;
   }
 
   public void setOrderNo(String orderNo) {
     this.orderNo = orderNo;
   }
 
   public String getOrderId()
   {
     return this.orderId;
   }
 
   public void setOrderId(String orderId) {
     this.orderId = orderId;
   }
 
   public Date getDiningTime() {
     return this.diningTime;
   }
 
   public void setDiningTime(Date diningTime) {
     this.diningTime = diningTime;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public String getOrderPeopleName() {
     return this.orderPeopleName;
   }
 
   public void setOrderPeopleName(String orderPeopleName) {
     this.orderPeopleName = orderPeopleName;
   }
 
   public String getOrderStatus() {
     return this.orderStatus;
   }
 
   public void setOrderStatus(String orderStatus) {
     this.orderStatus = orderStatus;
   }
 
   public Date getOrderTime() {
     return this.orderTime;
   }
 
   public void setOrderTime(Date orderTime) {
     this.orderTime = orderTime;
   }
 
   public String getOrderWay() {
     return this.orderWay;
   }
 
   public void setOrderWay(String orderWay) {
     this.orderWay = orderWay;
   }
 
   public Integer getPeopleNum() {
     return this.peopleNum;
   }
 
   public void setPeopleNum(Integer peopleNum) {
     this.peopleNum = peopleNum;
   }
 
   public BigDecimal getPrepay() {
     return this.prepay;
   }
 
   public void setPrepay(BigDecimal prepay) {
     this.prepay = prepay;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getTabNo() {
     return this.tabNo;
   }
 
   public void setTabNo(String tabNo) {
     this.tabNo = tabNo;
   }
 
   public String getTelphone() {
     return this.telphone;
   }
 
   public void setTelphone(String telphone) {
     this.telphone = telphone;
   }
 
   public Employee getSalesMg() {
     return this.salesMg;
   }
 
   public void setSalesMg(Employee salesMg) {
     this.salesMg = salesMg;
   }
 
   public Employee getWaiter() {
     return this.waiter;
   }
 
   public void setWaiter(Employee waiter) {
     this.waiter = waiter;
   }
 
   public TableBillRelation getTableBillRelation() {
     return this.tableBillRelation;
   }
 
   public void setTableBillRelation(TableBillRelation tableBillRelation) {
     this.tableBillRelation = tableBillRelation;
   }
 
   public String getIsEnterDiancaiPage() {
     return this.isEnterDiancaiPage;
   }
 
   public void setIsEnterDiancaiPage(String isEnterDiancaiPage) {
     this.isEnterDiancaiPage = isEnterDiancaiPage;
   }
 
   public PaymentType getPaymentType() {
     return this.paymentType;
   }
 
   public void setPaymentType(PaymentType paymentType) {
     this.paymentType = paymentType;
   }
 
   public String getOrderWayDesc() {
     this.orderWayDesc = OrderWayEnum.getDesc(getOrderWay());
     return this.orderWayDesc;
   }
 
   public void setOrderWayDesc(String orderWayDesc) {
     this.orderWayDesc = orderWayDesc;
   }
 
   public String getOrderStatusDesc() {
     this.orderStatusDesc = OrderStatusEnum.getDesc(this.orderStatus);
     return this.orderStatusDesc;
   }
 
   public void setOrderStatusDesc(String orderStatusDesc) {
     this.orderStatusDesc = orderStatusDesc;
   }
 
   public com.ndlan.canyin.base.entity.ctzh.Table getTable() {
     return this.table;
   }
 
   public void setTable(com.ndlan.canyin.base.entity.ctzh.Table table) {
     this.table = table;
   }
 
   public String getOrderTimeStr() {
     return this.orderTimeStr;
   }
 
   public void setOrderTimeStr(String orderTimeStr) {
     this.orderTimeStr = orderTimeStr;
   }
 
   public String getMbId() {
     return this.mbId;
   }
 
   public void setMbId(String mbId) {
     this.mbId = mbId;
   }
 
   public String getTasteIdArray() {
     return this.tasteIdArray;
   }
 
   public void setTasteIdArray(String tasteIdArray) {
     this.tasteIdArray = tasteIdArray;
   }
 
   public String getTasteNameArray() {
     return this.tasteNameArray;
   }
 
   public void setTasteNameArray(String tasteNameArray) {
     this.tasteNameArray = tasteNameArray;
   }
 
   public String getAvoidfoodIdArray() {
     return this.avoidfoodIdArray;
   }
 
   public void setAvoidfoodIdArray(String avoidfoodIdArray) {
     this.avoidfoodIdArray = avoidfoodIdArray;
   }
 
   public String getAvoidfoodNameArray() {
     return this.avoidfoodNameArray;
   }
 
   public void setAvoidfoodNameArray(String avoidfoodNameArray) {
     this.avoidfoodNameArray = avoidfoodNameArray;
   }
 
   public int getPungentLevel() {
     return this.pungentLevel;
   }
 
   public void setPungentLevel(int pungentLevel) {
     this.pungentLevel = pungentLevel;
   }
 
   public BigDecimal getOriCost() {
     return this.oriCost;
   }
 
   public void setOriCost(BigDecimal oriCost) {
     this.oriCost = oriCost;
   }
 
   public String getBillId() {
     return this.billId;
   }
 
   public void setBillId(String billId) {
     this.billId = billId;
   }
 
   public String getOrderPeopleNameCode() {
     return this.orderPeopleNameCode;
   }
 
   public void setOrderPeopleNameCode(String orderPeopleNameCode) {
     this.orderPeopleNameCode = orderPeopleNameCode;
   }
 
   public String getTabId() {
     return this.table != null ? this.table.getTabId() : "";
   }
 
   public String getClientTabId() {
     return this.tabId;
   }
 
   public void setTabId(String tabId) {
     this.tabId = tabId;
   }
 
   public String getGender() {
     return this.gender;
   }
 
   public void setGender(String gender) {
     this.gender = gender;
   }
 
   public String getOnlineOrderId() {
     return this.onlineOrderId;
   }
 
   public void setOnlineOrderId(String onlineOrderId) {
     this.onlineOrderId = onlineOrderId;
   }
 
   public List<OrderBillDishesSet> getOrderBillDishesSets() {
     return this.orderBillDishesSets;
   }
 
   public void setOrderBillDishesSets(List<OrderBillDishesSet> orderBillDishesSets) {
     this.orderBillDishesSets = orderBillDishesSets;
   }
 
   public String getMcId() {
     return this.mcId;
   }
 
   public void setMcId(String mcId) {
     this.mcId = mcId;
   }
 
   public String getOnlinePaymentType() {
     return this.onlinePaymentType;
   }
 
   public void setOnlinePaymentType(String onlinePaymentType) {
     this.onlinePaymentType = onlinePaymentType;
   }
 
   public String getCcdId() {
     return this.ccdId;
   }
 
   public void setCcdId(String ccdId) {
     this.ccdId = ccdId;
   }
 
   public String getOnlineUobId() {
     return this.onlineUobId;
   }
 
   public void setOnlineUobId(String onlineUobId) {
     this.onlineUobId = onlineUobId;
   }
 
   public String getOnlineAreaId() {
     return this.onlineAreaId;
   }
 
   public void setOnlineAreaId(String onlineAreaId) {
     this.onlineAreaId = onlineAreaId;
   }
 
   public String getOnlineAreaName() {
     return this.onlineAreaName;
   }
 
   public void setOnlineAreaName(String onlineAreaName) {
     this.onlineAreaName = onlineAreaName;
   }
 
   public String getOnlineOrderPeopleName() {
     return this.onlineOrderPeopleName;
   }
 
   public void setOnlineOrderPeopleName(String onlineOrderPeopleName) {
     this.onlineOrderPeopleName = onlineOrderPeopleName;
   }
 
   public String getOnlineGender() {
     return this.onlineGender;
   }
 
   public void setOnlineGender(String onlineGender) {
     this.onlineGender = onlineGender;
   }
 
   public String getOnlineMobile() {
     return this.onlineMobile;
   }
 
   public void setOnlineMobile(String onlineMobile) {
     this.onlineMobile = onlineMobile;
   }
 
   public String getOnlineTelphone() {
     return this.onlineTelphone;
   }
 
   public void setOnlineTelphone(String onlineTelphone) {
     this.onlineTelphone = onlineTelphone;
   }
 
   public Date getOnlineDiningTime() {
     return this.onlineDiningTime;
   }
 
   public void setOnlineDiningTime(Date onlineDiningTime) {
     this.onlineDiningTime = onlineDiningTime;
   }
 
   public Integer getOnlinePeopleNum() {
     return this.onlinePeopleNum;
   }
 
   public void setOnlinePeopleNum(Integer onlinePeopleNum) {
     this.onlinePeopleNum = onlinePeopleNum;
   }
 
   public String getTableStatus()
   {
     if ((this.table != null) && (this.table.getCurrentTableBillRelation() != null)) {
       return BillStatusEnum.getDesc(this.table.getCurrentTableBillRelation().getBillStatus());
     }
     return this.tableStatus;
   }
 
   public void setTableStatus(String tableStatus) {
     this.tableStatus = tableStatus;
   }
 
   public String getIsMoling() {
     return this.isMoling;
   }
 
   public void setIsMoling(String isMoling) {
     this.isMoling = isMoling;
   }
 
   public BigDecimal getMolingModeCost() {
     return this.molingModeCost;
   }
 
   public void setMolingModeCost(BigDecimal molingModeCost) {
     this.molingModeCost = molingModeCost;
   }
 
   public String getOnlineOrderNo() {
     return this.onlineOrderNo;
   }
 
   public void setOnlineOrderNo(String onlineOrderNo) {
     this.onlineOrderNo = onlineOrderNo;
   }
 
   public String getBillFrom() {
     return this.billFrom;
   }
 
   public void setBillFrom(String billFrom) {
     this.billFrom = billFrom;
   }
 
   public String getOnlineUserMobile() {
     return this.onlineUserMobile;
   }
 
   public void setOnlineUserMobile(String onlineUserMobile) {
     this.onlineUserMobile = onlineUserMobile;
   }

public String getIsStatus() {
	return isStatus;
}

public void setIsStatus(String isStatus) {
	this.isStatus = isStatus;
}
   
 }

