 package com.ndlan.canyin.base.entity.cygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesCategory;
 import com.ndlan.canyin.core.common.CategoryLevelEnum;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.io.Serializable;
 import java.util.List;
 import java.util.Set;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 /**
  * ���ȷ���
  * @author zhengjiansong
  *
  */
 @Entity
 @Table(name="cm_dishes_category")
 public class DishesCategory extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="category_id", unique=true, nullable=false, length=36)
   private String categoryId;
 
   @Column(name="category_code", length=32)
   private String categoryCode;
 
   @Column(name="category_level", length=36)
   private String categoryLevel = CategoryLevelEnum.FIRST.getCode();
 
   @Column(name="parent_category_id", length=36)
   private String parentCategoryId;
 
   @Transient
   private DishesCategory parentDishesCategory;
 
   @Column(name="category_name", length=128)
   private String categoryName;
 
   @Column(name="foreign_name", length=128)
   private String foreignName;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="show_seq")
   private int showSeq;
 
   @Column(name="sysdata_type", length=32)
   private String sysdataType;
 
   @Column(name="enable_status", length=32)
   private String enableStatus;
 
   @JsonIgnore
   @OneToMany(mappedBy="dishesCategory")
   private List<Dishe> dishes;
 
   @JsonIgnore
   @OneToMany(mappedBy="dishesCategory")
   private Set<DinerBillDishe> dinerBillDishes;
 
   @Transient
   private String dishAndSetDiv;
 
   public String getCategoryLevelDesc() { return CategoryLevelEnum.getDesc(this.categoryLevel);
   }
 
   public String getEnableStatusDesc()
   {
     return EnableStatusEnum.getDesc(this.enableStatus);
   }
 
   @JsonIgnore
   public String getSimpleDishNum()
   {
     int i = 0;
     for (Dishe dish : this.dishes) {
       if (dish.getIsUserDefined().equals(TrueFalseEnum.FALSE.getCode())) {
         i++;
       }
     }
     return String.valueOf(i);
   }
 
   public String getCategoryId()
   {
     return this.categoryId;
   }
 
   public void setCategoryId(String categoryId) {
     this.categoryId = categoryId;
   }
 
   public String getCategoryCode() {
     return this.categoryCode;
   }
 
   public void setCategoryCode(String categoryCode) {
     this.categoryCode = categoryCode;
   }
 
   public String getCategoryName() {
     return this.categoryName;
   }
 
   public void setCategoryName(String categoryName) {
     this.categoryName = categoryName;
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
 
   public List<Dishe> getDishes() {
     return this.dishes;
   }
 
   @JsonIgnore
   public List<Dishe> getDishesRoot() {
     List<Dishe> dishes = getDishes();
     if ((dishes != null) && (dishes.size() > 0))
     {
       for (Dishe dishe : dishes) {
         dishe.setDishAndSetDiv(getDishAndSetDiv());
         if ((dishe.getDishesCategory() == null) || 
           (dishe.getDishesCategory().getParentCategoryId() == null)) continue;
         dishe.setRootCategoryId(dishe.getDishesCategory().getParentCategoryId());
       }
 
     }
 
     return dishes;
   }
 
   public void setDishes(List<Dishe> dishes) {
     this.dishes = dishes;
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
 
   public Set<DinerBillDishe> getDinerBillDishes() {
     return this.dinerBillDishes;
   }
 
   public void setDinerBillDishes(Set<DinerBillDishe> dinerBillDishes) {
     this.dinerBillDishes = dinerBillDishes;
   }
 
   public String getParentCategoryId() {
     return this.parentCategoryId;
   }
 
   public void setParentCategoryId(String parentCategoryId) {
     this.parentCategoryId = parentCategoryId;
   }
 
   public DishesCategory getParentDishesCategory() {
     return this.parentDishesCategory;
   }
 
   public void setParentDishesCategory(DishesCategory parentDishesCategory) {
     this.parentDishesCategory = parentDishesCategory;
   }
 
   public String getCategoryLevel() {
     return this.categoryLevel;
   }
 
   public void setCategoryLevel(String categoryLevel) {
     this.categoryLevel = categoryLevel;
   }
 
   public String getDishAndSetDiv() {
     return this.dishAndSetDiv;
   }
 
   public void setDishAndSetDiv(String dishAndSetDiv) {
     this.dishAndSetDiv = dishAndSetDiv;
   }
 }

