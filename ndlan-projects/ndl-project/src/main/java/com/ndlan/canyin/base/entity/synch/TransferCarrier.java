 package com.ndlan.canyin.base.entity.synch;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.synch.Transaction;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.io.Serializable;
 import javax.persistence.Basic;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.Lob;
 import javax.persistence.ManyToOne;
 import javax.persistence.Table;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_transfer_carrier")
 public class TransferCarrier extends BaseEntity
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
 
   @Column(name="command", length=32)
   private String command;
 
   @Column(name="seq")
   private Integer seq;
 
   @Column(name="syn_version_id", length=36)
   private String synVersionId;
   private String method = "save";
 
   @Lob
   @Basic
   @Column(name="content", columnDefinition="Text", nullable=true)
   private String content;
   private String status = TrueFalseEnum.FALSE.getCode();
 
   @Transient
   Runnable task;
 
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="transaction_id")
   @JsonIgnore
   private Transaction transaction;
 
   public TransferCarrier(Transaction tr, String method, String content, int seq) { this.transaction = tr;
     this.method = method;
     this.content = content;
     this.seq = Integer.valueOf(seq); }
 
   public String getId()
   {
     return this.id;
   }
 
   public void setId(String id) {
     this.id = id;
   }
 
   public String getCommand() {
     return this.command;
   }
 
   public void setCommand(String command) {
     this.command = command;
   }
 
   public String getMethod() {
     return this.method;
   }
 
   public void setMethod(String method) {
     this.method = method;
   }
 
   public Runnable getTask() {
     return this.task;
   }
 
   public void setTask(Runnable task) {
     this.task = task;
   }
 
   public String getContent() {
     return this.content;
   }
 
   public void setContent(String content) {
     this.content = content;
   }
 
   public String getStatus()
   {
     return this.status;
   }
 
   public void setStatus(String status) {
     this.status = status;
   }
 
   public Transaction getTransaction() {
     return this.transaction;
   }
 
   public void setTransaction(Transaction transaction) {
     this.transaction = transaction;
   }
 
   public TransferCarrier()
   {
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public Integer getSeq() {
     return this.seq;
   }
 
   public void setSeq(Integer seq) {
     this.seq = seq;
   }
 
   public String getSynVersionId() {
     return this.synVersionId;
   }
 
   public void setSynVersionId(String synVersionId) {
     this.synVersionId = synVersionId;
   }
 }

