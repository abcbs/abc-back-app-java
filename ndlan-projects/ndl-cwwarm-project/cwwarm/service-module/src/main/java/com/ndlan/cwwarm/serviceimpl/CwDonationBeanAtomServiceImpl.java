package com.ndlan.cwwarm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;


import com.ndlan.cwwarm.dao.CwDonationBeanDao;


import com.ndlan.cwwarm.service.CwDonationBeanAtomService;
import com.ndlan.cwwarm.model.CwDonationBean;

@Service("cwDonationBeanAtomService")
public class CwDonationBeanAtomServiceImpl extends DefaultAtomBaseServiceImpl<CwDonationBeanDao,
	CwDonationBean> 
	implements CwDonationBeanAtomService {
	
	@Autowired(required=true)
	@Qualifier(value="cwDonationBeanDao")
	private CwDonationBeanDao cwDonationBeanDao;

	@Override
	public MyBatisDao<CwDonationBean> getBaseDao() {
		return cwDonationBeanDao;
	}

}
