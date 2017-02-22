package com.ndlanx.sys.web.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.web.controller.BaseControllerImpl;
import com.ndlan.framework.demo.domain.SysDictionary;
import com.ndlan.framework.demo.domain.query.SysDictionaryQuery;
import com.ndlan.framework.demo.service.SysDictionaryBusinessSerivce;
import com.ndlan.framework.demo.service.SysDictionaryService;


/**
 * 
 * @author LiuJQ
 */
@Controller
@RequestMapping("/sys/dictionary")
public class ProductCatatoryController extends BaseControllerImpl<SysDictionary, SysDictionaryQuery> 
{
	@Autowired(required=true)  
	@Qualifier("sysDictionaryBusinessSerivce")
	private SysDictionaryBusinessSerivce sysDictionaryBusinessSerivce;

	protected BusinessService<SysDictionary> getBaseService(){
		return sysDictionaryBusinessSerivce;
	}
	

}
