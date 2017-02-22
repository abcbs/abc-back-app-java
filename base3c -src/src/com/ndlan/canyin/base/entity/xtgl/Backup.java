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
 @Table(name="cm_backup")
 public class Backup extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="bak_id", unique=true, nullable=false, length=36)
   private String bakId;
 
   @Column(name="bak_name", length=128)
   private String bakName;
 
   @Column(name="bak_path", length=256)
   private String bakPath;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="bak_time")
   private Date bakTime;
 
   @Column(name="bak_version", length=32)
   private String bakVersion;
 
   @Column(name="notes", length=1024)
   private String notes;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   public String getBakId()
   {
     return this.bakId;
   }
 
   public void setBakId(String bakId) {
     this.bakId = bakId;
   }
 
   public String getBakName() {
     return this.bakName;
   }
 
   public void setBakName(String bakName) {
     this.bakName = bakName;
   }
 
   public String getBakPath() {
     return this.bakPath;
   }
 
   public void setBakPath(String bakPath) {
     this.bakPath = bakPath;
   }
 
   public Date getBakTime() {
     return this.bakTime;
   }
 
   public void setBakTime(Date bakTime) {
     this.bakTime = bakTime;
   }
 
   public String getBakVersion() {
     return this.bakVersion;
   }
 
   public void setBakVersion(String bakVersion) {
     this.bakVersion = bakVersion;
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
 }

