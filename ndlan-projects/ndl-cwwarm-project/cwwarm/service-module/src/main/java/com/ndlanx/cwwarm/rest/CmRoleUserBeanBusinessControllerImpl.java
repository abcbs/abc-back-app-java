package com.ndlanx.cwwarm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.web.restful.BaseBusinessControllerImpl;

import com.ndlan.cwwarm.model.CmRoleUserBean;

import com.ndlan.cwwarm.query.CmRoleUserQuery;


@Controller
@RequestMapping("/business/cm/role/user/")
public class CmRoleUserBeanBusinessControllerImpl 
extends BaseBusinessControllerImpl<CmRoleUserBean, CmRoleUserQuery> {

	@Autowired(required=true)
	@Qualifier(value="cmRoleUserBeanBusinessService")
	private BusinessService cmRoleUserBeanBusinessService;

	protected BusinessService<CmRoleUserBean> getBaseService(){
		return cmRoleUserBeanBusinessService;
	}

}

