 package com.ndlan.canyin.base.entity.log;
 
 import com.ndlan.canyin.base.entity.BaseEntity;

 import java.io.Serializable;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="ocm_visit_log")
 public class VisitLog extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="visit_log_id", unique=true, nullable=false, length=36)
   private String visitLogId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="region_id", length=36)
   private String regionId;
 
   @Column(name="region_div", length=32)
   private String regionDiv;
 
   @Column(name="city_code", length=32)
   private String cityCode;
 
   @Column(name="city_name", length=128)
   private String cityName;
 
   @Column(name="province_code", length=32)
   private String provinceCode;
 
   @Column(name="province_name", length=128)
   private String provinceName;
 
   @Column(name="visit_type", length=36)
   private String visitType;
 
   @Column(name="action_url", length=32)
   private String action_url;
 
   @Column(name="G2_version", length=32)
   private String G2Version;
 
   @Column(name="terminal_ip", length=32)
   private String terminalIp;
 
   @Column(name="terminal_address", length=32)
   private String terminalAddress;
 
   @Column(name="terminal_mac", length=256)
   private String terminalMac;
 
   @Column(name="terminal_type", length=32)
   private String terminalType;
 
   @Column(name="terminal_os_type", length=128)
   private String terminalOsType;
 
   @Column(name="terminal_os_version", length=128)
   private String terminalOsVersion;
 
   @Column(name="terminal_cpu_num")
   private Integer terminalCpuNum;
 
   @Column(name="terminal_cpu_hz")
   private String terminalCpuHz;
 
   @Column(name="terminal_memory", length=32)
   private String terminalMemory;
 
   @Column(name="notes", length=1024)
   private String notes;
 
   public String getVisitLogId()
   {
     return this.visitLogId;
   }
 
   public void setVisitLogId(String visitLogId) {
     this.visitLogId = visitLogId;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getRegionId() {
     return this.regionId;
   }
 
   public void setRegionId(String regionId) {
     this.regionId = regionId;
   }
 
   public String getRegionDiv() {
     return this.regionDiv;
   }
 
   public void setRegionDiv(String regionDiv) {
     this.regionDiv = regionDiv;
   }
 
   public String getCityCode() {
     return this.cityCode;
   }
 
   public void setCityCode(String cityCode) {
     this.cityCode = cityCode;
   }
 
   public String getCityName() {
     return this.cityName;
   }
 
   public void setCityName(String cityName) {
     this.cityName = cityName;
   }
 
   public String getProvinceCode() {
     return this.provinceCode;
   }
 
   public void setProvinceCode(String provinceCode) {
     this.provinceCode = provinceCode;
   }
 
   public String getProvinceName() {
     return this.provinceName;
   }
 
   public void setProvinceName(String provinceName) {
     this.provinceName = provinceName;
   }
 
   public String getVisitType() {
     return this.visitType;
   }
 
   public void setVisitType(String visitType) {
     this.visitType = visitType;
   }
 
   public String getAction_url() {
     return this.action_url;
   }
 
   public void setAction_url(String action_url) {
     this.action_url = action_url;
   }
 
   public String getG2Version() {
     return this.G2Version;
   }
 
   public void setG2Version(String G2Version) {
     this.G2Version = G2Version;
   }
 
   public String getTerminalIp() {
     return this.terminalIp;
   }
 
   public void setTerminalIp(String terminalIp) {
     this.terminalIp = terminalIp;
   }
 
   public String getTerminalMac() {
     return this.terminalMac;
   }
 
   public void setTerminalMac(String terminalMac) {
     this.terminalMac = terminalMac;
   }
 
   public String getTerminalType() {
     return this.terminalType;
   }
 
   public void setTerminalType(String terminalType) {
     this.terminalType = terminalType;
   }
 
   public String getTerminalOsType() {
     return this.terminalOsType;
   }
 
   public void setTerminalOsType(String terminalOsType) {
     this.terminalOsType = terminalOsType;
   }
 
   public String getTerminalOsVersion() {
     return this.terminalOsVersion;
   }
 
   public void setTerminalOsVersion(String terminalOsVersion) {
     this.terminalOsVersion = terminalOsVersion;
   }
 
   public Integer getTerminalCpuNum() {
     return this.terminalCpuNum;
   }
 
   public void setTerminalCpuNum(Integer terminalCpuNum) {
     this.terminalCpuNum = terminalCpuNum;
   }
 
   public String getTerminalCpuHz() {
     return this.terminalCpuHz;
   }
 
   public void setTerminalCpuHz(String terminalCpuHz) {
     this.terminalCpuHz = terminalCpuHz;
   }
 
   public String getTerminalMemory() {
     return this.terminalMemory;
   }
 
   public void setTerminalMemory(String terminalMemory) {
     this.terminalMemory = terminalMemory;
   }
 
   public String getNotes() {
     return this.notes;
   }
 
   public void setNotes(String notes) {
     this.notes = notes;
   }
 
   public String getTerminalAddress() {
     return this.terminalAddress;
   }
 
   public void setTerminalAddress(String terminalAddress) {
     this.terminalAddress = terminalAddress;
   }
 }

