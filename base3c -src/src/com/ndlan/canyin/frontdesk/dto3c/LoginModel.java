package com.ndlan.canyin.frontdesk.dto3c;
/**
 * 
 * @Description:
 * @author: fangmeiyu
 * @date: Jan 8, 2016 5:24:27 PM
 */
public class LoginModel {

	private String restId;//诺德兰商户号
	private String mId;//腾势商户号
	private String restName;//店名称
	private String restpic;//logo图片
	private String adrDetail;//店地址
	private String juridicalPhone;//法人手机号
	private String name;//收银员姓名
	private String userRole;//员工权限(0为管理员权限，1为收银员权限)
	public String getRestId() {
		return restId;
	}
	public void setRestId(String restId) {
		this.restId = restId;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public String getRestpic() {
		return restpic;
	}
	public void setRestpic(String restpic) {
		this.restpic = restpic;
	}
	public String getAdrDetail() {
		return adrDetail;
	}
	public void setAdrDetail(String adrDetail) {
		this.adrDetail = adrDetail;
	}
	public String getJuridicalPhone() {
		return juridicalPhone;
	}
	public void setJuridicalPhone(String juridicalPhone) {
		this.juridicalPhone = juridicalPhone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
}
