 package com.ndlan.canyin.base.entity.ylgl;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ylgl.DeliveryDetails;

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
 import org.hibernate.annotations.GenericGenerator;
 import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
 
 @Entity
 @Table(name="cm_delivery")
 public class Delivery extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="delivery_id", unique=true, nullable=false, length=36)
   private String deliveryId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="delivery_no", length=128)
   private String deliveryNo;
 
   @JsonIgnore
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="delivery_time")
   private Date deliveryTime;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="applicant_id")
   @NotFound(action=NotFoundAction.IGNORE)
   private Employee applicantEmployee;
 
   @Column(name="applicant_name", length=128)
   private String applicantName;
 
   @Column(name="applicant_phone", length=128)
   private String applicantPhone;
 
   @Column(name="notes", length=1024)
   private String notes;
 
   @Column(name="state", length=1)
   private String state;
 
   @JsonIgnore
   @OneToMany(mappedBy="delivery")
   private List<DeliveryDetails> deliveryDetails = new ArrayList();
 
   public BigDecimal getTotalMoney()
   {
     BigDecimal totalMoney = BigDecimal.ZERO;
     if ((this.deliveryDetails != null) && (this.deliveryDetails.size() != 0)) {
       for (DeliveryDetails deliveryDetail : this.deliveryDetails) {
         totalMoney = totalMoney.add(deliveryDetail.getMaterialPrice());
       }
     }
     return totalMoney;
   }
 
   public List<DeliveryDetails> getDeliveryDetails() {
     return this.deliveryDetails;
   }
 
   public void setDeliveryDetails(List<DeliveryDetails> deliveryDetails) {
     this.deliveryDetails = deliveryDetails;
   }
 
   public String getDeliveryId() {
     return this.deliveryId;
   }
 
   public void setDeliveryId(String deliveryId) {
     this.deliveryId = deliveryId;
   }
 
   public String getDeliveryNo() {
     return this.deliveryNo;
   }
 
   public void setDeliveryNo(String deliveryNo) {
     this.deliveryNo = deliveryNo;
   }
 
   public Date getDeliveryTime() {
     return this.deliveryTime;
   }
 
   public void setDeliveryTime(Date deliveryTime) {
     this.deliveryTime = deliveryTime;
   }
 
   public String getApplicantName() {
     return this.applicantName;
   }
 
   public void setApplicantName(String applicantName) {
     this.applicantName = applicantName;
   }
 
   public String getApplicantPhone() {
     return this.applicantPhone;
   }
 
   public void setApplicantPhone(String applicantPhone) {
     this.applicantPhone = applicantPhone;
   }
 
   public String getNotes() {
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
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public Employee getApplicantEmployee() {
     return this.applicantEmployee;
   }
 
   public void setApplicantEmployee(Employee applicantEmployee) {
     this.applicantEmployee = applicantEmployee;
   }
 }

