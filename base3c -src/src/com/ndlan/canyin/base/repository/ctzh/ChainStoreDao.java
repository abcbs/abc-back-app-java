package com.ndlan.canyin.base.repository.ctzh;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.ctzh.ChainStore;
import com.ndlan.canyin.base.repository.BaseDao;
/**
 * 
 * @author husitong
 *
 */
public interface ChainStoreDao extends BaseDao<ChainStore,String>{
	
	  @Query("select c from ChainStore c ")
	  public abstract List<ChainStore> findAll();
	  @Query("select c from ChainStore c where c.path like ?1 ")
	  public abstract List<ChainStore> searByPath(String paramString);
	  @Query("select c from ChainStore c where c.parentStoreId = ?1 ")
	  public abstract List<ChainStore> getByParent(String paramString);
	  @Query("select c from ChainStore c where c.storeName like ?1 ")
	  public abstract List<ChainStore> getChainStoreByStoreName(String paramString);
}
