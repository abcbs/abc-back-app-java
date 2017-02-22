 package com.ndlan.canyin.base.entity.ylgl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ylgl.StorageDetails;

 import java.io.Serializable;
 import java.math.BigDecimal;
 import java.util.ArrayList;
 import java.util.Date;
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
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
 import javax.persistence.Transient;
 import org.hibernate.annotations.GenericGenerator;
 import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
 
 @Entity
 @Table(name="cm_storage")
 @JsonIgnoreProperties(ignoreUnknown=true)
 public class Storage extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="storage_id", unique=true, nullable=false, length=36)
   private String storageId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="storage_no", length=128)
   private String storageNo;
 
   @JsonIgnore
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="storage_time")
   private Date storageTime;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="manager_id")
   @NotFound(action=NotFoundAction.IGNORE)
   private Employee managerEmployee;
 
   @Column(name="manager_name", length=128)
   private String managerName;
 
   @Column(name="manager_phone", length=128)
   private String managerPhone;
 
   @Column(name="notes", length=1024)
   private String notes;
 
   @Column(name="state", length=1)
   private String state;
 
   @Transient
   private String storageTimeForMobile;
   
   @Transient
   BigDecimal totalMoney = BigDecimal.ZERO;
 
   @OneToMany(mappedBy="storage")
   private List<StorageDetails> storageDetails = new ArrayList();
 
   public String getStorageTimeForMobile()
   {
     return this.storageTimeForMobile;
   }
 
   public void setStorageTimeForMobile(String storageTimeForMobile) {
     this.storageTimeForMobile = storageTimeForMobile;
   }
 
   public List<StorageDetails> getStorageDetails()
   {
     return this.storageDetails;
   }
 
   public void setStorageDetails(List<StorageDetails> storageDetails) {
     this.storageDetails = storageDetails;
   }
 
   public String getStorageId() {
     return this.storageId;
   }
 
   public void setStorageId(String storageId) {
     this.storageId = storageId;
   }
 
   public String getStorageNo() {
     return this.storageNo;
   }
 
   public void setStorageNo(String storageNo) {
     this.storageNo = storageNo;
   }
 
   public Date getStorageTime() {
     return this.storageTime;
   }
 
   public void setStorageTime(Date storageTime) {
     this.storageTime = storageTime;
   }
 
   public String getManagerName() {
     return this.managerName;
   }
 
   public void setManagerName(String managerName) {
     this.managerName = managerName;
   }
 
   public String getManagerPhone() {
     return this.managerPhone;
   }
 
   public void setManagerPhone(String managerPhone) {
     this.managerPhone = managerPhone;
   }
 
   public String getNotes()
   {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public String getState() {
     return this.state;
   }
 
   public void setState(String state) {
     this.state = state;
   }
 
   public BigDecimal getTotalMoney() {
     
     if ((this.storageDetails != null) && (this.storageDetails.size() != 0)) {
       for (StorageDetails storageDetail : this.storageDetails) {
         totalMoney = totalMoney.add(storageDetail.getMaterialPrice());
       }
     }
     return totalMoney;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public Employee getManagerEmployee() {
     return this.managerEmployee;
   }
 
   public void setManagerEmployee(Employee managerEmployee) {
     this.managerEmployee = managerEmployee;
   }

   public void setTotalMoney(BigDecimal totalMoney) {
	this.totalMoney = totalMoney;
}
   
   
 }

