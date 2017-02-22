package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.qtsy.CostExpend;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface CostExpendDao extends BaseDao<CostExpend, String>
{
  public abstract List<CostExpend> findByRestId(String paramString);
}

