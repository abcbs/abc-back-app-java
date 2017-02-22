package com.ndlan.canyin.base.repository.sygl;

import com.ndlan.canyin.base.entity.sygl.CashDiscount;
import com.ndlan.canyin.base.entity.sygl.CashDishesTypeDiscount;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface CashDishesTypeDiscountDao extends BaseDao<CashDishesTypeDiscount, String>
{
  public abstract List<CashDishesTypeDiscount> findByRestIdAndCashDiscountOrderByCreateTimeDesc(String paramString, CashDiscount paramCashDiscount);

  public abstract List<CashDishesTypeDiscount> findByRestIdAndDsCategoryIdLike(String paramString1, String paramString2);

  public abstract List<CashDishesTypeDiscount> findByRestIdAndCategoryIdLike(String paramString1, String paramString2);
}

