 package com.ndlan.canyin.base.entity.cygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.cygl.DishesSetCategory;
import com.ndlan.canyin.base.entity.cygl.DishesSetDishes;
import com.ndlan.canyin.base.entity.cygl.DishesSetPic;
import com.ndlan.canyin.base.entity.cygl.DishesUnit;
import com.ndlan.canyin.core.common.PicTypeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
 /**
  * �ײͻ���Ϣ
  * @author zhengjiansong
  *
  */
 @Entity
 @Table(name="cm_dishes_set")
 public class DishesSet extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="ds_id", unique=true, nullable=false, length=36)
   private String dsId;
 
   @Column(name="ds_code", length=1024)
   private String dsCode;
 
   @Column(name="ds_name", length=128)
   private String dsName;
 
   @Column(name="foreign_name", length=128)
   private String foreignName;
 
   @Column(name="is_add_min_charge", length=1)
   private String isAddMinCharge;
 
   @Column(name="is_in_rest_use", length=1)
   private String isInRestUse;
 
   @Column(name="sale_num")
   private Float saleNum = Float.valueOf(0.0F);
 
   @Column(name="is_onsale", length=1)
   private String isOnsale;
 
   @Column(name="is_recommend", length=1)
   private String isRecommend;
 
   @Column(name="is_specialty", length=1)
   private String isSpecialty;
   
   @Transient
   private String dishFlag;
 
   @Column(name="is_stop_sell", length=1)
   private String isStopSell;
 
   @Column(name="is_takeout", length=1)
   private String isTakeout;
 
   @Column(name="price")
   private BigDecimal price;
 
   @Column(name="member_price")
   private BigDecimal memberPrice;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="show_seq")
   private int showSeq;
 
   @Column(name="show_seq_takeout")
   private int showSeqTakeout;
 
   @Column(name="show_seq_recommend")
   private int showSeqRecommend;
 
   @Column(name="estimate")
   private Float estimate;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="ds_category_id")
   @JsonIgnore
   private DishesSetCategory dishesSetCategory;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="unit_id")
   @JsonIgnore
   private DishesUnit dishesUnit;
 
   @Column(name="service_commission")
   private BigDecimal serviceCommission;
 
   @Column(name="sale_commission")
   private BigDecimal saleCommission;
 
   @Column(name="total_score")
   private Integer totalScore;
 
   @Column(name="praise")
   private Integer praise;
 
   @Column(name="tread")
   private Integer tread;
 
   @Column(name="notes", length=2048)
   private String notes;
 
   @OneToMany(mappedBy="dishesSet", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.REMOVE})
   @JsonIgnore
   private List<DishesSetPic> dishesSetPics;
 
   @Transient
   private String dishesStr;
 
   @OneToMany(mappedBy="dishesSet", fetch=FetchType.LAZY)
   @JsonIgnore
   private List<DishesSetPic> dishesSetPic;
 
   @OneToMany(mappedBy="dishesSet", fetch=FetchType.LAZY)
   
   private List<DishesSetDishes> dishesSetDishes;
 
   @Transient
   private String isOrdered = TrueFalseEnum.FALSE.getCode();
 
   @Transient
   private String discountPriceDesc;
 
   @Transient
   private String discountType;
 
   @Transient
   private String isSpecial;
 
   @Transient
   private BigDecimal reallyPrice;
 
   @Transient
   private String rootCategoryId;
 
   @Transient
   private String dishesUnitName;
 
   
   public String getDishFlag() {
	return dishFlag;
}

public void setDishFlag(String dishFlag) {
	this.dishFlag = dishFlag;
}

