 package com.ndlan.canyin.base.entity.meta;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.meta.BaseCode;

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
 @Table(name="md_base_code_item")
 public class BaseCodeItem extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="bci_id", unique=true, nullable=false, length=36)
   private String bciId;
 
   @Column(name="bc_code", length=32)
   private String bcCode;
 
   @Column(name="bci_code", length=32)
   private String bciCode;
 
   @Column(name="bci_desc", length=2048)
   private String bciDesc;
 
   @Column(name="bci_name", length=128)
   private String bciName;
 
   @Column(name="show_seq")
   private Integer showSeq;
 
   @Column(name="is_enable", length=1)
   private String isEnable;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="bc_id")
   private BaseCode mdBaseCode;
 
   public String getBciId()
   {
     return this.bciId;
   }
 
   public void setBciId(String bciId) {
     this.bciId = bciId;
   }
 
   public String getBciCode() {
     return this.bciCode;
   }
 
   public void setBciCode(String bciCode) {
     this.bciCode = bciCode;
   }
 
   public String getBciDesc() {
     return this.bciDesc;
   }
 
   public void setBciDesc(String bciDesc) {
     this.bciDesc = bciDesc;
   }
 
   public String getBciName() {
     return this.bciName;
   }
 
   public void setBciName(String bciName) {
     this.bciName = bciName;
   }
 
   public Integer getShowSeq() {
     return this.showSeq;
   }
 
   public void setShowSeq(Integer showSeq) {
     this.showSeq = showSeq;
   }
 
   public BaseCode getMdBaseCode() {
     return this.mdBaseCode;
   }
 
   public void setMdBaseCode(BaseCode mdBaseCode) {
     this.mdBaseCode = mdBaseCode;
   }
 
   public String getIsEnable() {
     return this.isEnable;
   }
 
   public void setIsEnable(String isEnable) {
     this.isEnable = isEnable;
   }
 
   public String getBcCode() {
     return this.bcCode;
   }
 
   public void setBcCode(String bcCode) {
     this.bcCode = bcCode;
   }
 }

