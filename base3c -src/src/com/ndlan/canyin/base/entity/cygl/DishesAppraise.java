 package com.ndlan.canyin.base.entity.cygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.cygl.DishesAppraiseReply;

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
 @Table(name="cm_dishes_appraise")
 public class DishesAppraise extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="cda_id", unique=true, nullable=false, length=36)
   private String cdaId;
 
   @Column(name="comment", length=2048)
   private String comment;
 
   @Column(name="publish_terminal", length=32)
   private String publishTerminal;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="score")
   private int score;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="dishes_id")
   private Dishe dishe;
 
   @JsonIgnore
   @OneToMany(mappedBy="dishesAppraise")
   private List<DishesAppraiseReply> dishesAppraiseReplies;
 
   public String getCdaId()
   {
     return this.cdaId;
   }
 
   public void setCdaId(String cdaId) {
     this.cdaId = cdaId;
   }
 
   public String getComment() {
     return this.comment;
   }
 
   public void setComment(String comment) {
     this.comment = comment;
   }
 
   public String getPublishTerminal() {
     return this.publishTerminal;
   }
 
   public void setPublishTerminal(String publishTerminal) {
     this.publishTerminal = publishTerminal;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public int getScore() {
     return this.score;
   }
 
   public void setScore(int score) {
     this.score = score;
   }
 
   public Dishe getDishe() {
     return this.dishe;
   }
 
   public void setDishe(Dishe dishe) {
     this.dishe = dishe;
   }
 
   public List<DishesAppraiseReply> getDishesAppraiseReplies() {
     return this.dishesAppraiseReplies;
   }
 
   public void setDishesAppraiseReplies(List<DishesAppraiseReply> dishesAppraiseReplies)
   {
     this.dishesAppraiseReplies = dishesAppraiseReplies;
   }
 }

