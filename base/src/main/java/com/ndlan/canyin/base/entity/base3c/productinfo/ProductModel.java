package com.ndlan.canyin.base.entity.base3c.productinfo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductModel implements Serializable {

	private String proId;
	
	
	private String trademark;//品牌
	
	private String officialBarCode ;//官方条码
	
	private String name;// 名称 String（255）
	
	private String proDesc;// 商品描述 String（255）
	
	private String itemNo ; //产品型号
	private String recommendedPrice; // 官方建议价
	
	private String pic;// 图片 String（100）
	
	private BigDecimal quantity;// 数量 double
	
	private BigDecimal primeCost;// 进货价 double
	
	private BigDecimal price;// 零售价格 double
	
	private BigDecimal discount;// 折扣比例 double
	
	private BigDecimal privilege;// 优惠金额 double
	
	private String promotionStatus;// 促销状态（0不促销1促销） String（2）
	
	private BigDecimal promotionPrice;// 促销价格 double
	
	private String barCode;// 条码 String（50）
	
	private BigDecimal affterDiscount;// 折后价 double
	
	private String restId;// 店Id String（36）
	
	private String status;// 可用状态（0下架1可用） String（20）
	
	private String categoryToneIos; // ios 颜色
	private String categoryToneAndroid ;// android颜色值
	
	private String discountType;// 折扣类型（0比例1金额） String（2）

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public String getOfficialBarCode() {
		return officialBarCode;
	}

	public void setOfficialBarCode(String officialBarCode) {
		this.officialBarCode = officialBarCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getRecommendedPrice() {
		return recommendedPrice;
	}

	public void setRecommendedPrice(String recommendedPrice) {
		this.recommendedPrice = recommendedPrice;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrimeCost() {
		return primeCost;
	}

	public void setPrimeCost(BigDecimal primeCost) {
		this.primeCost = primeCost;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getPrivilege() {
		return privilege;
	}

	public void setPrivilege(BigDecimal privilege) {
		this.privilege = privilege;
	}

	public String getPromotionStatus() {
		return promotionStatus;
	}

	public void setPromotionStatus(String promotionStatus) {
		this.promotionStatus = promotionStatus;
	}

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public BigDecimal getAffterDiscount() {
		return affterDiscount;
	}

	public void setAffterDiscount(BigDecimal affterDiscount) {
		this.affterDiscount = affterDiscount;
	}

	public String getRestId() {
		return restId;
	}

	public void setRestId(String restId) {
		this.restId = restId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategoryToneIos() {
		return categoryToneIos;
	}

	public void setCategoryToneIos(String categoryToneIos) {
		this.categoryToneIos = categoryToneIos;
	}

	public String getCategoryToneAndroid() {
		return categoryToneAndroid;
	}

	public void setCategoryToneAndroid(String categoryToneAndroid) {
		this.categoryToneAndroid = categoryToneAndroid;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	
//
//	@Column(name = "pro_no")
//	private String proNo;// 商品编码 String（50）


}
