package com.ndlanx.cwwarm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.web.restful.BaseBusinessControllerImpl;

import com.ndlan.cwwarm.model.CwUserExtendBean;

import com.ndlan.cwwarm.query.CwUserExtendQuery;


@Controller
@RequestMapping("/business/cw/user/extend/")
public class CwUserExtendBeanBusinessControllerImpl 
extends BaseBusinessControllerImpl<CwUserExtendBean, CwUserExtendQuery> {

	@Autowired(required=true)
	@Qualifier(value="cwUserExtendBeanBusinessService")
	private BusinessService cwUserExtendBeanBusinessService;

	protected BusinessService<CwUserExtendBean> getBaseService(){
		return cwUserExtendBeanBusinessService;
	}

}

