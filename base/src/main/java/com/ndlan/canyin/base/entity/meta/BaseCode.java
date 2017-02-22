 package com.ndlan.canyin.base.entity.meta;
 
 import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.meta.BaseCodeItem;

 import java.io.Serializable;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="md_base_code")
 public class BaseCode extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="bc_id", unique=true, nullable=false, length=36)
   private String bcId;
 
   @Column(name="bc_code", length=32)
   private String bcCode;
 
   @Column(name="bc_desc", length=2048)
   private String bcDesc;
 
   @Column(name="bc_name", length=128)
   private String bcName;
 
   @Column(name="is_enable", length=1)
   private String isEnable;
 
   @OneToMany(mappedBy="mdBaseCode")
   private List<BaseCodeItem> mdBaseCodeItems;
 
   public String getBcId()
   {
     return this.bcId;
   }
 
   public void setBcId(String bcId) {
     this.bcId = bcId;
   }
 
   public String getBcCode() {
     return this.bcCode;
   }
 
   public void setBcCode(String bcCode) {
     this.bcCode = bcCode;
   }
 
   public String getBcDesc() {
     return this.bcDesc;
   }
 
   public void setBcDesc(String bcDesc) {
     this.bcDesc = bcDesc;
   }
 
   public String getBcName() {
     return this.bcName;
   }
 
   public void setBcName(String bcName) {
     this.bcName = bcName;
   }
 
   public String getIsEnable()
   {
     return this.isEnable;
   }
 
   public void setIsEnable(String isEnable) {
     this.isEnable = isEnable;
   }
 
   public List<BaseCodeItem> getMdBaseCodeItems()
   {
     return this.mdBaseCodeItems;
   }
 
   public void setMdBaseCodeItems(List<BaseCodeItem> mdBaseCodeItems) {
     this.mdBaseCodeItems = mdBaseCodeItems;
   }
 }

