package com.ndlan.canyin.base.repository.yygl;

import com.ndlan.canyin.base.repository.MyBatisRepository;

import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public abstract interface BusinessStatMyDao
{
  public abstract List getStatPaymentByDay(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List getStatByDay(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List getDayStat(@Param("daySql") String paramString);

  public abstract List getStatPaymentByMonth(@Param("month") String paramString1, @Param("restId") String paramString2);

  public abstract List getmonthStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List getMemberRechargeDayStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List getMemberEveryDayRechargeStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List getMemberRechargeMonthStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);

  public abstract List getMemberEveryMonthRechargeStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);
}

