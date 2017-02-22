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
 @Table(name="cm_backup_setting")
 public class BackupSetting extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="baks_id", unique=true, nullable=false, length=36)
   private String baksId;
 
   @Column(name="backup_interval", length=32)
   private String backupInterval;
 
   @Column(name="is_open_backup", length=1)
   private String isOpenBackup;
 
   @Column(name="backup_hour", length=32)
   private Integer backupHour;
 
   @Column(name="backup_min", length=32)
   private Integer backupMin;
 
   @Column(name="backup_disk_address", length=1024)
   private String backupDiskAddress;
 
   @Column(name="is_open_flush", length=1)
   private String isOpenFlush;
 
   @Column(name="is_open_cloud_backup", length=1)
   private String isOpenCloudBackup;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   public String getIsOpenFlush()
   {
     return this.isOpenFlush;
   }
 
   public void setIsOpenFlush(String isOpenFlush) {
     this.isOpenFlush = isOpenFlush;
   }
 
   public String getBaksId()
   {
     return this.baksId;
   }
 
   public void setBaksId(String baksId) {
     this.baksId = baksId;
   }
 
   public String getBackupInterval() {
     return this.backupInterval;
   }
 
   public void setBackupInterval(String backupInterval) {
     this.backupInterval = backupInterval;
   }
 
   public String getIsOpenCloudBackup() {
     return this.isOpenCloudBackup;
   }
 
   public void setIsOpenCloudBackup(String isOpenCloudBackup) {
     this.isOpenCloudBackup = isOpenCloudBackup;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getIsOpenBackup() {
     return this.isOpenBackup;
   }
 
   public void setIsOpenBackup(String isOpenBackup) {
     this.isOpenBackup = isOpenBackup;
   }
 
   public Integer getBackupHour() {
     return this.backupHour;
   }
 
   public void setBackupHour(Integer backupHour) {
     this.backupHour = backupHour;
   }
 
   public Integer getBackupMin() {
     return this.backupMin;
   }
 
   public void setBackupMin(Integer backupMin) {
     this.backupMin = backupMin;
   }
 
   public String getBackupDiskAddress() {
     return this.backupDiskAddress;
   }
 
   public void setBackupDiskAddress(String backupDiskAddress) {
     this.backupDiskAddress = backupDiskAddress;
   }
 }

