package com.ndlan.cwwarm.componentimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;



import com.ndlan.cwwarm.model.CwDonationBean;
import com.ndlan.cwwarm.query.CwDonationQuery;

import com.ndlan.cwwarm.component.CwDonationBeanBusinessService;



@Service("cwDonationBeanBusinessService")
public class CwDonationBeanBusinessServiceImpl
	extends DefaultBesinessServiceImpl<CwDonationBean>  
	implements CwDonationBeanBusinessService{

	@Autowired(required=true)
	@Qualifier(value="cwDonationBeanAtomService")
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
