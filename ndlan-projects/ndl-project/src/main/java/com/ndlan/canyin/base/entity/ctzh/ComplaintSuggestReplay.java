 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.ComplaintSuggest;
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
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_complaint_suggest_replay")
 @JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
 public class ComplaintSuggestReplay extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="ocsr_id", unique=true, nullable=false, length=36)
   private String ocsrId;
 
   @Column(name="content", length=1024)
   private String content;
 
   @Column(name="ocsr_status", length=32)
   private String ocsrStatus;
 
   @Column(name="publish_terminal", length=32)
   private String publishTerminal;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="ocs_id")
   private ComplaintSuggest complaintSuggest;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="emp_id")
   private Employee employee;
 
   public String getOcsrId()
   {
     return this.ocsrId;
   }
 
   public void setOcsrId(String ocsrId) {
     this.ocsrId = ocsrId;
   }
 
   public String getContent() {
     return this.content;
   }
 
   public void setContent(String content) {
     this.content = content;
   }
 
   public String getOcsrStatus() {
     return this.ocsrStatus;
   }
 
   public void setOcsrStatus(String ocsrStatus) {
     this.ocsrStatus = ocsrStatus;
   }
 
   public String getPublishTerminal() {
     return this.publishTerminal;
   }
 
   public void setPublishTerminal(String publishTerminal) {
     this.publishTerminal = publishTerminal;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public ComplaintSuggest getComplaintSuggest() {
     return this.complaintSuggest;
   }
 
   public void setComplaintSuggest(ComplaintSuggest complaintSuggest) {
     this.complaintSuggest = complaintSuggest;
   }
 
   public Employee getEmployee() {
     return this.employee;
   }
 
   public void setEmployee(Employee employee) {
     this.employee = employee;
   }
 }

