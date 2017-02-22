package com.ndlanx.cwwarm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.web.restful.BaseBusinessControllerImpl;

import com.ndlan.cwwarm.model.CwAlertBean;

import com.ndlan.cwwarm.query.CwAlertQuery;


@Controller
@RequestMapping("/business/cw/alert/")
public class CwAlertBeanBusinessControllerImpl 
extends BaseBusinessControllerImpl<CwAlertBean, CwAlertQuery> {

	@Autowired(required=true)
	@Qualifier(value="cwAlertBeanBusinessService")
	private BusinessService cwAlertBeanBusinessService;

	protected BusinessService<CwAlertBean> getBaseService(){
		return cwAlertBeanBusinessService;
	}

}

