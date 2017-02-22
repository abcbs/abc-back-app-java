 package com.ndlan.canyin.base.entity.hygl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;

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
 @Table(name="cm_member_dishes_favorite")
 public class MemberDishesFavorite extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="cmdf_id", unique=true, nullable=false, length=36)
   private String cmdfId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="dishes_id")
   private Dishe dishe;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="mb_id")
   private RestMemberInfo restMemberInfo;
 
   public String getCmdfId()
   {
     return this.cmdfId;
   }
 
   public void setCmdfId(String cmdfId) {
     this.cmdfId = cmdfId;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public Dishe getDishe() {
     return this.dishe;
   }
 
   public void setDishe(Dishe dishe) {
     this.dishe = dishe;
   }
 
   public RestMemberInfo getRestMemberInfo() {
     return this.restMemberInfo;
   }
 
   public void setRestMemberInfo(RestMemberInfo restMemberInfo) {
     this.restMemberInfo = restMemberInfo;
   }
 }

