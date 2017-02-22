package com.ndlan.canyin.base.repository.mybatis;

import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.entity.qtsy.DinerBill;
import com.ndlan.canyin.base.entity.qtsy.DinerBillPayment;
import com.ndlan.canyin.base.repository.MyBatisRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public abstract interface DinerBillMyDao
{
  public abstract List<DinerBill> getAll(@Param("restId") String paramString1, @Param("billStatus") String paramString2, @Param("pageType") String paramString3, @Param("billType") String paramString4, @Param("keywords") String paramString5, @Param("isValid") String paramString6, @Param("start") int paramInt1, @Param("pageSize") int paramInt2,
		  @Param("isHasAllArea") String paramString7,
		  @Param("userId") String paramString8,
		  @Param("startDate") String paramString9,
		  @Param("endDate") String paramString10
		  );
  public abstract DinerBill getAllss(@Param("billId") String paramString1, @Param("restId") String paramString2
		
		  );


  public abstract int getAllCount(@Param("restId") String paramString1, @Param("billStatus") String paramString2, @Param("pageType") String paramString3, @Param("billType") String paramString4, @Param("keywords") String paramString5, @Param("isValid") String paramString6, @Param("isHasAllArea") String paramString7,
		  @Param("userId") String paramString8,
		  @Param("startDate") String paramString9,
		  @Param("endDate") String paramString10
		  );

  public abstract List<Dishe> getALLDishesAndSet(@Param("restId") String paramString1, @Param("categoryId") String paramString2, @Param("dsCategoryId") String paramString3, @Param("keywords") String paramString4, @Param("estimateStatus") String paramString5, @Param("isTakeout") String paramString6, @Param("orderBy") String paramString7, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);

  public abstract int getALLDishesAndSetCount(@Param("restId") String paramString1, @Param("categoryId") String paramString2, @Param("dsCategoryId") String paramString3, @Param("keywords") String paramString4, @Param("estimateStatus") String paramString5, @Param("isTakeout") String paramString6);

  public abstract List<Dishe> getDishes(@Param("restId") String paramString1, @Param("categoryId") String paramString2, @Param("dsCategoryId") String paramString3, @Param("keywords") String paramString4, @Param("estimateStatus") String paramString5, @Param("isTakeout") String paramString6, @Param("orderBy") String paramString7, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);

  public abstract int getDishesCount(@Param("restId") String paramString1, @Param("categoryId") String paramString2, @Param("dsCategoryId") String paramString3, @Param("keywords") String paramString4, @Param("estimateStatus") String paramString5, @Param("isTakeout") String paramString6);

  public abstract List<Dishe> getSet(@Param("restId") String paramString1, @Param("categoryId") String paramString2, @Param("dsCategoryId") String paramString3, @Param("keywords") String paramString4, @Param("estimateStatus") String paramString5, @Param("isTakeout") String paramString6, @Param("orderBy") String paramString7, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);

  public abstract int getSetCount(@Param("restId") String paramString1, @Param("categoryId") String paramString2, @Param("dsCategoryId") String paramString3, @Param("keywords") String paramString4, @Param("estimateStatus") String paramString5, @Param("isTakeout") String paramString6);

  public abstract BigDecimal getCurrentDayPayableCostTotal(@Param("restId") String paramString1, @Param("isHasAllArea") String paramString2, @Param("userId") String paramString3);

  public abstract BigDecimal getCurrentDayRealCostTotal(@Param("restId") String paramString1, @Param("isHasAllArea") String paramString2, @Param("userId") String paramString3);

  public abstract List<Map<String, String>> getTakeoutList(@Param("restId") String paramString1, @Param("billStatus") String paramString2, @Param("sendTimeType") String paramString3, @Param("startDate") String paramString4, @Param("keywords") String paramString5, @Param("isCount") String paramString6, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);

  public abstract List<Map<String, String>> getTakeoutListForNoPay(@Param("restId") String paramString);

  public abstract long getTakeoutListForNoPayCount(@Param("restId") String paramString);

  public abstract Long getCountByBillId(@Param("billId") String paramString);
}

