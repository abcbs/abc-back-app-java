package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.qtsy.SelfOrder;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface SelfOrderDao extends BaseDao<SelfOrder, String>
{
  public abstract List<SelfOrder> findByRestId(String paramString);

  public abstract List<SelfOrder> findByRestIdAndTabNoAndStatusOrderByCreateTimeDesc(String paramString1, String paramString2, String paramString3);
}

