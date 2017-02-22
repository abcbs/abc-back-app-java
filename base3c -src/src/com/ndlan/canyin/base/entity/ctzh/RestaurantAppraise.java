 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.ctzh.RestaurantAppraiseReply;
import com.ndlan.canyin.base.entity.ctzh.RestaurantRecommendDishe;

 import java.io.Serializable;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_restaurant_appraise")
 public class RestaurantAppraise extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="ora_id", unique=true, nullable=false, length=36)
   private String oraId;
 
   @Column(name="comment", length=2048)
   private String comment;
 
   @Column(name="environment_score")
   private int environmentScore;
 
   @Column(name="mb_id", length=36)
   private String mbId;
 
   @Column(name="publish_terminal", length=32)
   private String publishTerminal;
 
   @Column(name="serve_speed_score")
   private int serveSpeedScore;
 
   @Column(name="service_score")
   private int serviceScore;
 
   @Column(name="taste_score")
   private int tasteScore;
 
   @Column(name="total_score")
   private int totalScore;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="rest_id")
   private Restaurant restaurant;
 
   @OneToMany(mappedBy="restaurantAppraise")
   private List<RestaurantAppraiseReply> restaurantAppraiseReplies;
 
   @OneToMany(mappedBy="restaurantAppraise")
   private List<RestaurantRecommendDishe> restaurantRecommendDishes;
 
   public String getOraId()
   {
     return this.oraId;
   }
 
   public void setOraId(String oraId) {
     this.oraId = oraId;
   }
 
   public String getComment() {
     return this.comment;
   }
 
   public void setComment(String comment) {
     this.comment = comment;
   }
 
   public int getEnvironmentScore() {
     return this.environmentScore;
   }
 
   public void setEnvironmentScore(int environmentScore) {
     this.environmentScore = environmentScore;
   }
 
   public String getMbId() {
     return this.mbId;
   }
 
   public void setMbId(String mbId) {
     this.mbId = mbId;
   }
 
   public String getPublishTerminal() {
     return this.publishTerminal;
   }
 
   public void setPublishTerminal(String publishTerminal) {
     this.publishTerminal = publishTerminal;
   }
 
   public int getServeSpeedScore() {
     return this.serveSpeedScore;
   }
 
   public void setServeSpeedScore(int serveSpeedScore) {
     this.serveSpeedScore = serveSpeedScore;
   }
 
   public int getServiceScore() {
     return this.serviceScore;
   }
 
   public void setServiceScore(int serviceScore) {
     this.serviceScore = serviceScore;
   }
 
   public int getTasteScore() {
     return this.tasteScore;
   }
 
   public void setTasteScore(int tasteScore) {
     this.tasteScore = tasteScore;
   }
 
   public int getTotalScore() {
     return this.totalScore;
   }
 
   public void setTotalScore(int totalScore) {
     this.totalScore = totalScore;
   }
 
   public Restaurant getRestaurant() {
     return this.restaurant;
   }
 
   public void setRestaurant(Restaurant restaurant) {
     this.restaurant = restaurant;
   }
 
   public List<RestaurantAppraiseReply> getRestaurantAppraiseReplies() {
     return this.restaurantAppraiseReplies;
   }
 
   public void setRestaurantAppraiseReplies(List<RestaurantAppraiseReply> restaurantAppraiseReplies)
   {
     this.restaurantAppraiseReplies = restaurantAppraiseReplies;
   }
 
   public List<RestaurantRecommendDishe> getRestaurantRecommendDishes() {
     return this.restaurantRecommendDishes;
   }
 
   public void setRestaurantRecommendDishes(List<RestaurantRecommendDishe> restaurantRecommendDishes)
   {
     this.restaurantRecommendDishes = restaurantRecommendDishes;
   }
 }

