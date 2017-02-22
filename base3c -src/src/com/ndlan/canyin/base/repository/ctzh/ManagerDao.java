package com.ndlan.canyin.base.repository.ctzh;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.ctzh.Accredit;
import com.ndlan.canyin.base.entity.ctzh.Restaurant;
import com.ndlan.canyin.base.repository.BaseDao;
/**
 * 
 * @author 
 *
 */
public abstract interface ManagerDao extends BaseDao<Accredit, String>
{

  @Query("select a from Accredit a ")
  public abstract List<Accredit> findAll();
  
  @Query("select a from Accredit a where a.storeId=?1 ")
  public abstract Accredit findAccreditByStoreId(String storeId);
}

