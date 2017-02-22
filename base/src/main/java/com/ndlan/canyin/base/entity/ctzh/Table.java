 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ctzh.TableArea;
import com.ndlan.canyin.base.entity.ctzh.TableBillRelation;
import com.ndlan.canyin.base.entity.ctzh.TablePic;
 import com.ndlan.canyin.core.common.BillStatusEnum;
 import com.ndlan.canyin.core.common.TrueFalseEnum;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.OneToMany;
 import javax.persistence.Transient;
 import org.hibernate.annotations.GenericGenerator;
 import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
 /**
  * ����
  * @author zhengjiansong
  *
  */
 @Entity
 @javax.persistence.Table(name="cm_table")
 @JsonIgnoreProperties(ignoreUnknown=true)
 public class Table extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="tab_id", unique=true, nullable=false, length=36)
   private String tabId;
 
   @Column(name="comment", length=2048)
   private String comment;
 
   @Column(name="dinner_status", length=32)
   private String dinnerStatus;
 
   @Column(name="is_enable", length=1)
   private String isEnable;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="area_id", nullable = false,updatable = false, insertable = false)
   private String areaId;
   
   @Column(name="seat")
   private String seat;
   
   @Column(name="min_seat")
   private String minSeat;
   
   @Column(name="show_seq")
   private int showSeq;
 
   @Column(name="tab_name", length=128)
   private String tabName;
 
   @Column(name="tab_no", length=64)
   private String tabNo;
 
   @ManyToOne(fetch=FetchType.LAZY, optional=true)
   @JoinColumn(name="waiter_id")
   @JsonIgnore
   @NotFound(action=NotFoundAction.IGNORE)
   private Employee waiter;
 
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="area_id" )
   @JsonIgnore
   private TableArea tableArea;
 
   @OneToMany(mappedBy="table", fetch=FetchType.LAZY)
   @JsonIgnore
   private List<TablePic> tablePics;
 
   @OneToMany(mappedBy="table", fetch=FetchType.LAZY)
   @JsonIgnore
   private List<DinerBill> dinerBills = new ArrayList();
 
   @OneToMany(mappedBy="table", fetch=FetchType.LAZY)
   @JsonIgnore
   private List<TableOrder> tableOrders = new ArrayList();
 
   @OneToMany(mappedBy="table", fetch=FetchType.LAZY)
   @JsonIgnore
   private List<TableBillRelation> tableBillRelations;
   
   @Transient
   private String totalPrice;//�˵��۸�  ios����
 
   @Transient
   @JsonIgnore
   private TableBillRelation lastedTableOrderBillRelation;
 
   @Transient
   @JsonIgnore
   private TableBillRelation lastedTableNormalBillRelation;
 
   @Transient
   @JsonIgnore
   private TableBillRelation currentTableBillRelation;
 
   @Transient
   private String isHasTableOrder = TrueFalseEnum.FALSE.getCode();
 
   @Transient
   private String isAtOrderWarnTime = TrueFalseEnum.FALSE.getCode();
 
   @Transient
   private String isAtOrderLockTime = TrueFalseEnum.FALSE.getCode();
 
   @Transient
   private String isAtOrderExpireTime = TrueFalseEnum.TRUE.getCode();
   
   @Transient
   private String isChedan ;
   @Transient
   @JsonIgnore
   private int picSize;
 
   @Transient
   private String orderStatus = "空闲";
 
   @Transient
   private int peopleCount = 0;
 
   @Transient
   private String tableOrderNo;
 
   @Transient
   private String openTableTime;
 
   @Transient
   private String billId;
 
   @Transient
   private String waiterId;
 
   @Transient
   private String saleManId;
 
   @Transient
   private String qrCodeImg;
 
   @JsonIgnore
   public TableBillRelation getCurrentTableBillRelation()
   {
     return this.currentTableBillRelation;
   }
 
   public void setCurrentTableBillRelation(TableBillRelation currentTableBillRelation)
   {
     this.currentTableBillRelation = currentTableBillRelation;
   }
 
   public void setLastedTableOrderBillRelation(TableBillRelation lastedTableOrderBillRelation) {
     this.lastedTableOrderBillRelation = lastedTableOrderBillRelation;
   }
 
   public String getAreaId() {
	return areaId;
}

public void setAreaId(String areaId) {
	this.areaId = areaId;
}

