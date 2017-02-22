package com.ndlan.canyin.base.entity.base3c.shoppingcart;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;

/**
 * @Description: 购物车实体类
 * @author: wangwei
 * @date: 2016年1月8日 下午1:37:19
 */
@Entity
@Table(name = "base3c_shopping_cart")
public class CartEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "cart_id", unique = true, nullable = false, length = 36)
	private String cartId;//店铺主键
	@Column(name = "customer_name", length = 50)
	private String customerName;//顾客姓名
	@Column(name="total", length = 100)
	private String total;//总金额（明细小计+无码支付）*整单折扣|或者|（明细小计总和+无码支付）-整单优惠
	@Column(name = "status", length = 20)
	private String status;//状态
	@Column(name = "rest_id", length = 36)
	private String restId;//店铺id
	@Column(name = "mb_id", length = 36)
	private String mbId;//会员id
	@Column(name="all_privilege", length = 100)
	private String allPrivilege;//整单优惠
	@Column(name="codeless_sum", length = 100)
	private String codelessSum;//无码支付
	@Column(name="all_discount", length = 100)
	private String allDiscount;//整单折扣
	@Column(name="discount_type", length = 2)
	private String discountType;//折扣类型
//	折扣总金额
	@Column(name="befor_discount_total", length = 100)
	private String beforDiscountTotal ;
	
//	折扣或者优惠标记
	@Column(name="is_discount_privilege", length = 100)
	private String isDiscountPrivilege ;


	@Transient
	@OneToMany(mappedBy = "cartEntity", cascade = { javax.persistence.CascadeType.ALL })
	private List<CartItemEntity> cartItemEntities;
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRestId() {
		return restId;
	}
	public void setRestId(String restId) {
		this.restId = restId;
	}
	public String getMbId() {
		return mbId;
	}
	public void setMbId(String mbId) {
		this.mbId = mbId;
	}
	public String getAllPrivilege() {
		return allPrivilege;
	}
	public void setAllPrivilege(String allPrivilege) {
		this.allPrivilege = allPrivilege;
	}
	public String getCodelessSum() {
		return codelessSum;
	}
	public void setCodelessSum(String codelessSum) {
		this.codelessSum = codelessSum;
	}
	public String getAllDiscount() {
		return allDiscount;
	}
	public void setAllDiscount(String allDiscount) {
		this.allDiscount = allDiscount;
	}
	@Transient
	public List<CartItemEntity> getCartItemEntities() {
		return cartItemEntities;
	}
	public void setCartItemEntities(List<CartItemEntity> cartItemEntities) {
		this.cartItemEntities = cartItemEntities;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public String getBeforDiscountTotal() {
		return beforDiscountTotal;
	}
	public void setBeforDiscountTotal(String beforDiscountTotal) {
		this.beforDiscountTotal = beforDiscountTotal;
	}
	public String getIsDiscountPrivilege() {
		return isDiscountPrivilege;
	}
	public void setIsDiscountPrivilege(String isDiscountPrivilege) {
		this.isDiscountPrivilege = isDiscountPrivilege;
	}
	
	
}
