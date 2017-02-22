package com.ndlan.canyin.base.repository.cygl;

import com.ndlan.canyin.base.entity.cygl.SpecialPriceInterval;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface SpecialPriceIntervalDao extends BaseDao<SpecialPriceInterval, String>
{
  public abstract List<SpecialPriceInterval> findByRestId(String paramString);
}

