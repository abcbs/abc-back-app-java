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
 * @Description: 账单总表
 * @author: qipeng
 * @date: 2016年1月7日 下午2:51:45  
 */
@Entity
@Table(name = "base3c_bill")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class BillEntity  extends BaseEntity
implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "b_id", unique = true, nullable = false, length = 36)
	private String bId;
	
	@Column(name = "cart_id", length = 36)
	private String cartId;
	
	@Column(name = "b_no", length = 36)
	private String bNo;
	
	@Column(name = "b_name", length = 64)
	private String bName;
	
	@Column(name = "b_amount", length = 36)
	private String bAmount;
	
	@Column(name = "mb_id", length = 36)
	private String mbId;
	
	@Column(name = "customer_name", length = 50)
	private String customerName;
	
	@Column(name = "amount_paid", length = 36)
	private String amountPaid;
	
	@Column(name = "pay_type", length = 36)
	private String payType;
	
	@Column(name = "discount_type", length = 36)
	private String discountType;
	
	@Column(name = "pay_status", length = 36)
	private String payStatus;
	@Column(name = "status", length = 2)
	private String status;
	@Column(name = "order_privilege", length = 100)
	private String orderPrivilege;
	@Column(name = "all_privilege", length = 36)
	private String allPrivilege;

	@Column(name = "all_discount", length = 36)
	private String allDiscount;
	
	@Column(name = "codeless_sum", length = 36)
	private String codelessSum;
	
	@Column(name = "rest_id", length = 36)
	private String restId;

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getbNo() {
		return bNo;
	}

	public void setbNo(String bNo) {
		this.bNo = bNo;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbAmount() {
		return bAmount;
	}

	public void setbAmount(String bAmount) {
		this.bAmount = bAmount;
	}

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getOrderPrivilege() {
		return orderPrivilege;
	}

	public void setOrderPrivilege(String orderPrivilege) {
		this.orderPrivilege = orderPrivilege;
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

	public String getAllPrivilege() {
		return allPrivilege;
	}

	public void setAllPrivilege(String allPrivilege) {
		this.allPrivilege = allPrivilege;
	}

	public String getAllDiscount() {
		return allDiscount;
	}

	public void setAllDiscount(String allDiscount) {
		this.allDiscount = allDiscount;
	}

	public String getCodelessSum() {
		return codelessSum;
	}

	public void setCodelessSum(String codelessSum) {
		this.codelessSum = codelessSum;
	}

	public String getRestId() {
		return restId;
	}

	public void setRestId(String restId) {
		this.restId = restId;
	}
	
	
}
