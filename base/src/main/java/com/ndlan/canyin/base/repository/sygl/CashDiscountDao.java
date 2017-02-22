package com.ndlan.canyin.base.repository.sygl;

import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.entity.sygl.CashDiscount;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface CashDiscountDao extends BaseDao<CashDiscount, String>
{
  public abstract List<CashDiscount> findByRestaurantOrderByCreateTimeDesc(Restaurant paramRestaurant);

  public abstract List<CashDiscount> findByRestaurantAndEnableStatusOrderByCreateTimeDesc(Restaurant paramRestaurant, String paramString);

  public abstract List<CashDiscount> findByRestaurantAndBelongOrg(Restaurant paramRestaurant, String paramString);
}

