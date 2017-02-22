package com.ndlan.cwwarm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;


import com.ndlan.cwwarm.dao.CwOrgSiteBeanDao;


import com.ndlan.cwwarm.service.CwOrgSiteBeanAtomService;
import com.ndlan.cwwarm.model.CwOrgSiteBean;

@Service("cwOrgSiteBeanAtomService")
public class CwOrgSiteBeanAtomServiceImpl extends DefaultAtomBaseServiceImpl<CwOrgSiteBeanDao,
	CwOrgSiteBean> 
	implements CwOrgSiteBeanAtomService {
	
	@Autowired(required=true)
	@Qualifier(value="cwOrgSiteBeanDao")
	private CwOrgSiteBeanDao cwOrgSiteBeanDao;

	@Override
	public MyBatisDao<CwOrgSiteBean> getBaseDao() {
		return cwOrgSiteBeanDao;
	}

}
