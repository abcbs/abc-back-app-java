package com.ndlan.canyin.base.repository.xtgl;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.xtgl.AdvertisingImage;
import com.ndlan.canyin.base.entity.xtgl.PaySet;
import com.ndlan.canyin.base.repository.BaseDao;
public abstract interface PaySetDao extends BaseDao<PaySet, String> {
	
	@Query("select a from PaySet a ")
	  public abstract List<PaySet> findAll();
	  
	  public abstract List<PaySet> findByRestId(String paramString1);

}
