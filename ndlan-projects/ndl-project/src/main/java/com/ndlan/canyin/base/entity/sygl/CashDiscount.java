 package com.ndlan.canyin.base.entity.sygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.sygl.CashDishesTypeDiscount;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import java.io.Serializable;
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
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_cash_discount")
 @JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
 public class CashDiscount extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="ccd_id", unique=true, nullable=false, length=36)
   private String ccdId;
 
   @Column(name="discount_name", length=128)
   private String discountName;
 
   @Column(name="enable_status", length=1)
   private String enableStatus;
 
   @Column(name="is_only_member", length=1)
   private String isOnlyMember;
 
   @Column(name="main_discount")
   private Integer mainDiscount;
 
   @Transient
   private String mainDiscountPercent;
   
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="rest_id")
   private Restaurant restaurant;
 
   @Column(name="roles_array", length=1024)
   private String rolesArray;
 
   @Column(name="belong_org", length=32)
   private String belongOrg;
 
   @JsonIgnore
   @OneToMany(cascade={javax.persistence.CascadeType.REMOVE}, mappedBy="cashDiscount")
   private List<CashDishesTypeDiscount> cashDishesTypeDiscounts;
 
   @JsonIgnore
   @OneToMany(mappedBy="cashDiscount")
   private List<DinerBill> dinerBills;
 
   @Transient
   private String enableStatusName;
 
   @Transient
   private String rolesName;
 
   @Transient
   private String dishesTypeDiscount;
 
   @Transient
   private String dishesTypeNoDiscount;
 
   public String getDishesTypeNoDiscount()
   {
     return this.dishesTypeNoDiscount;
   }
 
   public void setDishesTypeNoDiscount(String dishesTypeNoDiscount) {
     this.dishesTypeNoDiscount = dishesTypeNoDiscount;
   }
 
   public String getCcdId()
   {
     return this.ccdId;
   }
 
   public void setCcdId(String ccdId) {
     this.ccdId = ccdId;
   }
 
   public String getDiscountName() {
     return this.discountName;
   }
 
   public void setDiscountName(String discountName) {
     this.discountName = discountName;
   }
 
   public String getEnableStatus() {
     return this.enableStatus;
   }
 
   public void setEnableStatus(String enableStatus) {
     this.enableStatus = enableStatus;
   }
 
   public String getIsOnlyMember() {
     return this.isOnlyMember;
   }
 
   public void setIsOnlyMember(String isOnlyMember) {
     this.isOnlyMember = isOnlyMember;
   }
 
   public Integer getMainDiscount() {
     return this.mainDiscount;
   }
 
   public void setMainDiscount(Integer mainDiscount) {
     this.mainDiscount = mainDiscount;
   }
 
   public Restaurant getRestaurant() {
     return this.restaurant;
   }
 
   public void setRestaurant(Restaurant restaurant) {
     this.restaurant = restaurant;
   }
 
   public String getRolesArray() {
     return this.rolesArray;
   }
 
   public void setRolesArray(String rolesArray) {
     this.rolesArray = rolesArray;
   }
 
   public List<DinerBill> getDinerBills() {
     return this.dinerBills;
   }
 
   public void setDinerBills(List<DinerBill> dinerBills) {
     this.dinerBills = dinerBills;
   }
 
   public String getEnableStatusName() {
     this.enableStatusName = EnableStatusEnum.getDesc(this.enableStatus);
     return this.enableStatusName;
   }
 
   public void setEnableStatusName(String enableStatusName) {
     this.enableStatusName = enableStatusName;
   }
 
   public List<CashDishesTypeDiscount> getCashDishesTypeDiscounts() {
     return this.cashDishesTypeDiscounts;
   }
 
   public void setCashDishesTypeDiscounts(List<CashDishesTypeDiscount> cashDishesTypeDiscounts)
   {
     this.cashDishesTypeDiscounts = cashDishesTypeDiscounts;
   }
 
   public String getRolesName() {
     return this.rolesName;
   }
 
   public void setRolesName(String rolesName) {
     this.rolesName = rolesName;
   }
 
   public String getDishesTypeDiscount() {
     return this.dishesTypeDiscount;
   }
 
   public void setDishesTypeDiscount(String dishesTypeDiscount) {
     this.dishesTypeDiscount = dishesTypeDiscount;
   }
 
   public String getMainDiscountPercent() {
     this.mainDiscountPercent = (String.valueOf(this.mainDiscount) + "%");
     return this.mainDiscountPercent;
   }
 
   public void setMainDiscountPercent(String mainDiscountPercent) {
     this.mainDiscountPercent = mainDiscountPercent;
   }
 
   public String getBelongOrg() {
     return this.belongOrg;
   }
 
   public void setBelongOrg(String belongOrg) {
     this.belongOrg = belongOrg;
   }
 }

