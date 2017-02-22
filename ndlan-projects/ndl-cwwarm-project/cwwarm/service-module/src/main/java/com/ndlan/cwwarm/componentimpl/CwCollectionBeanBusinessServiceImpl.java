package com.ndlan.cwwarm.componentimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;



import com.ndlan.cwwarm.model.CwCollectionBean;
import com.ndlan.cwwarm.query.CwCollectionQuery;

import com.ndlan.cwwarm.component.CwCollectionBeanBusinessService;



@Service("cwCollectionBeanBusinessService")
public class CwCollectionBeanBusinessServiceImpl
	extends DefaultBesinessServiceImpl<CwCollectionBean>  
	implements CwCollectionBeanBusinessService{

	@Autowired(required=true)
	@Qualifier(value="cwCollectionBeanAtomService")
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
