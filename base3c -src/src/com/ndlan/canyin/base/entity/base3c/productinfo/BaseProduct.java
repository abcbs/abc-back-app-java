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
 * 商品基本信息表
 * @author john
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "base3c_base_product")
@JsonIgnoreProperties( { "handler", "hibernateLazyInitializer" })
public class BaseProduct extends BaseEntity implements Serializable{
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "base_pro_id", unique = true, nullable = false, length = 36)
	private String baseProId;
	
	
	@Column(name = "trademark")
	private String trademark;//	品牌	String（50）
	
	@Column(name = "official_bar_code")
	private String officialBarCode;//	官方条码	String（50）
	
	@Column(name = "official_name")
	private String officialName;//	官方名称	String（255）
	
	@Column(name = "official_desc")
	private String officialDesc;//	官方描述	String（255）
	
	@Column(name = "item_no")
	private String itemNo;//	产品型号	String（255）
	
	@Column(name = "recommended_price")
	private String recommendedPrice;//	官方建议价	double
	
	
	public String getBaseProId() {
		return baseProId;
	}
	public void setBaseProId(String baseProId) {
		this.baseProId = baseProId;
	}
	public String getTrademark() {
		return trademark;
	}
	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}
	public String getOfficialBarCode() {
		return officialBarCode;
	}
	public void setOfficialBarCode(String officialBarCode) {
		this.officialBarCode = officialBarCode;
	}
	public String getOfficialName() {
		return officialName;
	}
	public void setOfficialName(String officialName) {
		this.officialName = officialName;
	}
	public String getOfficialDesc() {
		return officialDesc;
	}
	public void setOfficialDesc(String officialDesc) {
		this.officialDesc = officialDesc;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getRecommendedPrice() {
		return recommendedPrice;
	}
	public void setRecommendedPrice(String recommendedPrice) {
		this.recommendedPrice = recommendedPrice;
	}


}
