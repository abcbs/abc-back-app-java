package com.ndlan.cwwarm.componentimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;



import com.ndlan.cwwarm.model.CwForHelpMsgBean;
import com.ndlan.cwwarm.query.CwForHelpMsgQuery;

import com.ndlan.cwwarm.component.CwForHelpMsgBeanBusinessService;



@Service("cwForHelpMsgBeanBusinessService")
public class CwForHelpMsgBeanBusinessServiceImpl
	extends DefaultBesinessServiceImpl<CwForHelpMsgBean>  
	implements CwForHelpMsgBeanBusinessService{

	@Autowired(required=true)
	@Qualifier(value="cwForHelpMsgBeanAtomService")
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
