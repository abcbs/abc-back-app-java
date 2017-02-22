package com.ndlan.cwwarm.componentimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;



import com.ndlan.cwwarm.model.CwOrgSiteBean;
import com.ndlan.cwwarm.query.CwOrgSiteQuery;

import com.ndlan.cwwarm.component.CwOrgSiteBeanBusinessService;



@Service("cwOrgSiteBeanBusinessService")
public class CwOrgSiteBeanBusinessServiceImpl
	extends DefaultBesinessServiceImpl<CwOrgSiteBean>  
	implements CwOrgSiteBeanBusinessService{

	@Autowired(required=true)
	@Qualifier(value="cwOrgSiteBeanAtomService")
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
