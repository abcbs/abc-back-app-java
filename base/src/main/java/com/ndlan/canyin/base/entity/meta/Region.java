 package com.ndlan.canyin.base.entity.meta;
 
 import com.ndlan.canyin.base.entity.BaseEntity;

 import java.io.Serializable;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="md_region")
 public class Region extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="region_id", unique=true, nullable=false, length=36)
   private String regionId;
 
   @Column(name="city_code", length=32)
   private String cityCode;
 
   @Column(name="city_name", length=128)
   private String cityName;
 
   @Column(name="is_enable", length=1)
   private String isEnable;
 
   @Column(name="province_code", length=32)
   private String provinceCode;
 
   @Column(name="province_name", length=128)
   private String provinceName;
 
   @Column(name="region_div", length=32)
   private String regionDiv;
 
   @Column(name="show_seq")
   private int showSeq;
 
   @Column(name="area_code", length=32)
   private String areaCode;
 
   @Column(name="area_name", length=128)
   private String areaName;
 
   @Transient
   private String pinyin;
 
   public String getRegionId()
   {
     return this.regionId;
   }
 
   public void setRegionId(String regionId) {
     this.regionId = regionId;
   }
 
   public String getPinyin() {
     return this.pinyin;
   }
 
   public void setPinyin(String pinyin) {
     this.pinyin = pinyin;
   }
 
   public String getCityCode() {
     return this.cityCode;
   }
 
   public void setCityCode(String cityCode) {
     this.cityCode = cityCode;
   }
 
   public String getCityName() {
     return this.cityName;
   }
 
   public void setCityName(String cityName) {
     this.cityName = cityName;
   }
 
   public String getIsEnable() {
     return this.isEnable;
   }
 
   public void setIsEnable(String isEnable) {
     this.isEnable = isEnable;
   }
 
   public String getProvinceCode() {
     return this.provinceCode;
   }
 
   public void setProvinceCode(String provinceCode) {
     this.provinceCode = provinceCode;
   }
 
   public String getProvinceName() {
     return this.provinceName;
   }
 
   public void setProvinceName(String provinceName) {
     this.provinceName = provinceName;
   }
 
   public String getRegionDiv() {
     return this.regionDiv;
   }
 
   public void setRegionDiv(String regionDiv) {
     this.regionDiv = regionDiv;
   }
 
   public int getShowSeq() {
     return this.showSeq;
   }
 
   public void setShowSeq(int showSeq) {
     this.showSeq = showSeq;
   }
 
   public String getAreaCode() {
     return this.areaCode;
   }
 
   public void setAreaCode(String areaCode) {
     this.areaCode = areaCode;
   }
 
   public String getAreaName() {
     return this.areaName;
   }
 
   public void setAreaName(String areaName) {
     this.areaName = areaName;
   }
 }

