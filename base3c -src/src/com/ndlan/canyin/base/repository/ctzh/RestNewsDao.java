package com.ndlan.canyin.base.repository.ctzh;

import com.ndlan.canyin.base.entity.ctzh.RestNews;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface RestNewsDao extends BaseDao<RestNews, String>
{
  public abstract List<RestNews> findByRestIdAndIsShowOrderByTopTimeDescCreateTimeDesc(String paramString1, String paramString2);

  public abstract List<RestNews> findByRestIdOrderByTopTimeDescCreateTimeDesc(String paramString);

  public abstract List<RestNews> findByTitleAndRestId(String paramString1, String paramString2);
}

