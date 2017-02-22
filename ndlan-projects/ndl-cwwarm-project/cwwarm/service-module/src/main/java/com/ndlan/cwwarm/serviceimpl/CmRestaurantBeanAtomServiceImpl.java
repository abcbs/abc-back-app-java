package com.ndlan.cwwarm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;


import com.ndlan.cwwarm.dao.CmRestaurantBeanDao;


import com.ndlan.cwwarm.service.CmRestaurantBeanAtomService;
import com.ndlan.cwwarm.model.CmRestaurantBean;

@Service("cmRestaurantBeanAtomService")
public class CmRestaurantBeanAtomServiceImpl extends DefaultAtomBaseServiceImpl<CmRestaurantBeanDao,
	CmRestaurantBean> 
	implements CmRestaurantBeanAtomService {
	
	@Autowired(required=true)
	@Qualifier(value="cmRestaurantBeanDao")
	private CmRestaurantBeanDao cmRestaurantBeanDao;

	@Override
	public MyBatisDao<CmRestaurantBean> getBaseDao() {
		return cmRestaurantBeanDao;
	}

}