public TableBillRelation getLastedTableOrderBillRelation()
   {
     return this.lastedTableOrderBillRelation;
   }
 
   public String getTabId()
   {
     return this.tabId;
   }
 
   public void setTabId(String tabId) {
     this.tabId = tabId;
   }
 
   public String getComment() {
     return this.comment;
   }
 
   public void setComment(String comment) {
     this.comment = comment;
   }
 
   public String getDinnerStatus() {
     return this.dinnerStatus;
   }
 
   public void setDinnerStatus(String dinnerStatus) {
     this.dinnerStatus = dinnerStatus;
   }
 
   public String getIsEnable() {
     return this.isEnable;
   }
 
   public void setIsEnable(String isEnable) {
     this.isEnable = isEnable;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
 
   public int getShowSeq() {
     return this.showSeq;
   }
 
   public void setShowSeq(int showSeq) {
     this.showSeq = showSeq;
   }
 
   public String getTabName() {
     return this.tabName;
   }
 
   public void setTabName(String tabName) {
     this.tabName = tabName;
   }
 
   public String getTabNo() {
     return this.tabNo;
   }
 
   public void setTabNo(String tabNo) {
     this.tabNo = tabNo;
   }
 
   public TableArea getTableArea() {
     return this.tableArea;
   }
 
   public void setTableArea(TableArea tableArea) {
     this.tableArea = tableArea;
   }
 
   public List<TablePic> getTablePics() {
     return this.tablePics;
   }
 
   public void setTablePics(List<TablePic> tablePics) {
     this.tablePics = tablePics;
   }
 
   public Employee getWaiter() {
     return this.waiter;
   }
 
   public void setWaiter(Employee waiter) {
     this.waiter = waiter;
   }
 
   public int getPicSize() {
     return this.tablePics.size();
   }
 
   public void setPicSize(int picSize) {
     this.picSize = picSize;
   }
 
   public List<DinerBill> getDinerBills() {
     return this.dinerBills;
   }
 
   public void setDinerBills(List<DinerBill> dinerBills) {
     this.dinerBills = dinerBills;
   }
   public List<TableBillRelation> getTableBillRelations() {
     return this.tableBillRelations;
   }
   public void setTableBillRelations(List<TableBillRelation> tableBillRelations) {
     this.tableBillRelations = tableBillRelations;
   }
 
   public List<TableOrder> getTableOrders() {
     return this.tableOrders;
   }
 
   public void setTableOrders(List<TableOrder> tableOrders) {
     this.tableOrders = tableOrders;
   }
 
   public String getOrderStatus() {
     if (getCurrentTableBillRelation() != null) {
       return BillStatusEnum.getDesc(getCurrentTableBillRelation().getBillStatus());
     }
     return this.orderStatus;
   }
 
   public void setOrderStatus(String orderStatus)
   {
     this.orderStatus = orderStatus;
   }
 
   public int getPeopleCount()
   {
     if ((getCurrentTableBillRelation() != null) && (getCurrentTableBillRelation().getPeopleNum() != null)) {
       return getCurrentTableBillRelation().getPeopleNum().intValue();
     }
     return this.peopleCount;
   }
 
   public void setPeopleCount(int peopleCount) {
     this.peopleCount = peopleCount;
   }
 
   public String getTableOrderNo()
   {
     if ((getCurrentTableBillRelation() != null) && (getCurrentTableBillRelation().getDinerBill() != null)) {
       return getCurrentTableBillRelation().getDinerBill().getBillNo();
     }
     return this.tableOrderNo;
   }
 
   public void setTableOrderNo(String tableOrderNo)
   {
     this.tableOrderNo = tableOrderNo;
   }
 
   public String getOpenTableTime()
   {
     if (getCurrentTableBillRelation() != null) {
       return getCurrentTableBillRelation().getBillTime().toString().substring(11, 16);
     }
     return this.openTableTime;
   }
 
   public void setOpenTableTime(String openTableTime) {
     this.openTableTime = openTableTime;
   }
 
   public String getBillId()
   {
     if ((getCurrentTableBillRelation() != null) && (getCurrentTableBillRelation().getDinerBill() != null)) {
       return getCurrentTableBillRelation().getDinerBill().getBillId();
     }
     return this.billId;
   }
   public void setBillId(String billId) {
     if (("".equals(billId)) && 
       (getCurrentTableBillRelation() != null) && (getCurrentTableBillRelation().getDinerBill() != null)) {
       this.billId = getCurrentTableBillRelation().getDinerBill().getBillId();
     }
 
     this.billId = billId;
   }
   public String getWaiterId() {
     if (this.waiter != null) {
       this.waiter.getEmpId();
     }
     return this.waiterId;
   }
   public void setWaiterId(String waiterId) {
     this.waiterId = waiterId;
   }
 
   public String getIsHasTableOrder()
   {
     return this.isHasTableOrder;
   }
   public void setIsHasTableOrder(String isHasTableOrder) {
     this.isHasTableOrder = isHasTableOrder;
   }
 
   public String getIsAtOrderWarnTime()
   {
     return this.isAtOrderWarnTime;
   }
   public void setIsAtOrderWarnTime(String isAtOrderWarnTime) {
     this.isAtOrderWarnTime = isAtOrderWarnTime;
   }
 
   public String getIsAtOrderLockTime()
   {
     return this.isAtOrderLockTime;
   }
   public void setIsAtOrderLockTime(String isAtOrderLockTime) {
     this.isAtOrderLockTime = isAtOrderLockTime;
   }
 
   public String getIsAtOrderExpireTime()
   {
     return this.isAtOrderExpireTime;
   }
   public void setIsAtOrderExpireTime(String isAtOrderExpireTime) {
     this.isAtOrderExpireTime = isAtOrderExpireTime;
   }
 
   public String getSaleManId()
   {
     if ((getCurrentTableBillRelation() != null) && (getCurrentTableBillRelation().getDinerBill() != null)) {
       return getCurrentTableBillRelation().getDinerBill().getSalesmanId();
     }
     return this.saleManId;
   }
   public void setSaleManId(String saleManId) {
     this.saleManId = saleManId;
   }
   public String getQrCodeImg() {
     return this.qrCodeImg;
   }
   public void setQrCodeImg(String qrCodeImg) {
     this.qrCodeImg = qrCodeImg;
   }
 
   public TableBillRelation getLastedTableNormalBillRelation() {
     return this.lastedTableNormalBillRelation;
   }
 
   public void setLastedTableNormalBillRelation(TableBillRelation lastedTableNormalBillRelation)
   {
     this.lastedTableNormalBillRelation = lastedTableNormalBillRelation;
   }

public String getIsChedan() {
	return isChedan;
}

public void setIsChedan(String isChedan) {
	this.isChedan = isChedan;
}

public String getTotalPrice() {
	return totalPrice;
}

public void setTotalPrice(String totalPrice) {
	this.totalPrice = totalPrice;
}

public String getSeat() {
	return seat;
}

public void setSeat(String seat) {
	this.seat = seat;
}

public String getMinSeat() {
	return minSeat;
}

public void setMinSeat(String minSeat) {
	this.minSeat = minSeat;
}




   
 }

