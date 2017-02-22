package com.ndlan.canyin.base.repository.report.syfx;

import com.ndlan.canyin.base.repository.MyBatisRepository;

import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public abstract interface SYFXReportMyDao
{
  public abstract List getResettleStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3, @Param("userId") String paramString4, @Param("type") String paramString5);

  public abstract List getResettleReasonStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3, @Param("userId") String paramString4);

  public abstract List getForceStat(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3, @Param("forcePeople") String paramString4);

  public abstract List getForceStatDetail(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3, @Param("forcePeople") String paramString4);
}

