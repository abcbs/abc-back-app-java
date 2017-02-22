 package com.ndlan.canyin.base.entity.sygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.sygl.CashDiscount;

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
 @Table(name="cm_cash_dishes_type_discount")
 public class CashDishesTypeDiscount extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="ccdtd_id", unique=true, nullable=false, length=36)
   private String ccdtdId;
 
   @Column(name="category_dicount", length=36)
   private Integer categoryDicount;
 
   @Column(name="category_id", length=36)
   private String categoryId;
 
   @Column(name="ds_category_id", length=36)
   private String dsCategoryId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="ccd_id")
   private CashDiscount cashDiscount;
 
   public String getCcdtdId()
   {
     return this.ccdtdId;
   }
 
   public void setCcdtdId(String ccdtdId) {
     this.ccdtdId = ccdtdId;
   }
 
   public Integer getCategoryDicount() {
     return this.categoryDicount;
   }
 
   public void setCategoryDicount(Integer categoryDicount) {
     this.categoryDicount = categoryDicount;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public CashDiscount getCashDiscount() {
     return this.cashDiscount;
   }
 
   public void setCashDiscount(CashDiscount cashDiscount) {
     this.cashDiscount = cashDiscount;
   }
 
   public String getCategoryId() {
     return this.categoryId;
   }
 
   public void setCategoryId(String categoryId) {
     this.categoryId = categoryId;
   }
 
   public String getDsCategoryId() {
     return this.dsCategoryId;
   }
 
   public void setDsCategoryId(String dsCategoryId) {
     this.dsCategoryId = dsCategoryId;
   }
 }

