/**
 * 
 */
package com.ndlan.canyin.base.entity.base3c.billinfo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;

/**
 * @Description: 退货明细表
 * @author: qipeng
 * @date: 2016年1月9日 上午11:37:02  
 */
@Entity
@Table(name = "base3c_bill_return_item")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class BillReturnItemEntity extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "br_id", unique = true, nullable = false, length = 36)
	private String brId;
	@Column(name = "bd_no", length = 36)
	private String bdNo;
	@Column(name = "b_id", length = 36)
	private String bId;
	@Column(name = "bd_id", length = 36)
	private String bdId;
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
	@Column(name = "quantity", length = 100)
	private String quantity;
	@Column(name = "price", length = 100)
	private String price;
	@Column(name = "discount", length = 100)
	private String discount;
	@Column(name = "rest_id", length = 36)
	private String restId;
	@Column(name = "derate", length = 100)
	private String derate;
	@Column(name = "subtotal", length = 100)
	private String subtotal;
	@Column(name = "pay_status", length = 36)
	private String payStatus;
	@Column(name = "return_reason", length = 36)
	private String returnReason;
	@Column(name = "return_price", length = 100)
	private String returnPrice;
	@Column(name = "return_quantity", length = 100)
	private String returnQuantity;
	@Column(name = "return_total", length = 100)
	private String returnTotal;
	@Column(name = "return_type", length = 36)
	private String returnType;
	public String getBrId() {
		return brId;
	}
	public void setBrId(String brId) {
		this.brId = brId;
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
	public String getBdId() {
		return bdId;
	}
	public void setBdId(String bdId) {
		this.bdId = bdId;
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
