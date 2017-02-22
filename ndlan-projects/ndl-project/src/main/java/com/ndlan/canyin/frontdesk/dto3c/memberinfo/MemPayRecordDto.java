/**
 * 
 */
package com.ndlan.canyin.frontdesk.dto3c.memberinfo;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: qipeng
 * @date: 2016年1月8日 下午8:40:59  
 */
public class MemPayRecordDto implements Serializable{
	private static final long serialVersionUID = 1L;
private String	id;
private String	memberNo;
private String	createTime;
private String	seller;
private String	paySum;
private String	proceeds;
private String	memberIntegral;
private String	payStatus;
private String	cardStatus;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getMemberNo() {
	return memberNo;
}
public void setMemberNo(String memberNo) {
	this.memberNo = memberNo;
}
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}
public String getSeller() {
	return seller;
}
public void setSeller(String seller) {
	this.seller = seller;
}
public String getPaySum() {
	return paySum;
}
public void setPaySum(String paySum) {
	this.paySum = paySum;
}
public String getProceeds() {
	return proceeds;
}
public void setProceeds(String proceeds) {
	this.proceeds = proceeds;
}
public String getMemberIntegral() {
	return memberIntegral;
}
public void setMemberIntegral(String memberIntegral) {
	this.memberIntegral = memberIntegral;
}
public String getPayStatus() {
	return payStatus;
}
public void setPayStatus(String payStatus) {
	this.payStatus = payStatus;
}
public String getCardStatus() {
	return cardStatus;
}
public void setCardStatus(String cardStatus) {
	this.cardStatus = cardStatus;
}


}
