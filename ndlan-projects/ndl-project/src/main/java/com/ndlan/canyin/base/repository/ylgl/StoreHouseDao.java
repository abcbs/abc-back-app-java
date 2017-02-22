package com.ndlan.canyin.base.repository.ylgl;

import com.ndlan.canyin.base.entity.ctzh.Employee;
import com.ndlan.canyin.base.entity.ylgl.StoreHouse;
import com.ndlan.canyin.base.repository.BaseDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public abstract interface StoreHouseDao extends BaseDao<StoreHouse, String>
{
  @Query("from StoreHouse a where a.shName=?1 and a.restId=?2")
  public abstract List<StoreHouse> findByShNameAndRestId(String paramString1, String paramString2);

  public abstract List<StoreHouse> findByRestId(String paramString);

  public abstract List<StoreHouse> findByManagerEmployee(Employee paramEmployee);

@Query("from StoreHouse a where a.managerCode=?1 and a.restId=?2 ")
public abstract List<StoreHouse> findByManagerCodeAndRestId(String managerCode,String currentUserRestId);
}

