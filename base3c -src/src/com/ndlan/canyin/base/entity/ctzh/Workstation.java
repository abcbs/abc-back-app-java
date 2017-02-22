 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.SessionUtil;

 import java.io.Serializable;
 import java.util.Date;
 import java.util.HashMap;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_workstation")
 public class Workstation extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="ws_id", unique=true, nullable=false, length=36)
   private String wsId;
 
   @Column(name="ip", length=128)
   private String ip;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="last_time")
   private Date lastTime;
 
   @Column(name="mac", length=128)
   private String mac;
 
   @Column(name="notes", length=2048)
   private String notes;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="status", length=32)
   private String status;
 
   @Column(name="ws_name", length=32)
   private String wsName;
 
   @Transient
   private String isOnline;
 
   public String getIsOnline()
   {
     if (SessionUtil.sessionMap.get(getIp()) != null) {
       return "在线";
     }
     return "离线";
   }
 
   public void setIsOnline(String isOnline)
   {
     this.isOnline = isOnline;
   }
 
   public String getWsId()
   {
     return this.wsId;
   }
 
   public void setWsId(String wsId) {
     this.wsId = wsId;
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
 
   public String getWsName()
   {
     return this.wsName;
   }
 
   public void setWsName(String wsName) {
     this.wsName = wsName;
   }
 }

