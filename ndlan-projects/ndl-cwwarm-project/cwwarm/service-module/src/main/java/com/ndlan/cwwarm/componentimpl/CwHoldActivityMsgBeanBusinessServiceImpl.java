package com.ndlan.cwwarm.componentimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;



import com.ndlan.cwwarm.model.CwHoldActivityMsgBean;
import com.ndlan.cwwarm.query.CwHoldActivityMsgQuery;

import com.ndlan.cwwarm.component.CwHoldActivityMsgBeanBusinessService;



@Service("cwHoldActivityMsgBeanBusinessService")
public class CwHoldActivityMsgBeanBusinessServiceImpl
	extends DefaultBesinessServiceImpl<CwHoldActivityMsgBean>  
	implements CwHoldActivityMsgBeanBusinessService{

	@Autowired(required=true)
	@Qualifier(value="cwHoldActivityMsgBeanAtomService")
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
