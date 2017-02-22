/**
 * 
 */
package com.ndlan.canyin.base.entity.base3c.productinfo;

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
 * @Description:支付明细表
 * @author zhangts
 * @date: 2016-1-11 上午10:12:09
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "base3c_pay_item")
@JsonIgnoreProperties( { "handler", "hibernateLazyInitializer" })
public class PayItem extends BaseEntity implements Serializable {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "p_id", unique = true, nullable = false, length = 36)
	private String pId; // 主键 String(36)

	@Column(name = "b_id")
	private String bId;// 销售总表Id String（36）

	@Column(name = "p_no")
	private String pNo;// 支付帐单号 String（36）

	@Column(name = "pay_type")
	private String payType;// 支付方式 String（36）

	@Column(name = "pay_amount")
	private String payAmount;// 支付金额 double

	@Column(name = "pay_status")
	private String payStatus; // 支付状态 String（36）

	@Column(name = "rest_id")
	private String restId;// 店Id String（36）

	@Column(name = "currency")
	private String currency;// 币种(CNY/USD) String（20）

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public String getpNo() {
		return pNo;
	}

	public void setpNo(String pNo) {
		this.pNo = pNo;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getRestId() {
		return restId;
	}

	public void setRestId(String restId) {
		this.restId = restId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
