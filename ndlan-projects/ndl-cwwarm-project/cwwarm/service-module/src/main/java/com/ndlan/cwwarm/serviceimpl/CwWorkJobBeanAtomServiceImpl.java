package com.ndlan.cwwarm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;


import com.ndlan.cwwarm.dao.CwWorkJobBeanDao;


import com.ndlan.cwwarm.service.CwWorkJobBeanAtomService;
import com.ndlan.cwwarm.model.CwWorkJobBean;

@Service("cwWorkJobBeanAtomService")
public class CwWorkJobBeanAtomServiceImpl extends DefaultAtomBaseServiceImpl<CwWorkJobBeanDao,
	CwWorkJobBean> 
	implements CwWorkJobBeanAtomService {
	
	@Autowired(required=true)
	@Qualifier(value="cwWorkJobBeanDao")
	private CwWorkJobBeanDao cwWorkJobBeanDao;

	@Override
	public MyBatisDao<CwWorkJobBean> getBaseDao() {
		return cwWorkJobBeanDao;
	}

}
