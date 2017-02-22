package com.ndlanx.cwwarm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.web.restful.BaseBusinessControllerImpl;

import com.ndlan.cwwarm.model.CwCollectionBean;

import com.ndlan.cwwarm.query.CwCollectionQuery;


@Controller
@RequestMapping("/business/cw/collection/")
public class CwCollectionBeanBusinessControllerImpl 
extends BaseBusinessControllerImpl<CwCollectionBean, CwCollectionQuery> {

	@Autowired(required=true)
	@Qualifier(value="cwCollectionBeanBusinessService")
	private BusinessService cwCollectionBeanBusinessService;

	protected BusinessService<CwCollectionBean> getBaseService(){
		return cwCollectionBeanBusinessService;
	}

}

