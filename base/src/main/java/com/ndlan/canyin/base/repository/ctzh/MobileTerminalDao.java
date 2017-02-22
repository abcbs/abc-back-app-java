package com.ndlan.canyin.base.repository.ctzh;

import com.ndlan.canyin.base.entity.ctzh.MobileTerminal;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface MobileTerminalDao extends BaseDao<MobileTerminal, String>
{
  public abstract List<MobileTerminal> findByIp(String paramString);
}

