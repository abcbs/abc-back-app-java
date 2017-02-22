 package com.ndlan.canyin.base.entity.qtsy;
 
 import java.io.Serializable;
 import java.util.Date;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
 import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_message")
 public class Message
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="msg_id", unique=true, nullable=false, length=36)
   private String msgId;
 
   @Column(name="bill_id", nullable=false, length=36)
   private String billId;
 
   @Column(name="content", length=1024)
   private String content;
 
   @Column(name="create_by", length=36)
   private String createBy;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="create_time")
   private Date createTime;
 
   @Column(name="deal_by", length=36)
   private String dealBy;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="deal_time")
   private Date dealTime;
 
   @Column(name="msg_status", length=32)
   private String msgStatus;
 
   @Column(name="msg_type", length=32)
   private String msgType;
 
   @Column(name="order_id", nullable=false, length=36)
   private String orderId;
 
   @Column(name="read_by", length=36)
   private String readBy;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="read_time")
   private Date readTime;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="tab_id", length=36)
   private String tabId;
 
   @Column(name="tab_no", length=32)
   private String tabNo;
 
   @Column(name="update_by", length=36)
   private String updateBy;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="update_time")
   private Date updateTime;
   private int version;
 
   public String getMsgId()
   {
     return this.msgId;
   }
 
   public void setMsgId(String msgId) {
     this.msgId = msgId;
   }
 
   public String getBillId() {
     return this.billId;
   }
 
   public void setBillId(String billId) {
     this.billId = billId;
   }
 
   public String getContent() {
     return this.content;
   }
 
   public void setContent(String content) {
     this.content = content;
   }
 
   public String getCreateBy() {
     return this.createBy;
   }
 
   public void setCreateBy(String createBy) {
     this.createBy = createBy;
   }
 
   public Date getCreateTime() {
     return this.createTime;
   }
 
   public void setCreateTime(Date createTime) {
     this.createTime = createTime;
   }
 
   public String getDealBy() {
     return this.dealBy;
   }
 
   public void setDealBy(String dealBy) {
     this.dealBy = dealBy;
   }
 
   public Date getDealTime() {
     return this.dealTime;
   }
 
   public void setDealTime(Date dealTime) {
     this.dealTime = dealTime;
   }
 
   public String getMsgStatus() {
     return this.msgStatus;
   }
 
   public void setMsgStatus(String msgStatus) {
     this.msgStatus = msgStatus;
   }
 
   public String getMsgType() {
     return this.msgType;
   }
 
   public void setMsgType(String msgType) {
     this.msgType = msgType;
   }
 
   public String getOrderId() {
     return this.orderId;
   }
 
   public void setOrderId(String orderId) {
     this.orderId = orderId;
   }
 
   public String getReadBy() {
     return this.readBy;
   }
 
   public void setReadBy(String readBy) {
     this.readBy = readBy;
   }
 
   public Date getReadTime() {
     return this.readTime;
   }
 
   public void setReadTime(Date readTime) {
     this.readTime = readTime;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getTabId() {
     return this.tabId;
   }
 
   public void setTabId(String tabId) {
     this.tabId = tabId;
   }
 
   public String getTabNo() {
     return this.tabNo;
   }
 
   public void setTabNo(String tabNo) {
     this.tabNo = tabNo;
   }
 
   public String getUpdateBy() {
     return this.updateBy;
   }
 
   public void setUpdateBy(String updateBy) {
     this.updateBy = updateBy;
   }
 
   public Date getUpdateTime() {
     return this.updateTime;
   }
 
   public void setUpdateTime(Date updateTime) {
     this.updateTime = updateTime;
   }
 
   public int getVersion() {
     return this.version;
   }
 
   public void setVersion(int version) {
     this.version = version;
   }
 }

