package com.ndlan.canyin.base.entity.xtgl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ndlan.canyin.base.entity.BaseEntity;

@Entity
@Table(name = "cm_pay_set")
public class PaySet extends BaseEntity {
	/**
	 * 主键id
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String psid;
	/**
	 * 卖家支付宝用户 ID，或微信用户Id 卖家支付宝用户 ID，或微信用户Id
	 */
	@Column(name = "seller_id", length = 32)
	private String sellerId;
	/**
	 * 支付方式 1支付宝支付，2微信支付,在字典表维护PayType类型
	 */
	@Column(name = "pay_type", length = 11)
	private int payType;

	@Column(name = "rest_id", length = 36)
	private String restId;

	public String getPsid() {
		return psid;
	}

	public void setPsid(String psid) {
		this.psid = psid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getRestId() {
		return restId;
	}

	public void setRestId(String restId) {
		this.restId = restId;
	}

}
