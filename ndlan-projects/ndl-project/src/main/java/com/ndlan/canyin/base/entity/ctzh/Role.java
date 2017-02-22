 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.AuthorityModule;
import com.ndlan.canyin.base.entity.ctzh.Role;
import com.ndlan.canyin.base.entity.ctzh.TableArea;
 import com.ndlan.canyin.core.common.SysDataTypeEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinTable;
 import javax.persistence.ManyToMany;
 import javax.persistence.OrderBy;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_role")
 public class Role extends BaseEntity
   implements Serializable, Cloneable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="cr_id", unique=true, nullable=false, length=36)
   private String crId;
 
   @Column(name="name", length=128)
   private String name;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="sysdata_type", length=32)
   private String sysdataType = SysDataTypeEnum.USERDEFINED.getCode();
 
   @Column(name="role_type", length=32)
   private String roleType;
 
   @Column(name="is_stop_use", length=1)
   private String isStopUse;
 
   @OrderBy("showSeq ASC")
   @ManyToMany(fetch=FetchType.LAZY)
   @JoinTable(name="cm_role_auth", joinColumns={@javax.persistence.JoinColumn(name="FK_ROLE_ID")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="FK_AUTH_ID")})
   private List<AuthorityModule> authList = new ArrayList();
 
   @ManyToMany(fetch=FetchType.LAZY)
   @JoinTable(name="cm_role_tableArea", joinColumns={@javax.persistence.JoinColumn(name="FK_ROLE_ID")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="FK_TABLEAREA_ID")})
   private List<TableArea> tableAreaList = new ArrayList();
 
   @Column(name="is_all_tablearea", length=32)
   private String isAllTablearea = "1";
 
   public String getIsStopUseDesc()
   {
     return TrueFalseEnum.getDesc(this.isStopUse);
   }
 
   public String getIsStopUse()
   {
     return this.isStopUse;
   }
 
   public void setIsStopUse(String isStopUse) {
     this.isStopUse = isStopUse;
   }
 
   public String getCrId()
   {
     return this.crId;
   }
 
   public void setCrId(String crId) {
     this.crId = crId;
   }
 
   public String getName() {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public List<AuthorityModule> getAuthList() {
     return this.authList;
   }
 
   public void setAuthList(List<AuthorityModule> authList) {
     this.authList = authList;
   }
 
   public Role clone()
   {
     try
     {
       return (Role)super.clone();
     }
     catch (CloneNotSupportedException e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public List<TableArea> getTableAreaList() {
     return this.tableAreaList;
   }
 
   public void setTableAreaList(List<TableArea> tableAreaList) {
     this.tableAreaList = tableAreaList;
   }
 
   public String getSysdataType()
   {
     return this.sysdataType;
   }
 
   public void setSysdataType(String sysdataType) {
     this.sysdataType = sysdataType;
   }
 
   public int hashCode()
   {
     return this.crId.hashCode();
   }
 
   public boolean equals(Object obj)
   {
     if (obj == null) {
       return false;
     }
     if (!(obj instanceof Role)) {
       return false;
     }
     return this.crId == ((Role)obj).getCrId();
   }
 
   public String toString()
   {
     return this.crId;
   }
 
   public String getIsAllTablearea() {
     return this.isAllTablearea;
   }
 
   public void setIsAllTablearea(String isAllTablearea) {
     this.isAllTablearea = isAllTablearea;
   }
 
   public String getRoleType() {
     return this.roleType;
   }
 
   public void setRoleType(String roleType) {
     this.roleType = roleType;
   }
 }

