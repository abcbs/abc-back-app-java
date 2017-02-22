package com.ndlan.canyin.base.repository.report.ystj;

import com.ndlan.canyin.base.repository.MyBatisRepository;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public abstract interface YSTJReportMyDao
{
  public abstract List getHourStatByDay(@Param("dateStr") String paramString1, @Param("restId") String paramString2);

  public abstract List<HashMap> getStatPaymentByDayHour(@Param("dayHour") String paramString1, @Param("restId") String paramString2);

  public abstract List getyearStat(@Param("year") String paramString1, @Param("endYear") String paramString2, @Param("restId") String paramString3);

  public abstract List<HashMap> getStatPaymentByYear(@Param("restId") String paramString1, @Param("dateStr") String paramString2);

  public abstract List getWeekStat(@Param("weekSql") String paramString);

  public abstract List<HashMap> getStatPaymentByWeek(@Param("startTime") String paramString1, @Param("time") String paramString2, @Param("restId") String paramString3);

  public abstract List getSeasonStat(@Param("seasonSql") String paramString);

  public abstract List<HashMap> getStatPaymentBySeason(@Param("season") String paramString1, @Param("endSeason") String paramString2, @Param("year") String paramString3, @Param("restId") String paramString4);

  public abstract List getPeriodStat(@Param("dateList") List paramList, @Param("restId") String paramString);

  public abstract List<HashMap> getPeriodStatPayment(@Param("dateList") List paramList, @Param("restId") String paramString);
}

