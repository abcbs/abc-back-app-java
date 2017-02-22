 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.ctzh.RestaurantAppraise;

 import java.io.Serializable;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_restaurant_recommend_dishes")
 public class RestaurantRecommendDishe extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="crrd_id", unique=true, nullable=false, length=36)
   private String crrdId;
 
   @Column(name="mb_id", length=36)
   private String mbId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="dishes_id")
   private Dishe dishe;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="ora_id")
   private RestaurantAppraise restaurantAppraise;
 
   public String getCrrdId()
   {
     return this.crrdId;
   }
 
   public void setCrrdId(String crrdId) {
     this.crrdId = crrdId;
   }
 
   public String getMbId() {
     return this.mbId;
   }
 
   public void setMbId(String mbId) {
     this.mbId = mbId;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public RestaurantAppraise getRestaurantAppraise()
   {
     return this.restaurantAppraise;
   }
 
   public void setRestaurantAppraise(RestaurantAppraise restaurantAppraise) {
     this.restaurantAppraise = restaurantAppraise;
   }
 
   public Dishe getDishe() {
     return this.dishe;
   }
 
   public void setDishe(Dishe dishe) {
     this.dishe = dishe;
   }
 }

