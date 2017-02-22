package com.ndlan.canyin.base.repository.ctzh;

import com.ndlan.canyin.base.entity.ctzh.Workstation;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface WorkStationDao extends BaseDao<Workstation, String>
{
  public abstract List<Workstation> findByIp(String paramString);
}

