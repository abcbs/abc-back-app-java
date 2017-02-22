package com.ndlan.cwwarm.componentimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;



import com.ndlan.cwwarm.model.CwAlertBean;
import com.ndlan.cwwarm.query.CwAlertQuery;

import com.ndlan.cwwarm.component.CwAlertBeanBusinessService;



@Service("cwAlertBeanBusinessService")
public class CwAlertBeanBusinessServiceImpl
	extends DefaultBesinessServiceImpl<CwAlertBean>  
	implements CwAlertBeanBusinessService{

	@Autowired(required=true)
	@Qualifier(value="cwAlertBeanAtomService")
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
