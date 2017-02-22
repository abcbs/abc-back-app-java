package com.ndlan.cwwarm.componentimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;



import com.ndlan.cwwarm.model.CwPartakeBean;
import com.ndlan.cwwarm.query.CwPartakeQuery;

import com.ndlan.cwwarm.component.CwPartakeBeanBusinessService;



@Service("cwPartakeBeanBusinessService")
public class CwPartakeBeanBusinessServiceImpl
	extends DefaultBesinessServiceImpl<CwPartakeBean>  
	implements CwPartakeBeanBusinessService{

	@Autowired(required=true)
	@Qualifier(value="cwPartakeBeanAtomService")
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
