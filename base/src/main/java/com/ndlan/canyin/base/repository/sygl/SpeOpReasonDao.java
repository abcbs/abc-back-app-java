package com.ndlan.canyin.base.repository.sygl;

import com.ndlan.canyin.base.entity.sygl.SpeOpReason;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public abstract interface SpeOpReasonDao extends BaseDao<SpeOpReason, String>
{
  public abstract List<SpeOpReason> findByRestIdAndReaTypeAndEnableStatus(String paramString1, String paramString2, String paramString3);

  @Query("select speOpReason from SpeOpReason speOpReason where restId=?1 and reaType = ?2 and enableStatus = ?3 order by createTime asc ")
  public abstract List<SpeOpReason> findByRestIdAndReaTypeAndStatus(String paramString1, String paramString2, String paramString3);
}

