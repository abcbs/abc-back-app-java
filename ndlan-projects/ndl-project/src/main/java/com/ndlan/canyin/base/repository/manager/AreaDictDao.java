package com.ndlan.canyin.base.repository.manager;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.manager.AreaDict;
import com.ndlan.canyin.base.repository.BaseDao;

public abstract interface AreaDictDao extends BaseDao<AreaDict, String> {
	@Query("select a from AreaDict a where a.parentCode='0000'")
	 public abstract List<AreaDict> getAllProvince();
	
	@Query("select a from AreaDict a where a.parentCode=?")
	 public abstract List<AreaDict> getAllCityByProvince(String str);

	@Query("select a from AreaDict a where a.areaCode=?1 or a.areaCode=?2 ")
	public abstract List<AreaDict> getProvinceCityName(String str1,String str2);
}
