 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.SelfOrder;

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
 @Table(name="cm_self_dinner_bill_dishe")
 public class SelfDish extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="id", unique=true, nullable=false, length=36)
   private String id;
 
   @Column(name="dishes_id", length=36)
   private String dishesId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="dishes_name", length=255)
   private String dishesName;
 
   @Column(name="status", length=32)
   private String status;
 
   @Column(name="sale_num")
   private int saleNum;
 
   @Column(name="real_cost")
   private BigDecimal realCost;
 
   @Column(name="is_set")
   private Integer isSet = Integer.valueOf(0);
 
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="order_id")
   @JsonIgnore
   private SelfOrder selfOrder;
 
   public String getId() { return this.id; }
 
   public void setId(String id)
   {
     this.id = id;
   }
 
   public String getDishesId() {
     return this.dishesId;
   }
 
   public void setDishesId(String dishesId) {
     this.dishesId = dishesId;
   }
 
   public String getStatus()
   {
     return this.status;
   }
 
   public void setStatus(String status) {
     this.status = status;
   }
 
   public int getSaleNum() {
     return this.saleNum;
   }
 
   public void setSaleNum(int saleNum) {
     this.saleNum = saleNum;
   }
 
   public SelfOrder getSelfOrder() {
     return this.selfOrder;
   }
 
   public void setSelfOrder(SelfOrder selfOrder) {
     this.selfOrder = selfOrder;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public BigDecimal getRealCost() {
     return this.realCost;
   }
 
   public void setRealCost(BigDecimal realCost) {
     this.realCost = realCost;
   }
 
   public static long getSerialversionuid() {
     return 1L;
   }
 
   public String getDishesName() {
     return this.dishesName;
   }
 
   public void setDishesName(String dishesName) {
     this.dishesName = dishesName;
   }
 
   public Integer getIsSet() {
     return this.isSet;
   }
 
   public void setIsSet(Integer isSet) {
     this.isSet = isSet;
   }
 }

