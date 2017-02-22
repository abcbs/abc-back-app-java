package com.ndlanx.cwwarm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.web.restful.BaseBusinessControllerImpl;

import com.ndlan.cwwarm.model.CwForHelpMsgBean;

import com.ndlan.cwwarm.query.CwForHelpMsgQuery;


@Controller
@RequestMapping("/business/cw/for/help/msg/")
public class CwForHelpMsgBeanBusinessControllerImpl 
extends BaseBusinessControllerImpl<CwForHelpMsgBean, CwForHelpMsgQuery> {

	@Autowired(required=true)
	@Qualifier(value="cwForHelpMsgBeanBusinessService")
	private BusinessService cwForHelpMsgBeanBusinessService;

	protected BusinessService<CwForHelpMsgBean> getBaseService(){
		return cwForHelpMsgBeanBusinessService;
	}

}

