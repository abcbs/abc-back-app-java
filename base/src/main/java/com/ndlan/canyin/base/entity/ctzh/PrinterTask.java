 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.Printer;

 import java.io.Serializable;
 import java.util.Date;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_printer_task")
 public class PrinterTask extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="task_id", unique=true, nullable=false, length=36)
   private String taskId;
 
   @Column(name="bill_id", length=36)
   private String billId;
 
   @Column(name="bill_no", length=32)
   private String billNo;
 
   @Column(name="name", length=128)
   private String name;
 
   @Column(name="print_type", length=32)
   private String printType;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="task_time")
   private Date taskTime;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="printer_id")
   private Printer printer;
 
   public String getTaskId()
   {
     return this.taskId;
   }
 
   public void setTaskId(String taskId) {
     this.taskId = taskId;
   }
 
   public String getBillId() {
     return this.billId;
   }
 
   public void setBillId(String billId) {
     this.billId = billId;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public String getName() {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String getPrintType() {
     return this.printType;
   }
 
   public void setPrintType(String printType) {
     this.printType = printType;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public Date getTaskTime() {
     return this.taskTime;
   }
 
   public void setTaskTime(Date taskTime) {
     this.taskTime = taskTime;
   }
 
   public Printer getPrinter() {
     return this.printer;
   }
 
   public void setPrinter(Printer printer) {
     this.printer = printer;
   }
 }

