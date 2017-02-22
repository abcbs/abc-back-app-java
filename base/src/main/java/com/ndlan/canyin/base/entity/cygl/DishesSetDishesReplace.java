 package com.ndlan.canyin.base.entity.cygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesSetDishes;

 import java.io.Serializable;
 import java.math.BigDecimal;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.OneToOne;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_dishes_set_dishes_replace")
 public class DishesSetDishesReplace extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="replace_id", unique=true, nullable=false, length=36)
   private String replaceId;
 
   @OneToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="replace_dishes_Id")
   private Dishe replaceDishe;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="ds_dishes_id")
   private DishesSetDishes dishesSetDishes;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="ds_id", length=36)
   private String dsId;
 
   @Column(name="dishes_name", length=128)
   private String dishesName;
 
   @Column(name="unit_num")
   private BigDecimal unitNum;
 
   @Column(name="unit_name", length=128)
   private String unitName;
 
   public String getRestId()
   {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public BigDecimal getUnitNum() {
     return this.unitNum;
   }
 
   public void setUnitNum(BigDecimal unitNum) {
     this.unitNum = unitNum;
   }
 
   public String getReplaceId() {
     return this.replaceId;
   }
 
   public void setReplaceId(String replaceId) {
     this.replaceId = replaceId;
   }
 
   public Dishe getReplaceDishe() {
     return this.replaceDishe;
   }
 
   public void setReplaceDishe(Dishe replaceDishe) {
     this.replaceDishe = replaceDishe;
   }
 
   public DishesSetDishes getDishesSetDishes() {
     return this.dishesSetDishes;
   }
 
   public void setDishesSetDishes(DishesSetDishes dishesSetDishes) {
     this.dishesSetDishes = dishesSetDishes;
   }
 
   public String getDsId() {
     return this.dsId;
   }
 
   public void setDsId(String dsId) {
     this.dsId = dsId;
   }
 
   public String getDishesName() {
     return this.dishesName;
   }
 
   public void setDishesName(String dishesName) {
     this.dishesName = dishesName;
   }
 
   public String getUnitName() {
     return this.unitName;
   }
 
   public void setUnitName(String unitName) {
     this.unitName = unitName;
   }
 }

