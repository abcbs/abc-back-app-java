 package com.ndlan.canyin.base.entity.cygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.cygl.DishesAppraise;

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
 @Table(name="cm_dishes_appraise_reply")
 public class DishesAppraiseReply extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="cdar_id", unique=true, nullable=false, length=36)
   private String cdarId;
 
   @Column(name="publish_terminal", length=32)
   private String publishTerminal;
 
   @Column(name="reply_content", length=36)
   private String replyContent;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="cda_id")
   @JsonIgnore
   private DishesAppraise dishesAppraise;
 
   public String getCdarId()
   {
     return this.cdarId;
   }
 
   public void setCdarId(String cdarId) {
     this.cdarId = cdarId;
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
 
   public DishesAppraise getDishesAppraise() {
     return this.dishesAppraise;
   }
 
   public void setDishesAppraise(DishesAppraise dishesAppraise) {
     this.dishesAppraise = dishesAppraise;
   }
 }

