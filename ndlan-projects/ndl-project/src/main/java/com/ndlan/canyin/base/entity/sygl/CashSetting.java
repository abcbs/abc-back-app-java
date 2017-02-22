 package com.ndlan.canyin.base.entity.sygl;
 
 import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;

 import java.io.Serializable;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.OneToOne;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_cash_setting")
 public class CashSetting extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="st_id", unique=true, nullable=false, length=36)
   private String stId;
 
   @Column(name="is_enter_order_page", length=1)
   private String isEnterOrderPage;
 
   @Column(name="is_show_undone_order", length=1)
   private String isShowUndoneOrder;
 
   @Column(name="is_round", length=1)
   private String isRound;
 
   @Column(name="moling_mode", length=32)
   private String molingMode;
 
   @Column(name="ordered_same_dishes_merge", length=1)
   private String orderedSameDishesMerge;
 
   @Column(name="ordering_same_dishes_merge", length=1)
   private String orderingSameDishesMerge;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="sysdata_type", length=32)
   private String sysdataType;
 
   @OneToOne(mappedBy="cashSetting")
   private DinerBill dinerBill;
 
   public String getStId()
   {
     return this.stId;
   }
 
   public void setStId(String stId) {
     this.stId = stId;
   }
 
   public String getIsEnterOrderPage() {
     return this.isEnterOrderPage;
   }
 
   public void setIsEnterOrderPage(String isEnterOrderPage) {
     this.isEnterOrderPage = isEnterOrderPage;
   }
 
   public String getIsShowUndoneOrder() {
     return this.isShowUndoneOrder;
   }
 
   public void setIsShowUndoneOrder(String isShowUndoneOrder) {
     this.isShowUndoneOrder = isShowUndoneOrder;
   }
 
   public String getMolingMode() {
     return this.molingMode;
   }
 
   public void setMolingMode(String molingMode) {
     this.molingMode = molingMode;
   }
 
   public String getOrderedSameDishesMerge() {
     return this.orderedSameDishesMerge;
   }
 
   public void setOrderedSameDishesMerge(String orderedSameDishesMerge) {
     this.orderedSameDishesMerge = orderedSameDishesMerge;
   }
 
   public String getOrderingSameDishesMerge() {
     return this.orderingSameDishesMerge;
   }
 
   public void setOrderingSameDishesMerge(String orderingSameDishesMerge) {
     this.orderingSameDishesMerge = orderingSameDishesMerge;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getSysdataType() {
     return this.sysdataType;
   }
 
   public void setSysdataType(String sysdataType) {
     this.sysdataType = sysdataType;
   }
 
   public DinerBill getDinerBill() {
     return this.dinerBill;
   }
 
   public void setDinerBill(DinerBill dinerBill) {
     this.dinerBill = dinerBill;
   }
 
   public String getIsRound() {
     return this.isRound;
   }
 
   public void setIsRound(String isRound) {
     this.isRound = isRound;
   }
 }

