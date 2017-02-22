package com.ndlan.canyin.frontdesk.dto3c.employee;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
/**
 * 
 * @Description:
 * @author: fangmeiyu
 * @date: Jan 9, 2016 7:42:31 PM
 */
public class EmployeeDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String payerId;//主键
	private String name;//员工姓名
	private String empNum;//员工工号
	private String mobile;//手机号
	private String jobPic;//照片
	private String loginPassword;//密码
	private String createBy;//创建日期
	private String status;//状态
	private String shopId;//店Id
	
	public String getPayerId() {
		return payerId;
	}
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmpNum() {
		return empNum;
	}
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getJobPic() {
		return jobPic;
	}
	public void setJobPic(String jobPic) {
		this.jobPic = jobPic;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	
	
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	
}
