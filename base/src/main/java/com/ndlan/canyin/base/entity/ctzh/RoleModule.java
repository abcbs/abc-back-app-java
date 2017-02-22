 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.AuthorityModule;
import com.ndlan.canyin.base.entity.ctzh.Role;

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
 @Table(name="cm_role_module")
 public class RoleModule extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="crm_id", unique=true, nullable=false, length=36)
   private String crmId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="cam_id")
   private AuthorityModule authorityModule;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="cr_id")
   private Role role;
 
   public String getCrmId()
   {
     return this.crmId;
   }
 
   public void setCrmId(String crmId) {
     this.crmId = crmId;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public AuthorityModule getAuthorityModule() {
     return this.authorityModule;
   }
 
   public void setAuthorityModule(AuthorityModule authorityModule) {
     this.authorityModule = authorityModule;
   }
 
   public Role getRole() {
     return this.role;
   }
 
   public void setRole(Role role) {
     this.role = role;
   }
 }

