/**
 * 
 */
package com.ndlan.canyin.base.entity.base3c.billinfo;

import java.io.Serializable;

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
 * @Description: 账单 明细
 * @author: qipeng
 * @date: 2016年1月7日 下午3:12:29  
 */
@Entity
@Table(name = "base3c_bill_item")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class BillItemEntity  extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "bd_id", unique = true, nullable = false, length = 36)
	private String bdId;
	
	@Column(name = "bd_no", length = 36)
	private String bdNo;
	
//	@JsonIgnore
//	@ManyToOne(fetch=FetchType.LAZY)
	@Column(name="b_id")
	private String bId;
	
	@Column(name="cart_item_id")
	private String cartItemId;
	
	@Column(name = "pro_id", length = 36)
	private String proId;
	
	@Column(name = "category_id", length = 36)
	private String categoryId;
	
	@Column(name = "bar_Code", length = 36)
	private String barCode;
	
	@Column(name = "name", length = 36)
	private String name;
	@Column(name = "pro_desc", length = 36)
	private String proDesc;
	@Column(name = "pic", length = 36)
	private String pic;
	@Column(name = "quantity", length = 36)
	private String quantity;
	@Column(name = "price", length = 36)
	private String price;
	@Column(name = "privilege", length = 36)
	private String privilege;
	@Column(name = "discount", length = 36)
	private String discount;
	@Column(name = "discount_type", length = 2)
	private String discountType;
	@Column(name = "is_codeless", length = 2)
	private String isCodeless;
	@Column(name = "rest_id", length = 36)
	private String restId;
	@Column(name = "derate", length = 36)
	private String derate;
	@Column(name = "subtotal", length = 36)
	private String subtotal;
	@Column(name = "status", length = 2)
	private String status;
	@Column(name = "pay_status", length = 2)
	private String payStatus;
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
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	
	public String getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
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
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
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
	public String getIsCodeless() {
		return isCodeless;
	}
	public void setIsCodeless(String isCodeless) {
		this.isCodeless = isCodeless;
	}
	
	public String getRestId() {
		return restId;
	}
	public void setRestId(String restId) {
		this.restId = restId;
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
	
}