public String getIsAddMinChargeDesc()
   {
     return TrueFalseEnum.getDesc(this.isAddMinCharge);
   }
 
   public String getIsInRestUse()
   {
     return TrueFalseEnum.getDesc(this.isInRestUse);
   }
 
   public String getIsRecommendDesc()
   {
     return TrueFalseEnum.getDesc(this.isRecommend);
   }
 
   public String getIsSpecialtyDesc()
   {
     return TrueFalseEnum.getDesc(this.isSpecialty);
   }
 
   public String getIsOnsaleDesc()
   {
     return TrueFalseEnum.getDesc(this.isOnsale);
   }
 
   public String getIsStopSellDesc()
   {
     return TrueFalseEnum.getDesc(this.isStopSell);
   }
 
   public String getIsTakeoutDesc()
   {
     return TrueFalseEnum.getDesc(this.isTakeout);
   }
 
   public String getDishesSetPicUrl()
   {
     String url = null;
     if ((this.dishesSetPics != null) && (this.dishesSetPics.size() > 0))
     {
       Collections.sort(this.dishesSetPics, new Comparator()
       {
         public int compare(Object pic1, Object pic2)
         {
           int result = Integer.parseInt((((DishesSetPic)pic2).getCreateTime().getTime() - ((DishesSetPic)pic1).getCreateTime().getTime())+"");
           return result;
         }
       });
       for (DishesSetPic dishesSetPic : this.dishesSetPics) {
         if (!dishesSetPic.getPicType().equals(PicTypeEnum.COMMON.getCode()))
           continue;
         url = dishesSetPic.getPicUrl();
         break;
       }
     }
 
     return url;
   }
 
   public String getDishesSetPicUrl200x200()
   {
     String url = null;
     if ((this.dishesSetPics != null) && (this.dishesSetPics.size() > 0))
     {
       Collections.sort(this.dishesSetPics, new Comparator()
       {
         public int compare(Object pic1, Object pic2)
         {
           int result = Integer.parseInt((((DishesSetPic)pic2).getCreateTime().getTime() - ((DishesSetPic)pic1).getCreateTime().getTime())+"");
           return result;
         }
       });
       for (DishesSetPic dishesSetPic : this.dishesSetPics) {
         if (!dishesSetPic.getPicType().equals(PicTypeEnum.COMMON.getCode()))
           continue;
         url = dishesSetPic.getPicUrl200x200();
         break;
       }
     }
 
     return url;
   }
 
   public String getDishesSetPicUrl1024x1024()
   {
     String url = null;
     if ((this.dishesSetPics != null) && (this.dishesSetPics.size() > 0))
     {
       Collections.sort(this.dishesSetPics, new Comparator()
       {
         public int compare(Object pic1, Object pic2)
         {
           int result = Integer.parseInt((((DishesSetPic)pic2).getCreateTime().getTime() - ((DishesSetPic)pic1).getCreateTime().getTime())+"");
           return result;
         }
       });
       for (DishesSetPic dishesSetPic : this.dishesSetPics) {
         if (!dishesSetPic.getPicType().equals(PicTypeEnum.COMMON.getCode()))
           continue;
         url = dishesSetPic.getPicUrl1024x1024();
         break;
       }
     }
 
     return url;
   }
 
   public List<DishesSetPic> getDishesSetPics() {
     return this.dishesSetPics;
   }
 
   public void setDishesSetPics(List<DishesSetPic> dishesSetPics) {
     this.dishesSetPics = dishesSetPics;
   }
 
   public String getForeignName()
   {
     return this.foreignName;
   }
 
   public void setForeignName(String foreignName) {
     this.foreignName = foreignName;
   }
 
   public String getIsRecommend() {
     return this.isRecommend;
   }
 
   public void setIsRecommend(String isRecommend) {
     this.isRecommend = isRecommend;
   }
 
   public String getIsSpecialty() {
     return this.isSpecialty;
   }
 
   public void setIsSpecialty(String isSpecialty) {
     this.isSpecialty = isSpecialty;
   }
 
   public String getIsAddMinCharge() {
     return this.isAddMinCharge;
   }
 
   public void setIsAddMinCharge(String isAddMinCharge) {
     this.isAddMinCharge = isAddMinCharge;
   }
 
   public String getIsOnsale() {
     return this.isOnsale;
   }
 
   public void setIsOnsale(String isOnsale) {
     this.isOnsale = isOnsale;
   }
 
   public String getIsStopSell()
   {
     return this.isStopSell;
   }
 
   public void setIsStopSell(String isStopSell) {
     this.isStopSell = isStopSell;
   }
 
   public String getIsTakeout() {
     return this.isTakeout;
   }
 
   public void setIsTakeout(String isTakeout) {
     this.isTakeout = isTakeout;
   }
 
   public BigDecimal getPrice()
   {
     return this.price;
   }
 
   public void setPrice(BigDecimal price) {
     this.price = price;
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
 
   public int getShowSeqTakeout() {
     return this.showSeqTakeout;
   }
 
   public void setShowSeqTakeout(int showSeqTakeout) {
     this.showSeqTakeout = showSeqTakeout;
   }
 
   public int getShowSeqRecommend() {
     return this.showSeqRecommend;
   }
 
   public void setShowSeqRecommend(int showSeqRecommend) {
     this.showSeqRecommend = showSeqRecommend;
   }
 
   public Float getEstimate() {
     return this.estimate;
   }
 
   public String getDsId() {
     return this.dsId;
   }
 
   public void setDsId(String dsId) {
     this.dsId = dsId;
   }
 
   public String getDsCode() {
     return this.dsCode;
   }
 
   public void setDsCode(String dsCode) {
     this.dsCode = dsCode;
   }
 
   public String getDsName() {
     return this.dsName;
   }
 
   public void setDsName(String dsName) {
     this.dsName = dsName;
   }
 
   public DishesSetCategory getDishesSetCategory() {
     return this.dishesSetCategory;
   }
 
   public void setDishesSetCategory(DishesSetCategory dishesSetCategory) {
     this.dishesSetCategory = dishesSetCategory;
   }
 
   public void setEstimate(Float estimate) {
     this.estimate = estimate;
   }
 
   public DishesUnit getDishesUnit() {
     return this.dishesUnit;
   }
 
   public void setDishesUnit(DishesUnit dishesUnit) {
     this.dishesUnit = dishesUnit;
   }
 
   public BigDecimal getServiceCommission() {
     return this.serviceCommission;
   }
 
   public void setServiceCommission(BigDecimal serviceCommission) {
     this.serviceCommission = serviceCommission;
   }
 
   public BigDecimal getSaleCommission() {
     return this.saleCommission;
   }
 
   public void setSaleCommission(BigDecimal saleCommission) {
     this.saleCommission = saleCommission;
   }
 
   public List<DishesSetPic> getDishesSetPic() {
     return this.dishesSetPic;
   }
 
   public void setDishesSetPic(List<DishesSetPic> dishesSetPic) {
     this.dishesSetPic = dishesSetPic;
   }
 
   public String getDishesStr() {
     return this.dishesStr;
   }
 
   public void setDishesStr(String dishesStr) {
     this.dishesStr = dishesStr;
   }
 
   public List<DishesSetDishes> getDishesSetDishes() {
     return this.dishesSetDishes;
   }
 
   public void setDishesSetDishes(List<DishesSetDishes> dishesSetDishes) {
     this.dishesSetDishes = dishesSetDishes;
   }
 
   public BigDecimal getMemberPrice() {
     return this.memberPrice;
   }
 
   public void setMemberPrice(BigDecimal memberPrice) {
     this.memberPrice = memberPrice;
   }
 
   public String getIsOrdered() {
     return this.isOrdered;
   }
 
   public void setIsOrdered(String isOrdered) {
     this.isOrdered = isOrdered;
   }
 
   public Float getSaleNum() {
     return this.saleNum;
   }
 
   public void setSaleNum(Float saleNum) {
     this.saleNum = saleNum;
   }
 
   public Integer getTotalScore() {
     return this.totalScore;
   }
 
   public void setTotalScore(Integer totalScore) {
     this.totalScore = totalScore;
   }
 
   public Integer getPraise() {
     return this.praise;
   }
 
   public void setPraise(Integer praise) {
     this.praise = praise;
   }
 
   public Integer getTread() {
     return this.tread;
   }
 
   public void setTread(Integer tread) {
     this.tread = tread;
   }
 
   public void setIsInRestUse(String isInRestUse) {
     this.isInRestUse = isInRestUse;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public String getDiscountPriceDesc() {
     return this.discountPriceDesc;
   }
 
   public void setDiscountPriceDesc(String discountPriceDesc) {
     this.discountPriceDesc = discountPriceDesc;
   }
 
   public String getDiscountType() {
     return this.discountType;
   }
 
   public void setDiscountType(String discountType) {
     this.discountType = discountType;
   }
 
   public String getIsSpecial() {
     return this.isSpecial;
   }
 
   public void setIsSpecial(String isSpecial) {
     this.isSpecial = isSpecial;
   }
 
   public BigDecimal getReallyPrice() {
     return this.reallyPrice;
   }
 
   public void setReallyPrice(BigDecimal reallyPrice) {
     this.reallyPrice = reallyPrice;
   }
 
   public String getCategoryId() {
     return this.dishesSetCategory != null ? this.dishesSetCategory.getDsCategoryId() : "";
   }
 
   public String getRootCategoryId() {
     return this.rootCategoryId;
   }
 
   public void setRootCategoryId(String rootCategoryId) {
     this.rootCategoryId = rootCategoryId;
   }
 
   public String getDishesUnitName() {
     return this.dishesUnit != null ? this.dishesUnit.getName() : this.dishesUnitName;
   }
 
   public void setDishesUnitName(String dishesUnitName) {
     this.dishesUnitName = dishesUnitName;
   }
 
   public List<DishesSetPic> getCommonDishesSetPics() {
     ArrayList commanDishesSetPics = new ArrayList();
     if ((this.dishesSetPics != null) && (this.dishesSetPics.size() > 0)) {
       for (DishesSetPic e : this.dishesSetPics) {
         if (e.getPicType().equals(PicTypeEnum.COMMON.getCode())) {
           commanDishesSetPics.add(e);
         }
       }
     }
     return commanDishesSetPics;
   }
 }

