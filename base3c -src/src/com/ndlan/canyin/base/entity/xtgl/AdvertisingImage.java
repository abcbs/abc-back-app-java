package com.ndlan.canyin.base.entity.xtgl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ndlan.canyin.base.entity.BaseEntity;

/**
 * 广告图片实体
 * @author zhengjiansong
 *
 */
@Entity
@Table(name = "cm_advertising_image")
public class AdvertisingImage extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "img_id", unique = true, nullable = false, length = 36)
	private String imgId;
	
	@Column(name = "rest_id", length = 36)
	private String restId;

	@Column(name = "img_desc", length = 255)
	private String imgDesc;

	@Column(name = "img_path", length = 255)
	private String imgPath;
	
	@Column(name = "img_name", length = 255)
	private String imgName;

	@Column(name = "img_index", length = 11)
	private int imgIndex;

	public String getImgId() {
		return imgId;
	}

	
	public String getImgDesc() {
		return imgDesc;
	}


	public String getImgName() {
		return imgName;
	}


	public void setImgName(String imgName) {
		this.imgName = imgName;
	}


	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}


	public String getImgPath() {
		return imgPath;
	}


	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}


	public int getImgIndex() {
		return imgIndex;
	}


	public void setImgIndex(int imgIndex) {
		this.imgIndex = imgIndex;
	}


	public void setImgId(String imgId) {
		this.imgId = imgId;
	}


	public String getRestId() {
		return restId;
	}

	public void setRestId(String restId) {
		this.restId = restId;
	}
	
	

}	
