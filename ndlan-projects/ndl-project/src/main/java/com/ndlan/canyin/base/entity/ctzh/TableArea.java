 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.Role;
import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.core.common.SeviceChargeTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
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
 import javax.persistence.ManyToMany;
 import javax.persistence.ManyToOne;
 import javax.persistence.OneToMany;
 import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
 /**
  * 餐桌区域
  * @author zhengjiansong
  *
  */
 @Entity
 @javax.persistence.Table(name="cm_table_area")
 public class TableArea extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="area_id", unique=true, nullable=false, length=36)
   private String areaId;
 
   @Column(name="consume_low")
   private BigDecimal consumeLow;
   
   @Column(name="teamare")
   private String teamare;
   
   @Column(name="is_on_sale", length=1)
   private String isOnSale;
 
   @Column(name="is_special_price", length=1)
   private String isSpecialPrice;
 
   @Column(name="name", length=128)
   private String name;
 
   @Column(name="notes", length=1024)
   private String notes;
 
   @Column(name="sevice_charge_num")
   private BigDecimal seviceChargeNum = new BigDecimal(0);
 
   @Column(name="sevice_charge_type", length=32)
   private String seviceChargeType;
 
   @JsonIgnore
   @OneToMany(mappedBy="tableArea")
   private List<Table> tables = new ArrayList();
 
   @JsonIgnore
   @ManyToMany(fetch=FetchType.LAZY, mappedBy="tableAreaList", cascade={javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.MERGE})
   private List<Role> roleList = new ArrayList();
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="rest_id")
   private Restaurant restaurant;
 
   public String getAreaId() {
     return this.areaId;
   }
 
   public void setAreaId(String areaId) {
     this.areaId = areaId;
   }
 
   public BigDecimal getConsumeLow() {
     return this.consumeLow;
   }
 
   public void setConsumeLow(BigDecimal consumeLow) {
     this.consumeLow = consumeLow;
   }
 
   public String getIsOnSale() {
     return this.isOnSale;
   }
 
   public void setIsOnSale(String isOnSale) {
     this.isOnSale = isOnSale;
   }
 
   public String getIsSpecialPrice() {
     return this.isSpecialPrice;
   }
 
   public void setIsSpecialPrice(String isSpecialPrice) {
     this.isSpecialPrice = isSpecialPrice;
   }
 
   public String getName() {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public BigDecimal getSeviceChargeNum() {
     return this.seviceChargeNum;
   }
 
   public void setSeviceChargeNum(BigDecimal seviceChargeNum) {
     this.seviceChargeNum = seviceChargeNum;
   }
 
   public String getSeviceChargeType() {
     return this.seviceChargeType;
   }
 
   public void setSeviceChargeType(String seviceChargeType) {
     this.seviceChargeType = seviceChargeType;
   }
 
   public List<Table> getTables() {
     return this.tables;
   }
 
   public void setTables(List<Table> tables) {
     this.tables = tables;
   }
 
   public Restaurant getRestaurant() {
     return this.restaurant;
   }
 
   public void setRestaurant(Restaurant restaurant) {
     this.restaurant = restaurant;
   }
 
   public List<Role> getRoleList() {
     return this.roleList;
   }
 
   public void setRoleList(List<Role> roleList) {
     this.roleList = roleList;
   }
 
   public String getTableAreaServiceDesc()
   {
     String serviceDescript = (getSeviceChargeNum() != null) && (getSeviceChargeNum().compareTo(BigDecimal.ZERO) == 1) ? 
       "服务费：" + getSeviceChargeNum() + SeviceChargeTypeEnum.getAppendix(getSeviceChargeType()) : "";
 
     serviceDescript = serviceDescript + ((getConsumeLow() != null) && (getConsumeLow().compareTo(BigDecimal.ZERO) == 1) ? 
       "最低消费：￥" + getConsumeLow() : StringUtils.isNotEmpty(serviceDescript) ? "，最低消费：￥" + getConsumeLow() : 
       "");
 
     serviceDescript = serviceDescript + (TrueFalseEnum.FALSE.getCode().equals(getIsOnSale()) ? 
       "该分区不参与打折" : StringUtils.isNotEmpty(serviceDescript) ? "，该分区不参与打折" : 
       "");
 
     serviceDescript = serviceDescript + (TrueFalseEnum.FALSE.getCode().equals(getIsSpecialPrice()) ? 
       "该分区不参与特价" : StringUtils.isNotEmpty(serviceDescript) ? "，该分区不参与特价" : 
       "");
     return serviceDescript;
   }
 
   public String toString()
   {
     return this.areaId;
   }

public String getTeamare() {
	return teamare;
}

public void setTeamare(String teamare) {
	this.teamare = teamare;
}
   
 }

