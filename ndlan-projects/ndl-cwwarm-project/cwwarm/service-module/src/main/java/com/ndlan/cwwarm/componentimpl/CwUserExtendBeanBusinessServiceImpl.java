package com.ndlan.cwwarm.componentimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;



import com.ndlan.cwwarm.model.CwUserExtendBean;
import com.ndlan.cwwarm.query.CwUserExtendQuery;

import com.ndlan.cwwarm.component.CwUserExtendBeanBusinessService;



@Service("cwUserExtendBeanBusinessService")
public class CwUserExtendBeanBusinessServiceImpl
	extends DefaultBesinessServiceImpl<CwUserExtendBean>  
	implements CwUserExtendBeanBusinessService{

	@Autowired(required=true)
	@Qualifier(value="cwUserExtendBeanAtomService")
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
