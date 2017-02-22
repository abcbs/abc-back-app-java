package com.ndlan.canyin.base.entity.base3c.shoppingcart;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.ndlan.canyin.base.entity.BaseEntity;

/**
 * @Description:购物车明细实体类
 * @author: wangwei
 * @date: 2016年1月8日 下午1:37:39
 */
@Entity
@Table(name = "base3c_shopping_cart_item")
public class CartItemEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "cart_item_id", unique = true, nullable = false, length = 36)
	private String cartItemId;//明细主键主键
	@Column(name = "cart_id", length = 36)
	private String cartId;//购物车主键
	@Column(name = "rest_id", length = 36)
	private String restId;//店铺主键
	@Column(name = "pro_id", length = 36)
	private String proId;//产品主键
	@Column(name = "category_id", length = 36)
	private String categoryId;//分类id
	@Column(name = "bar_code", length = 36)
	private String barCode;//条码
	@Column(name = "name", length = 36)
	private String name;//名称
	@Column(name = "pro_desc", length = 255)
	private String proDesc;//描述
	@Column(name = "pic", length = 36)
	private String pic;//图片
	@Column(name="quantity", length = 100)
	private String quantity;//数量
	@Column(name="price", length = 100)
	private String price;//销售价格
	@Column(name="discount" , length = 100)
	private String discount;//折扣
	@Column(name="derate", length = 100)
	private String derate;//减免
	@Column(name="subtotal", length = 100)
	private String subtotal;//小计（售价-减免）*数量
	@Column(name = "status", length = 20)
	private String status;//状态
	@Column(name="privilege", length = 100)
	private String privilege;//优惠
	@Column(name = "is_codeless", length = 2)
	private String isCodeless;//是否无码支付（0为否1为是）
	@Column(name = "discount_type", length = 20)
	private String discountType;//折扣类型
	@Column(name = "pay_status", length = 2)
	private String payStatus;//结算状态（0未结账，1已结账）
	@Column(name="is_discount_privilege",length=2)
	private String isDiscountOrPrivate;//优惠还是折扣  0折扣 1优惠
	/*@Transient
	private CartEntity cartEntity;*/
	
	public String getRestId() {
		return restId;
	}
	public String getIsDiscountOrPrivate() {
		return isDiscountOrPrivate;
	}
	public void setIsDiscountOrPrivate(String isDiscountOrPrivate) {
		this.isDiscountOrPrivate = isDiscountOrPrivate;
	}
	public void setRestId(String restId) {
		this.restId = restId;
	}
	public String getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
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
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
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
	public String getDerate() {
		return derate;
	}
	public void setDerate(String derate) {
		this.derate = derate;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public String getIsCodeless() {
		return isCodeless;
	}
	public void setIsCodeless(String isCodeless) {
		this.isCodeless = isCodeless;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	/*@Transient
	public CartEntity getCartEntity() {
		return cartEntity;
	}
	@Transient
	public void setCartEntity(CartEntity cartEntity) {
		this.cartEntity = cartEntity;
	}*/
	
	
}
