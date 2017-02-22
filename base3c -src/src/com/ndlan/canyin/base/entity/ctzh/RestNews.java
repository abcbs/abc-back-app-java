 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;

 import java.io.Serializable;
 import java.util.Date;
 import javax.persistence.Basic;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Lob;
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_rest_news")
 public class RestNews extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="rn_id", unique=true, nullable=false, length=36)
   private String rnId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="title", length=256)
   private String title;
 
   @Lob
   @Basic
   @Column(name="content", columnDefinition="text", nullable=true)
   private String content;
 
   @Column(name="is_show", length=1)
   private String isShow;
 
   @JsonIgnore
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="top_time")
   private Date topTime;
 
   public Date getTopTime()
   {
     return this.topTime;
   }
 
   public void setTopTime(Date topTime) {
     this.topTime = topTime;
   }
 
   public String getRnId() {
     return this.rnId;
   }
 
   public void setRnId(String rnId) {
     this.rnId = rnId;
   }
 
   public String getRestId()
   {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getTitle() {
     return this.title;
   }
 
   public void setTitle(String title) {
     this.title = title;
   }
 
   public String getContent() {
     return this.content;
   }
 
   public void setContent(String content) {
     this.content = content;
   }
 
   public String getIsShow() {
     return this.isShow;
   }
 
   public void setIsShow(String isShow) {
     this.isShow = isShow;
   }
 }

