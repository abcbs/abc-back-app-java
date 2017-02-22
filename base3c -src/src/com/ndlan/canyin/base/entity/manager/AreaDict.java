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

/**
 * µØÇø±àÂë×Öµä±í
 * @author wy
 *
 */

@Entity
@Table(name="cm_area_dict")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class AreaDict extends BaseEntity
implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 
	  @Id
	   @GeneratedValue(generator="system-uuid")
	   @GenericGenerator(name="system-uuid", strategy="uuid")
	   @Column(name="area_code", unique=true, nullable=false)
	   private String areaCode;
	
	@Column(name="area_name")
	   private String areaName;
	 
	 @Column(name="area_desc" )
	   private String areaDesc;
	 
	 @Column(name="parent_code" )
	   private String parentCode;

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaDesc() {
		return areaDesc;
	}

	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

}
