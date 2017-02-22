 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
 import com.google.common.collect.ImmutableList;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.cygl.DishesSetCategory;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.core.common.PungentLevelEnum;
 import com.ndlan.canyin.core.utils.BigDecimalUtil;
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
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
 import javax.persistence.Transient;
 import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_diner_bill_dishes_set")
 public class DinerBillDishesSet extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="bds_id", unique=true, nullable=false, length=36)
   private String bdsId;
 
   @Column(name="bill_no", length=32)
   private String billNo;
 
   @Column(name="give_operator")
   private String giveOperator;
 
   @Column(name="give_time")
   private Date giveTime;
 
   @Column(name="cancel_reason_id", length=36)
   private String cancelReasonId;
 
   @Column(name="cancel_reason_name", length=36)
   private String cancelReasonName;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="cancel_time")
   private Date cancelTime;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="ds_category_id")
   private DishesSetCategory dishesSetCategory;
 
   @Column(name="avoidfood_id_array", length=1024)
   private String avoidfoodIdArray;
 
   @Column(name="avoidfood_name_array", length=1024)
   private String avoidfoodNameArray;
 
   @Column(name="discount")
   private Integer discount;
 
   @Column(name="discount_type", length=32)
   private String discountType;
 
   @Column(name="ds_code", length=64)
   private String dsCode;
 
   @Column(name="ds_name", length=32)
   private String dsName;
 
   @Column(name="ds_dishes_desc", length=8000)
   private String dsDishesDesc;
 
   @Column(name="ds_status", length=32)
   private String dsStatus;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="fixed_time")
   private Date fixedTime;
 
   @Column(name="fixed_version")
   private int fixedVersion;
 
   @Column(name="is_onsale", length=1)
   private String isOnsale;
 
   @Column(name="is_ruling_price", length=1)
   private String isRulingPrice;
 
   @Column(name="is_user_defined", length=1)
   private String isUserDefined;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="last_urge_time")
   private Date lastUrgeTime;
 
   @Column(name="notes", length=1024)
   private String notes;
 
   @Column(name="rm_return", length=1)
   private String rmReturn;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="order_emp_id")
   private Employee orderEmp;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="order_time")
   private Date orderTime;
 
   @Column(name="ori_cost")
   private BigDecimal oriCost;
 
   @Column(name="pungent_level")
   private int pungentLevel;
 
   @Column(name="real_cost")
   private BigDecimal realCost;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="taste_id_array", length=1024)
   private String tasteIdArray;
 
   @Column(name="taste_name_array", length=1024)
   private String tasteNameArray;
 
   @Column(name="service_commission")
   private BigDecimal serviceCommission;
 
   @Column(name="sale_commission")
   private BigDecimal saleCommission;
 
   @Transient
   @JsonIgnore
   private List<String> tasteIdList;
 
   @Transient
   @JsonIgnore
   private List<String> avoidfoodIdList;
 
   @Column(name="unit_num")
   private float unitNum;
 
   @Transient
   private int orderNum = 1;
 
   @Column(name="unit_price")
   private BigDecimal unitPrice;
 
   @Column(name="real_unit_price")
   private BigDecimal realUnitPrice;
 
   @Column(name="urge_num")
   private int urgeNum;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="bill_id")
   private DinerBill dinerBill;
 
   @Column(name="ds_id")
   private String dsId;
 
   @Column(name="unit_id")
   private String unitId;
 
   @Column(name="unit_name")
   private String unitName;
 
   public String getGiveOperator()
   {
     return this.giveOperator;
   }
 
   public void setGiveOperator(String giveOperator) {
     this.giveOperator = giveOperator;
   }
 
   public Date getGiveTime() {
     return this.giveTime;
   }
 
   public void setGiveTime(Date giveTime) {
     this.giveTime = giveTime;
   }
 
   public String getRmReturn()
   {
     return this.rmReturn;
   }
 
   public void setRmReturn(String rmReturn) {
     this.rmReturn = rmReturn;
   }
 
   public String getAllNotes()
   {
     String e_cookingNotes = "";
     String e_avoidfoodNameArray = (getAvoidfoodNameArray() != null) && (!getAvoidfoodNameArray().isEmpty()) ? getAvoidfoodNameArray() : "";
     String e_tasteNameArray = (getTasteNameArray() != null) && (!getTasteNameArray().isEmpty()) ? " " + getTasteNameArray() : "";
     int e_pungentLevel = getPungentLevel();
     String e_pungentLevelDesc = " " + PungentLevelEnum.getDesc(String.valueOf(e_pungentLevel)) + " ";
     String e_notes = getNotes() == null ? "" : getNotes();
 
     e_cookingNotes = e_avoidfoodNameArray + e_tasteNameArray + e_pungentLevelDesc + e_notes;
     return e_cookingNotes;
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
 
   public String getUnitNumStr()
   {
     return BigDecimalUtil.formatFloat(Float.valueOf(this.unitNum));
   }
 
   public int getOrderNum()
   {
     return this.orderNum;
   }
 
   public void setOrderNum(int orderNum) {
     this.orderNum = orderNum;
   }
 
   public boolean getPriceDifferent()
   {
     return this.unitPrice.equals(this.realUnitPrice);
   }
 
   public String getBillNo()
   {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public String getCancelReasonId() {
     return this.cancelReasonId;
   }
 
   public void setCancelReasonId(String cancelReasonId) {
     this.cancelReasonId = cancelReasonId;
   }
 
   public Date getCancelTime() {
     return this.cancelTime;
   }
 
   public void setCancelTime(Date cancelTime) {
     this.cancelTime = cancelTime;
   }
 
   public DishesSetCategory getDishesSetCategory() {
     return this.dishesSetCategory;
   }
 
   public void setDishesSetCategory(DishesSetCategory dishesSetCategory) {
     this.dishesSetCategory = dishesSetCategory;
   }
 
   public Integer getDiscount() {
     return this.discount;
   }
 
   public void setDiscount(Integer discount) {
     this.discount = discount;
   }
 
   public String getDiscountType() {
     return this.discountType;
   }
 
   public void setDiscountType(String discountType) {
     this.discountType = discountType;
   }
 
   public Date getFixedTime() {
     return this.fixedTime;
   }
 
   public void setFixedTime(Date fixedTime) {
     this.fixedTime = fixedTime;
   }
 
   public int getFixedVersion() {
     return this.fixedVersion;
   }
 
   public void setFixedVersion(int fixedVersion) {
     this.fixedVersion = fixedVersion;
   }
 
   public String getIsOnsale() {
     return this.isOnsale;
   }
 
   public void setIsOnsale(String isOnsale) {
     this.isOnsale = isOnsale;
   }
 
   public String getIsRulingPrice() {
     return this.isRulingPrice;
   }
 
   public void setIsRulingPrice(String isRulingPrice) {
     this.isRulingPrice = isRulingPrice;
   }
 
   public String getIsUserDefined() {
     return this.isUserDefined;
   }
 
   public void setIsUserDefined(String isUserDefined) {
     this.isUserDefined = isUserDefined;
   }
 
   public Date getLastUrgeTime() {
     return this.lastUrgeTime;
   }
 
   public void setLastUrgeTime(Date lastUrgeTime) {
     this.lastUrgeTime = lastUrgeTime;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public Employee getOrderEmp() {
     return this.orderEmp;
   }
 
   public void setOrderEmp(Employee orderEmp) {
     this.orderEmp = orderEmp;
   }
 
   public Date getOrderTime() {
     return this.orderTime;
   }
 
   public void setOrderTime(Date orderTime) {
     this.orderTime = orderTime;
   }
 
   public BigDecimal getOriCost() {
     return this.oriCost;
   }
 
   public void setOriCost(BigDecimal oriCost) {
     this.oriCost = oriCost;
   }
 
   public int getPungentLevel() {
     return this.pungentLevel;
   }
 
   public void setPungentLevel(int pungentLevel) {
     this.pungentLevel = pungentLevel;
   }
 
   public BigDecimal getRealCost() {
     return this.realCost;
   }
 
   public void setRealCost(BigDecimal realCost) {
     this.realCost = realCost;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getTasteIdArray() {
     return this.tasteIdArray;
   }
 
   public void setTasteIdArray(String tasteIdArray) {
     this.tasteIdArray = tasteIdArray;
   }
 
   public float getUnitNum() {
     return this.unitNum;
   }
 
   public void setUnitNum(float unitNum) {
     this.unitNum = unitNum;
   }
 
   public BigDecimal getUnitPrice() {
     return this.unitPrice;
   }
 
   public void setUnitPrice(BigDecimal unitPrice) {
     this.unitPrice = unitPrice;
   }
 
   public int getUrgeNum() {
     return this.urgeNum;
   }
 
   public void setUrgeNum(int urgeNum) {
     this.urgeNum = urgeNum;
   }
 
   public DinerBill getDinerBill() {
     return this.dinerBill;
   }
 
   public void setDinerBill(DinerBill dinerBill) {
     this.dinerBill = dinerBill;
   }
 
   public BigDecimal getRealUnitPrice() {
     return this.realUnitPrice;
   }
 
   public String getUnitId() {
     return this.unitId;
   }
 
   public void setUnitId(String unitId)
   {
     this.unitId = unitId;
   }
 
   public String getUnitName()
   {
     return this.unitName;
   }
 
   public void setUnitName(String unitName)
   {
     this.unitName = unitName;
   }
 
   public void setRealUnitPrice(BigDecimal realUnitPrice) {
     this.realUnitPrice = realUnitPrice;
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
 
   public String getTasteNameArray() {
     return this.tasteNameArray;
   }
 
   public void setTasteNameArray(String tasteNameArray) {
     this.tasteNameArray = tasteNameArray;
   }
 
   public String getCancelReasonName() {
     return this.cancelReasonName;
   }
 
   public void setCancelReasonName(String cancelReasonName) {
     this.cancelReasonName = cancelReasonName;
   }
 
   public String getBdsId() {
     return this.bdsId;
   }
 
   public void setBdsId(String bdsId) {
     this.bdsId = bdsId;
   }
 
   public String getDsCode() {
     return this.dsCode;
   }
 
   public void setDsCode(String dsCode) {
     this.dsCode = dsCode;
   }
 
   public String getDsName() {
     return this.dsName;
   }
 
   public void setDsName(String dsName) {
     this.dsName = dsName;
   }
 
   public String getDsStatus() {
     return this.dsStatus;
   }
 
   public void setDsStatus(String dsStatus) {
     this.dsStatus = dsStatus;
   }
 
   public String getDsId() {
     return this.dsId;
   }
 
   public void setDsId(String dsId) {
     this.dsId = dsId;
   }
 
   public String getDsDishesDesc() {
     return this.dsDishesDesc;
   }
 
   public void setDsDishesDesc(String dsDishesDesc) {
     this.dsDishesDesc = dsDishesDesc;
   }
 
   public BigDecimal getServiceCommission() {
     return this.serviceCommission;
   }
 
   public void setServiceCommission(BigDecimal serviceCommission) {
     this.serviceCommission = serviceCommission;
   }
 
   public BigDecimal getSaleCommission() {
     return this.saleCommission;
   }
 
   public void setSaleCommission(BigDecimal saleCommission) {
     this.saleCommission = saleCommission;
   }
 }

