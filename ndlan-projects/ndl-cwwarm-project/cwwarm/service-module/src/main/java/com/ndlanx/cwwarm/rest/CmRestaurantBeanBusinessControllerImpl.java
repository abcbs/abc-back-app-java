package com.ndlanx.cwwarm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.web.restful.BaseBusinessControllerImpl;

import com.ndlan.cwwarm.model.CmRestaurantBean;

import com.ndlan.cwwarm.query.CmRestaurantQuery;


@Controller
@RequestMapping("/business/cm/restaurant/")
public class CmRestaurantBeanBusinessControllerImpl 
extends BaseBusinessControllerImpl<CmRestaurantBean, CmRestaurantQuery> {

	@Autowired(required=true)
	@Qualifier(value="cmRestaurantBeanBusinessService")
	private BusinessService cmRestaurantBeanBusinessService;

	protected BusinessService<CmRestaurantBean> getBaseService(){
		return cmRestaurantBeanBusinessService;
	}

}

