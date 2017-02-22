 package com.ndlan.canyin.base.entity.cygl;
 
 import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.cygl.Dishe;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.UnitTypeEnum;
 import java.io.Serializable;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_dishes_unit")
 public class DishesUnit extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="unit_id", unique=true, nullable=false, length=36)
   private String unitId;
 
   @Column(name="code", length=64)
   private String code;
 
   @Column(name="name", length=128)
   private String name;
 
   @Column(name="foreign_name", length=128)
   private String foreignName;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="show_seq")
   private int showSeq;
 
   @Column(name="unit_type")
   private String unitType;
 
   @Column(name="sysdata_type", length=32)
   private String sysdataType;
 
   @Column(name="enable_status", length=32)
   private String enableStatus;
 
   @OneToMany(mappedBy="dishesUnit")
   private List<Dishe> dishes;
 
   public String getUnitTypeDesc()
   {
     return UnitTypeEnum.getDesc(this.unitType);
   }
 
   public String getEnableStatusDesc()
   {
     return EnableStatusEnum.getDesc(this.enableStatus);
   }
 
   public String getUnitId()
   {
     return this.unitId;
   }
 
   public void setUnitId(String unitId) {
     this.unitId = unitId;
   }
 
   public String getCode() {
     return this.code;
   }
 
   public void setCode(String code) {
     this.code = code;
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
 
   public int getShowSeq() {
     return this.showSeq;
   }
 
   public void setShowSeq(int showSeq) {
     this.showSeq = showSeq;
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
 
   public String getForeignName() {
     return this.foreignName;
   }
 
   public void setForeignName(String foreignName) {
     this.foreignName = foreignName;
   }
 
   public String getUnitType() {
     return this.unitType;
   }
 
   public void setUnitType(String unitType) {
     this.unitType = unitType;
   }
 }

