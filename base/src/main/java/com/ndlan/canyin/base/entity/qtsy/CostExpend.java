 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.ndlan.canyin.base.entity.BaseEntity;

 import java.io.Serializable;
 import java.math.BigDecimal;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_cost_expend")
 public class CostExpend extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="id", unique=true, nullable=false, length=36)
   private String id;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="material_consumption")
   private BigDecimal materialConsumption;
 
   @Column(name="transport_cost")
   private BigDecimal transportCost;
 
   @Column(name="material_other")
   private BigDecimal materialOther;
 
   @Column(name="salary")
   private BigDecimal salary;
 
   @Column(name="food_cost")
   private BigDecimal foodCost;
 
   @Column(name="employee_benefit")
   private BigDecimal employeeBenefit;
 
   @Column(name="labor_other")
   private BigDecimal laborOther;
 
   @Column(name="chummage")
   private BigDecimal chummage;
 
   @Column(name="taxes_cost")
   private BigDecimal taxesCost;
 
   @Column(name="maintain")
   private BigDecimal maintain;
 
   @Column(name="consumables")
   private BigDecimal consumables;
 
   @Column(name="water_cost")
   private BigDecimal waterCost;
 
   @Column(name="electric_cost")
   private BigDecimal electricCost;
 
   @Column(name="natgas")
   private BigDecimal natgas;
 
   @Column(name="cost_other")
   private BigDecimal costOther;
 
   public String getId()
   {
     return this.id;
   }
   public void setId(String id) {
     this.id = id;
   }
   public BigDecimal getMaterialConsumption() {
     return this.materialConsumption;
   }
   public void setMaterialConsumption(BigDecimal materialConsumption) {
     this.materialConsumption = materialConsumption;
   }
   public BigDecimal getTransportCost() {
     return this.transportCost;
   }
   public void setTransportCost(BigDecimal transportCost) {
     this.transportCost = transportCost;
   }
   public BigDecimal getMaterialOther() {
     return this.materialOther;
   }
   public void setMaterialOther(BigDecimal materialOther) {
     this.materialOther = materialOther;
   }
   public BigDecimal getSalary() {
     return this.salary;
   }
   public void setSalary(BigDecimal salary) {
     this.salary = salary;
   }
   public BigDecimal getFoodCost() {
     return this.foodCost;
   }
   public void setFoodCost(BigDecimal foodCost) {
     this.foodCost = foodCost;
   }
   public BigDecimal getEmployeeBenefit() {
     return this.employeeBenefit;
   }
   public void setEmployeeBenefit(BigDecimal employeeBenefit) {
     this.employeeBenefit = employeeBenefit;
   }
   public BigDecimal getChummage() {
     return this.chummage;
   }
   public void setChummage(BigDecimal chummage) {
     this.chummage = chummage;
   }
   public BigDecimal getTaxesCost() {
     return this.taxesCost;
   }
   public void setTaxesCost(BigDecimal taxesCost) {
     this.taxesCost = taxesCost;
   }
   public BigDecimal getMaintain() {
     return this.maintain;
   }
   public void setMaintain(BigDecimal maintain) {
     this.maintain = maintain;
   }
   public BigDecimal getConsumables() {
     return this.consumables;
   }
   public void setConsumables(BigDecimal consumables) {
     this.consumables = consumables;
   }
   public BigDecimal getWaterCost() {
     return this.waterCost;
   }
   public void setWaterCost(BigDecimal waterCost) {
     this.waterCost = waterCost;
   }
   public BigDecimal getElectricCost() {
     return this.electricCost;
   }
   public void setElectricCost(BigDecimal electricCost) {
     this.electricCost = electricCost;
   }
   public BigDecimal getNatgas() {
     return this.natgas;
   }
   public void setNatgas(BigDecimal natgas) {
     this.natgas = natgas;
   }
   public BigDecimal getCostOther() {
     return this.costOther;
   }
   public void setCostOther(BigDecimal costOther) {
     this.costOther = costOther;
   }
   public String getRestId() {
     return this.restId;
   }
   public void setRestId(String restId) {
     this.restId = restId;
   }
   public BigDecimal getLaborOther() {
     return this.laborOther;
   }
   public void setLaborOther(BigDecimal laborOther) {
     this.laborOther = laborOther;
   }
 }

