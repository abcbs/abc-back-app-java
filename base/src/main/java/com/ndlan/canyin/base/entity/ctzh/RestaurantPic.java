 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
 import com.ndlan.canyin.core.common.PicTypeEnum;
 import java.io.Serializable;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.Table;
 import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_restaurant_pic")
 public class RestaurantPic extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="pic_id", unique=true, nullable=false, length=36)
   private String picId;
 
   @Column(name="pic_type", length=32)
   private String picType;
 
   @Column(name="pic_url", length=500)
   private String picUrl;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="rest_id")
   private Restaurant restaurant;
 
   public String getPicUrl1024x1024()
   {
     if (!StringUtils.isEmpty(this.picUrl))
     {
       if ((PicTypeEnum.LOGO.getCode().equals(this.picType)) && (this.picUrl.indexOf("_") > 0)) {
         String ori = this.picUrl.substring(0, this.picUrl.indexOf("_") + 1);
         String dot = this.picUrl.substring(this.picUrl.indexOf("."), this.picUrl.length());
         return ori + "1024x1024" + dot;
       }
     }
     return this.picUrl;
   }
 
   public String getPicUrl200x200()
   {
     if (!StringUtils.isEmpty(this.picUrl))
     {
       if ((PicTypeEnum.LOGO.getCode().equals(this.picType)) && (this.picUrl.indexOf("_") > 0)) {
         String ori = this.picUrl.substring(0, this.picUrl.indexOf("_") + 1);
         String dot = this.picUrl.substring(this.picUrl.indexOf("."), this.picUrl.length());
         return ori + "200x200" + dot;
       }
     }
     return this.picUrl;
   }
 
   public String getPicUrl1200x800()
   {
     if (!StringUtils.isEmpty(this.picUrl))
     {
       if ((PicTypeEnum.COMMON.getCode().equals(this.picType)) && (this.picUrl.indexOf("_") > 0)) {
         String ori = this.picUrl.substring(0, this.picUrl.indexOf("_") + 1);
         String dot = this.picUrl.substring(this.picUrl.indexOf("."), this.picUrl.length());
         return ori + "1200x800" + dot;
       }
     }
     return this.picUrl;
   }
 
   public String getPicUrl300x200()
   {
     if (!StringUtils.isEmpty(this.picUrl))
     {
       if ((PicTypeEnum.COMMON.getCode().equals(this.picType)) && (this.picUrl.indexOf("_") > 0)) {
         String ori = this.picUrl.substring(0, this.picUrl.indexOf("_") + 1);
         String dot = this.picUrl.substring(this.picUrl.indexOf("."), this.picUrl.length());
         return ori + "300x200" + dot;
       }
     }
     return this.picUrl;
   }
 
   public String getPicId()
   {
     return this.picId;
   }
 
   public void setPicId(String picId) {
     this.picId = picId;
   }
 
   public String getPicType()
   {
     return this.picType;
   }
 
   public void setPicType(String picType) {
     this.picType = picType;
   }
 
   public String getPicUrl() {
     return this.picUrl;
   }
 
   public void setPicUrl(String picUrl) {
     this.picUrl = picUrl;
   }
 
   public Restaurant getRestaurant() {
     return this.restaurant;
   }
 
   public void setRestaurant(Restaurant restaurant) {
     this.restaurant = restaurant;
   }
 }

