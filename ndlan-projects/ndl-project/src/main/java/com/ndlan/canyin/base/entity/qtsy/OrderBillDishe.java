 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
 import com.google.common.collect.ImmutableList;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
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
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
 import javax.persistence.Transient;
 import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_order_bill_dishes")
 public class OrderBillDishe extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="bd_id", unique=true, nullable=false, length=36)
   private String bdId;
 
   @Column(name="discount")
   private int discount;
 
   @Column(name="discount_type", length=32)
   private String discountType;
 
   @Column(name="dishes_name", length=128)
   private String dishesName;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="order_time")
   private Date orderTime;
 
   @Column(name="unit_id")
   private String unitId;
 
   @Column(name="unit_name")
   private String unitName;
 
   @Column(name="ori_cost")
   private BigDecimal oriCost;
 
   @Column(name="real_cost")
   private BigDecimal realCost;
 
   @Column(name="real_unit_price")
   private BigDecimal realUnitPrice;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="taste_id", length=36)
   private String tasteId;
 
   @Column(name="taste_name", length=64)
   private String tasteName;
 
   @Column(name="cda_id_array", length=1024)
   private String cdaIdArray;
 
   @Column(name="unit_num")
   private float unitNum;
 
   @Column(name="unit_price")
   private BigDecimal unitPrice;
 
   @Column(name="is_user_defined", length=1)
   private String isUserDefined;
 
   @Column(name="is_ruling_price", length=1)
   private String isRulingPrice;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="dishes_id")
   private Dishe dishe;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="order_id")
   private TableOrder tableOrder;
 
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
 
   @Transient
   private String isSet;
 
   @Transient
   private String dsId;
 
   public boolean getPriceDifferent()
   {
     return this.unitPrice.compareTo(this.realUnitPrice) == 0;
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
 
   public String getBdId()
   {
     return this.bdId;
   }
 
   public void setBdId(String bdId) {
     this.bdId = bdId;
   }
 
   public int getDiscount() {
     return this.discount;
   }
 
   public void setDiscount(int discount) {
     this.discount = discount;
   }
 
   public String getDiscountType() {
     return this.discountType;
   }
 
   public void setDiscountType(String discountType) {
     this.discountType = discountType;
   }
 
   public String getDishesName() {
     return this.dishesName;
   }
 
   public void setDishesName(String dishesName) {
     this.dishesName = dishesName;
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
 
   public String getTasteId() {
     return this.tasteId;
   }
 
   public void setTasteId(String tasteId) {
     this.tasteId = tasteId;
   }
 
   public String getTasteName() {
     return this.tasteName;
   }
 
   public void setTasteName(String tasteName) {
     this.tasteName = tasteName;
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
 
   public Dishe getDishe() {
     return this.dishe;
   }
 
   public void setDishe(Dishe dishe) {
     this.dishe = dishe;
   }
 
   public String getCdaIdArray() {
     return this.cdaIdArray;
   }
 
   public void setCdaIdArray(String cdaIdArray) {
     this.cdaIdArray = cdaIdArray;
   }
 
   public TableOrder getTableOrder() {
     return this.tableOrder;
   }
 
   public void setTableOrder(TableOrder tableOrder) {
     this.tableOrder = tableOrder;
   }
 
   public String getIsUserDefined() {
     return this.isUserDefined;
   }
 
   public void setIsUserDefined(String isUserDefined) {
     this.isUserDefined = isUserDefined;
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
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public String getIsRulingPrice() {
     return this.isRulingPrice;
   }
 
   public void setIsRulingPrice(String isRulingPrice) {
     this.isRulingPrice = isRulingPrice;
   }
 
   public BigDecimal getRealUnitPrice() {
     return this.realUnitPrice;
   }
 
   public void setRealUnitPrice(BigDecimal realUnitPrice) {
     this.realUnitPrice = realUnitPrice;
   }
 
   public String getUnitId() {
     return this.unitId;
   }
 
   public void setUnitId(String unitId) {
     this.unitId = unitId;
   }
 
   public String getUnitName() {
     return this.unitName;
   }
 
   public void setUnitName(String unitName) {
     this.unitName = unitName;
   }
 
   public String getIsSet() {
     return this.isSet;
   }
 
   public void setIsSet(String isSet) {
     this.isSet = isSet;
   }
 
   public String getDsId() {
     return this.dsId;
   }
 
   public void setDsId(String dsId) {
     this.dsId = dsId;
   }
 }

