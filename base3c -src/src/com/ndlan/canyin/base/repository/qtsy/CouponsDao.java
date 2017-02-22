package com.ndlan.canyin.base.repository.qtsy;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.qtsy.Coupons;
import com.ndlan.canyin.base.repository.BaseDao;

public abstract interface CouponsDao extends BaseDao<Coupons, String>
{
	@Query("select a from Coupons a where couId = ?1")
  public abstract Coupons findByCouId(String paramString1);
}

