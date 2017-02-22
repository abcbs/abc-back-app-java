 package com.ndlan.canyin.base.entity.cygl;
 
 import com.ndlan.canyin.base.entity.BaseEntity;
import com.ndlan.canyin.base.entity.cygl.SpecialDishe;

 import java.io.Serializable;
 import java.util.Date;
 import java.util.List;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.OneToMany;
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
 import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_special_price_interval")
 public class SpecialPriceInterval extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="spi_id", unique=true, nullable=false, length=36)
   private String spiId;
 
   @Temporal(TemporalType.DATE)
   @Column(name="end_date")
   private Date endDate;
 
   @Transient
   private String endDateStr;
 
   @Column(name="name")
   private String name;
 
   @Column(name="end_time", length=32)
   private String endTime;
 
   @Column(name="is_date_valid", length=1)
   private String isDateValid;
 
   @Column(name="is_friday", length=1)
   private String isFriday;
 
   @Column(name="is_monday", length=1)
   private String isMonday;
 
   @Column(name="is_saturday", length=1)
   private String isSaturday;
 
   @Column(name="is_special_day", length=1)
   private String isSpecialDay;
 
   @Column(name="is_sunday", length=1)
   private String isSunday;
 
   @Column(name="is_thursday", length=1)
   private String isThursday;
 
   @Column(name="is_time_limit", length=1)
   private String isTimeLimit;
 
   @Column(name="is_time_valid", length=1)
   private String isTimeValid;
 
   @Column(name="is_tuesday", length=1)
   private String isTuesday;
 
   @Column(name="is_wednesday", length=1)
   private String isWednesday;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Temporal(TemporalType.DATE)
   @Column(name="start_date")
   private Date startDate;
 
   @Transient
   private String startDateStr;
 
   @Column(name="start_time", length=32)
   private String startTime;
 
   @OneToMany(mappedBy="specialPriceInterval")
   private List<SpecialDishe> specialDishes;
 
   public String getSpiId()
   {
     return this.spiId;
   }
 
   public void setSpiId(String spiId) {
     this.spiId = spiId;
   }
 
   public Date getEndDate() {
     return this.endDate;
   }
 
   public void setEndDate(Date endDate) {
     this.endDate = endDate;
   }
 
   public String getEndTime() {
     return this.endTime;
   }
 
   public void setEndTime(String endTime) {
     this.endTime = endTime;
   }
 
   public String getIsDateValid() {
     return this.isDateValid;
   }
 
   public void setIsDateValid(String isDateValid) {
     this.isDateValid = isDateValid;
   }
 
   public String getIsFriday() {
     return this.isFriday;
   }
 
   public void setIsFriday(String isFriday) {
     this.isFriday = isFriday;
   }
 
   public String getIsMonday() {
     return this.isMonday;
   }
 
   public void setIsMonday(String isMonday) {
     this.isMonday = isMonday;
   }
 
   public String getIsSaturday() {
     return this.isSaturday;
   }
 
   public void setIsSaturday(String isSaturday) {
     this.isSaturday = isSaturday;
   }
 
   public String getIsSpecialDay() {
     return this.isSpecialDay;
   }
 
   public void setIsSpecialDay(String isSpecialDay) {
     this.isSpecialDay = isSpecialDay;
   }
 
   public String getIsSunday() {
     return this.isSunday;
   }
 
   public void setIsSunday(String isSunday) {
     this.isSunday = isSunday;
   }
 
   public String getIsThursday() {
     return this.isThursday;
   }
 
   public void setIsThursday(String isThursday) {
     this.isThursday = isThursday;
   }
 
   public String getIsTimeLimit() {
     return this.isTimeLimit;
   }
 
   public void setIsTimeLimit(String isTimeLimit) {
     this.isTimeLimit = isTimeLimit;
   }
 
   public String getIsTimeValid() {
     return this.isTimeValid;
   }
 
   public void setIsTimeValid(String isTimeValid) {
     this.isTimeValid = isTimeValid;
   }
 
   public String getIsTuesday() {
     return this.isTuesday;
   }
 
   public void setIsTuesday(String isTuesday) {
     this.isTuesday = isTuesday;
   }
 
   public String getIsWednesday() {
     return this.isWednesday;
   }
 
   public void setIsWednesday(String isWednesday) {
     this.isWednesday = isWednesday;
   }
 
   public String getRestId() {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public Date getStartDate() {
     return this.startDate;
   }
 
   public void setStartDate(Date startDate) {
     this.startDate = startDate;
   }
 
   public String getStartTime() {
     return this.startTime;
   }
 
   public void setStartTime(String startTime) {
     this.startTime = startTime;
   }
 
   public List<SpecialDishe> getSpecialDishes() {
     return this.specialDishes;
   }
 
   public void setSpecialDishes(List<SpecialDishe> specialDishes) {
     this.specialDishes = specialDishes;
   }
 
   public String getName() {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String getEndDateStr() {
     return this.endDateStr;
   }
 
   public void setEndDateStr(String endDateStr) {
     this.endDateStr = endDateStr;
   }
 
   public String getStartDateStr() {
     return this.startDateStr;
   }
 
   public void setStartDateStr(String startDateStr) {
     this.startDateStr = startDateStr;
   }
   public SpecialPriceInterval(String isTimeLimit){
	   this.isTimeLimit=isTimeLimit;
   }
   public SpecialPriceInterval(){
	   
   }
 }

