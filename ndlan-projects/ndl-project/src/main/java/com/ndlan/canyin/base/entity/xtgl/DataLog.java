 package com.ndlan.canyin.base.entity.xtgl;
 
 import com.ndlan.canyin.base.entity.BaseEntity;
 import com.ndlan.canyin.core.common.DataLogActEnum;
 import com.ndlan.canyin.core.common.DataLogTypeEnum;
 import java.io.Serializable;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_data_log")
 public class DataLog extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="log_id", unique=true, nullable=false, length=36)
   private String logId;
 
   @Column(name="data_log_type", length=32)
   private String dataLogType;
 
   @Transient
   private String dataLogTypeName;
 
   @Column(name="operate_act", length=32)
   private String operateAct;
 
   @Transient
   private String operateActName;
 
   @Column(name="operate_attr", length=32)
   private String operateAttr;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="notes", length=1024)
   private String notes;
 
   public String getDataLogTypeName()
   {
     return DataLogTypeEnum.getDesc(getDataLogType());
   }
 
   public void setDataLogTypeName(String dataLogTypeName) {
     this.dataLogTypeName = dataLogTypeName;
   }
 
   public String getOperateActName()
   {
     return DataLogActEnum.getDesc(getOperateAct());
   }
 
   public void setOperateActName(String operateActName) {
     this.operateActName = operateActName;
   }
 
   public String getRestId()
   {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getReplaceNotes()
   {
     String ns = this.notes == null ? "" : this.notes.replaceAll("<em class=\"red-star\">*</em>", "");
     ns = ns.replaceAll("<em class=\"red-star\">&nbsp;</em>", "");
     return ns;
   }
 
   public String getLogId()
   {
     return this.logId;
   }
 
   public void setLogId(String logId) {
     this.logId = logId;
   }
 
   public String getDataLogType() {
     return this.dataLogType;
   }
 
   public void setDataLogType(String dataLogType) {
     this.dataLogType = dataLogType;
   }
 
   public String getOperateAct() {
     return this.operateAct;
   }
 
   public void setOperateAct(String operateAct) {
     this.operateAct = operateAct;
   }
 
   public String getOperateAttr() {
     return this.operateAttr;
   }
 
   public void setOperateAttr(String operateAttr) {
     this.operateAttr = operateAttr;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 }

