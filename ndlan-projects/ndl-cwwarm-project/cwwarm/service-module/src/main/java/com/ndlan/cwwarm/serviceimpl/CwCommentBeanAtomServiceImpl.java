package com.ndlan.cwwarm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;


import com.ndlan.cwwarm.dao.CwCommentBeanDao;


import com.ndlan.cwwarm.service.CwCommentBeanAtomService;
import com.ndlan.cwwarm.model.CwCommentBean;

@Service("cwCommentBeanAtomService")
public class CwCommentBeanAtomServiceImpl extends DefaultAtomBaseServiceImpl<CwCommentBeanDao,
	CwCommentBean> 
	implements CwCommentBeanAtomService {
	
	@Autowired(required=true)
	@Qualifier(value="cwCommentBeanDao")
	private CwCommentBeanDao cwCommentBeanDao;

	@Override
	public MyBatisDao<CwCommentBean> getBaseDao() {
		return cwCommentBeanDao;
	}

}
