 package com.ndlan.canyin.base.entity.ylgl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ylgl.Storage;

 import java.io.PrintStream;
 import java.io.Serializable;
 import java.math.BigDecimal;
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
 @Table(name="cm_storage_details")
 @JsonIgnoreProperties(ignoreUnknown=true)
 public class StorageDetails extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="sd_id", unique=true, nullable=false, length=36)
   private String sdId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="storage_id")
   @JsonIgnore
   private Storage storage;
 
   @Column(name="storage_count")
   private Float storageCount;
 
   @Column(name="unit_name", length=128)
   private String unitName;
 
   @Column(name="material_name", length=128)
   private String materialName;
 
   @Column(name="material_code", length=128)
   private String materialCode;
 
   @Column(name="price")
   private BigDecimal price;
 
   @Column(name="rm_id", length=36)
   private String rmId;
 
   @Column(name="store_name", length=128)
   private String storeName;
 
   @Column(name="before_storage_count")
   private Float beforeStorageCount;
 
   @Column(name="package_capacity", length=36)
   private String packageCapacity;
 
   public String getPackageCapacity()
   {
     return this.packageCapacity;
   }
 
   public void setPackageCapacity(String packageCapacity)
   {
     this.packageCapacity = packageCapacity;
   }
 
   public BigDecimal getMaterialPrice()
   {
     BigDecimal priceTotal = BigDecimal.ZERO;
     priceTotal = this.price.multiply(new BigDecimal(this.storageCount));
     return priceTotal.setScale(2, 6);
   }
 
   public Float getBeforeStorageCount()
   {
     return this.beforeStorageCount;
   }
 
   public void setBeforeStorageCount(Float beforeStorageCount)
   {
     this.beforeStorageCount = beforeStorageCount;
   }
 
   public static void main(String[] args)
   {
     BigDecimal price = new BigDecimal(15.0D);
     float count = 2.2F;
     BigDecimal aa = price.multiply(BigDecimal.valueOf(count * 1000.0F)).divide(BigDecimal.valueOf(1000L));
     BigDecimal bb = price.multiply(new BigDecimal(count));
   }
 
   public String getMaterialCode()
   {
     return this.materialCode;
   }
 
   public void setMaterialCode(String materialCode) {
     this.materialCode = materialCode;
   }
 
   public String getMaterialName() {
     return this.materialName;
   }
 
   public void setMaterialName(String materialName) {
     this.materialName = materialName;
   }
 
   public String getUnitName() {
     return this.unitName;
   }
 
   public void setUnitName(String unitName) {
     this.unitName = unitName;
   }
 
   public BigDecimal getPrice() {
     return this.price;
   }
 
   public void setPrice(BigDecimal price) {
     this.price = price;
   }
 
   public String getRmId() {
     return this.rmId;
   }
 
   public void setRmId(String rmId) {
     this.rmId = rmId;
   }
 
   public String getStoreName() {
     return this.storeName;
   }
 
   public void setStoreName(String storeName) {
     this.storeName = storeName;
   }
 
   public Float getStorageCount() {
     return this.storageCount;
   }
   public void setStorageCount(Float storageCount) {
     this.storageCount = storageCount;
   }
   public String getSdId() {
     return this.sdId;
   }
   public void setSdId(String sdId) {
     this.sdId = sdId;
   }
   public Storage getStorage() {
     return this.storage;
   }
   public void setStorage(Storage storage) {
     this.storage = storage;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String toString()
   {
     return "StorageDetails [sdId=" + this.sdId + ", storage=" + this.storage + 
       ", storageCount=" + this.storageCount + ", unitName=" + this.unitName + 
       ", materialName=" + this.materialName + ", materialCode=" + 
       this.materialCode + ", price=" + this.price + ", rmId=" + this.rmId + 
       ", storeName=" + this.storeName + "]";
   }
 }

