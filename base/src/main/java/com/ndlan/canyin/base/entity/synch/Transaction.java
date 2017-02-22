 package com.ndlan.canyin.base.entity.synch;
 
 import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.synch.TransferCarrier;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
 import javax.persistence.OneToMany;
import javax.persistence.Table;
 
 @Entity
 @Table(name="cm_transaction")
 public class Transaction extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="id", unique=true, nullable=false, length=36)
   private Integer id;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="syn_version_id", length=36)
   private String synVersionId;
   private String status = TrueFalseEnum.FALSE.getCode();
 
   @Column(name="type", length=32)
   private String type = TrueFalseEnum.TRUE.getCode();
 
   @OneToMany(mappedBy="transaction")
   private List<TransferCarrier> transferCarriers = new ArrayList();
 
   public List<TransferCarrier> getTransferCarriers()
   {
     return this.transferCarriers;
   }
 
   public void setTransferCarriers(List<TransferCarrier> transferCarriers)
   {
     this.transferCarriers = transferCarriers;
   }
 
   public static long getSerialversionuid()
   {
     return 1L;
   }
 
   public String getStatus() {
     return this.status;
   }
 
   public void setStatus(String status) {
     this.status = status;
   }
 
   public String getType() {
     return this.type;
   }
 
   public void setType(String type) {
     this.type = type;
   }
 
   public String getRestId()
   {
     return this.restId;
   }
 
   public void setRestId(String restId)
   {
     this.restId = restId;
   }
 
   public Integer getId()
   {
     return this.id;
   }
 
   public void setId(Integer id)
   {
     this.id = id;
   }
 
   public String getSynVersionId()
   {
     return this.synVersionId;
   }
 
   public void setSynVersionId(String synVersionId)
   {
     this.synVersionId = synVersionId;
   }
 }

