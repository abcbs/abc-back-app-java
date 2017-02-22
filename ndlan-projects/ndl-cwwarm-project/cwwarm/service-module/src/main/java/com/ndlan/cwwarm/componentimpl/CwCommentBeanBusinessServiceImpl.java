package com.ndlan.cwwarm.componentimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;



import com.ndlan.cwwarm.model.CwCommentBean;
import com.ndlan.cwwarm.query.CwCommentQuery;

import com.ndlan.cwwarm.component.CwCommentBeanBusinessService;



@Service("cwCommentBeanBusinessService")
public class CwCommentBeanBusinessServiceImpl
	extends DefaultBesinessServiceImpl<CwCommentBean>  
	implements CwCommentBeanBusinessService{

	@Autowired(required=true)
	@Qualifier(value="cwCommentBeanAtomService")
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
