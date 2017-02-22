package com.ndlan.canyin.base.repository.meta;

import com.ndlan.canyin.base.entity.meta.BaseCodeItem;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface BaseCodeItemDao extends BaseDao<BaseCodeItem, String>
{
  public abstract List<BaseCodeItem> findByBcCode(String paramString);

  public abstract BaseCodeItem findByBcCodeAndBciCode(String paramString1, String paramString2);

  public abstract List<BaseCodeItem> findByBcCodeOrderByShowSeqAsc(String paramString);

  public abstract List<BaseCodeItem> findByBcCodeOrderByBciCodeAsc(String paramString);
}

