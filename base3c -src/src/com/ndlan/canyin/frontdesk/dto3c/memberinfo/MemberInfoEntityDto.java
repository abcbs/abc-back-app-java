package com.ndlan.canyin.frontdesk.dto3c.memberinfo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;

public class MemberInfoEntityDto  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	   private String brithdayDay;
	 
	   private String edu;
	 
	   private String email;
	 
	   private String gender;
	 
	   private String mobile;
	 
	   private String name;
	 
	   private String notes;
	 
	   private String work;
	 
	   private String salesmanId;
	   
	   private String salesmanName;
	   
	   private String balance;//余额
	 
	   private String cardNo;//卡号
	   
	   private String memberIntegral;//积分
	   
	   private String cardStatus;//状态
	   
	    private String cardType;
	
	    private String mcId;//会员卡Id
	    
	    private String createTime;
	    
	    private String mbId;//会员主键


	public String getBrithdayDay() {
			return brithdayDay;
		}

		public void setBrithdayDay(String brithdayDay) {
			this.brithdayDay = brithdayDay;
		}

	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(String salesmanId) {
		this.salesmanId = salesmanId;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getMemberIntegral() {
		return memberIntegral;
	}

	public void setMemberIntegral(String memberIntegral) {
		this.memberIntegral = memberIntegral;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getMcId() {
		return mcId;
	}

	public void setMcId(String mcId) {
		this.mcId = mcId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}
	   
	   
	   
}
