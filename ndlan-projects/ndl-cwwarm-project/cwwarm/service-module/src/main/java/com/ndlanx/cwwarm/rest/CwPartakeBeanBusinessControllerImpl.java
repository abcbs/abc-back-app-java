package com.ndlanx.cwwarm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.web.restful.BaseBusinessControllerImpl;

import com.ndlan.cwwarm.model.CwPartakeBean;

import com.ndlan.cwwarm.query.CwPartakeQuery;


@Controller
@RequestMapping("/business/cw/partake/")
public class CwPartakeBeanBusinessControllerImpl 
extends BaseBusinessControllerImpl<CwPartakeBean, CwPartakeQuery> {

	@Autowired(required=true)
	@Qualifier(value="cwPartakeBeanBusinessService")
	private BusinessService cwPartakeBeanBusinessService;

	protected BusinessService<CwPartakeBean> getBaseService(){
		return cwPartakeBeanBusinessService;
	}

}

