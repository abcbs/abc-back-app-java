 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
import com.ndlan.canyin.base.entity.ctzh.Table;

 import java.io.Serializable;
 import java.util.Date;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.OneToOne;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @javax.persistence.Table(name="cm_table_bill_relation")
 public class TableBillRelation extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="tab_bill_id", unique=true, nullable=false, length=36)
   private String tabBillId;
 
   @JsonIgnore
   @OneToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="tab_id")
   private Table table;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @JsonIgnore
   @OneToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="bill_id")
   private DinerBill dinerBill;
 
   @JsonIgnore
   @OneToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="order_id")
   private TableOrder tableOrder;
 
   @Column(name="people_num")
   private Integer peopleNum;
 
   @Column(name="bill_status", length=32)
   private String billStatus;
 
   @Column(name="tab_bill_type", length=32)
   private String tabBillType;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="bill_time")
   private Date billTime;
 
   public String getTabBillId()
   {
     return this.tabBillId;
   }
 
   public void setTabBillId(String tabBillId) {
     this.tabBillId = tabBillId;
   }
 
   public Table getTable() {
     return this.table;
   }
 
   public void setTable(Table table) {
     this.table = table;
   }
 
   public DinerBill getDinerBill() {
     return this.dinerBill;
   }
 
   public void setDinerBill(DinerBill dinerBill) {
     this.dinerBill = dinerBill;
   }
 
   public String getBillStatus() {
     return this.billStatus;
   }
 
   public void setBillStatus(String billStatus) {
     this.billStatus = billStatus;
   }
 
   public Date getBillTime() {
     return this.billTime;
   }
 
   public void setBillTime(Date billTime) {
     this.billTime = billTime;
   }
 
   public Integer getPeopleNum() {
     return this.peopleNum;
   }
 
   public void setPeopleNum(Integer peopleNum) {
     this.peopleNum = peopleNum;
   }
 
   public TableOrder getTableOrder() {
     return this.tableOrder;
   }
 
   public void setTableOrder(TableOrder tableOrder) {
     this.tableOrder = tableOrder;
   }
 
   public String getTabBillType() {
     return this.tabBillType;
   }
 
   public void setTabBillType(String tabBillType) {
     this.tabBillType = tabBillType;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 }

