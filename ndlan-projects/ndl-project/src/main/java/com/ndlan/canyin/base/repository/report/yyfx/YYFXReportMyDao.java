package com.ndlan.canyin.base.repository.report.yyfx;

import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
import com.ndlan.canyin.base.repository.MyBatisRepository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public abstract interface YYFXReportMyDao
{
  public abstract List getPaymentTypeStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);
  /**
   * 
   * @param paramString1
   * @param paramString2
   * @param paramString3
   * @return
   */
  public abstract List getPaymentTypeStats(@Param("pageType") String paramString1,@Param("startDate") String paramString2, @Param("endDate") String paramString3, @Param("restId") String paramString4);
  /**
   * 
   * @param paramString1
   * @param paramString2
   * @param paramString3
   * @return
   */
  public abstract List getPaymentTypeAllTotalmoneys(@Param("pageType") String paramString1,@Param("startDate") String paramString2, @Param("endDate") String paramString3, @Param("restId") String paramString4);

  
  public abstract List getPaymentTypeAllTotalmoney(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List<Map> getDiscountStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);
  public abstract List<Map> get7DayDiscountStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List<Map> getCustomerDiscountStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List getTableStat(@Param("tableAreaId") String paramString1, @Param("startDate") String paramString2, @Param("endDate") String paramString3, @Param("restId") String paramString4);
  
  public abstract List getTableAreaStatWeb(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);
  
  public abstract List getTableAreaStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List getTakeOutDinnerBillStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List getTakeOutDinnerBillStatByMonth(@Param("startMonth") String paramString1, @Param("endMonth") String paramString2, @Param("restId") String paramString3);

  public abstract List getGiveDishStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List getGiveDishDetails(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);

  public abstract Integer getGiveDishDetailsCount(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List getGiveDishDetailsSum(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List getPerCapitaConsumStatByMonth(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3, @Param("billType") String paramString4);

  public abstract List getFastfoodDinnerBillStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List getFastfoodDinnerBillStaByMonth(@Param("startMonth") String paramString1, @Param("endMonth") String paramString2, @Param("restId") String paramString3);

  public abstract List getDrawBillDayBrief(@Param("dateStart") String paramString1, @Param("dateEnd") String paramString2, @Param("restId") String paramString3);
  
  public abstract List getDrawBillDay(@Param("dateStart") String paramString1, @Param("dateEnd") String paramString2, @Param("restId") String paramString3);

  public abstract List getFastfoodDinnerYingShouzonghe(@Param("startMonth") String paramString1, @Param("endMonth") String paramString2, @Param("restId") String paramString3);
  
  public abstract List getFastfoodDinnerYingShouzongheFrontWeb(@Param("pageType") String paramString1,@Param("startDate") String paramString2,@Param("endDate") String paramString3, @Param("restId") String paramString4);
  //��̨
  public abstract Map getFastfoodDinnerYingShouzonghe2(@Param("startMonth") String paramString1, @Param("endMonth") String paramString2, @Param("restId") String paramString3);
  
  public abstract List getFastfoodDinnerBillStatlsit(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);
 
  
  public abstract List getFastfoodDinnerBillStatflist(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  
  public abstract List getSingleDishStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3, @Param("dishesId") String paramString4);

public abstract List<Map> getTimeDivSeatOccupancyRateStat(@Param("selectedDate") String paramString1, @Param("restId") String paramString2);
public abstract List<Map> get7DayDivSeatOccupancyRateStat(@Param("startDate") String paramString1,@Param("endDate") String paramString2 ,@Param("restId") String paramString3);

 
  
 /* public abstract List getTimeDivSeatOccupancyRateStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);
*/
  
  
  public abstract List getTimeDivSeatOccupancyRateStatLista(@Param("selectedDate") String paramString1, @Param("restId") String paramString2);

  
  public abstract List getDishSetSalesStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List getDishSalesStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("categoryId") String paramString3, @Param("restId") String paramString4, @Param("orderBy") String paramString5, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);
  
  public abstract List getDayBriefReport(@Param("dateStart") String paramString1, @Param("dateEnd") String paramString2, @Param("restId") String paramString3);

  public abstract List get30DayAgoBriefReport(@Param("dateStart") String paramString1, @Param("dateEnd") String paramString2, @Param("restId") String paramString3);

  public abstract List getDayBriefMemberCard(@Param("dateStart") String paramString1, @Param("dateEnd") String paramString2, @Param("restId") String paramString3);

  public abstract Double getDayBriefTuiCard(@Param("dateStart") String paramString1, @Param("dateEnd") String paramString2, @Param("restId") String paramString3);

  public abstract List getDayBriefOrder(@Param("dateStart") String paramString1, @Param("dateEnd") String paramString2, @Param("restId") String paramString3);

  public abstract List getBillCount(@Param("dateStart") String paramString1, @Param("dateEnd") String paramString2, @Param("restId") String paramString3);

  public abstract List getDayBriefTableAreaStat(@Param("dateStart") String paramString1, @Param("dateEnd") String paramString2, @Param("restId") String paramString3);

  public abstract List cashStatDayBrief(@Param("dateStart") String paramString1, @Param("dateEnd") String paramString2, @Param("restId") String paramString3);

  public abstract List giveDishDayBrief(@Param("dateStart") String paramString1, @Param("dateEnd") String paramString2, @Param("restId") String paramString3);

  public abstract List getRetreatDishDayBrief(@Param("dateStart") String paramString1, @Param("dateEnd") String paramString2, @Param("restId") String paramString3);

  public abstract List getForceStatDayBrief(@Param("dateStart") String paramString1, @Param("dateEnd") String paramString2, @Param("restId") String paramString3);

  public abstract List getSalesByDishCategoryDayBrief(@Param("dateStart") String paramString1, @Param("dateEnd") String paramString2, @Param("restId") String paramString3, @Param("level") String paramString4);
  
  public abstract List<Map<String,String>> getTop10(@Param("pageType")String paramString1,@Param("categoryId")String paramString2,@Param("restId")String paramString3,@Param("startDate")String paramString4 
		  ,@Param("endDate")String paramString5,@Param("sorting")String paramString6);

  public abstract List<Map<String,String>> getSetTop10(@Param("pageType")String paramString1,@Param("dsId")String paramString2,@Param("restId")String paramString3);
//qipeng
public abstract Integer getDishSalesStatCount(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("categoryId") String paramString3, @Param("restId") String paramString4);
//求和--菜肴销售排行新的
public abstract List getDishSalesStats(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("categoryId") String paramString3, @Param("restId") String paramString4, @Param("orderBy") String paramString5, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);
}

