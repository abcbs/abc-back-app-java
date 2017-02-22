package com.ndlan.cwwarm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;


import com.ndlan.cwwarm.dao.CwUserExtendBeanDao;


import com.ndlan.cwwarm.service.CwUserExtendBeanAtomService;
import com.ndlan.cwwarm.model.CwUserExtendBean;

@Service("cwUserExtendBeanAtomService")
public class CwUserExtendBeanAtomServiceImpl extends DefaultAtomBaseServiceImpl<CwUserExtendBeanDao,
	CwUserExtendBean> 
	implements CwUserExtendBeanAtomService {
	
	@Autowired(required=true)
	@Qualifier(value="cwUserExtendBeanDao")
	private CwUserExtendBeanDao cwUserExtendBeanDao;

	@Override
	public MyBatisDao<CwUserExtendBean> getBaseDao() {
		return cwUserExtendBeanDao;
	}

}
