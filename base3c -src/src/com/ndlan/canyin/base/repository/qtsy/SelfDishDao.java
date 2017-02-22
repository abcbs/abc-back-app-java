package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.qtsy.SelfDish;
import com.ndlan.canyin.base.entity.qtsy.SelfOrder;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface SelfDishDao extends BaseDao<SelfDish, String>
{
  public abstract List<SelfDish> findByRestId(String paramString);

  public abstract List<SelfDish> findBySelfOrderAndStatus(SelfOrder paramSelfOrder, String paramString);
}

