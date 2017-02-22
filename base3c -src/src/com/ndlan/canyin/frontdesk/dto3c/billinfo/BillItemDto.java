/**
 * 
 */
package com.ndlan.canyin.frontdesk.dto3c.billinfo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.base3c.billinfo.BillEntity;

/**
 * @Description: TODO
 * @author: qipeng
 * @date: 2016年1月7日 下午7:20:47  
 */
public class BillItemDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String bdId;
	private String bdNo;
	private String bId;
	private String proId;
	private String CardItemId;
	private String categoryId;
	private String barCode;
	private String name;
	private String proDesc;
	private String pic;
	private String quantity;
	private String price;
	private String discount;
	private String discountType;
	private String derate;
	private String subtotal;
	private String payStatus;
	private String bdStatus;
	private String returnTotal;
	
	
	public String getReturnTotal() {
		return returnTotal;
	}
	public void setReturnTotal(String returnTotal) {
		this.returnTotal = returnTotal;
	}
	public String getCardItemId() {
		return CardItemId;
	}
	public void setCardItemId(String cardItemId) {
		this.CardItemId = cardItemId;
	}
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
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
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
	public String getBdStatus() {
		return bdStatus;
	}
	public void setBdStatus(String bdStatus) {
		this.bdStatus = bdStatus;
	}
	
}
