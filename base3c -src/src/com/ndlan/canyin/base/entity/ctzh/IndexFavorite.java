 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.AuthorityModule;
import com.ndlan.canyin.base.entity.ctzh.Employee;

 import java.io.Serializable;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.Table;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_index_favorite")
 @JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
 public class IndexFavorite extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="if_id", unique=true, nullable=false, length=36)
   private String ifId;
 
   @Column(name="model_name", length=128)
   private String modelName;
 
   @Column(name="module_size", length=32)
   private String moduleSize;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="show_seq")
   private int showSeq;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="cam_id")
   private AuthorityModule authorityModule;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="emp_id")
   private Employee employee;
 
   @Transient
   private String camLink;
 
   @Transient
   private String camName;
 
   @Transient
   private String moduleColor;
 
   @Transient
   private String moduleIcon;
 
   @Transient
   private String moduleStatus;
 
   @Transient
   private String moduleDesc;
 
   @Transient
   private String actionUrl;
 
   public String getIfId()
   {
     return this.ifId;
   }
 
   public void setIfId(String ifId) {
     this.ifId = ifId;
   }
 
   public String getModelName() {
     return this.modelName;
   }
 
   public void setModelName(String modelName) {
     this.modelName = modelName;
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
 
   public AuthorityModule getAuthorityModule() {
     return this.authorityModule;
   }
 
   public void setAuthorityModule(AuthorityModule authorityModule) {
     this.authorityModule = authorityModule;
   }
 
   public Employee getEmployee() {
     return this.employee;
   }
 
   public void setEmployee(Employee employee) {
     this.employee = employee;
   }
 
   public String getModuleSize() {
     return this.moduleSize;
   }
 
   public void setModuleSize(String moduleSize) {
     this.moduleSize = moduleSize;
   }
 
   public String getCamLink() {
     return this.camLink;
   }
 
   public void setCamLink(String camLink) {
     this.camLink = camLink;
   }
 
   public String getCamName() {
     return this.camName;
   }
 
   public void setCamName(String camName) {
     this.camName = camName;
   }
 
   public String getModuleColor() {
     return this.moduleColor;
   }
 
   public void setModuleColor(String moduleColor) {
     this.moduleColor = moduleColor;
   }
 
   public String getModuleIcon() {
     return this.moduleIcon;
   }
 
   public void setModuleIcon(String moduleIcon) {
     this.moduleIcon = moduleIcon;
   }
 
   public String getModuleStatus() {
     return this.moduleStatus;
   }
 
   public void setModuleStatus(String moduleStatus) {
     this.moduleStatus = moduleStatus;
   }
 
   public String getModuleDesc() {
     return this.moduleDesc;
   }
 
   public void setModuleDesc(String moduleDesc) {
     this.moduleDesc = moduleDesc;
   }
 
   public String getActionUrl() {
     return this.actionUrl;
   }
 
   public void setActionUrl(String actionUrl) {
     this.actionUrl = actionUrl;
   }
 }

