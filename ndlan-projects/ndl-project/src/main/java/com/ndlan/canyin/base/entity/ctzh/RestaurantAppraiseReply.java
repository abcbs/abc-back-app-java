 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
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
 @Table(name="cm_restaurant_appraise_reply")
 public class RestaurantAppraiseReply extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="crar_id", unique=true, nullable=false, length=36)
   private String crarId;
 
   @Column(name="publish_terminal", length=32)
   private String publishTerminal;
 
   @Column(name="reply_content", length=36)
   private String replyContent;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="ora_id")
   private RestaurantAppraise restaurantAppraise;
 
   public String getCrarId()
   {
     return this.crarId;
   }
 
   public void setCrarId(String crarId) {
     this.crarId = crarId;
   }
 
   public String getPublishTerminal()
   {
     return this.publishTerminal;
   }
 
   public void setPublishTerminal(String publishTerminal) {
     this.publishTerminal = publishTerminal;
   }
 
   public String getReplyContent() {
     return this.replyContent;
   }
 
   public void setReplyContent(String replyContent) {
     this.replyContent = replyContent;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public RestaurantAppraise getRestaurantAppraise() {
     return this.restaurantAppraise;
   }
 
   public void setRestaurantAppraise(RestaurantAppraise restaurantAppraise) {
     this.restaurantAppraise = restaurantAppraise;
   }
 }

