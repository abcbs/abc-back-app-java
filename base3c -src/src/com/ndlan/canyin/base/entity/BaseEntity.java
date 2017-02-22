 package com.ndlan.canyin.base.entity;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.ctzh.Employee;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.io.Serializable;
 import java.util.Date;
 import javax.persistence.Column;
 import javax.persistence.FetchType;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.MappedSuperclass;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
import javax.persistence.Version;
 
 @MappedSuperclass
 public abstract class BaseEntity
   implements Serializable
 {
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="create_by")
   private Employee createEmployee;
 
   @JsonIgnore
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="create_time")
   private Date createTime;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="update_by")
   private Employee updateEmployee;
 
   @JsonIgnore
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="update_time")
   private Date updateTime;
 
   @JsonIgnore
   @Version
   private Integer version = 0;
 
   @JsonIgnore
   @Column(name="is_syn", length=1)
   private String isSyn = TrueFalseEnum.FALSE.getCode();
 
   @JsonIgnore
   @Column(name="syn_version")
   private Integer synVersion = Integer.valueOf(0);
 
   public Date getCreateTime() {
     return this.createTime;
   }
 
   public void setCreateTime(Date createTime) {
     this.createTime = createTime;
   }
 
   public Date getUpdateTime() {
     return this.updateTime;
   }
 
   public void setUpdateTime(Date updateTime) {
     this.updateTime = updateTime;
   }
 
   public Employee getCreateEmployee()
   {
     return this.createEmployee;
   }
 
   public void setCreateEmployee(Employee createEmployee) {
     this.createEmployee = createEmployee;
   }
 
   public Employee getUpdateEmployee() {
     return this.updateEmployee;
   }
 
   public void setUpdateEmployee(Employee updateEmployee) {
     this.updateEmployee = updateEmployee;
   }
 
   public String getIsSyn() {
     return this.isSyn;
   }
 
   public void setIsSyn(String isSyn) {
     this.isSyn = isSyn;
   }
 
   public Integer getSynVersion() {
     return this.synVersion;
   }
 
   public void setSynVersion(Integer synVersion) {
     this.synVersion = synVersion;
   }
 
   public int getVersion() {
     return this.version;
   }
 
   public void setVersion(int version) {
     this.version = version;
   }
 }

