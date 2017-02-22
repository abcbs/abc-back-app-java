 package com.ndlan.canyin.base.entity.xtgl;
 
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
 @Table(name="cm_sys_version_setting")
 public class SysVersionSetting
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="svs_id", unique=true, nullable=false, length=36)
   private String svsId;
 
   @Column(name="auto_update", length=1)
   private String autoUpdate;
 
   @Column(name="create_by", length=36)
   private String createBy;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="create_time")
   private Date createTime;
 
   @Column(name="curr_version_id")
   private int currVersionId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="update_by", length=36)
   private String updateBy;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="update_time")
   private Date updateTime;
   private int version;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="version_update_time")
   private Date versionUpdateTime;
 
   public String getSvsId()
   {
     return this.svsId;
   }
 
   public void setSvsId(String svsId) {
     this.svsId = svsId;
   }
 
   public String getAutoUpdate() {
     return this.autoUpdate;
   }
 
   public void setAutoUpdate(String autoUpdate) {
     this.autoUpdate = autoUpdate;
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
 
   public int getCurrVersionId() {
     return this.currVersionId;
   }
 
   public void setCurrVersionId(int currVersionId) {
     this.currVersionId = currVersionId;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
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
 
   public Date getVersionUpdateTime() {
     return this.versionUpdateTime;
   }
 
   public void setVersionUpdateTime(Date versionUpdateTime) {
     this.versionUpdateTime = versionUpdateTime;
   }
 }

