package com.ndlan.cwwarm.componentimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;



import com.ndlan.cwwarm.model.CmEmployeeBean;
import com.ndlan.cwwarm.query.CmEmployeeQuery;

import com.ndlan.cwwarm.component.CmEmployeeBeanBusinessService;



@Service("cmEmployeeBeanBusinessService")
public class CmEmployeeBeanBusinessServiceImpl
	extends DefaultBesinessServiceImpl<CmEmployeeBean>  
	implements CmEmployeeBeanBusinessService{

	@Autowired(required=true)
	@Qualifier(value="cmEmployeeBeanAtomService")
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
