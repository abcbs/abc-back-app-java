 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.SelfDish;
import com.ndlan.canyin.base.entity.qtsy.SelfMessage;

 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_self_order")
 public class SelfOrder extends BaseEntity
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
 
   @Column(name="tab_no", length=36)
   private String tabNo;
 
   @Column(name="status", length=32)
   private String status;
 
   @OneToMany(mappedBy="selfOrder")
   private List<SelfDish> dishList = new ArrayList();
 
   @OneToMany(mappedBy="selfOrder")
   private List<SelfMessage> messageList = new ArrayList();
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="bill_time")
   private Date billTime;
 
   @Column(name="notes", length=255)
   private String notes;
 
   public String getId() { return this.id; }
 
   public void setId(String id)
   {
     this.id = id;
   }
 
   public String getTabNo() {
     return this.tabNo;
   }
 
   public void setTabNo(String tabNo) {
     this.tabNo = tabNo;
   }
 
   public Date getBillTime() {
     return this.billTime;
   }
 
   public void setBillTime(Date billTime) {
     this.billTime = billTime;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public String getStatus()
   {
     return this.status;
   }
 
   public void setStatus(String status) {
     this.status = status;
   }
 
   public List<SelfDish> getDishList() {
     return this.dishList;
   }
 
   public void setDishList(List<SelfDish> dishList) {
     this.dishList = dishList;
   }
 
   public List<SelfMessage> getMessageList() {
     return this.messageList;
   }
 
   public void setMessageList(List<SelfMessage> messageList) {
     this.messageList = messageList;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 }

