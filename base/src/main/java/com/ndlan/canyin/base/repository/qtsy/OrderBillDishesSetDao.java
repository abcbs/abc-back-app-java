package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.cygl.DishesSet;
import com.ndlan.canyin.base.entity.qtsy.OrderBillDishesSet;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface OrderBillDishesSetDao extends BaseDao<OrderBillDishesSet, String>
{
  public abstract List<OrderBillDishesSet> findByRestIdAndDishesSet(String paramString, DishesSet paramDishesSet);

  public abstract List<OrderBillDishesSet> findByRestIdAndTableOrder(String paramString, TableOrder paramTableOrder);
}

