 package com.ndlan.canyin.base.entity.cygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.base.entity.cygl.DishesSetCategory;
 import com.ndlan.canyin.core.common.CategoryLevelEnum;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import java.io.Serializable;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_dishes_set_category")
 @JsonIgnoreProperties({"handler","hibernateLazyInitializer","dishesSetSize"})
 public class DishesSetCategory extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="ds_category_id", unique=true, nullable=false, length=36)
   private String dsCategoryId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="category_name", length=128)
   private String categoryName;
 
   @Column(name="category_code", length=32)
   private String categoryCode;
 
   @Column(name="category_level", length=36)
   private String categoryLevel = CategoryLevelEnum.FIRST.getCode();
 
   @Column(name="parent_category_id", length=36)
   private String parentCategoryId;
 
   @Transient
   private DishesSetCategory parentDishesSetCategory;
 
   @Column(name="foreign_name", length=128)
   private String foreignName;
 
   @Column(name="show_seq")
   private int showSeq;
 
   @Column(name="sysdata_type", length=32)
   private String sysdataType;
 
   @Column(name="enable_status", length=32)
   private String enableStatus;
 
   @JsonIgnore
   @OneToMany(mappedBy="dishesSetCategory")
   private List<DishesSet> dishesSets;
 
   public String getCategoryLevelDesc() { return CategoryLevelEnum.getDesc(this.categoryLevel);
   }
 
   public String getdishesSetSize()
   {
     int size = 0;
     if ((this.dishesSets != null) && (this.dishesSets.size() > 0)) {
       size = this.dishesSets.size();
     }
     return String.valueOf(size);
   }
 
   public String getDsCategoryId() {
     return this.dsCategoryId;
   }
   public void setDsCategoryId(String dsCategoryId) {
     this.dsCategoryId = dsCategoryId;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getCategoryName() {
     return this.categoryName;
   }
   public void setCategoryName(String categoryName) {
     this.categoryName = categoryName;
   }
   public String getCategoryCode() {
     return this.categoryCode;
   }
   public void setCategoryCode(String categoryCode) {
     this.categoryCode = categoryCode;
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
 
   public String getEnableStatusDesc() {
     return EnableStatusEnum.getDesc(this.enableStatus);
   }
 
   public List<DishesSet> getDishesSets() {
     return this.dishesSets;
   }
 
   public void setDishesSets(List<DishesSet> dishesSets) {
     this.dishesSets = dishesSets;
   }
 
   public String getCategoryLevel() {
     return this.categoryLevel;
   }
 
   public void setCategoryLevel(String categoryLevel) {
     this.categoryLevel = categoryLevel;
   }
 
   public String getParentCategoryId() {
     return this.parentCategoryId;
   }
 
   public void setParentCategoryId(String parentCategoryId) {
     this.parentCategoryId = parentCategoryId;
   }
 
   public DishesSetCategory getParentDishesSetCategory() {
     return this.parentDishesSetCategory;
   }
 
   public void setParentDishesSetCategory(DishesSetCategory parentDishesSetCategory) {
     this.parentDishesSetCategory = parentDishesSetCategory;
   }
 
   @JsonIgnore
   public List<DishesSet> getDishesRoot() {
     List<DishesSet> dishes = getDishesSets();
     if ((dishes != null) && (dishes.size() > 0))
     {
       for (DishesSet dishe : dishes) {
         if ((dishe.getDishesSetCategory() == null) || 
           (dishe.getDishesSetCategory().getParentCategoryId() == null)) continue;
         dishe.setRootCategoryId(dishe.getDishesSetCategory().getParentCategoryId());
       }
 
     }
 
     return dishes;
   }
 }

