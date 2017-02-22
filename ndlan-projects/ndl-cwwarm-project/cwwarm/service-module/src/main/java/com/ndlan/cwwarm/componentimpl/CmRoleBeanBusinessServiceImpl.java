package com.ndlan.cwwarm.componentimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;



import com.ndlan.cwwarm.model.CmRoleBean;
import com.ndlan.cwwarm.query.CmRoleQuery;

import com.ndlan.cwwarm.component.CmRoleBeanBusinessService;



@Service("cmRoleBeanBusinessService")
public class CmRoleBeanBusinessServiceImpl
	extends DefaultBesinessServiceImpl<CmRoleBean>  
	implements CmRoleBeanBusinessService{

	@Autowired(required=true)
	@Qualifier(value="cmRoleBeanAtomService")
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
