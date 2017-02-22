package com.ndlanx.cwwarm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.web.restful.BaseBusinessControllerImpl;

import com.ndlan.cwwarm.model.CwDonationBean;

import com.ndlan.cwwarm.query.CwDonationQuery;


@Controller
@RequestMapping("/business/cw/donation/")
public class CwDonationBeanBusinessControllerImpl 
extends BaseBusinessControllerImpl<CwDonationBean, CwDonationQuery> {

	@Autowired(required=true)
	@Qualifier(value="cwDonationBeanBusinessService")
	private BusinessService cwDonationBeanBusinessService;

	protected BusinessService<CwDonationBean> getBaseService(){
		return cwDonationBeanBusinessService;
	}

}

