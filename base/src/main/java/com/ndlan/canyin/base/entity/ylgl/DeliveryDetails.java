 package com.ndlan.canyin.base.entity.ylgl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ylgl.Delivery;

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
 @Table(name="cm_delivery_details")
 @JsonIgnoreProperties(ignoreUnknown=true)
 public class DeliveryDetails extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="dd_id", unique=true, nullable=false, length=36)
   private String ddId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="rm_id", length=36)
   private String rmId;
 
   @Column(name="material_name", length=128)
   private String materialName;
 
   @Column(name="material_code", length=128)
   private String materialCode;
 
   @Column(name="unit_name", length=255)
   private String unitName;
 
   @Column(name="price")
   private BigDecimal price;
 
   @Column(name="store_name", length=128)
   private String storeName;
 
   @Column(name="delivery_count")
   private Float deliveryCount;
 
   @Column(name="before_delivery_count")
   private Float beforeDeliveryCount;
 
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="delivery_id")
   @JsonIgnore
   private Delivery delivery;
 
   public BigDecimal getMaterialPrice()
   {
     BigDecimal priceTotal = BigDecimal.ZERO;
     priceTotal = this.price.multiply(new BigDecimal(this.deliveryCount));
     return priceTotal.setScale(2, 6);
   }
 
   public Float getBeforeDeliveryCount() {
     return this.beforeDeliveryCount;
   }
 
   public void setBeforeDeliveryCount(Float beforeDeliveryCount) {
     this.beforeDeliveryCount = beforeDeliveryCount;
   }
 
   public String getStoreName() {
     return this.storeName;
   }
 
   public void setStoreName(String storeName) {
     this.storeName = storeName;
   }
 
   public String getDdId() {
     return this.ddId;
   }
 
   public void setDdId(String ddId) {
     this.ddId = ddId;
   }
 
   public String getRmId() {
     return this.rmId;
   }
 
   public void setRmId(String rmId) {
     this.rmId = rmId;
   }
 
   public String getMaterialName() {
     return this.materialName;
   }
 
   public void setMaterialName(String materialName) {
     this.materialName = materialName;
   }
 
   public String getMaterialCode() {
     return this.materialCode;
   }
 
   public void setMaterialCode(String materialCode) {
     this.materialCode = materialCode;
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
 
   public Float getDeliveryCount() {
     return this.deliveryCount;
   }
 
   public void setDeliveryCount(Float deliveryCount) {
     this.deliveryCount = deliveryCount;
   }
 
   public Delivery getDelivery() {
     return this.delivery;
   }
 
   public void setDelivery(Delivery delivery) {
     this.delivery = delivery;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 }

