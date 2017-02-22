package com.ndlan.canyin.base.entity.base3c.productinfo;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;

/**
 * 商品信息表
 * 
 * @author zhangts
 * @Date 2016/1/7
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "base3c_product")
@JsonIgnoreProperties( { "handler", "hibernateLazyInitializer" })
public class Product extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "pro_id", unique = true, nullable = false, length = 36)
	private String proId;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "base_pro_id")
	private BaseProduct baseProduct; // base3c_base_product外键 String（36） 外键

	@Column(name = "pro_no")
	private String proNo;// 商品编码 String（50）

	@Column(name = "name")
	private String name;// 名称 String（255）

	@Column(name = "quantity")
	private String quantity;// 数量 double

	@Column(name = "pro_desc")
	private String proDesc;// 商品描述 String（255）

	@Column(name = "prime_cost")
	private String primeCost;// 进货价 double

	@Column(name = "price")
	private String price;// 零售价格 double

	@Column(name = "pic")
	private String pic;// 图片 String（100）

	@Column(name = "discount")
	private String discount;// 折扣比例 double

	@Column(name = "privilege")
	private String privilege;// 优惠金额 double

	@Column(name = "discount_type")
	private String discountType;// 折扣类型（0比例1金额） String（2）

	@Column(name = "promotion_status")
	private String promotionStatus;// 促销状态（0不促销1促销） String（2）

	@Column(name = "promotion_price")
	private String promotionPrice;// 促销价格 double

	@Column(name = "bar_code")
	private String barCode;// 条码 String（50）

	@Column(name = "affter_discount")
	private String affterDiscount;// 折后价 double

	@Column(name = "rest_id")
	private String restId;// 店Id String（36）

	@Column(name = "status")
	private String status;// 可用状态（0下架1可用） String（20）

	@Column(name = "parent_category_id")
	private String parentCategoryId;// 一级品类ID String（36）

	@Column(name = "category_id")
	private String categoryId;// 二级品类ID String（36）

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	

	public BaseProduct getBaseProduct() {
		return baseProduct;
	}

	public void setBaseProduct(BaseProduct baseProduct) {
		this.baseProduct = baseProduct;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
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



	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}


	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getPromotionStatus() {
		return promotionStatus;
	}

	public void setPromotionStatus(String promotionStatus) {
		this.promotionStatus = promotionStatus;
	}


	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getRestId() {
		return restId;
	}

	public void setRestId(String restId) {
		this.restId = restId;
	}


	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrimeCost() {
		return primeCost;
	}

	public void setPrimeCost(String primeCost) {
		this.primeCost = primeCost;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public String getAffterDiscount() {
		return affterDiscount;
	}

	public void setAffterDiscount(String affterDiscount) {
		this.affterDiscount = affterDiscount;
	}

	@Override
	public String toString() {
		return "Product [affterDiscount=" + affterDiscount + ", barCode="
				+ barCode + ", baseProduct=" + baseProduct + ", categoryId="
				+ categoryId + ", discount=" + discount + ", discountType="
				+ discountType + ", name=" + name + ", parentCategoryId="
				+ parentCategoryId + ", pic=" + pic + ", price=" + price
				+ ", primeCost=" + primeCost + ", privilege=" + privilege
				+ ", proDesc=" + proDesc + ", proId=" + proId + ", proNo="
				+ proNo + ", promotionPrice=" + promotionPrice
				+ ", promotionStatus=" + promotionStatus + ", quantity="
				+ quantity + ", restId=" + restId + ", status=" + status + "]";
	}
	
	
	
	
	

}
