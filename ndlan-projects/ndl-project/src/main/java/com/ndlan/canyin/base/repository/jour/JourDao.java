package com.ndlan.canyin.base.repository.jour;

import com.ndlan.canyin.base.entity.jour.Jour;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface JourDao extends BaseDao<Jour, String>
{
  public abstract List<Jour> findByJourNoAndJourStatusAndRestId(String paramString1, String paramString2, String paramString3);
}

