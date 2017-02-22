package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.qtsy.DinerBillZiZhuDishe;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface DinerBillZiZhuDisheDao extends BaseDao<DinerBillZiZhuDishe, String>
{
  public abstract List<DinerBillZiZhuDishe> findByRestId(String paramString);
}

