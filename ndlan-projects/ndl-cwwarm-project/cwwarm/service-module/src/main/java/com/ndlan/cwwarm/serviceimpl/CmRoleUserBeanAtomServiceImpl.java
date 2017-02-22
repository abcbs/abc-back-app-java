package com.ndlan.cwwarm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;


import com.ndlan.cwwarm.dao.CmRoleUserBeanDao;


import com.ndlan.cwwarm.service.CmRoleUserBeanAtomService;
import com.ndlan.cwwarm.model.CmRoleUserBean;

@Service("cmRoleUserBeanAtomService")
public class CmRoleUserBeanAtomServiceImpl extends DefaultAtomBaseServiceImpl<CmRoleUserBeanDao,
	CmRoleUserBean> 
	implements CmRoleUserBeanAtomService {
	
	@Autowired(required=true)
	@Qualifier(value="cmRoleUserBeanDao")
	private CmRoleUserBeanDao cmRoleUserBeanDao;

	@Override
	public MyBatisDao<CmRoleUserBean> getBaseDao() {
		return cmRoleUserBeanDao;
	}

}
