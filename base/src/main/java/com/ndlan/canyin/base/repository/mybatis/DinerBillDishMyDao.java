package com.ndlan.canyin.base.repository.mybatis;

import com.ndlan.canyin.base.entity.cygl.Dishe;
import com.ndlan.canyin.base.repository.MyBatisRepository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public abstract interface DinerBillDishMyDao
{
	
public abstract List getuuuuu(@Param("selectedDate") String paramString1, @Param("restId") String paramString2);
	 
	
  public abstract List<Dishe> getALLDishesAndSet(@Param("restId") String paramString1, @Param("categoryId") String paramString2, @Param("dsCategoryId") String paramString3, @Param("keywords") String paramString4, @Param("estimateStatus") String paramString5, @Param("isRecommend") String paramString6, @Param("orderBy") String paramString7, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);

  public abstract int getALLDishesAndSetCount(@Param("restId") String paramString1, @Param("categoryId") String paramString2, @Param("dsCategoryId") String paramString3, @Param("keywords") String paramString4, @Param("estimateStatus") String paramString5, @Param("isRecommend") String paramString6);

  public abstract List<Dishe> getDishes(@Param("restId") String paramString1, @Param("categoryId") String paramString2, @Param("dsCategoryId") String paramString3, @Param("keywords") String paramString4, @Param("estimateStatus") String paramString5, @Param("isRecommend") String paramString6, @Param("orderBy") String paramString7, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);

  public abstract int getDishesCount(@Param("restId") String paramString1, @Param("categoryId") String paramString2, @Param("dsCategoryId") String paramString3, @Param("keywords") String paramString4, @Param("estimateStatus") String paramString5, @Param("isRecommend") String paramString6);

  public abstract List<Dishe> getSet(@Param("restId") String paramString1, @Param("categoryId") String paramString2, @Param("dsCategoryId") String paramString3, @Param("keywords") String paramString4, @Param("estimateStatus") String paramString5, @Param("isRecommend") String paramString6, @Param("orderBy") String paramString7, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);

  public abstract int getSetCount(@Param("restId") String paramString1, @Param("categoryId") String paramString2, @Param("dsCategoryId") String paramString3, @Param("keywords") String paramString4, @Param("estimateStatus") String paramString5, @Param("isRecommend") String paramString6);
}

