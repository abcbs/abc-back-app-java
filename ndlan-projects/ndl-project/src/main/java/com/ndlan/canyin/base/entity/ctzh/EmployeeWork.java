 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
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
 @Table(name="cm_employee_work")
 @JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
 public class EmployeeWork extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="work_id", unique=true, nullable=false, length=36)
   private String workId;
 
   @Column(name="bill_no", length=32)
   private String billNo;
 
   @Column(name="emp_op_content", length=256)
   private String empOpContent;
 
   @Column(name="emp_op_type", length=32)
   private String empOpType;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="tab_no", length=32)
   private String tabNo;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="emp_id")
   private Employee employee;
 
   public String getWorkId()
   {
     return this.workId;
   }
 
   public void setWorkId(String workId) {
     this.workId = workId;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public String getEmpOpContent()
   {
     return this.empOpContent;
   }
 
   public void setEmpOpContent(String empOpContent) {
     this.empOpContent = empOpContent;
   }
 
   public String getEmpOpType() {
     return this.empOpType;
   }
 
   public void setEmpOpType(String empOpType) {
     this.empOpType = empOpType;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getTabNo() {
     return this.tabNo;
   }
 
   public void setTabNo(String tabNo) {
     this.tabNo = tabNo;
   }
 
   public Employee getEmployee() {
     return this.employee;
   }
 
   public void setEmployee(Employee employee) {
     this.employee = employee;
   }
 }

