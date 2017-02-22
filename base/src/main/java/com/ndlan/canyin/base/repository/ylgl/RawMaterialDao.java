package com.ndlan.canyin.base.repository.ylgl;

import com.ndlan.canyin.base.entity.ylgl.RawMaterial;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public abstract interface RawMaterialDao extends BaseDao<RawMaterial, String>
{
  public abstract List<RawMaterial> findByRmNameAndRestId(String paramString1, String paramString2);

  public abstract List<RawMaterial> getMaterialByRmName(String paramString);

  @Query("select r from RawMaterial r  where  r.storeHouse.shId=?1 ")
  public abstract List<RawMaterial> findByShId(String paramString);

@Query("from RawMaterial a where a.code=?1 and a.restId=?2 ")
public abstract List<RawMaterial> findByCodeAndRestId(String code,String currentUserRestId);
}

