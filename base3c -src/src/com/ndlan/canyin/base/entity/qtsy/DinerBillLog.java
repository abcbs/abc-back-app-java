 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
 import com.ndlan.canyin.core.common.BillOpTypeEnum;
 import java.io.Serializable;
 import java.util.Date;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
 import javax.persistence.Transient;
 import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @javax.persistence.Table(name="cm_diner_bill_log")
 public class DinerBillLog extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="dbl_id", unique=true, nullable=false, length=36)
   private String dblId;
 
   @Column(name="bill_no", length=32)
   private String billNo;
 
   @Column(name="bill_op_type", length=32)
   private String billOpType;
 
   @Column(name="dishes_ids", length=1024)
   private String dishesIds;
 
   @Column(name="dishes_names", length=1024)
   private String dishesNames;
 
   @Column(name="notes", length=1024)
   private String notes;
 
   @Column(name="relation_tab_no", length=64)
   private String relationTabNo;
 
   @Column(name="relation_tab_id", length=36)
   private String relationTabId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="tab_no", length=64)
   private String tabNo;
 
   @Column(name="online_id", length=36)
   private String onlineId;
 
   @Column(name="online_no", length=64)
   private String onlineNo;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="pay_time")
   private Date payTime;
   
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="tab_id")
   private com.ndlan.canyin.base.entity.ctzh.Table table;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="bill_id")
   private DinerBill dinerBill;
 
   @Transient
   private String billOpTypeDesc;
 
   public String getDblId()
   {
     return this.dblId;
   }
 
   public void setDblId(String dblId) {
     this.dblId = dblId;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public String getBillOpType() {
     return this.billOpType;
   }
 
   public void setBillOpType(String billOpType) {
     this.billOpType = billOpType;
   }
 
   public String getDishesNames()
   {
     return this.dishesNames;
   }
 
   public void setDishesNames(String dishesNames) {
     this.dishesNames = dishesNames;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public String getRelationTabNo() {
     return this.relationTabNo;
   }
 
   public void setRelationTabNo(String relationTabNo) {
     this.relationTabNo = relationTabNo;
   }
 
   public String getRelationTabId() {
     return this.relationTabId;
   }
 
   public void setRelationTabId(String relationTabId) {
     this.relationTabId = relationTabId;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getTabNo()
   {
     return this.tabNo;
   }
 
   public void setTabNo(String tabNo) {
     this.tabNo = tabNo;
   }
 
   public DinerBill getDinerBill() {
     return this.dinerBill;
   }
 
   public void setDinerBill(DinerBill dinerBill) {
     this.dinerBill = dinerBill;
   }
 
   public com.ndlan.canyin.base.entity.ctzh.Table getTable() {
     return this.table;
   }
 
   public void setTable(com.ndlan.canyin.base.entity.ctzh.Table table) {
     this.table = table;
   }
 
   public String getDishesIds() {
     return this.dishesIds;
   }
 
   public void setDishesIds(String dishesIds) {
     this.dishesIds = dishesIds;
   }
 
   public Date getPayTime() {
     return this.payTime;
   }
 
   public void setPayTime(Date payTime) {
     this.payTime = payTime;
   }
 
   public void setBillOpTypeDesc(String billOpTypeDesc) {
     this.billOpTypeDesc = billOpTypeDesc;
   }
 
   public String getBillOpTypeDesc() {
     if (StringUtils.isEmpty(this.billOpTypeDesc)) {
       this.billOpTypeDesc = BillOpTypeEnum.getDesc(getBillOpType());
     }
     return this.billOpTypeDesc;
   }
 
   public String getOnlineId() {
     return this.onlineId;
   }
 
   public void setOnlineId(String onlineId) {
     this.onlineId = onlineId;
   }
 
   public String getOnlineNo() {
     return this.onlineNo;
   }
 
   public void setOnlineNo(String onlineNo) {
     this.onlineNo = onlineNo;
   }
 }

