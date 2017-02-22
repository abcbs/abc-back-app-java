 package com.ndlan.canyin.base.entity.cygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.base.entity.cygl.SpecialPriceInterval;
 import com.ndlan.canyin.core.common.DishesTypeEnum;
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
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_special_dishes")
 public class SpecialDishe extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="sf_id", unique=true, nullable=false, length=36)
   private String sfId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="special_price")
   private BigDecimal specialPrice;
 
   @Transient
   private String formDishesId;
 
   @Transient
   private String formDishesSetId;
 
   @Column(name="show_seq")
   private int showSeq;
 
   @JsonIgnore
   @ManyToOne
   @JoinColumn(name="dishes_id")
   private Dishe dishe;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY, optional=true)
   @JoinColumn(name="ds_id")
   private DishesSet dishesSet;
 
   @Column(name="cy_tc", length=1)
   private String cyTc;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="spi_id")
   private SpecialPriceInterval specialPriceInterval;
 
   public String getSfId()
   {
     return this.sfId;
   }
 
   public String getcyTcName()
   {
     String dishName = "";
     if ((this.dishe != null) || (this.dishesSet != null)) {
       dishName = DishesTypeEnum.DISHES_SET.getCode().equals(getCyTc()) ? getDishesSet().getDsName() : this.dishe.getDishesName();
     }
 
     return dishName;
   }
 
   public String getcyTcPrice()
   {
     String dishPrice = "";
     if ((this.dishe != null)) {
       dishPrice = this.dishe.getPrice().toString();
     }else if(this.dishesSet != null){
    	 dishPrice = this.dishesSet.getPrice().toString();
     }
 
     return dishPrice;
   }
   public DishesSet getDishesSet() {
     return this.dishesSet;
   }
 
   public String getFormDishesSetId() {
     return this.formDishesSetId;
   }
 
   public void setFormDishesSetId(String formDishesSetId) {
     this.formDishesSetId = formDishesSetId;
   }
 
   public void setDishesSet(DishesSet dishesSet)
   {
     this.dishesSet = dishesSet;
   }
 
   public void setSfId(String sfId) {
     this.sfId = sfId;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public BigDecimal getSpecialPrice() {
     return this.specialPrice;
   }
 
   public void setSpecialPrice(BigDecimal specialPrice) {
     this.specialPrice = specialPrice;
   }
 
   public Dishe getDishe() {
     return this.dishe;
   }
 
   public void setDishe(Dishe dishe) {
     this.dishe = dishe;
   }
 
   public SpecialPriceInterval getSpecialPriceInterval() {
     return this.specialPriceInterval;
   }
 
   public void setSpecialPriceInterval(SpecialPriceInterval specialPriceInterval) {
     this.specialPriceInterval = specialPriceInterval;
   }
 
   public String getFormDishesId() {
     return this.formDishesId;
   }
 
   public void setFormDishesId(String formDishesId) {
     this.formDishesId = formDishesId;
   }
 
   public int getShowSeq() {
     return this.showSeq;
   }
 
   public void setShowSeq(int showSeq) {
     this.showSeq = showSeq;
   }
 
   public String getCyTc() {
     return this.cyTc;
   }
 
   public void setCyTc(String cyTc) {
     this.cyTc = cyTc;
   }
 }

