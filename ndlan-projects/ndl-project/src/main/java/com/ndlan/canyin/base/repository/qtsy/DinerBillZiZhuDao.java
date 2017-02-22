package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.qtsy.DinerBillZiZhu;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface DinerBillZiZhuDao extends BaseDao<DinerBillZiZhu, String>
{
  public abstract List<DinerBillZiZhu> findByRestId(String paramString);
}

