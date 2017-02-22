package com.ndlan.canyin.base.repository.yygl;

import com.ndlan.canyin.base.entity.qtsy.DinerBillDishe;
import com.ndlan.canyin.base.repository.MyBatisRepository;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public abstract interface DinerBillDisheMyDao
{
  public abstract List<DinerBillDishe> getAll();

  public abstract List getWeekRetreatDish(@Param("weekSql") String paramString);

  public abstract List getSalesByDish(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("categoryId") String paramString3, @Param("restId") String paramString4, @Param("orderBy") String paramString5, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);

  //--------求和--菜肴销售排行（老的）-----
  public abstract List getSalesByDishs(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("categoryId") String paramString3, @Param("restId") String paramString4, @Param("orderBy") String paramString5, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);
  
  
  
  public abstract Integer getSalesByDishCount(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("categoryId") String paramString3, @Param("restId") String paramString4);

  public abstract List getTotalSalesByDish(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("categoryId") String paramString3, @Param("restId") String paramString4);

  public abstract List getSalesByDishCategory(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3, @Param("orderBy") String paramString4, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);
/**
 * 求和 菜肴分类销售排行
 * @param paramString1
 * @param paramString2
 * @param paramString3
 * @return
 */
  
  public abstract List getSalesByDishCategorys(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3, @Param("orderBy") String paramString4, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);
 
  
  
  public abstract Integer getSalesByDishCategoryCount(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract Double getTuiCaiDishCount(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3, @Param("keyWord") String paramString4);

  public abstract List searchCancelBillDishes(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3, @Param("keyWord") String paramString4, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);

  public abstract Integer searchCancelBillDishesCount(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3, @Param("keyWord") String paramString4);

  public abstract BigDecimal getTuiCaiDishTotalCost(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3, @Param("keyWord") String paramString4);

  public abstract List getRetreatReasonDish(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List getCurrentShiftDishesStat(@Param("restId") String paramString);

  public abstract List getCurrentShiftDishesStatAndBillType(@Param("restId") String paramString1, @Param("billType") String paramString2);

  public abstract List getCurrentShiftDishesStatByCesId(@Param("restId") String paramString1, @Param("cesId") String paramString2, @Param("billStatus") String paramString3);
}

