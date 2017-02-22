 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.SelfOrder;

 import java.io.Serializable;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.Table;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_self_message")
 public class SelfMessage extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="mes_id", unique=true, nullable=false, length=36)
   private String id;
 
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="order_id")
   @JsonIgnore
   private SelfOrder selfOrder;
 
   @Column(name="rest_id")
   private String restId;
 
   @Column(name="title", length=255)
   private String title;
 
   @Column(name="content", length=255)
   private String content;
 
   @Column(name="tab_no", length=36)
   private String tabNo;
 
   @Column(name="status", length=36)
   private String status;
 
   @Column(name="usered_hanle_status", length=36)
   private String useredHanleStatus;
 
   @Column(name="classify", length=36)
   private String classify;
 
   @Column(name="mes_type", length=36)
   private String mesType;
 
   @Column(name="serial_no", length=36)
   private String serialNo;
 
   @Column(name="bill_id", length=36)
   private String billId;
 
   @Column(name="waiter_id", length=36)
   private String waiterId;
 
   @Column(name="waiter_name", length=255)
   private String waiterName;
 
   @Transient
   @JsonIgnore
   private List<String> dishIdList;
 
   @Transient
   @JsonIgnore
   private String memberName;
 
   @Column(name="dish_ids", length=1024)
   private String dishIds;
 
   public SelfMessage()
   {
   }
 
   public SelfMessage(SelfOrder selfOrder, String restId, String content, String tabNo, String status, String mesType)
   {
     this.restId = restId;
     this.content = content;
     this.tabNo = tabNo;
     this.status = status;
     this.mesType = mesType;
     this.selfOrder = selfOrder;
   }
 
   public String getId()
   {
     return this.id;
   }
 
   public void setId(String id) {
     this.id = id;
   }
 
   public String getTitle() {
     return this.title;
   }
 
   public void setTitle(String title) {
     this.title = title;
   }
 
   public String getContent() {
     return this.content;
   }
 
   public void setContent(String content) {
     this.content = content;
   }
 
   public String getTabNo() {
     return this.tabNo;
   }
 
   public void setTabNo(String tabNo) {
     this.tabNo = tabNo;
   }
 
   public String getStatus() {
     return this.status;
   }
 
   public void setStatus(String status) {
     this.status = status;
   }
 
   public String getMesType() {
     return this.mesType;
   }
 
   public void setMesType(String mesType) {
     this.mesType = mesType;
   }
 
   public String getSerialNo() {
     return this.serialNo;
   }
 
   public void setSerialNo(String serialNo) {
     this.serialNo = serialNo;
   }
 
   public SelfOrder getSelfOrder() {
     return this.selfOrder;
   }
 
   public void setSelfOrder(SelfOrder selfOrder) {
     this.selfOrder = selfOrder;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getBillId() {
     return this.billId;
   }
 
   public void setBillId(String billId) {
     this.billId = billId;
   }
 
   public List<String> getDishIdList() {
     return this.dishIdList;
   }
 
   public void setDishIdList(List<String> dishIdList) {
     this.dishIdList = dishIdList;
   }
 
   public String getDishIds() {
     return this.dishIds;
   }
 
   public void setDishIds(String dishIds) {
     this.dishIds = dishIds;
   }
 
   public String getClassify() {
     return this.classify;
   }
 
   public void setClassify(String classify) {
     this.classify = classify;
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
 
   public String getUseredHanleStatus() {
     return this.useredHanleStatus;
   }
 
   public void setUseredHanleStatus(String useredHanleStatus) {
     this.useredHanleStatus = useredHanleStatus;
   }
 
   public String getMemberName() {
     return this.memberName;
   }
 
   public void setMemberName(String memberName) {
     this.memberName = memberName;
   }
 }

