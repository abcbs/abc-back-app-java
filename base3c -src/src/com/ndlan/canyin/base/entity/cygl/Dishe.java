 package com.ndlan.canyin.base.entity.cygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 import com.google.common.collect.ImmutableList;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.RestaurantRecommendDishe;
import com.ndlan.canyin.base.entity.qtsy.OrderBillDishe;
import com.ndlan.canyin.base.entity.cygl.DishesAppraise;
import com.ndlan.canyin.base.entity.cygl.DishesCategory;
import com.ndlan.canyin.base.entity.cygl.DishesPic;
import com.ndlan.canyin.base.entity.cygl.DishesSetPic;
import com.ndlan.canyin.base.entity.cygl.DishesUnit;
 import com.ndlan.canyin.core.common.DishesTypeEnum;
 import com.ndlan.canyin.core.common.PicTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import com.ndlan.canyin.core.utils.BigDecimalUtil;
 import com.ndlan.canyin.core.utils.Collections3;
 import java.io.Serializable;
 import java.math.BigDecimal;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Set;
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
 import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
 /**
  * ������Ϣ
  * @author zhengjiansong
  *
  */
 @Entity
 @Table(name="cm_dishes")
 @JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
 public class Dishe extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="dishes_id", unique=true, nullable=false, length=36)
   private String dishesId;
 
   @Column(name="dashes_style_id_array", length=1024)
   private String dashesStyleIdArray;
 
   @Transient
   @JsonIgnore
   private List<String> dashesStyleIdList;
 
   @Column(name="dishes_code", length=1024)
   private String dishesCode;
 
   @Column(name="dishes_name", length=128)
   private String dishesName;
 
   @Column(name="foreign_name", length=128)
   private String foreignName;
 
   @Column(name="is_add_min_charge", length=1)
   private String isAddMinCharge;
 
   @Column(name="is_onsale", length=1)
   private String isOnsale;
 
   @Column(name="is_recommend", length=1)
   private String isRecommend;
 
   @Column(name="is_ruling_price", length=1)
   private String isRulingPrice;
 
   @Column(name="is_specialty", length=1)
   private String isSpecialty;
 
   @Column(name="is_stop_sell", length=1)
   private String isStopSell;
 
   @Column(name="is_takeout", length=1)
   private String isTakeout;
 
   @Column(name="is_in_rest_use", length=1)
   private String isInRestUse;
 
   @Column(name="material_id_array", length=1024)
   private String materialIdArray;
 
   @Transient
   private BigDecimal reallyPrice;
 
   @Transient
   private BigDecimal specialPrice;
   
   @Transient
   @JsonIgnore
   private List<String> materialIdList;
 
   @Column(name="member_price")
   private BigDecimal memberPrice;
 
   @Column(name="notes", length=2048)
   private String notes;
 
   @Column(name="price")
   private BigDecimal price;
   
   @Column(name="price" ,updatable=false,insertable=false)
   private String oriCostStr;
 
   @Transient
   private BigDecimal priceFormat;
 
   @Column(name="pungent_level")
   private Integer pungentLevel;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="sale_num")
   private Float saleNum = Float.valueOf(0.0F);
 
   @Transient
   private BigDecimal dsUnitNum;
 
   @Column(name="show_seq")
   private Integer showSeq;
 
   @Column(name="show_seq_takeout")
   private Integer showSeqTakeout;
 
   @Column(name="show_seq_recommend")
   private Integer showSeqRecommend;
 
   @Column(name="taste_id_array", length=1024)
   private String tasteIdArray;
 
   @Transient
   @JsonIgnore
   private List<String> tasteIdList;
 
   @Column(name="total_score")
   private Integer totalScore;
 
   @Column(name="estimate")
   private Float estimate;
 
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="category_id")
   @JsonIgnore
   private DishesCategory dishesCategory;
 
   @Transient
   private String categoryId;
 
   @Transient
   private String rootCategoryId;
 
   @Transient
   private String categoryCode;
 
   @Transient
   private String dishAndSetDiv;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="unit_id")
   @JsonIgnore
   private DishesUnit dishesUnit;
 
   @Transient
   private String unitId;
 
   @Transient
   private String dishesUnitName;
 
   @OneToMany(mappedBy="dishe", fetch=FetchType.LAZY)
   @JsonIgnore
   private Set<DishesAppraise> dishesAppraises;
 
   @OneToMany(mappedBy="dishe", fetch=FetchType.LAZY)
   @JsonIgnore
   private Set<OrderBillDishe> orderBillDishes;
 
   @OneToMany(mappedBy="dishe", fetch=FetchType.LAZY)
   @JsonIgnore
   private Set<RestaurantRecommendDishe> sestaurantRecommendDishes;
 
   @OneToMany(mappedBy="dishe", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.REMOVE})
   @JsonIgnore
   private List<DishesPic> dishesPics;
 
   @Transient
   private List<DishesSetPic> dishesSetPics;
 
   @Column(name="is_user_defined", length=1)
   private String isUserDefined;
 
   @Column(name="service_commission")
   private BigDecimal serviceCommission;
 
   @Transient
   private String dsDishesId;
 
   @Column(name="sale_commission")
   private BigDecimal saleCommission;
 
   @Column(name="praise")
   private Integer praise;
 
   @Column(name="tread")
   private Integer tread;
 
   @Transient
   private String selfDishId;
 
   @Transient
   private Integer selfDishSaleNum;
 
   @Transient
   private String discountPriceDesc;
 
   @Transient
   private String discountType;
 
   @Transient
   private String isSpecial;
   
   @Transient
   private String dishFlag;
 
   @Transient
   private String isOrdered = TrueFalseEnum.FALSE.getCode();
 
   @Transient
   private String picUrl;
   
   @Transient
   private String picUrlSet;   //套餐图片路径      只针对获取全部菜品的时候用到
   
   
   public String getDishFlag() {
	return dishFlag;
}

