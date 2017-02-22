 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonBackReference;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.AuthorityModule;
import com.ndlan.canyin.base.entity.ctzh.IndexFavorite;
import com.ndlan.canyin.base.entity.ctzh.Role;
 import com.ndlan.canyin.core.common.CamLevelEnum;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.JoinTable;
 import javax.persistence.ManyToMany;
 import javax.persistence.ManyToOne;
 import javax.persistence.OneToMany;
 import javax.persistence.OrderBy;
 import javax.persistence.Table;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_authority_module")
 @JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
 public class AuthorityModule extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="cam_id", unique=true, nullable=false, length=36)
   private String camId;
 
   @Column(name="cam_level", length=32)
   private String camLevel;
 
   @Column(name="cam_link", length=256)
   private String camLink;
 
   @Column(name="cam_name", length=128)
   private String camName;
   
//   @Column(name="module_color", length=255)
   private String moduleColor;
 
   @Column(name="module_icon", length=256)
   private String moduleIcon;
 
   @Column(name="module_status", length=32)
   private String moduleStatus;
 
   @Column(name="module_desc", length=1024)
   private String moduleDesc;
 
   @Column(name="parent_cam_id", length=36)
   private String parentCamId;
 
   @Column(name="action_url", length=36)
   private String actionUrl;
 
   @Column(name="permission", length=64)
   private String permission;
   
  /* @Column(name="create_by", length=64)
   private String createBy;*/
 
   @Column(name="show_seq")
   private int showSeq;
 
   @Transient
   private boolean isCollected;
 
   @OneToMany(mappedBy="authorityModule")
   @JsonIgnore
   private List<IndexFavorite> indexFavorites;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_PARENT_ID")
   @JsonIgnore
   private AuthorityModule parent;
 
   @OrderBy("showSeq ASC")
   @OneToMany(mappedBy="parent", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL})
   @JsonBackReference
   @JsonManagedReference
   @JsonIgnore
   private List<AuthorityModule> children = new ArrayList();
 
   @JsonIgnore
   @ManyToMany(fetch=FetchType.LAZY)
   @JoinTable(name="cm_role_auth", joinColumns={@JoinColumn(name="FK_AUTH_ID")}, inverseJoinColumns={@JoinColumn(name="FK_ROLE_ID")})
   private List<Role> roleList = new ArrayList();
 
   public String getCamLevelName()
   {
     return CamLevelEnum.getDesc(this.camLevel);
   }
 
   public AuthorityModule()
   {
   }
 
   public AuthorityModule(String camId)
   {
     this.camId = camId;
   }
 
   public String getCamId()
   {
     return this.camId;
   }
 
   public void setCamId(String camId) {
     this.camId = camId;
   }
 
   public String getCamLevel() {
     return this.camLevel;
   }
 
   public void setCamLevel(String camLevel) {
     this.camLevel = camLevel;
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
 
   public String getParentCamId() {
     return this.parentCamId;
   }
 
   public void setParentCamId(String parentCamId) {
     this.parentCamId = parentCamId;
   }
 
   public int getShowSeq() {
     return this.showSeq;
   }
 
   public void setShowSeq(int showSeq) {
     this.showSeq = showSeq;
   }
 
   public List<IndexFavorite> getIndexFavorites() {
     return this.indexFavorites;
   }
 
   public void setIndexFavorites(List<IndexFavorite> indexFavorites) {
     this.indexFavorites = indexFavorites;
   }
 
   public String getActionUrl() {
     return this.actionUrl;
   }
 
   public void setActionUrl(String actionUrl) {
     this.actionUrl = actionUrl;
   }
 
   public String getPermission() {
     return this.permission;
   }
 
   public void setPermission(String permission) {
     this.permission = permission;
   }
 
   public AuthorityModule getParent() {
     return this.parent;
   }
 
   public void setParent(AuthorityModule parent) {
     this.parent = parent;
   }
 
   public List<AuthorityModule> getChildren() {
     return this.children;
   }
 
   public void setChildren(List<AuthorityModule> children) {
     this.children = children;
   }
 
   public String getModuleDesc()
   {
     return this.moduleDesc;
   }
 
   public void setModuleDesc(String moduleDesc)
   {
     this.moduleDesc = moduleDesc;
   }
 
   public String getModuleColor()
   {
     return this.moduleColor;
   }
 
   public void setModuleColor(String moduleColor)
   {
     this.moduleColor = moduleColor;
   }
 
   public boolean isCollected()
   {
     return this.isCollected;
   }
 
   public void setCollected(boolean isCollected)
   {
     this.isCollected = isCollected;
   }
 
   public List<Role> getRoleList()
   {
     return this.roleList;
   }
 
   public void setRoleList(List<Role> roleList)
   {
     this.roleList = roleList;
   }

   /*public String getCreateBy() {
	return createBy;
}

public void setCreateBy(String createBy) {
	this.createBy = createBy;
}*/
   
   
 }

