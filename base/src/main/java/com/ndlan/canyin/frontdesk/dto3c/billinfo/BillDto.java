/**
 * 
 */
package com.ndlan.canyin.frontdesk.dto3c.billinfo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Description: TODO
 * @author: qipeng
 * @date: 2016年1月7日 下午6:38:48  
 */
public class BillDto  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String bId;
	private String bNo;
	private String bName;
	private String bAmount;
	private String mbId;
	private String customerName;
	private String amountPaid;
	private String payType;
	private String discountType;
	private String payStatus;
	private String allDiscount;
	private String createBy;
	private Date createTime;
	private String casherId;
	private String cardId;
	
	public String getCasherId() {
		return casherId;
	}

	public void setCasherId(String casherId) {
		this.casherId = casherId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
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

	public String getAllDiscount() {
		return allDiscount;
	}

	public void setAllDiscount(String allDiscount) {
		this.allDiscount = allDiscount;
	}



	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	   
	   
}