public void setDishFlag(String dishFlag) {
	this.dishFlag = dishFlag;
}

public String getPicUrlSet() {
	return picUrlSet;
	}
	
	public void setPicUrlSet(String picUrlSet) {
		this.picUrlSet = picUrlSet;
	}
	
	public void setDashesStyleIdList(List<String> dashesStyleIdList)
   {
     this.dashesStyleIdArray = Collections3.convertToString(dashesStyleIdList, ",");
     this.dashesStyleIdList = dashesStyleIdList;
   }
 
   public List<String> getDashesStyleIdList()
   {
     if (this.dashesStyleIdArray != null)
     {
       this.dashesStyleIdList = ImmutableList.copyOf(StringUtils.split(this.dashesStyleIdArray, ","));
     }
     return this.dashesStyleIdList;
   }
 
   public String getIsAddMinChargeDesc()
   {
     return TrueFalseEnum.getDesc(this.isAddMinCharge);
   }
 
   public String getIsOnsaleDesc()
   {
     return TrueFalseEnum.getDesc(this.isOnsale);
   }
 
   public String getIsRecommendDesc()
   {
     return TrueFalseEnum.getDesc(this.isRecommend);
   }
 
   public String getIsRulingPriceDesc()
   {
     return TrueFalseEnum.getDesc(this.isRulingPrice);
   }
 
   public String getIsSpecialtyDesc()
   {
     return TrueFalseEnum.getDesc(this.isSpecialty);
   }
 
   public String getIsStopSellDesc()
   {
     return TrueFalseEnum.getDesc(isStopSell);
   }
 
   public String getIsTakeoutDesc()
   {
     return TrueFalseEnum.getDesc(this.isTakeout);
   }
 
   public String getIsInRestUseDesc()
   {
     return TrueFalseEnum.getDesc(this.isInRestUse);
   }
 
   public void setMaterialIdList(List<String> materialIdList)
   {
     this.materialIdArray = Collections3.convertToString(materialIdList, ",");
     this.materialIdList = materialIdList;
   }
 
   public List<String> getMaterialIdList()
   {
     if (this.materialIdArray != null)
     {
       this.materialIdList = ImmutableList.copyOf(StringUtils.split(this.materialIdArray, ","));
     }
     return this.materialIdList;
   }
 
   public void setTasteIdList(List<String> tasteIdList)
   {
     this.tasteIdArray = Collections3.convertToString(tasteIdList, ",");
     this.tasteIdList = tasteIdList;
   }
 
   public List<String> getTasteIdList()
   {
     if (this.tasteIdArray != null)
     {
       this.tasteIdList = ImmutableList.copyOf(StringUtils.split(this.tasteIdArray, ","));
     }
     return this.tasteIdList;
   }
 
   public List<DishesSetPic> getDishesSetPics()
   {
     return this.dishesSetPics;
   }
 
   public void setDishesSetPics(List<DishesSetPic> dishesSetPics) {
     this.dishesSetPics = dishesSetPics;
   }
 
   public String getDishesPicUrl()
   {
     if (!DishesTypeEnum.DISHES_SET.getCode().equals(this.dishAndSetDiv)) {
       if ((this.dishesPics != null) && (this.dishesPics.size() > 0))
       {
         for (DishesPic e : this.dishesPics)
         {
           if (!e.getPicType().equals(PicTypeEnum.COMMON.getCode()))
             continue;
           this.picUrl = e.getPicUrl();
           break;
         }
       }
 
     }
     else if ((this.dishesSetPics != null) && (this.dishesSetPics.size() > 0))
     {
       for (DishesSetPic e : this.dishesSetPics)
       {
         if (!e.getPicType().equals(PicTypeEnum.COMMON.getCode()))
           continue;
         this.picUrl = e.getPicUrl();
         break;
       }
 
     }
 
     return this.picUrl;
   }
 
   public String getDishesPicUrl200x200()
   {
     if (!DishesTypeEnum.DISHES_SET.getCode().equals(this.dishAndSetDiv)) {
       if ((this.dishesPics != null) && (this.dishesPics.size() > 0))
       {
         for (DishesPic e : this.dishesPics)
         {
           if (!e.getPicType().equals(PicTypeEnum.COMMON.getCode()))
             continue;
           this.picUrl = e.getPicUrl200x200();
           break;
         }
       }
 
     }
     else if ((this.dishesSetPics != null) && (this.dishesSetPics.size() > 0))
     {
       for (DishesSetPic e : this.dishesSetPics)
       {
         if (!e.getPicType().equals(PicTypeEnum.COMMON.getCode()))
           continue;
         this.picUrl = e.getPicUrl200x200();
         break;
       }
 
     }
 
     return this.picUrl;
   }
 
   public String getDishesPicUrl1024x1024()
   {
     if (!DishesTypeEnum.DISHES_SET.getCode().equals(this.dishAndSetDiv)) {
       if ((this.dishesPics != null) && (this.dishesPics.size() > 0))
       {
         for (DishesPic e : this.dishesPics)
         {
           if (!e.getPicType().equals(PicTypeEnum.COMMON.getCode()))
             continue;
           this.picUrl = e.getPicUrl1024x1024();
           break;
         }
       }
 
     }
     else if ((this.dishesSetPics != null) && (this.dishesSetPics.size() > 0))
     {
       for (DishesSetPic e : this.dishesSetPics)
       {
         if (!e.getPicType().equals(PicTypeEnum.COMMON.getCode()))
           continue;
         this.picUrl = e.getPicUrl1024x1024();
         break;
       }
 
     }
 
     return this.picUrl;
   }
 
   public BigDecimal getServiceCommission()
   {
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
 
   public String getDishesId()
   {
     return this.dishesId;
   }
 
   public void setDishesId(String dishesId) {
     this.dishesId = dishesId;
   }
 
   public String getDashesStyleIdArray()
   {
     return this.dashesStyleIdArray;
   }
 
   public void setDashesStyleIdArray(String dashesStyleIdArray) {
     this.dashesStyleIdArray = dashesStyleIdArray;
   }
 
   public String getDishesCode() {
     return this.dishesCode;
   }
 
   public void setDishesCode(String dishesCode) {
     this.dishesCode = dishesCode;
   }
 
   public String getDishesName() {
     return this.dishesName;
   }
 
   public void setDishesName(String dishesName) {
     this.dishesName = dishesName;
   }
 
   public String getForeignName() {
     return this.foreignName;
   }
 
   public void setForeignName(String foreignName) {
     this.foreignName = foreignName;
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
 
   public String getIsRecommend() {
     return this.isRecommend;
   }
 
   public void setIsRecommend(String isRecommend) {
     this.isRecommend = isRecommend;
   }
 
   public String getIsRulingPrice() {
     return this.isRulingPrice;
   }
 
   public void setIsRulingPrice(String isRulingPrice) {
     this.isRulingPrice = isRulingPrice;
   }
 
   public String getIsSpecialty() {
     return this.isSpecialty;
   }
 
   public void setIsSpecialty(String isSpecialty) {
     this.isSpecialty = isSpecialty;
   }
 
   public String getIsStopSell() {
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
 
   public String getMaterialIdArray() {
     return this.materialIdArray;
   }
 
   public void setMaterialIdArray(String materialIdArray) {
     this.materialIdArray = materialIdArray;
   }
 
   public BigDecimal getMemberPrice() {
     return this.memberPrice;
   }
 
   public void setMemberPrice(BigDecimal memberPrice) {
     this.memberPrice = memberPrice;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public BigDecimal getPrice() {
     return this.price;
   }
 
   public void setPrice(BigDecimal price) {
     this.price = price;
   }
 
   public BigDecimal getPriceFormat() {
     return BigDecimalUtil.format(this.price);
   }
 
   public Integer getPungentLevel() {
     return this.pungentLevel;
   }
 
   public void setPungentLevel(Integer pungentLevel) {
     this.pungentLevel = pungentLevel;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public Float getSaleNum() {
     return this.saleNum;
   }
 
   public void setSaleNum(Float saleNum) {
     this.saleNum = saleNum;
   }
 
   public Integer getShowSeq() {
     return this.showSeq;
   }
 
   public void setShowSeq(Integer showSeq) {
     this.showSeq = showSeq;
   }
 
   public String getTasteIdArray() {
     return this.tasteIdArray;
   }
 
   public void setTasteIdArray(String tasteIdArray) {
     this.tasteIdArray = tasteIdArray;
   }
 
   public Integer getTotalScore() {
     return this.totalScore;
   }
 
   public void setTotalScore(Integer totalScore) {
     this.totalScore = totalScore;
   }
 
   public DishesCategory getDishesCategory() {
     return this.dishesCategory;
   }
 
   public void setDishesCategory(DishesCategory dishesCategory) {
     this.dishesCategory = dishesCategory;
   }
 
   public DishesUnit getDishesUnit() {
     return this.dishesUnit;
   }
 
   public void setDishesUnit(DishesUnit dishesUnit) {
     this.dishesUnit = dishesUnit;
   }
 
   public Set<DishesAppraise> getDishesAppraises() {
     return this.dishesAppraises;
   }
 
   public void setDishesAppraises(Set<DishesAppraise> dishesAppraises) {
     this.dishesAppraises = dishesAppraises;
   }
 
   public Set<OrderBillDishe> getOrderBillDishes() {
     return this.orderBillDishes;
   }
 
   public void setOrderBillDishes(Set<OrderBillDishe> orderBillDishes) {
     this.orderBillDishes = orderBillDishes;
   }
 
   public Set<RestaurantRecommendDishe> getSestaurantRecommendDishes() {
     return this.sestaurantRecommendDishes;
   }
 
   public void setSestaurantRecommendDishes(Set<RestaurantRecommendDishe> sestaurantRecommendDishes)
   {
     this.sestaurantRecommendDishes = sestaurantRecommendDishes;
   }
 
   public Integer getShowSeqTakeout() {
     return this.showSeqTakeout;
   }
 
   public void setShowSeqTakeout(Integer showSeqTakeout) {
     this.showSeqTakeout = showSeqTakeout;
   }
 
   public Integer getShowSeqRecommend() {
     return this.showSeqRecommend;
   }
 
   public void setShowSeqRecommend(Integer showSeqRecommend) {
     this.showSeqRecommend = showSeqRecommend;
   }
 
   public List<DishesPic> getDishesPics() {
     return this.dishesPics;
   }
 
   public void setDishesPics(List<DishesPic> dishesPics) {
     this.dishesPics = dishesPics;
   }
 
   public String getIsUserDefined() {
     return this.isUserDefined;
   }
 
   public void setIsUserDefined(String isUserDefined) {
     this.isUserDefined = isUserDefined;
   }
 
   public String getCategoryId() {
     return this.dishesCategory != null ? this.dishesCategory.getCategoryId() : "";
   }
 
   public void setCategoryId(String categoryId) {
     this.categoryId = categoryId;
   }
 
   public String getCategoryCode() {
     return this.dishesCategory != null ? this.dishesCategory.getCategoryCode() : "";
   }
 
   public void setCategoryCode(String categoryCode) {
     this.categoryCode = categoryCode;
   }
 
   public Float getEstimate() {
     return this.estimate;
   }
 
   public void setEstimate(Float estimate) {
     this.estimate = estimate;
   }
 
   public String getDishesUnitName() {
     return this.dishesUnit != null ? this.dishesUnit.getName() : this.dishesUnitName;
   }
 
   public void setDishesUnitName(String dishesUnitName) {
     this.dishesUnitName = dishesUnitName;
   }
 
   public String getUnitId() {
     return this.dishesUnit != null ? this.dishesUnit.getUnitId() : this.unitId;
   }
 
   public void setUnitId(String unitId) {
     this.unitId = unitId;
   }
 
   public String getDishAndSetDiv() {
     return this.dishAndSetDiv;
   }
 
   public void setDishAndSetDiv(String dishAndSetDiv) {
     this.dishAndSetDiv = dishAndSetDiv;
   }
 
   public String getSelfDishId() {
     return this.selfDishId;
   }
 
   public void setSelfDishId(String selfDishId) {
     this.selfDishId = selfDishId;
   }
 
   public Integer getSelfDishSaleNum() {
     return this.selfDishSaleNum;
   }
 
   public void setSelfDishSaleNum(Integer selfDishSaleNum) {
     this.selfDishSaleNum = selfDishSaleNum;
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
 
   public String getDsDishesId() {
     return this.dsDishesId;
   }
 
   public void setDsDishesId(String dsDishesId) {
     this.dsDishesId = dsDishesId;
   }
 
   public String getIsOrdered() {
     return this.isOrdered;
   }
 
   public void setIsOrdered(String isOrdered) {
     this.isOrdered = isOrdered;
   }
 
   public BigDecimal getDsUnitNum() {
     return this.dsUnitNum;
   }
 
   public void setDsUnitNum(BigDecimal dsUnitNum) {
     this.dsUnitNum = dsUnitNum;
   }
 
   public String getPicUrl() {
     return this.picUrl;
   }
 
   public void setPicUrl(String picUrl) {
     this.picUrl = picUrl;
   }
 
   public BigDecimal getReallyPrice() {
     return this.reallyPrice;
   }
 
   public void setReallyPrice(BigDecimal reallyPrice) {
     this.reallyPrice = reallyPrice;
   }
 
   public BigDecimal getSpecialPrice() {
     return this.specialPrice;
   }
 
   public void setSpecialPrice(BigDecimal specialPrice) {
     this.specialPrice = specialPrice;
   }
 
   public void setIsInRestUse(String isInRestUse) {
     this.isInRestUse = isInRestUse;
   }
 
   public String getIsInRestUse() {
     return this.isInRestUse;
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
 
   public String getRootCategoryId() {
     return this.rootCategoryId;
   }
 
   public void setRootCategoryId(String rootCategoryId) {
     this.rootCategoryId = rootCategoryId;
   }
   
   
   public String getOriCostStr() {
	return price.toString();
}



public void setPriceFormat(BigDecimal priceFormat) {
	this.priceFormat = priceFormat;
}

@JsonIgnore
   public List<DishesPic> getCommonDishesPics() {
     ArrayList commanDishesPics = new ArrayList();
     for (DishesPic e : this.dishesPics) {
       if (PicTypeEnum.COMMON.getCode().equals(e.getPicType())) {
         commanDishesPics.add(e);
       }
     }
     return commanDishesPics;
   }
 }

