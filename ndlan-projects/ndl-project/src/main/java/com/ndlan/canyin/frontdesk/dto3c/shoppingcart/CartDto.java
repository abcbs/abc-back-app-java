package com.ndlan.canyin.frontdesk.dto3c.shoppingcart;



import java.util.Date;
import java.util.List;

/**
 * 
 * @Description:购物车Dto用于返回给前端数据
 * @author: wangwei
 * @date: 2016年1月8日 下午1:38:48
 */
public class CartDto{
	private static final long serialVersionUID = 1L;
	private String casherId;//收银员id
	private String cartId;//购物车主键
	private String customerName;//顾客姓名
	private String total;//总金额（明细小计+无码支付）*整单折扣|或者|（明细小计总和+无码支付）-整单优惠
	private String status;//状态
	private String mbId;//会员id
	private String allPrefer;//整单优惠
	private String codelessSum;//无码支付
	private String allDiscount;//整单折扣
	private String createTime;
	private List<CartItemDto> cartItems;
	
	
	public String getCasherId() {
		return casherId;
	}
	public void setCasherId(String casherId) {
		this.casherId = casherId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
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
	public String getMbId() {
		return mbId;
	}
	public void setMbId(String mbId) {
		this.mbId = mbId;
	}
	
	public String getAllPrefer() {
		return allPrefer;
	}
	public void setAllPrefer(String allPrefer) {
		this.allPrefer = allPrefer;
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
	public List<CartItemDto> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItemDto> cartItems) {
		this.cartItems = cartItems;
	}
	
	
}
