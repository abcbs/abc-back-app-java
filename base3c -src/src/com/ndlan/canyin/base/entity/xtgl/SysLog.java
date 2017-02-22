 package com.ndlan.canyin.base.entity.xtgl;
 
 import com.ndlan.canyin.base.entity.BaseEntity;

 import java.io.Serializable;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_sys_log")
 public class SysLog extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="sl_id", unique=true, nullable=false, length=36)
   private String slId;
 
   @Column(name="ip", length=64)
   private String ip;
 
   @Column(name="mac", length=64)
   private String mac;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="sys_log_type", length=32)
   private String sysLogType;
 
   @Column(name="notes", length=1024)
   private String notes;
 
   public String getSlId()
   {
     return this.slId;
   }
 
   public void setSlId(String slId) {
     this.slId = slId;
   }
 
   public String getIp() {
     return this.ip;
   }
 
   public void setIp(String ip) {
     this.ip = ip;
   }
 
   public String getMac()
   {
     return this.mac;
   }
 
   public void setMac(String mac) {
     this.mac = mac;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getSysLogType() {
     return this.sysLogType;
   }
 
   public void setSysLogType(String sysLogType) {
     this.sysLogType = sysLogType;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 }

