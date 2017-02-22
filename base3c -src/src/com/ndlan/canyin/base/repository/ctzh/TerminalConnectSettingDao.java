package com.ndlan.canyin.base.repository.ctzh;

import com.ndlan.canyin.base.entity.ctzh.TerminalConnectSetting;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface TerminalConnectSettingDao extends BaseDao<TerminalConnectSetting, String>
{
  public abstract List<TerminalConnectSetting> findByTerminalType(String paramString);
}

