package com.ndlan.cwwarm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;


import com.ndlan.cwwarm.dao.CmRoleBeanDao;


import com.ndlan.cwwarm.service.CmRoleBeanAtomService;
import com.ndlan.cwwarm.model.CmRoleBean;

@Service("cmRoleBeanAtomService")
public class CmRoleBeanAtomServiceImpl extends DefaultAtomBaseServiceImpl<CmRoleBeanDao,
	CmRoleBean> 
	implements CmRoleBeanAtomService {
	
	@Autowired(required=true)
	@Qualifier(value="cmRoleBeanDao")
	private CmRoleBeanDao cmRoleBeanDao;

	@Override
	public MyBatisDao<CmRoleBean> getBaseDao() {
		return cmRoleBeanDao;
	}

}
