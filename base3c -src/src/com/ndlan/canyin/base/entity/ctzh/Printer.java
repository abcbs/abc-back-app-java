 package com.ndlan.canyin.base.entity.ctzh;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 import com.google.common.collect.ImmutableList;
import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.ctzh.PrinterLog;
import com.ndlan.canyin.base.entity.ctzh.PrinterTask;
 import com.ndlan.canyin.core.utils.Collections3;
 import java.io.Serializable;
 import java.math.BigDecimal;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
 import javax.persistence.Transient;
 import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_printer")
 @JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
 public class Printer extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="printer_id", unique=true, nullable=false, length=36)
   private String printerId;
 
   @Column(name="backup_printer", length=32)
   private String backupPrinter;
 
   @Column(name="init_seq", length=32)
   private String initSeq;
 
   @Column(name="interface", length=32)
   private String interface_;
 
   @Column(name="ip", length=128)
   private String ip;
 
   @Column(name="is_one_bill_one_times", length=1)
   private String isOneBillOneTimes;
 
   @Column(name="mac", length=128)
   private String mac;
 
   @Column(name="mode", length=32)
   private String mode;
 
   @Column(name="model", length=32)
   private String model;
 
   @Column(name="sys_name", length=64)
   private String sysName;
 
   @Column(name="name", length=64)
   private String name;
 
   @Column(name="template", length=32)
   private String template;
 
   @Column(name="notes", length=2048)
   private String notes;
 
   @Column(name="print_times")
   private int printTimes;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="status", length=32)
   private String status;
 
   @Column(name="type", length=32)
   private String type;
 
   @Column(name="dishe_category", length=1024)
   private String disheCategory;
 
   @Column(name="is_all_area", length=32)
   private String isAllArea = "1";
 
   @Column(name="is_all_dishe")
   private String isAllDishe = "1";
 
   @Column(name="is_use_drawer")
   private String isUseDrawer = "0";
 
   @Column(name="is_print_call_no")
   private String isPrintCallNo = "0";
 
   @Column(name="is_print_card_his")
   private String isPrintCardHis = "0";
 
   @Transient
   @JsonIgnore
   private List<String> disheCategoryIdList;
 
   @Column(name="table_area", length=1024)
   private String tableArea;
 
   @Column(name="margin_left")
   private BigDecimal marginLeft;
 
   @Column(name="margin_right")
   private BigDecimal marginRight;
 
   @Column(name="margin_up")
   private BigDecimal marginUp;
 
   @Column(name="margin_down")
   private BigDecimal marginDown;
 
   @Column(name="title")
   private String title;
 
   @Column(name="footnote")
   private String footNote;
 
   @Column(name="foot_image_url")
   private String footImgUrl;
 
   @Transient
   @JsonIgnore
   private List<String> tableAreaIdList;
 
   @OneToMany(mappedBy="printer", cascade={javax.persistence.CascadeType.ALL, javax.persistence.CascadeType.REMOVE})
   private List<PrinterLog> printerLogs;
 
   @OneToMany(mappedBy="printer", cascade={javax.persistence.CascadeType.ALL, javax.persistence.CascadeType.REMOVE})
   private List<PrinterTask> printerTasks;
 
   public String getPrinterId() { return this.printerId; }
 
   public void setPrinterId(String printerId)
   {
     this.printerId = printerId;
   }
 
   public String getBackupPrinter() {
     return this.backupPrinter;
   }
 
   public void setBackupPrinter(String backupPrinter) {
     this.backupPrinter = backupPrinter;
   }
 
   public String getInitSeq() {
     return this.initSeq;
   }
 
   public void setInitSeq(String initSeq) {
     this.initSeq = initSeq;
   }
 
   public String getInterface_() {
     return this.interface_;
   }
 
   public void setInterface_(String interface_) {
     this.interface_ = interface_;
   }
 
   public String getIp() {
     return this.ip;
   }
 
   public void setIp(String ip) {
     this.ip = ip;
   }
 
   public String getIsOneBillOneTimes() {
     return this.isOneBillOneTimes;
   }
 
   public void setIsOneBillOneTimes(String isOneBillOneTimes) {
     this.isOneBillOneTimes = isOneBillOneTimes;
   }
 
   public String getMac() {
     return this.mac;
   }
 
   public void setMac(String mac) {
     this.mac = mac;
   }
 
   public String getMode() {
     return this.mode;
   }
 
   public void setMode(String mode) {
     this.mode = mode;
   }
 
   public String getModel() {
     return this.model;
   }
 
   public void setModel(String model) {
     this.model = model;
   }
 
   public String getName() {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public int getPrintTimes() {
     return this.printTimes;
   }
 
   public void setPrintTimes(int printTimes) {
     this.printTimes = printTimes;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
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
 
   public List<PrinterLog> getPrinterLogs() {
     return this.printerLogs;
   }
 
   public void setPrinterLogs(List<PrinterLog> printerLogs) {
     this.printerLogs = printerLogs;
   }
 
   public List<PrinterTask> getPrinterTasks() {
     return this.printerTasks;
   }
 
   public void setPrinterTasks(List<PrinterTask> printerTasks) {
     this.printerTasks = printerTasks;
   }
 
   public String getSysName() {
     return this.sysName;
   }
 
   public void setSysName(String sysName) {
     this.sysName = sysName;
   }
 
   public String getTemplate() {
     return this.template;
   }
 
   public void setTemplate(String template) {
     this.template = template;
   }
 
   public String getDisheCategory() {
     return this.disheCategory;
   }
 
   public void setDisheCategory(String disheCategory) {
     this.disheCategory = disheCategory;
   }
 
   public List<String> getDisheCategoryIdList() {
     if (this.disheCategory != null)
     {
       this.disheCategoryIdList = ImmutableList.copyOf(StringUtils.split(this.disheCategory, ","));
     }
     return this.disheCategoryIdList;
   }
 
   public void setDisheCategoryIdList(List<String> disheCategoryIdList) {
     this.disheCategory = Collections3.convertToString(disheCategoryIdList, ",");
     this.disheCategoryIdList = disheCategoryIdList;
   }
 
   public String getTableArea() {
     return this.tableArea;
   }
 
   public void setTableArea(String tableArea) {
     this.tableArea = tableArea;
   }
 
   public List<String> getTableAreaIdList() {
     if (this.tableArea != null)
     {
       this.tableAreaIdList = ImmutableList.copyOf(StringUtils.split(this.tableArea, ","));
     }
     return this.tableAreaIdList;
   }
 
   public void setTableAreaIdList(List<String> tableAreaIdList) {
     this.tableArea = Collections3.convertToString(tableAreaIdList, ",");
     this.tableAreaIdList = tableAreaIdList;
   }
 
   public String getIsAllArea() {
     return this.isAllArea;
   }
 
   public void setIsAllArea(String isAllArea) {
     this.isAllArea = isAllArea;
   }
 
   public String getIsAllDishe() {
     return this.isAllDishe;
   }
 
   public void setIsAllDishe(String isAllDishe) {
     this.isAllDishe = isAllDishe;
   }
 
   public String getIsUseDrawer() {
     return this.isUseDrawer;
   }
 
   public void setIsUseDrawer(String isUseDrawer) {
     this.isUseDrawer = isUseDrawer;
   }
 
   public BigDecimal getMarginLeft() {
     return this.marginLeft;
   }
 
   public void setMarginLeft(BigDecimal marginLeft) {
     this.marginLeft = marginLeft;
   }
 
   public BigDecimal getMarginRight() {
     return this.marginRight;
   }
 
   public void setMarginRight(BigDecimal marginRight) {
     this.marginRight = marginRight;
   }
 
   public BigDecimal getMarginUp() {
     return this.marginUp;
   }
 
   public void setMarginUp(BigDecimal marginUp) {
     this.marginUp = marginUp;
   }
 
   public BigDecimal getMarginDown() {
     return this.marginDown;
   }
 
   public void setMarginDown(BigDecimal marginDown) {
     this.marginDown = marginDown;
   }
 
   public String getTitle() {
     return this.title;
   }
 
   public void setTitle(String title) {
     this.title = title;
   }
 
   public String getFootNote() {
     return this.footNote;
   }
 
   public void setFootNote(String footNote) {
     this.footNote = footNote;
   }
 
   public String getFootImgUrl() {
     return this.footImgUrl;
   }
 
   public void setFootImgUrl(String footImgUrl) {
     this.footImgUrl = footImgUrl;
   }
 
   public String getIsPrintCallNo() {
     return this.isPrintCallNo;
   }
 
   public void setIsPrintCallNo(String isPrintCallNo) {
     this.isPrintCallNo = isPrintCallNo;
   }
 
   public String getIsPrintCardHis() {
     return this.isPrintCardHis;
   }
 
   public void setIsPrintCardHis(String isPrintCardHis) {
     this.isPrintCardHis = isPrintCardHis;
   }
 }

