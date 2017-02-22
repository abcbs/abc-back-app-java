package com.ndlan.canyin.frontdesk.dto3c.shoppingcart;



import com.ndlan.canyin.base.entity.BaseEntity;

/**
 * 
 * @Description:购物车明细Dto  用于返回给前端数据
 * @author: wangwei
 * @date: 2016年1月8日 下午1:39:08
 */
public class CartItemDto{
	private static final long serialVersionUID = 1L;
	private String cardItemId;//明细主键主键
	private String cartId;//店铺主键
	private String proId;//产品主键
	private String categoryId;//分类id
	private String barCode;//条码
	private String name;//名称
	private String proDesc;//描述
	private String pic;//图片
	private String quantity;//数量
	private String price;//销售价格
	private String discount;//折扣
	private String derate;//减免
	private String subtotal;//小计（售价-减免）*数量
	private String status;//状态
	private String privilege;//优惠
	private String isCodeless;//是否无码支付（0为否1为是）
	private String discountType;//折扣类型
	private String payStatus;//结算状态（0未结账，1已结账）
	private String disAndPri;//折扣&优惠
	private String inventory;//库存
	private String categoryName;//类别
	
	
	
	
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCardItemId() {
		return cardItemId;
	}
	public void setCardItemId(String cardItemId) {
		this.cardItemId = cardItemId;
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
	public String getDisAndPri() {
		return disAndPri;
	}
	public void setDisAndPri(String disAndPri) {
		this.disAndPri = disAndPri;
	}
	
}
