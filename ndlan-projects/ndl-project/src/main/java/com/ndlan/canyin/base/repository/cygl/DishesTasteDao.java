package com.ndlan.canyin.base.repository.cygl;

import com.ndlan.canyin.base.entity.cygl.DishesTaste;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public abstract interface DishesTasteDao extends BaseDao<DishesTaste, String>
{
  public abstract List<DishesTaste> findByRestId(String paramString);

  public abstract DishesTaste findByRestIdAndName(String paramString1, String paramString2);

  public abstract List<DishesTaste> findByCodeAndRestId(String paramString1, String paramString2);

  public abstract List<DishesTaste> findByRestIdAndEnableStatus(String paramString1, String paramString2);

  public abstract List<DishesTaste> findByCodeAndRestIdAndEnableStatus(String paramString1, String paramString2, String paramString3);
  
  @Query("from DishesTaste a where a.name=? ")
  public DishesTaste DgetoneByTasteName(String tasteName);
  
  
}

