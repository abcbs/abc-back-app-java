 package com.ndlan.canyin.base.entity.xtgl;
 
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
 @Table(name="cm_sys_version")
 public class SysVersion extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="ver_id", unique=true, nullable=false, length=36)
   private String verId;
 
   @Column(name="name", length=128)
   private String name;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="ver_desc", length=1024)
   private String verDesc;
 
   @Column(name="ver_number", length=32)
   private String verNumber;
 
   @Column(name="run_data_sql_version")
   private Integer runDataSqlVersion;
 
   @Column(name="run_table_sql_version")
   private Integer runTableSqlVersion;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="ver_time")
   private Date verTime;
 
   public String getVerId()
   {
     return this.verId;
   }
 
   public void setVerId(String verId) {
     this.verId = verId;
   }
 
   public String getName() {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getVerDesc() {
     return this.verDesc;
   }
 
   public void setVerDesc(String verDesc) {
     this.verDesc = verDesc;
   }
 
   public String getVerNumber() {
     return this.verNumber;
   }
 
   public void setVerNumber(String verNumber) {
     this.verNumber = verNumber;
   }
 
   public Date getVerTime() {
     return this.verTime;
   }
 
   public void setVerTime(Date verTime) {
     this.verTime = verTime;
   }
 
   public Integer getRunDataSqlVersion() {
     return this.runDataSqlVersion;
   }
 
   public void setRunDataSqlVersion(Integer runDataSqlVersion) {
     this.runDataSqlVersion = runDataSqlVersion;
   }
 
   public Integer getRunTableSqlVersion() {
     return this.runTableSqlVersion;
   }
 
   public void setRunTableSqlVersion(Integer runTableSqlVersion) {
     this.runTableSqlVersion = runTableSqlVersion;
   }
 }

