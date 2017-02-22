 package com.ndlan.canyin.base.entity.sygl;
 
 import com.ndlan.canyin.base.entity.BaseEntity;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.SpecialReasonTypeEnum;
 import java.io.Serializable;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_spe_op_reason")
 public class SpeOpReason extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="rea_id", unique=true, nullable=false, length=36)
   private String reaId;
 
   @Column(name="name", length=128)
   private String name;
 
   @Column(name="notes", length=1024)
   private String notes;
 
   @Column(name="rea_type", length=32)
   private String reaType;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="sysdata_type", length=32)
   private String sysdataType;
 
   @Column(name="enable_status", length=32)
   private String enableStatus;
 
   @Transient
   private String reaTypeName;
 
   public String getEnableStatusName()
   {
     return EnableStatusEnum.getDesc(this.enableStatus);
   }
 
   public String getReaId()
   {
     return this.reaId;
   }
 
   public void setReaId(String reaId) {
     this.reaId = reaId;
   }
 
   public String getName()
   {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public String getReaType() {
     return this.reaType;
   }
 
   public void setReaType(String reaType) {
     this.reaType = reaType;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getSysdataType() {
     return this.sysdataType;
   }
 
   public void setSysdataType(String sysdataType) {
     this.sysdataType = sysdataType;
   }
 
   public String getEnableStatus() {
     return this.enableStatus;
   }
 
   public void setEnableStatus(String enableStatus) {
     this.enableStatus = enableStatus;
   }
 
   public String getReaTypeName() {
     this.reaTypeName = SpecialReasonTypeEnum.getDesc(this.reaType);
     return this.reaTypeName;
   }
 
   public void setReaTypeName(String reaTypeName) {
     this.reaTypeName = reaTypeName;
   }
 
   public String toString()
   {
     return "SpeOpReason [reaId=" + this.reaId + ", name=" + this.name + ", notes=" + 
       this.notes + ", reaType=" + this.reaType + ", restId=" + this.restId + 
       ", sysdataType=" + this.sysdataType + ", enableStatus=" + 
       this.enableStatus + ", reaTypeName=" + this.reaTypeName + "]";
   }
 }

