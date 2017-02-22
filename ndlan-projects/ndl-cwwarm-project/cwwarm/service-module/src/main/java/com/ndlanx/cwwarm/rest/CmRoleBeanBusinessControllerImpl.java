package com.ndlanx.cwwarm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.web.restful.BaseBusinessControllerImpl;

import com.ndlan.cwwarm.model.CmRoleBean;

import com.ndlan.cwwarm.query.CmRoleQuery;


@Controller
@RequestMapping("/business/cm/role/")
public class CmRoleBeanBusinessControllerImpl 
extends BaseBusinessControllerImpl<CmRoleBean, CmRoleQuery> {

	@Autowired(required=true)
	@Qualifier(value="cmRoleBeanBusinessService")
	private BusinessService cmRoleBeanBusinessService;

	protected BusinessService<CmRoleBean> getBaseService(){
		return cmRoleBeanBusinessService;
	}

}

