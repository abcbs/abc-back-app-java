 package com.ndlan.canyin.base.entity.cygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.cygl.DishesSet;

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
 @Table(name="cm_dishes_set_pic")
 public class DishesSetPic extends BaseEntity
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
 
   @Column(name="pic_url", length=512)
   private String picUrl;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="show_seq")
   private int showSeq;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="ds_id")
   private DishesSet dishesSet;
 
   public String getPicUrl1024x1024()
   {
     if ((!StringUtils.isEmpty(this.picUrl)) && (this.picUrl.indexOf("80x80") > 0))
     {
       String ori = this.picUrl.substring(0, this.picUrl.indexOf("_") + 1);
       String dot = this.picUrl.substring(this.picUrl.indexOf("."), this.picUrl.length());
       return ori + "1024x1024" + dot;
     }
     return this.picUrl;
   }
 
   public String getPicUrl200x200()
   {
     if ((!StringUtils.isEmpty(this.picUrl)) && (this.picUrl.indexOf("80x80") > 0))
     {
       String ori = this.picUrl.substring(0, this.picUrl.indexOf("_") + 1);
       String dot = this.picUrl.substring(this.picUrl.indexOf("."), this.picUrl.length());
       return ori + "200x200" + dot;
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
 
   public String getPicType() {
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
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public int getShowSeq() {
     return this.showSeq;
   }
 
   public void setShowSeq(int showSeq) {
     this.showSeq = showSeq;
   }
 
   public DishesSet getDishesSet() {
     return this.dishesSet;
   }
 
   public void setDishesSet(DishesSet dishesSet) {
     this.dishesSet = dishesSet;
   }
 }

