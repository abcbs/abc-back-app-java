package com.ndlan.cwwarm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;


import com.ndlan.cwwarm.dao.CwHoldActivityMsgBeanDao;


import com.ndlan.cwwarm.service.CwHoldActivityMsgBeanAtomService;
import com.ndlan.cwwarm.model.CwHoldActivityMsgBean;

@Service("cwHoldActivityMsgBeanAtomService")
public class CwHoldActivityMsgBeanAtomServiceImpl extends DefaultAtomBaseServiceImpl<CwHoldActivityMsgBeanDao,
	CwHoldActivityMsgBean> 
	implements CwHoldActivityMsgBeanAtomService {
	
	@Autowired(required=true)
	@Qualifier(value="cwHoldActivityMsgBeanDao")
	private CwHoldActivityMsgBeanDao cwHoldActivityMsgBeanDao;

	@Override
	public MyBatisDao<CwHoldActivityMsgBean> getBaseDao() {
		return cwHoldActivityMsgBeanDao;
	}

}
