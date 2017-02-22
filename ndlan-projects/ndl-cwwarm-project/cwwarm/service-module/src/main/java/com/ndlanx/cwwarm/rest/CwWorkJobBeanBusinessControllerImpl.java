package com.ndlanx.cwwarm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.web.restful.BaseBusinessControllerImpl;

import com.ndlan.cwwarm.model.CwWorkJobBean;

import com.ndlan.cwwarm.query.CwWorkJobQuery;


@Controller
@RequestMapping("/business/cw/work/job/")
public class CwWorkJobBeanBusinessControllerImpl 
extends BaseBusinessControllerImpl<CwWorkJobBean, CwWorkJobQuery> {

	@Autowired(required=true)
	@Qualifier(value="cwWorkJobBeanBusinessService")
	private BusinessService cwWorkJobBeanBusinessService;

	protected BusinessService<CwWorkJobBean> getBaseService(){
		return cwWorkJobBeanBusinessService;
	}

}

