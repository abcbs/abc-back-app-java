package com.ndlan.canyin.base.entity.ctzh;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.ImmutableList;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.ComplaintSuggest;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.RestaurantAppraise;
import com.ndlan.canyin.base.entity.ctzh.RestaurantPic;
import com.ndlan.canyin.base.entity.ctzh.TableArea;
import com.ndlan.canyin.core.common.PicTypeEnum;
import com.ndlan.canyin.core.utils.Collections3;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 
 * @author 
 * 
 */
@Entity        
@Table(name = "cm_signin_authcode")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class SignInAuthCode extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(name = "auth_id", unique = true, nullable = false, length = 36)
	private String authId;
	
	@Column(name = "auth_code", unique = true, nullable = false, length = 36)
	private String authCode;
	
	@Column(name="code")
	private String code;
	
	@Column(name="agent_Name")
	private String agentName;
	
	@Column(name="agent_no")
	private String agentNo;
	
	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Column(name="msg")
	private String msg;

}
