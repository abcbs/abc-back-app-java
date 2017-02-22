package com.ndlan.canyin.base.repository.sygl;

import com.ndlan.canyin.base.entity.sygl.CashSetting;
import com.ndlan.canyin.base.repository.BaseDao;

public abstract interface CashSettingDao extends BaseDao<CashSetting, String>
{
  public abstract CashSetting findByRestId(String paramString);
}

