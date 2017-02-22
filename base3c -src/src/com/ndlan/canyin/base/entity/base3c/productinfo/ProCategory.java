package com.ndlan.canyin.base.entity.base3c.productinfo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;

/**
 * 商品分类表
 * @author zhangts
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "base3c_pro_category")
@JsonIgnoreProperties( { "handler", "hibernateLazyInitializer" })
public class ProCategory extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "category_id", unique = true, nullable = false, length = 36)
	private String categoryId;// 品类id String（36）

	@Column(name = "category_name")
	private String categoryName;// 品类名称 String（255）

	@Column(name = "category_tone_ios")
	private String categoryToneIos;// ios颜色值 String（36）

	@Column(name = "category_tone_android")
	private String categoryToneAndroid;// android颜色值 String（36）

	@Column(name = "parent_category_id")
	private String parentCategoryId;// 父类id String（36）

	@Column(name = "parent_category_name")
	private String parentCategoryName;// 父类名称 String（255）

	@JsonIgnore
	@Column(name = "rest_id")
	private String restId;// 店id String（36）
	
	@JsonIgnore
	@Column(name = "category_status")
	private String categoryStatus;// 品类状态 String（32）

	@JsonIgnore
	@Column(name = "category_grade")
	private String categoryGrade;// 品类等级 String（20）

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryToneIos() {
		return categoryToneIos;
	}

	public void setCategoryToneIos(String categoryToneIos) {
		this.categoryToneIos = categoryToneIos;
	}

	public String getCategoryToneAndroid() {
		return categoryToneAndroid;
	}

	public void setCategoryToneAndroid(String categoryToneAndroid) {
		this.categoryToneAndroid = categoryToneAndroid;
	}

	public String getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getParentCategoryName() {
		return parentCategoryName;
	}

	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}


	public String getRestId() {
		return restId;
	}

	public void setRestId(String restId) {
		this.restId = restId;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public String getCategoryGrade() {
		return categoryGrade;
	}

	public void setCategoryGrade(String categoryGrade) {
		this.categoryGrade = categoryGrade;
	}

}
