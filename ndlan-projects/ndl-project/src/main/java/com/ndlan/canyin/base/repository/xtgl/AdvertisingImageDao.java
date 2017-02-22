package com.ndlan.canyin.base.repository.xtgl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.xtgl.AdvertisingImage;
import com.ndlan.canyin.base.repository.BaseDao;

public abstract interface AdvertisingImageDao extends BaseDao<AdvertisingImage, String> {
	
	  @Query("select a from AdvertisingImage a ")
	  public abstract List<AdvertisingImage> findAll();
	  
	  public abstract List<AdvertisingImage> findByRestId(String paramString1);
	  
	 /* public abstract int getAllCount(@Param("restId") String paramString1);
	  
	  public abstract List<AdvertisingImage> getAll(@Param("restId") String paramString1, @Param("start") int paramInt1, @Param("pageSize") int paramInt2);*/

}
