package com.ndlan.canyin.base.repository.report.commission;

import com.ndlan.canyin.base.repository.MyBatisRepository;

import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public abstract interface CommissionReportMyDao
{
  public abstract List getBillOfSalesman(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("salesmanName") String paramString3, @Param("restId") String paramString4);

  public abstract List getBillsDetailOfSalesman(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3, @Param("salesmanId") String paramString4);

  public abstract List getDishesCommissionOfWaiter(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("waiterName") String paramString3, @Param("restId") String paramString4);
}

