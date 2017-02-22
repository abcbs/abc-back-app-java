package com.ndlan.cwwarm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;


import com.ndlan.cwwarm.dao.CwCollectionBeanDao;


import com.ndlan.cwwarm.service.CwCollectionBeanAtomService;
import com.ndlan.cwwarm.model.CwCollectionBean;

@Service("cwCollectionBeanAtomService")
public class CwCollectionBeanAtomServiceImpl extends DefaultAtomBaseServiceImpl<CwCollectionBeanDao,
	CwCollectionBean> 
	implements CwCollectionBeanAtomService {
	
	@Autowired(required=true)
	@Qualifier(value="cwCollectionBeanDao")
	private CwCollectionBeanDao cwCollectionBeanDao;

	@Override
	public MyBatisDao<CwCollectionBean> getBaseDao() {
		return cwCollectionBeanDao;
	}

}
