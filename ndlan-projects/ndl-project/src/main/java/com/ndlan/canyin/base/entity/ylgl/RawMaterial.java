 package com.ndlan.canyin.base.entity.ylgl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.cygl.DishesMaterial;
import com.ndlan.canyin.base.entity.cygl.DishesUnit;
import com.ndlan.canyin.base.entity.ylgl.DishesRaw;
import com.ndlan.canyin.base.entity.ylgl.StoreHouse;

 import java.io.Serializable;
 import java.math.BigDecimal;
 import java.util.ArrayList;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
 import org.hibernate.annotations.GenericGenerator;
 import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
 
 @Entity
 @Table(name="cm_raw_material")
 @JsonIgnoreProperties(ignoreUnknown=true)
 public class RawMaterial extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="rm_id", unique=true, nullable=false, length=36)
   private String rmId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="rm_name", length=128)
   private String rmName;
 
   @Column(name="code", length=128)
   private String code;
 
   @Column(name="price")
   private BigDecimal price;
 
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="unit_id")
   @JsonIgnore
   private DishesUnit dishesUnit;
 
   @Column(name="stock_count")
   private Float stockCount;
 
   @Column(name="package_capacity", length=36)
   private String packageCapacity;
 
   @Column(name="warn_count")
   private Float warnCount;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="sh_id")
   @JsonIgnore
   private StoreHouse storeHouse;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="material_id")
   @NotFound(action=NotFoundAction.IGNORE)
   @JsonIgnore
   private DishesMaterial dishesMaterial;
 
   @OneToMany(mappedBy="rawMaterial")
   private List<DishesRaw> dishesRaws = new ArrayList();
 
   public List<DishesRaw> getDishesRaws() {
     return this.dishesRaws;
   }
 
   public void setDishesRaws(List<DishesRaw> dishesRaws) {
     this.dishesRaws = dishesRaws;
   }
 
   public DishesMaterial getDishesMaterial() {
     return this.dishesMaterial;
   }
 
   public void setDishesMaterial(DishesMaterial dishesMaterial) {
     this.dishesMaterial = dishesMaterial;
   }
 
   public String getRmId() {
     return this.rmId;
   }
 
   public void setRmId(String rmId) {
     this.rmId = rmId;
   }
 
   public String getRmName()
   {
     return this.rmName;
   }
 
   public void setRmName(String rmName) {
     this.rmName = rmName;
   }
 
   public String getCode() {
     return this.code;
   }
 
   public void setCode(String code) {
     this.code = code;
   }
 
   public BigDecimal getPrice() {
     return this.price;
   }
 
   public void setPrice(BigDecimal price) {
     this.price = price;
   }
 
   public DishesUnit getDishesUnit() {
     return this.dishesUnit;
   }
 
   public void setDishesUnit(DishesUnit dishesUnit) {
     this.dishesUnit = dishesUnit;
   }
 
   public Float getStockCount() {
     return this.stockCount;
   }
 
   public void setStockCount(Float stockCount) {
     this.stockCount = stockCount;
   }
 
   public String getPackageCapacity()
   {
     return this.packageCapacity;
   }
 
   public void setPackageCapacity(String packageCapacity) {
     this.packageCapacity = packageCapacity;
   }
 
   public Float getWarnCount() {
     return this.warnCount;
   }
 
   public void setWarnCount(Float warnCount) {
     this.warnCount = warnCount;
   }
 
   public StoreHouse getStoreHouse() {
     return this.storeHouse;
   }
 
   public void setStoreHouse(StoreHouse storeHouse) {
     this.storeHouse = storeHouse;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 }

