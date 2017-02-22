 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.EmployeShift;

 import java.io.Serializable;
 import java.math.BigDecimal;
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
 @Table(name="cm_employe_shift_income")
 public class EmployeShiftIncome extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="cesi_id", unique=true, nullable=false, length=36)
   private String cesiId;
 
   @Column(name="cpt_id", length=36)
   private String cptId;
 
   @Column(name="cpt_money")
   private BigDecimal cptMoney;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="ces_id")
   private EmployeShift employeShift;
 
   public String getCesiId()
   {
     return this.cesiId;
   }
 
   public void setCesiId(String cesiId) {
     this.cesiId = cesiId;
   }
 
   public String getCptId() {
     return this.cptId;
   }
 
   public void setCptId(String cptId) {
     this.cptId = cptId;
   }
 
   public BigDecimal getCptMoney() {
     return this.cptMoney;
   }
 
   public void setCptMoney(BigDecimal cptMoney) {
     this.cptMoney = cptMoney;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public EmployeShift getEmployeShift() {
     return this.employeShift;
   }
 
   public void setEmployeShift(EmployeShift employeShift) {
     this.employeShift = employeShift;
   }
 }

