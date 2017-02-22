 package com.ndlan.canyin.base.entity.ylgl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.ylgl.RawMaterial;

 import java.io.Serializable;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_dishes_raw")
 @JsonIgnoreProperties(ignoreUnknown=true)
 public class DishesRaw extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="dr_id", unique=true, nullable=false, length=36)
   private String drId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="dishes_id")
   @JsonIgnore
   private Dishe dishe;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="rm_id")
   @JsonIgnore
   private RawMaterial rawMaterial;
 
   @Column(name="use_count")
   private Float useCount;
 
   public Float getUseCount()
   {
     return this.useCount;
   }
 
   public void setUseCount(Float useCount) {
     this.useCount = useCount;
   }
 
   public String getDrId() {
     return this.drId;
   }
 
   public void setDrId(String drId) {
     this.drId = drId;
   }
 
   public Dishe getDishe() {
     return this.dishe;
   }
 
   public void setDishe(Dishe dishe) {
     this.dishe = dishe;
   }
 
   public RawMaterial getRawMaterial() {
     return this.rawMaterial;
   }
 
   public void setRawMaterial(RawMaterial rawMaterial) {
     this.rawMaterial = rawMaterial;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 }

