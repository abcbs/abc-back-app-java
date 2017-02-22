package com.ndlan.cwwarm.componentimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;



import com.ndlan.cwwarm.model.CmRoleUserBean;
import com.ndlan.cwwarm.query.CmRoleUserQuery;

import com.ndlan.cwwarm.component.CmRoleUserBeanBusinessService;



@Service("cmRoleUserBeanBusinessService")
public class CmRoleUserBeanBusinessServiceImpl
	extends DefaultBesinessServiceImpl<CmRoleUserBean>  
	implements CmRoleUserBeanBusinessService{

	@Autowired(required=true)
	@Qualifier(value="cmRoleUserBeanAtomService")
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
