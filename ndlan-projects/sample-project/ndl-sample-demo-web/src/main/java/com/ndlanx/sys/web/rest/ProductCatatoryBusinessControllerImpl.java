package com.ndlanx.sys.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.web.restful.BaseBusinessControllerImpl;
import com.ndlan.framework.demo.domain.SysDictionary;
import com.ndlan.framework.demo.domain.query.SysDictionaryQuery;

@Controller
@RequestMapping("/business/sys/dictionary")
public class ProductCatatoryBusinessControllerImpl 
extends BaseBusinessControllerImpl<SysDictionary, SysDictionaryQuery> {

	@Autowired(required=true)
	@Qualifier(value="sysDictionaryBusinessSerivce")
	private BusinessService sysDictionaryBusinessSerivce;

	protected BusinessService<SysDictionary> getBaseService(){
		return sysDictionaryBusinessSerivce;
	}

}
