package com.ndlan.cwwarm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;


import com.ndlan.cwwarm.dao.CwForHelpMsgBeanDao;


import com.ndlan.cwwarm.service.CwForHelpMsgBeanAtomService;
import com.ndlan.cwwarm.model.CwForHelpMsgBean;

@Service("cwForHelpMsgBeanAtomService")
public class CwForHelpMsgBeanAtomServiceImpl extends DefaultAtomBaseServiceImpl<CwForHelpMsgBeanDao,
	CwForHelpMsgBean> 
	implements CwForHelpMsgBeanAtomService {
	
	@Autowired(required=true)
	@Qualifier(value="cwForHelpMsgBeanDao")
	private CwForHelpMsgBeanDao cwForHelpMsgBeanDao;

	@Override
	public MyBatisDao<CwForHelpMsgBean> getBaseDao() {
		return cwForHelpMsgBeanDao;
	}

}
