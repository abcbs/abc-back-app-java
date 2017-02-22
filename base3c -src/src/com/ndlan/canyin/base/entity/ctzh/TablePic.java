 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.Table;

 import java.io.Serializable;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @javax.persistence.Table(name="cm_table_pic")
 public class TablePic extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="pic_id", unique=true, nullable=false, length=36)
   private String picId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="pic_type", length=32)
   private String picType;
 
   @Column(name="pic_url", length=500)
   private String picUrl;
 
   @Column(name="tab_no", length=64)
   private String tabNo;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="tab_id")
   private Table table;
 
   public TablePic()
   {
   }
 
   public TablePic(String picUrl)
   {
     this.picUrl = picUrl;
   }
 
   public String getPicId()
   {
     return this.picId;
   }
 
   public void setPicId(String picId) {
     this.picId = picId;
   }
 
   public String getPicType() {
     return this.picType;
   }
 
   public void setPicType(String picType) {
     this.picType = picType;
   }
 
   public String getPicUrl() {
     return this.picUrl;
   }
 
   public void setPicUrl(String picUrl) {
     this.picUrl = picUrl;
   }
 
   public String getTabNo() {
     return this.tabNo;
   }
 
   public void setTabNo(String tabNo) {
     this.tabNo = tabNo;
   }
 
   public Table getTable() {
     return this.table;
   }
 
   public void setTable(Table table) {
     this.table = table;
   }
 
   public String getRestId()
   {
     return this.restId;
   }
 
   public void setRestId(String restId)
   {
     this.restId = restId;
   }
 }

