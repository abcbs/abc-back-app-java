 package com.ndlan.canyin.base.entity.qtsy;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.DinerBillZiZhu;

 import java.io.Serializable;
 import java.math.BigDecimal;
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
 @Table(name="cm_diner_bill_zizhu_dishe")
 @JsonIgnoreProperties(ignoreUnknown=true)
 public class DinerBillZiZhuDishe extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="bd_id", unique=true, nullable=false, length=36)
   private String bdId;
 
   @Column(name="rest_id")
   private String restId;
 
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="bill_id")
   private DinerBillZiZhu dinerBillZiZhu;
 
   @Column(name="bill_no", length=32)
   private String billNo;
 
   @Column(name="unit_num")
   private float unitNum;
 
   @Column(name="zi_zhu_youhui_name", length=32)
   private String ziZhuYouhuiName;
 
   @Column(name="zi_zhu_youhui_unit_price")
   private BigDecimal ziZhuYouhuiUnitPrice;
 
   @Column(name="zi_zhu_youhui_total_price")
   private BigDecimal ziZhuYouhuiTotalPrice;
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="order_time")
   private Date orderTime;
 
   public String getBdId()
   {
     return this.bdId;
   }
 
   public void setBdId(String bdId) {
     this.bdId = bdId;
   }
 
   public DinerBillZiZhu getDinerBillZiZhu() {
     return this.dinerBillZiZhu;
   }
 
   public void setDinerBillZiZhu(DinerBillZiZhu dinerBillZiZhu) {
     this.dinerBillZiZhu = dinerBillZiZhu;
   }
 
   public String getBillNo() {
     return this.billNo;
   }
 
   public void setBillNo(String billNo) {
     this.billNo = billNo;
   }
 
   public float getUnitNum() {
     return this.unitNum;
   }
 
   public void setUnitNum(float unitNum) {
     this.unitNum = unitNum;
   }
 
   public String getZiZhuYouhuiName() {
     return this.ziZhuYouhuiName;
   }
 
   public void setZiZhuYouhuiName(String ziZhuYouhuiName) {
     this.ziZhuYouhuiName = ziZhuYouhuiName;
   }
 
   public BigDecimal getZiZhuYouhuiUnitPrice()
   {
     return this.ziZhuYouhuiUnitPrice;
   }
 
   public void setZiZhuYouhuiUnitPrice(BigDecimal ziZhuYouhuiUnitPrice) {
     this.ziZhuYouhuiUnitPrice = ziZhuYouhuiUnitPrice;
   }
 
   public BigDecimal getZiZhuYouhuiTotalPrice() {
     return this.ziZhuYouhuiTotalPrice;
   }
 
   public void setZiZhuYouhuiTotalPrice(BigDecimal ziZhuYouhuiTotalPrice) {
     this.ziZhuYouhuiTotalPrice = ziZhuYouhuiTotalPrice;
   }
 
   public Date getOrderTime()
   {
     return this.orderTime;
   }
 
   public void setOrderTime(Date orderTime) {
     this.orderTime = orderTime;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 }

