package com.ndlan.canyin.base.entity.qtsy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ndlan.canyin.base.entity.BaseEntity;

@Entity
@Table(name = "cm_coupons")
public class Coupons extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "cou_id", unique = true, nullable = false, length = 36)
	private String couId;

	@Column(name = "cou_type")
	private String couType;

	@Column(name = "cou_no")
	private String couNo;

	@Column(name = "cou_amount")
	private Double couAmount;

	@Column(name = "cou_range_type")
	private String couRangeType;

	@Column(name = "cou_company")
	private String couCompany;

	public String getCouId() {
		return couId;
	}

	public void setCouId(String couId) {
		this.couId = couId;
	}

	public String getCouType() {
		return couType;
	}

	public void setCouType(String couType) {
		this.couType = couType;
	}

	public String getCouNo() {
		return couNo;
	}

	public void setCouNo(String couNo) {
		this.couNo = couNo;
	}

	public Double getCouAmount() {
		return couAmount;
	}

	public void setCouAmount(Double couAmount) {
		this.couAmount = couAmount;
	}

	public String getCouRangeType() {
		return couRangeType;
	}

	public void setCouRangeType(String couRangeType) {
		this.couRangeType = couRangeType;
	}

	public String getCouCompany() {
		return couCompany;
	}

	public void setCouCompany(String couCompany) {
		this.couCompany = couCompany;
	}

}
