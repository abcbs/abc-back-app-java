package com.ndlan.canyin.base.entity.manager;

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
@Table(name="cm_sms")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class zhuceMobile extends BaseEntity
implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 
	  @Id
	   @GeneratedValue(generator="system-uuid")
	   @GenericGenerator(name="system-uuid", strategy="uuid")
	   @Column(name="id", unique=true, nullable=false, length=36)
	   private String id;
	
	 
	 public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="UserID", length=255)
	   private String UserID;
	 
	 @Column(name="Account", length=255)
	   private String Account;
	 
	 @Column(name="Password", length=255)
	   private String Password;
	 
	 @Column(name="url", length=255)
	   private String url;
	 
	 @Column(name="status", length=11)
	   private Integer status;



	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	 
	 
	 

}
