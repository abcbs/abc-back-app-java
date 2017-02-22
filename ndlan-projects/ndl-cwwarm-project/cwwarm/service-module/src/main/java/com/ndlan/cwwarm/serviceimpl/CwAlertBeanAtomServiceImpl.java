package com.ndlan.cwwarm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;


import com.ndlan.cwwarm.dao.CwAlertBeanDao;


import com.ndlan.cwwarm.service.CwAlertBeanAtomService;
import com.ndlan.cwwarm.model.CwAlertBean;

@Service("cwAlertBeanAtomService")
public class CwAlertBeanAtomServiceImpl extends DefaultAtomBaseServiceImpl<CwAlertBeanDao,
	CwAlertBean> 
	implements CwAlertBeanAtomService {
	
	@Autowired(required=true)
	@Qualifier(value="cwAlertBeanDao")
	private CwAlertBeanDao cwAlertBeanDao;

	@Override
	public MyBatisDao<CwAlertBean> getBaseDao() {
		return cwAlertBeanDao;
	}

}
