 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;

 import java.io.Serializable;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_printer_setting")
 @JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
 public class PrinterSetting extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="ps_id", unique=true, nullable=false, length=36)
   private String psId;
 
   @Column(name="is_financial_print_dishes_type", length=1)
   private String isFinancialPrintDishesType;
 
   @Column(name="is_print_dishes_type", length=1)
   private String isPrintDishesType;
 
   @Column(name="is_print_financial", length=1)
   private String isPrintFinancial;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   public String getPsId()
   {
     return this.psId;
   }
 
   public void setPsId(String psId) {
     this.psId = psId;
   }
 
   public String getIsFinancialPrintDishesType() {
     return this.isFinancialPrintDishesType;
   }
 
   public void setIsFinancialPrintDishesType(String isFinancialPrintDishesType) {
     this.isFinancialPrintDishesType = isFinancialPrintDishesType;
   }
 
   public String getIsPrintDishesType() {
     return this.isPrintDishesType;
   }
 
   public void setIsPrintDishesType(String isPrintDishesType) {
     this.isPrintDishesType = isPrintDishesType;
   }
 
   public String getIsPrintFinancial() {
     return this.isPrintFinancial;
   }
 
   public void setIsPrintFinancial(String isPrintFinancial) {
     this.isPrintFinancial = isPrintFinancial;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 }

