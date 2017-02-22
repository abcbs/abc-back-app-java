package com.ndlan.cwwarm.componentimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;



import com.ndlan.cwwarm.model.CwWorkJobBean;
import com.ndlan.cwwarm.query.CwWorkJobQuery;

import com.ndlan.cwwarm.component.CwWorkJobBeanBusinessService;



@Service("cwWorkJobBeanBusinessService")
public class CwWorkJobBeanBusinessServiceImpl
	extends DefaultBesinessServiceImpl<CwWorkJobBean>  
	implements CwWorkJobBeanBusinessService{

	@Autowired(required=true)
	@Qualifier(value="cwWorkJobBeanAtomService")
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
