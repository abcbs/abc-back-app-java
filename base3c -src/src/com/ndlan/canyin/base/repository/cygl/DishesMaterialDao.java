package com.ndlan.canyin.base.repository.cygl;

import com.ndlan.canyin.base.entity.cygl.DishesMaterial;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public abstract interface DishesMaterialDao extends BaseDao<DishesMaterial, String>
{
  public abstract List<DishesMaterial> findByRestId(String paramString);

  public abstract List<DishesMaterial> findByRestIdAndEnableStatus(String paramString1, String paramString2);

  public abstract DishesMaterial findByRestIdAndName(String paramString1, String paramString2);

  public abstract List<DishesMaterial> findListByRestIdAndName(String paramString1, String paramString2);

  public abstract List<DishesMaterial> findByCodeAndRestId(String paramString1, String paramString2);

@Query("from DishesMaterial a where a.name=? ")
public abstract DishesMaterial getoneByMaterialName(String materialName);
}

