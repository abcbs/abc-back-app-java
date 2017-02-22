/**
 * 
 */
package com.ndlan.canyin.frontdesk.dto3c.billinfo;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: qipeng
 * @date: 2016年1月9日 下午12:08:24  
 */
public class BillReturnItemDto  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String bdId;
	private String bdNo;
	private String bId;
	private String proId;
	private String categoryId;
	private String barCode;
	private String name;
	private String proDesc;
	private String pic;
	private String quantity;
	private String price;
	private String discount;
	private String shopId;
	private String derate;
	private String subtotal;
	private String payStatus;
	private String returnReason;
	private String returnPrice;
	private String returnQuantity;
	private String returnTotal;
	private String returnType;
	public String getBdId() {
		return bdId;
	}
	public void setBdId(String bdId) {
		this.bdId = bdId;
	}
	public String getBdNo() {
		return bdNo;
	}
	public void setBdNo(String bdNo) {
		this.bdNo = bdNo;
	}
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
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
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
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
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public String getReturnPrice() {
		return returnPrice;
	}
	public void setReturnPrice(String returnPrice) {
		this.returnPrice = returnPrice;
	}
	public String getReturnQuantity() {
		return returnQuantity;
	}
	public void setReturnQuantity(String returnQuantity) {
		this.returnQuantity = returnQuantity;
	}
	public String getReturnTotal() {
		return returnTotal;
	}
	public void setReturnTotal(String returnTotal) {
		this.returnTotal = returnTotal;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

}
