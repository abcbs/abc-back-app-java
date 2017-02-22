package com.ndlan.canyin.base.repository.mybatis;

import com.ndlan.canyin.base.repository.MyBatisRepository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public abstract interface CostExpendMyDao
{
  public abstract List getmonthStat(@Param("startDate") String paramString1, @Param("restId") String paramString2, @Param("shopMan") String paramString3);

  public abstract List getPaymentTypeStats(@Param("startDate") String paramString1, @Param("endDate") String paramString2, @Param("restId") String paramString3);
  
}


