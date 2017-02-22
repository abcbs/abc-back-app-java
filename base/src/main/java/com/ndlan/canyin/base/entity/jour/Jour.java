 package com.ndlan.canyin.base.entity.jour;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;

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
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @javax.persistence.Table(name="cm_jour")
 public class Jour extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="jour_id", unique=true, nullable=false, length=36)
   private String jourId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="jour_no", length=36)
   private String jourNo;
 
   @Column(name="internal_jour_no", length=36)
   private String internalJourNo;
 
   @Column(name="terminal_no", length=36)
   private String terminalNo;
 
   @Column(name="terminal_type", length=32)
   private String terminalType;
 
   @Column(name="terminal_ip", length=32)
   private String terminalIp;
 
   @Column(name="terminal_mac", length=256)
   private String terminalMac;
 
   @Column(name="bill_no", length=32)
   private String billNo;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="bill_id")
   private DinerBill bill;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="tab_id")
   private com.ndlan.canyin.base.entity.ctzh.Table table;
 
   @Column(name="operate_type", length=36)
   private String operateType;
 
   @Column(name="action_url", length=1024)
   private String actionUrl;
 
   @Column(name="jour_status", length=36)
   private String jourStatus;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="jour_time")
   private Date jourTime;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="operator")
   private Employee operator;
 
   @Column(name="note", length=256)
   private String note;
 
   @Column(name="return_message", length=256)
   private String returnMessage;
 
   @Column(name="return_no", length=256)
   private String returnNo;
 
   @Column(name="function_no", length=256)
   private String functionNo;
 
   public String getJourId()
   {
     return this.jourId;
   }
 
   public void setJourId(String jourId) {
     this.jourId = jourId;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getJourNo() {
     return this.jourNo;
   }
 
   public void setJourNo(String jourNo) {
     this.jourNo = jourNo;
   }
 
   public String getInternalJourNo() {
     return this.internalJourNo;
   }
 
   public void setInternalJourNo(String internalJourNo) {
     this.internalJourNo = internalJourNo;
   }
 
   public String getTerminalNo() {
     return this.terminalNo;
   }
 
   public void setTerminalNo(String terminalNo) {
     this.terminalNo = terminalNo;
   }
 
   public String getTerminalType() {
     return this.terminalType;
   }
 
   public void setTerminalType(String terminalType) {
     this.terminalType = terminalType;
   }
 
   public String getTerminalIp() {
     return this.terminalIp;
   }
 
   public void setTerminalIp(String terminalIp) {
     this.terminalIp = terminalIp;
   }
 
   public String getTerminalMac() {
     return this.terminalMac;
   }
 
   public void setTerminalMac(String terminalMac) {
     this.terminalMac = terminalMac;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public DinerBill getBill() {
     return this.bill;
   }
 
   public void setBill(DinerBill bill) {
     this.bill = bill;
   }
 
   public com.ndlan.canyin.base.entity.ctzh.Table getTable() {
     return this.table;
   }
 
   public void setTable(com.ndlan.canyin.base.entity.ctzh.Table table) {
     this.table = table;
   }
 
   public String getOperateType() {
     return this.operateType;
   }
 
   public void setOperateType(String operateType) {
     this.operateType = operateType;
   }
 
   public String getActionUrl() {
     return this.actionUrl;
   }
 
   public void setActionUrl(String actionUrl) {
     this.actionUrl = actionUrl;
   }
 
   public Employee getOperator() {
     return this.operator;
   }
 
   public void setOperator(Employee operator) {
     this.operator = operator;
   }
 
   public String getNote() {
     return this.note;
   }
 
   public void setNote(String note) {
     this.note = note;
   }
 
   public Date getJourTime() {
     return this.jourTime;
   }
 
   public void setJourTime(Date jourTime) {
     this.jourTime = jourTime;
   }
 
   public String getJourStatus() {
     return this.jourStatus;
   }
 
   public void setJourStatus(String jourStatus) {
     this.jourStatus = jourStatus;
   }
 
   public String getReturnMessage() {
     return this.returnMessage;
   }
 
   public void setReturnMessage(String returnMessage) {
     this.returnMessage = returnMessage;
   }
 
   public String getReturnNo() {
     return this.returnNo;
   }
 
   public void setReturnNo(String returnNo) {
     this.returnNo = returnNo;
   }
 
   public String getFunctionNo() {
     return this.functionNo;
   }
 
   public void setFunctionNo(String functionNo) {
     this.functionNo = functionNo;
   }
 }

