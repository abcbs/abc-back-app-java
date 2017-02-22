package com.ndlanx.cwwarm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.web.restful.BaseBusinessControllerImpl;

import com.ndlan.cwwarm.model.CmEmployeeBean;

import com.ndlan.cwwarm.query.CmEmployeeQuery;


@Controller
@RequestMapping("/business/cm/employee/")
public class CmEmployeeBeanBusinessControllerImpl 
extends BaseBusinessControllerImpl<CmEmployeeBean, CmEmployeeQuery> {

	@Autowired(required=true)
	@Qualifier(value="cmEmployeeBeanBusinessService")
	private BusinessService cmEmployeeBeanBusinessService;

	protected BusinessService<CmEmployeeBean> getBaseService(){
		return cmEmployeeBeanBusinessService;
	}

}

