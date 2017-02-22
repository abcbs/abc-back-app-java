package com.ndlanx.cwwarm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.web.restful.BaseBusinessControllerImpl;

import com.ndlan.cwwarm.model.CwCommentBean;

import com.ndlan.cwwarm.query.CwCommentQuery;


@Controller
@RequestMapping("/business/cw/comment/")
public class CwCommentBeanBusinessControllerImpl 
extends BaseBusinessControllerImpl<CwCommentBean, CwCommentQuery> {

	@Autowired(required=true)
	@Qualifier(value="cwCommentBeanBusinessService")
	private BusinessService cwCommentBeanBusinessService;

	protected BusinessService<CwCommentBean> getBaseService(){
		return cwCommentBeanBusinessService;
	}

}

