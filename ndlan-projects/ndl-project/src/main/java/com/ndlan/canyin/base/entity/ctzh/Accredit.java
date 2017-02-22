package com.ndlan.canyin.base.entity.ctzh;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;

@Entity
@Table(name = "cm_accredit")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class Accredit extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	// 授权码id
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "acc_id", unique = true, nullable = false, length = 36)
	private String accId;
	// 授权码状态  0未使用 1使用
	@Column(name = "acc_status", length = 128)
	private String accStatus;
	//连锁店id
	@Column(name = "store_id", length = 36)
	private String storeId;

	public String getAccId() {
		return accId;
	}
	
	public void setAccId(String accId) {
		this.accId = accId;
	}

	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

}
