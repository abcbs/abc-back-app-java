 package com.ndlan.canyin.base.entity.sygl;
 
 import com.google.common.collect.ImmutableList;
import com.ndlan.canyin.base.entity.BaseEntity;
 import com.ndlan.canyin.core.common.BuffetTypeEnum;
 import com.ndlan.canyin.core.common.EnableStatusEnum;
 import java.io.Serializable;
 import java.math.BigDecimal;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
 import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
 
 @Entity
 @Table(name="cm_buffet_setting")
 public class BuffetSetting extends BaseEntity
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
 
   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy="uuid")
   @Column(name="cbs_id", unique=true, nullable=false, length=36)
   private String cbsId;
 
   @Column(name="rest_id", length=36)
   private String restId;
 
   @Column(name="buffet_type", length=128)
   private String buffetType;
 
   @Column(name="buffet_name", length=128)
   private String buffetName;
 
   @Column(name="buffet_start_time", length=128)
   private String buffetStartTime;
 
   @Column(name="buffet_end_time", length=128)
   private String buffetEndTime;
 
   @Column(name="price")
   private BigDecimal price;
 
   @Column(name="enable_status", length=1)
   private String enableStatus;
 
   @Column(name="monday_price")
   private BigDecimal mondayPrice;
 
   @Column(name="monday_youhui_namearray", length=128)
   private String mondayYouhuiNameArray;
 
   @Column(name="monday_youhui_pricearray", length=128)
   private String mondayYouhuiPriceArray;
 
   @Column(name="tuesday_price")
   private BigDecimal tuesdayPrice;
 
   @Column(name="tuesday_youhui_namearray", length=128)
   private String tuesdayYouhuiNameArray;
 
   @Column(name="tuesday_youhui_pricearray", length=128)
   private String tuesdayYouhuiPriceArray;
 
   @Column(name="wednesday_price")
   private BigDecimal wednesdayPrice;
 
   @Column(name="wednesday_youhui_namearray", length=128)
   private String wednesdayYouhuiNameArray;
 
   @Column(name="wednesday_youhui_pricearray", length=128)
   private String wednesdayYouhuiPriceArray;
 
   @Column(name="thursday_price")
   private BigDecimal thursdayPrice;
 
   @Column(name="thursday_youhui_namearray", length=128)
   private String thursdayYouhuiNameArray;
 
   @Column(name="thursday_youhui_pricearray", length=128)
   private String thursdayYouhuiPriceArray;
 
   @Column(name="friday_price")
   private BigDecimal fridayPrice;
 
   @Column(name="friday_youhui_namearray", length=128)
   private String fridayYouhuiNameArray;
 
   @Column(name="friday_youhui_pricearray", length=128)
   private String fridayYouhuiPriceArray;
 
   @Column(name="saturday_price")
   private BigDecimal saturdayPrice;
 
   @Column(name="saturday_youhui_namearray", length=128)
   private String saturdayYouhuiNameArray;
 
   @Column(name="saturday_youhui_pricearray", length=128)
   private String saturdayYouhuiPriceArray;
 
   @Column(name="sunday_price")
   private BigDecimal sundayPrice;
 
   @Column(name="sunday_youhui_namearray", length=128)
   private String sundayYouhuiNameArray;
 
   @Column(name="sunday_youhui_pricearray", length=128)
   private String sundayYouhuiPriceArray;
 
   public String getBuffetTypeDesc()
   {
     return BuffetTypeEnum.getDesc(this.buffetType);
   }
 
   public String getEnableStatusDesc()
   {
     return EnableStatusEnum.getDesc(this.enableStatus);
   }
 
   public List<Map> getMondayYouhuiList()
   {
     List list = new ArrayList();
     List mondayYouhuiNameList = getMondayYouhuiNameList();
     List mondayYouhuiPriceList = getMondayYouhuiPriceList();
     if (mondayYouhuiNameList != null)
     {
       for (int i = 0; i < mondayYouhuiNameList.size(); i++)
       {
         Map youhui = new HashMap();
         if ((StringUtils.isEmpty((CharSequence)mondayYouhuiNameList.get(i))) || 
           (StringUtils.isEmpty((CharSequence)mondayYouhuiPriceList.get(i))))
           continue;
         youhui.put("name", mondayYouhuiNameList.get(i));
         youhui.put("price", mondayYouhuiPriceList.get(i));
         list.add(youhui);
       }
     }
 
     return list;
   }
   public List<String> getMondayYouhuiNameList() {
     if (this.mondayYouhuiNameArray != null)
     {
       return ImmutableList.copyOf(StringUtils.split(this.mondayYouhuiNameArray, ","));
     }
     return null;
   }
   public List<String> getMondayYouhuiPriceList() {
     if (this.mondayYouhuiPriceArray != null)
     {
       return ImmutableList.copyOf(StringUtils.split(this.mondayYouhuiPriceArray, ","));
     }
     return null;
   }
 
   public List<Map> getTuesdayYouhuiList()
   {
     List list = new ArrayList();
     List tuesdayYouhuiNameList = getTuesdayYouhuiNameList();
     List tuesdayYouhuiPriceList = getTuesdayYouhuiPriceList();
     if (tuesdayYouhuiNameList != null)
     {
       for (int i = 0; i < tuesdayYouhuiNameList.size(); i++)
       {
         Map youhui = new HashMap();
         if ((StringUtils.isEmpty((CharSequence)tuesdayYouhuiNameList.get(i))) || 
           (StringUtils.isEmpty((CharSequence)tuesdayYouhuiPriceList.get(i))))
           continue;
         youhui.put("name", tuesdayYouhuiNameList.get(i));
         youhui.put("price", tuesdayYouhuiPriceList.get(i));
         list.add(youhui);
       }
     }
 
     return list;
   }
   public List<String> getTuesdayYouhuiNameList() {
     if (this.tuesdayYouhuiNameArray != null)
     {
       return ImmutableList.copyOf(StringUtils.split(this.tuesdayYouhuiNameArray, ","));
     }
     return null;
   }
   public List<String> getTuesdayYouhuiPriceList() {
     if (this.tuesdayYouhuiPriceArray != null)
     {
       return ImmutableList.copyOf(StringUtils.split(this.tuesdayYouhuiPriceArray, ","));
     }
     return null;
   }
 
   public List<Map> getWednesdayYouhuiList()
   {
     List list = new ArrayList();
     List wednesdayYouhuiNameList = getWednesdayYouhuiNameList();
     List wednesdayYouhuiPriceList = getWednesdayYouhuiPriceList();
     if (wednesdayYouhuiNameList != null)
     {
       for (int i = 0; i < wednesdayYouhuiNameList.size(); i++)
       {
         Map youhui = new HashMap();
         if ((StringUtils.isEmpty((CharSequence)wednesdayYouhuiNameList.get(i))) || 
           (StringUtils.isEmpty((CharSequence)wednesdayYouhuiPriceList.get(i))))
           continue;
         youhui.put("name", wednesdayYouhuiNameList.get(i));
         youhui.put("price", wednesdayYouhuiPriceList.get(i));
         list.add(youhui);
       }
     }
 
     return list;
   }
   public List<String> getWednesdayYouhuiNameList() {
     if (this.wednesdayYouhuiNameArray != null)
     {
       return ImmutableList.copyOf(StringUtils.split(this.wednesdayYouhuiNameArray, ","));
     }
     return null;
   }
   public List<String> getWednesdayYouhuiPriceList() {
     if (this.wednesdayYouhuiPriceArray != null)
     {
       return ImmutableList.copyOf(StringUtils.split(this.wednesdayYouhuiPriceArray, ","));
     }
     return null;
   }
 
   public List<Map> getThursdayYouhuiList()
   {
     List list = new ArrayList();
     List thursdayYouhuiNameList = getThursdayYouhuiNameList();
     List thursdayYouhuiPriceList = getThursdayYouhuiPriceList();
     if (thursdayYouhuiNameList != null)
     {
       for (int i = 0; i < thursdayYouhuiNameList.size(); i++)
       {
         Map youhui = new HashMap();
         if ((StringUtils.isEmpty((CharSequence)thursdayYouhuiNameList.get(i))) || 
           (StringUtils.isEmpty((CharSequence)thursdayYouhuiPriceList.get(i))))
           continue;
         youhui.put("name", thursdayYouhuiNameList.get(i));
         youhui.put("price", thursdayYouhuiPriceList.get(i));
         list.add(youhui);
       }
     }
 
     return list;
   }
   public List<String> getThursdayYouhuiNameList() {
     if (this.thursdayYouhuiNameArray != null)
     {
       return ImmutableList.copyOf(StringUtils.split(this.thursdayYouhuiNameArray, ","));
     }
     return null;
   }
   public List<String> getThursdayYouhuiPriceList() {
     if (this.thursdayYouhuiPriceArray != null)
     {
       return ImmutableList.copyOf(StringUtils.split(this.thursdayYouhuiPriceArray, ","));
     }
     return null;
   }
 
   public List<Map> getFridayYouhuiList()
   {
     List list = new ArrayList();
     List fridayYouhuiNameList = getFridayYouhuiNameList();
     List fridayYouhuiPriceList = getFridayYouhuiPriceList();
     if (fridayYouhuiNameList != null)
     {
       for (int i = 0; i < fridayYouhuiNameList.size(); i++)
       {
         Map youhui = new HashMap();
         if ((StringUtils.isEmpty((CharSequence)fridayYouhuiNameList.get(i))) || 
           (StringUtils.isEmpty((CharSequence)fridayYouhuiPriceList.get(i))))
           continue;
         youhui.put("name", fridayYouhuiNameList.get(i));
         youhui.put("price", fridayYouhuiPriceList.get(i));
         list.add(youhui);
       }
     }
 
     return list;
   }
   public List<String> getFridayYouhuiNameList() {
     if (this.fridayYouhuiNameArray != null)
     {
       return ImmutableList.copyOf(StringUtils.split(this.fridayYouhuiNameArray, ","));
     }
     return null;
   }
   public List<String> getFridayYouhuiPriceList() {
     if (this.fridayYouhuiPriceArray != null)
     {
       return ImmutableList.copyOf(StringUtils.split(this.fridayYouhuiPriceArray, ","));
     }
     return null;
   }
 
   public List<Map> getSaturdayYouhuiList()
   {
     List list = new ArrayList();
     List saturdayYouhuiNameList = getSaturdayYouhuiNameList();
     List saturdayYouhuiPriceList = getSaturdayYouhuiPriceList();
     if (saturdayYouhuiNameList != null)
     {
       for (int i = 0; i < saturdayYouhuiNameList.size(); i++)
       {
         Map youhui = new HashMap();
         if ((StringUtils.isEmpty((CharSequence)saturdayYouhuiNameList.get(i))) || 
           (StringUtils.isEmpty((CharSequence)saturdayYouhuiPriceList.get(i))))
           continue;
         youhui.put("name", saturdayYouhuiNameList.get(i));
         youhui.put("price", saturdayYouhuiPriceList.get(i));
         list.add(youhui);
       }
     }
 
     return list;
   }
   public List<String> getSaturdayYouhuiNameList() {
     if (this.saturdayYouhuiNameArray != null)
     {
       return ImmutableList.copyOf(StringUtils.split(this.saturdayYouhuiNameArray, ","));
     }
     return null;
   }
   public List<String> getSaturdayYouhuiPriceList() {
     if (this.saturdayYouhuiPriceArray != null)
     {
       return ImmutableList.copyOf(StringUtils.split(this.saturdayYouhuiPriceArray, ","));
     }
     return null;
   }
 
   public List<Map> getSundayYouhuiList()
   {
     List list = new ArrayList();
     List sundayYouhuiNameList = getSundayYouhuiNameList();
     List sundayYouhuiPriceList = getSundayYouhuiPriceList();
     if (sundayYouhuiNameList != null)
     {
       for (int i = 0; i < sundayYouhuiNameList.size(); i++)
       {
         Map youhui = new HashMap();
         if ((StringUtils.isEmpty((CharSequence)sundayYouhuiNameList.get(i))) || 
           (StringUtils.isEmpty((CharSequence)sundayYouhuiPriceList.get(i))))
           continue;
         youhui.put("name", sundayYouhuiNameList.get(i));
         youhui.put("price", sundayYouhuiPriceList.get(i));
         list.add(youhui);
       }
     }
 
     return list;
   }
   public List<String> getSundayYouhuiNameList() {
     if (this.sundayYouhuiNameArray != null)
     {
       return ImmutableList.copyOf(StringUtils.split(this.sundayYouhuiNameArray, ","));
     }
     return null;
   }
   public List<String> getSundayYouhuiPriceList() {
     if (this.sundayYouhuiPriceArray != null)
     {
       return ImmutableList.copyOf(StringUtils.split(this.sundayYouhuiPriceArray, ","));
     }
     return null;
   }
 
   public String getCbsId() {
     return this.cbsId;
   }
 
   public void setCbsId(String cbsId) {
     this.cbsId = cbsId;
   }
 
   public String getRestId()
   {
     return this.restId;
   }
 
   public void setRestId(String restId) {
     this.restId = restId;
   }
 
   public String getBuffetType() {
     return this.buffetType;
   }
 
   public void setBuffetType(String buffetType) {
     this.buffetType = buffetType;
   }
 
   public String getBuffetName() {
     return this.buffetName;
   }
 
   public void setBuffetName(String buffetName) {
     this.buffetName = buffetName;
   }
 
   public String getBuffetStartTime() {
     return this.buffetStartTime;
   }
 
   public void setBuffetStartTime(String buffetStartTime) {
     this.buffetStartTime = buffetStartTime;
   }
 
   public String getBuffetEndTime() {
     return this.buffetEndTime;
   }
 
   public void setBuffetEndTime(String buffetEndTime) {
     this.buffetEndTime = buffetEndTime;
   }
 
   public BigDecimal getPrice() {
     return this.price;
   }
 
   public void setPrice(BigDecimal price) {
     this.price = price;
   }
 
   public String getEnableStatus() {
     return this.enableStatus;
   }
 
   public void setEnableStatus(String enableStatus) {
     this.enableStatus = enableStatus;
   }
 
   public BigDecimal getMondayPrice() {
     if ((this.mondayPrice == null) || (this.mondayPrice.equals(BigDecimal.ZERO)))
     {
       return this.price;
     }
     return this.mondayPrice;
   }
 
   public void setMondayPrice(BigDecimal mondayPrice) {
     this.mondayPrice = mondayPrice;
   }
 
   public String getMondayYouhuiNameArray() {
     return this.mondayYouhuiNameArray;
   }
 
   public void setMondayYouhuiNameArray(String mondayYouhuiNameArray) {
     this.mondayYouhuiNameArray = mondayYouhuiNameArray;
   }
 
   public String getMondayYouhuiPriceArray() {
     return this.mondayYouhuiPriceArray;
   }
 
   public void setMondayYouhuiPriceArray(String mondayYouhuiPriceArray) {
     this.mondayYouhuiPriceArray = mondayYouhuiPriceArray;
   }
   public BigDecimal getTuesdayPrice() {
     if ((this.tuesdayPrice == null) || (this.tuesdayPrice.equals(BigDecimal.ZERO)))
     {
       return this.price;
     }
     return this.tuesdayPrice;
   }
   public void setTuesdayPrice(BigDecimal tuesdayPrice) {
     this.tuesdayPrice = tuesdayPrice;
   }
   public String getTuesdayYouhuiNameArray() {
     return this.tuesdayYouhuiNameArray;
   }
   public void setTuesdayYouhuiNameArray(String tuesdayYouhuiNameArray) {
     this.tuesdayYouhuiNameArray = tuesdayYouhuiNameArray;
   }
   public String getTuesdayYouhuiPriceArray() {
     return this.tuesdayYouhuiPriceArray;
   }
   public void setTuesdayYouhuiPriceArray(String tuesdayYouhuiPriceArray) {
     this.tuesdayYouhuiPriceArray = tuesdayYouhuiPriceArray;
   }
   public BigDecimal getWednesdayPrice() {
     if ((this.wednesdayPrice == null) || (this.wednesdayPrice.equals(BigDecimal.ZERO)))
     {
       return this.price;
     }
     return this.wednesdayPrice;
   }
   public void setWednesdayPrice(BigDecimal wednesdayPrice) {
     this.wednesdayPrice = wednesdayPrice;
   }
   public String getWednesdayYouhuiNameArray() {
     return this.wednesdayYouhuiNameArray;
   }
   public void setWednesdayYouhuiNameArray(String wednesdayYouhuiNameArray) {
     this.wednesdayYouhuiNameArray = wednesdayYouhuiNameArray;
   }
   public String getWednesdayYouhuiPriceArray() {
     return this.wednesdayYouhuiPriceArray;
   }
   public void setWednesdayYouhuiPriceArray(String wednesdayYouhuiPriceArray) {
     this.wednesdayYouhuiPriceArray = wednesdayYouhuiPriceArray;
   }
   public BigDecimal getThursdayPrice() {
     if ((this.thursdayPrice == null) || (this.thursdayPrice.equals(BigDecimal.ZERO)))
     {
       return this.price;
     }
     return this.thursdayPrice;
   }
   public void setThursdayPrice(BigDecimal thursdayPrice) {
     this.thursdayPrice = thursdayPrice;
   }
   public String getThursdayYouhuiNameArray() {
     return this.thursdayYouhuiNameArray;
   }
   public void setThursdayYouhuiNameArray(String thursdayYouhuiNameArray) {
     this.thursdayYouhuiNameArray = thursdayYouhuiNameArray;
   }
   public String getThursdayYouhuiPriceArray() {
     return this.thursdayYouhuiPriceArray;
   }
   public void setThursdayYouhuiPriceArray(String thursdayYouhuiPriceArray) {
     this.thursdayYouhuiPriceArray = thursdayYouhuiPriceArray;
   }
   public BigDecimal getFridayPrice() {
     if ((this.fridayPrice == null) || (this.fridayPrice.equals(BigDecimal.ZERO)))
     {
       return this.price;
     }
     return this.fridayPrice;
   }
   public void setFridayPrice(BigDecimal fridayPrice) {
     this.fridayPrice = fridayPrice;
   }
   public String getFridayYouhuiNameArray() {
     return this.fridayYouhuiNameArray;
   }
   public void setFridayYouhuiNameArray(String fridayYouhuiNameArray) {
     this.fridayYouhuiNameArray = fridayYouhuiNameArray;
   }
   public String getFridayYouhuiPriceArray() {
     return this.fridayYouhuiPriceArray;
   }
   public void setFridayYouhuiPriceArray(String fridayYouhuiPriceArray) {
     this.fridayYouhuiPriceArray = fridayYouhuiPriceArray;
   }
   public BigDecimal getSaturdayPrice() {
     if ((this.saturdayPrice == null) || (this.saturdayPrice.equals(BigDecimal.ZERO)))
     {
       return this.price;
     }
     return this.saturdayPrice;
   }
   public void setSaturdayPrice(BigDecimal saturdayPrice) {
     this.saturdayPrice = saturdayPrice;
   }
   public String getSaturdayYouhuiNameArray() {
     return this.saturdayYouhuiNameArray;
   }
   public void setSaturdayYouhuiNameArray(String saturdayYouhuiNameArray) {
     this.saturdayYouhuiNameArray = saturdayYouhuiNameArray;
   }
   public String getSaturdayYouhuiPriceArray() {
     return this.saturdayYouhuiPriceArray;
   }
   public void setSaturdayYouhuiPriceArray(String saturdayYouhuiPriceArray) {
     this.saturdayYouhuiPriceArray = saturdayYouhuiPriceArray;
   }
   public BigDecimal getSundayPrice() {
     if ((this.sundayPrice == null) || (this.sundayPrice.equals(BigDecimal.ZERO)))
     {
       return this.price;
     }
     return this.sundayPrice;
   }
   public void setSundayPrice(BigDecimal sundayPrice) {
     this.sundayPrice = sundayPrice;
   }
   public String getSundayYouhuiNameArray() {
     return this.sundayYouhuiNameArray;
   }
   public void setSundayYouhuiNameArray(String sundayYouhuiNameArray) {
     this.sundayYouhuiNameArray = sundayYouhuiNameArray;
   }
   public String getSundayYouhuiPriceArray() {
     return this.sundayYouhuiPriceArray;
   }
   public void setSundayYouhuiPriceArray(String sundayYouhuiPriceArray) {
     this.sundayYouhuiPriceArray = sundayYouhuiPriceArray;
   }
 }

