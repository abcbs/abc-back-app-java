 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;

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
 @Table(name="cm_mobile_terminal")
 @JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
 public class MobileTerminal extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="mt_id", unique=true, nullable=false, length=36)
   private String mtId;
 
   @Column(name="ip", length=128)
   private String ip;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="last_time")
   private Date lastTime;
 
   @Column(name="mac", length=128)
   private String mac;
 
   @Column(name="mt_name", length=32)
   private String mtName;
 
   @Column(name="notes", length=2048)
   private String notes;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="status", length=32)
   private String status;
 
   @Column(name="terminal_div", length=36)
   private String terminalDiv;
 
   public String getMtId()
   {
     return this.mtId;
   }
 
   public void setMtId(String mtId) {
     this.mtId = mtId;
   }
 
   public String getIp() {
     return this.ip;
   }
 
   public void setIp(String ip) {
     this.ip = ip;
   }
 
   public Date getLastTime() {
     return this.lastTime;
   }
 
   public void setLastTime(Date lastTime) {
     this.lastTime = lastTime;
   }
 
   public String getMac() {
     return this.mac;
   }
 
   public void setMac(String mac) {
     this.mac = mac;
   }
 
   public String getMtName() {
     return this.mtName;
   }
 
   public void setMtName(String mtName) {
     this.mtName = mtName;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getStatus() {
     return this.status;
   }
 
   public void setStatus(String status) {
     this.status = status;
   }
 
   public String getTerminalDiv() {
     return this.terminalDiv;
   }
 
   public void setTerminalDiv(String terminalDiv) {
     this.terminalDiv = terminalDiv;
   }
 }

