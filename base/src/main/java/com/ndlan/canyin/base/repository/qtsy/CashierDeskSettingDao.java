package com.ndlan.canyin.base.repository.qtsy;

import com.ndlan.canyin.base.entity.qtsy.CashierDeskSetting;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface CashierDeskSettingDao extends BaseDao<CashierDeskSetting, String>
{
  public abstract List<CashierDeskSetting> findByRestIdAndEmpIdIsNull(String paramString);

  public abstract List<CashierDeskSetting> findByRestIdAndEmpId(String paramString1, String paramString2);
}

