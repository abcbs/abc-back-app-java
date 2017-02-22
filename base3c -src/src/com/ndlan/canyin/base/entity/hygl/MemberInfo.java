 package com.ndlan.canyin.base.entity.hygl;
 
 import com.ndlan.canyin.base.entity.BaseEntity;

 import java.io.Serializable;
 import java.util.Date;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_member_info")
 public class MemberInfo extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="mb_id", unique=true, nullable=false, length=36)
   private String mbId;
 
   @Column(name="address", length=128)
   private String address;
 
   @Column(name="brithday_day")
   private Date brithdayDay;
 
   @Column(name="edu", length=32)
   private String edu;
 
   @Column(name="email", length=128)
   private String email;
 
   @Column(name="gender", length=1)
   private String gender;
 
   @Column(name="login_password", length=128)
   private String loginPassword;
 
   @Column(name="login_username", length=128)
   private String loginUsername;
 
   @Column(name="mobile", length=11)
   private String mobile;
 
   @Column(name="name", length=128)
   private String name;
 
   @Column(name="notes", length=1204)
   private String notes;
 
   @Column(name="origin_rest_id", length=36)
   private String originRestId;
 
   @Column(name="reg_origin", length=32)
   private String regOrigin;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="salesman_id", length=36)
   private String salesmanId;
 
   @Column(name="work", length=32)
   private String work;
 
   public String getMbId()
   {
     return this.mbId;
   }
 
   public void setMbId(String mbId) {
     this.mbId = mbId;
   }
 
   public String getAddress() {
     return this.address;
   }
 
   public void setAddress(String address) {
     this.address = address;
   }
 
   public Date getBrithdayDay() {
     return this.brithdayDay;
   }
 
   public void setBrithdayDay(Date brithdayDay) {
     this.brithdayDay = brithdayDay;
   }
 
   public String getEdu() {
     return this.edu;
   }
 
   public void setEdu(String edu) {
     this.edu = edu;
   }
 
   public String getEmail() {
     return this.email;
   }
 
   public void setEmail(String email) {
     this.email = email;
   }
 
   public String getGender() {
     return this.gender;
   }
 
   public void setGender(String gender) {
     this.gender = gender;
   }
 
   public String getLoginPassword() {
     return this.loginPassword;
   }
 
   public void setLoginPassword(String loginPassword) {
     this.loginPassword = loginPassword;
   }
 
   public String getLoginUsername() {
     return this.loginUsername;
   }
 
   public void setLoginUsername(String loginUsername) {
     this.loginUsername = loginUsername;
   }
 
   public String getMobile() {
     return this.mobile;
   }
 
   public void setMobile(String mobile) {
     this.mobile = mobile;
   }
 
   public String getName() {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public String getOriginRestId() {
     return this.originRestId;
   }
 
   public void setOriginRestId(String originRestId) {
     this.originRestId = originRestId;
   }
 
   public String getRegOrigin() {
     return this.regOrigin;
   }
 
   public void setRegOrigin(String regOrigin) {
     this.regOrigin = regOrigin;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getSalesmanId() {
     return this.salesmanId;
   }
 
   public void setSalesmanId(String salesmanId) {
     this.salesmanId = salesmanId;
   }
 
   public String getWork() {
     return this.work;
   }
 
   public void setWork(String work) {
     this.work = work;
   }
 }

