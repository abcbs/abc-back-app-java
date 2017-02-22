package com.ndlan.canyin.base.repository.sygl;

import com.ndlan.canyin.base.entity.sygl.BuffetSetting;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

public abstract interface BuffetSettingDao extends BaseDao<BuffetSetting, String>
{
  public abstract List<BuffetSetting> findByRestId(String paramString);

  public abstract List<BuffetSetting> findByRestIdAndEnableStatus(String paramString1, String paramString2);

  public abstract List<BuffetSetting> findByRestIdAndBuffetType(String paramString1, String paramString2);
}

