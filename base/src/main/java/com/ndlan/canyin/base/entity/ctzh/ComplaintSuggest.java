 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.hygl.RestMemberInfo;
import com.ndlan.canyin.base.entity.ctzh.ComplaintSuggestReplay;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;

 import java.io.Serializable;
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
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_complaint_suggest")
 @JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
 public class ComplaintSuggest extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="ocs_id", unique=true, nullable=false, length=36)
   private String ocsId;
 
   @Column(name="bill_no", length=32)
   private String billNo;
 
   @Column(name="complaint_name", length=128)
   private String complaintName;
 
   @Column(name="complaint_tel", length=32)
   private String complaintTel;
 
   @Column(name="content", length=1024)
   private String content;
 
   @Column(name="employee_id", length=32)
   private String employeeId;
 
   @Column(name="employee_no", length=32)
   private String employeeNo;
 
   @Column(name="ocs_status", length=32)
   private String ocsStatus;
 
   @Column(name="ocs_type", length=32)
   private String ocsType;
 
   @Column(name="publish_terminal", length=32)
   private String publishTerminal;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="mb_id")
   private RestMemberInfo restMemberInfo;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="rest_id")
   private Restaurant restaurant;
   
   @JsonIgnore
   @OneToMany(mappedBy="complaintSuggest")
   private List<ComplaintSuggestReplay> complaintSuggestReplays;
 
   public String getOcsId()
   {
     return this.ocsId;
   }
 
   public void setOcsId(String ocsId) {
     this.ocsId = ocsId;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public String getComplaintName() {
     return this.complaintName;
   }
 
   public void setComplaintName(String complaintName) {
     this.complaintName = complaintName;
   }
 
   public String getComplaintTel() {
     return this.complaintTel;
   }
 
   public void setComplaintTel(String complaintTel) {
     this.complaintTel = complaintTel;
   }
 
   public String getContent() {
     return this.content;
   }
 
   public void setContent(String content) {
     this.content = content;
   }
 
   public String getEmployeeId() {
     return this.employeeId;
   }
 
   public void setEmployeeId(String employeeId) {
     this.employeeId = employeeId;
   }
 
   public String getEmployeeNo() {
     return this.employeeNo;
   }
 
   public void setEmployeeNo(String employeeNo) {
     this.employeeNo = employeeNo;
   }
 
   public String getOcsStatus() {
     return this.ocsStatus;
   }
 
   public void setOcsStatus(String ocsStatus) {
     this.ocsStatus = ocsStatus;
   }
 
   public String getOcsType() {
     return this.ocsType;
   }
 
   public void setOcsType(String ocsType) {
     this.ocsType = ocsType;
   }
 
   public String getPublishTerminal() {
     return this.publishTerminal;
   }
 
   public void setPublishTerminal(String publishTerminal) {
     this.publishTerminal = publishTerminal;
   }
 
   public RestMemberInfo getRestMemberInfo() {
     return this.restMemberInfo;
   }
 
   public void setRestMemberInfo(RestMemberInfo restMemberInfo) {
     this.restMemberInfo = restMemberInfo;
   }
 
   public Restaurant getRestaurant() {
     return this.restaurant;
   }
 
   public void setRestaurant(Restaurant restaurant) {
     this.restaurant = restaurant;
   }
 
   public List<ComplaintSuggestReplay> getComplaintSuggestReplays() {
     return this.complaintSuggestReplays;
   }
 
   public void setComplaintSuggestReplays(List<ComplaintSuggestReplay> complaintSuggestReplays)
   {
     this.complaintSuggestReplays = complaintSuggestReplays;
   }
 }

