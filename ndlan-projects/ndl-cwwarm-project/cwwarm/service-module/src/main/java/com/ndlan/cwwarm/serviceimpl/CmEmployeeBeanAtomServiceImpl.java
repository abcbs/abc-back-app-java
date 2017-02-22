package com.ndlan.cwwarm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;


import com.ndlan.cwwarm.dao.CmEmployeeBeanDao;


import com.ndlan.cwwarm.service.CmEmployeeBeanAtomService;
import com.ndlan.cwwarm.model.CmEmployeeBean;

@Service("cmEmployeeBeanAtomService")
public class CmEmployeeBeanAtomServiceImpl extends DefaultAtomBaseServiceImpl<CmEmployeeBeanDao,
	CmEmployeeBean> 
	implements CmEmployeeBeanAtomService {
	
	@Autowired(required=true)
	@Qualifier(value="cmEmployeeBeanDao")
	private CmEmployeeBeanDao cmEmployeeBeanDao;

	@Override
	public MyBatisDao<CmEmployeeBean> getBaseDao() {
		return cmEmployeeBeanDao;
	}

}
