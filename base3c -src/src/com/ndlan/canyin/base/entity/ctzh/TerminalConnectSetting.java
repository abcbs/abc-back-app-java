 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.ndlan.canyin.base.entity.BaseEntity;

 import java.io.Serializable;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_terminal_connect_setting")
 public class TerminalConnectSetting extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="mts_id", unique=true, nullable=false, length=36)
   private String mtsId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="visit_type", length=32)
   private String visitType;
 
   @Column(name="terminal_type", length=32)
   private String terminalType;
 
   public String getMtsId()
   {
     return this.mtsId;
   }
 
   public void setMtsId(String mtsId) {
     this.mtsId = mtsId;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getVisitType() {
     return this.visitType;
   }
 
   public void setVisitType(String visitType) {
     this.visitType = visitType;
   }
 
   public String getTerminalType() {
     return this.terminalType;
   }
 
   public void setTerminalType(String terminalType) {
     this.terminalType = terminalType;
   }
 }

