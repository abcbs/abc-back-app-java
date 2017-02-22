package com.ndlan.cwwarm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;


import com.ndlan.cwwarm.dao.CwPartakeBeanDao;


import com.ndlan.cwwarm.service.CwPartakeBeanAtomService;
import com.ndlan.cwwarm.model.CwPartakeBean;

@Service("cwPartakeBeanAtomService")
public class CwPartakeBeanAtomServiceImpl extends DefaultAtomBaseServiceImpl<CwPartakeBeanDao,
	CwPartakeBean> 
	implements CwPartakeBeanAtomService {
	
	@Autowired(required=true)
	@Qualifier(value="cwPartakeBeanDao")
	private CwPartakeBeanDao cwPartakeBeanDao;

	@Override
	public MyBatisDao<CwPartakeBean> getBaseDao() {
		return cwPartakeBeanDao;
	}

}
