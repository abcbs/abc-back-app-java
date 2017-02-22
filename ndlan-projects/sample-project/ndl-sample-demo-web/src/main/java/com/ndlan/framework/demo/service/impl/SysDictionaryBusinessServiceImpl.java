package com.ndlan.framework.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;
import com.ndlan.framework.demo.domain.SysDictionary;
import com.ndlan.framework.demo.service.SysDictionaryBusinessSerivce;

@Service("sysDictionaryBusinessSerivce")
//@Service
public class SysDictionaryBusinessServiceImpl 
	extends DefaultBesinessServiceImpl<SysDictionary>  
	implements SysDictionaryBusinessSerivce{

	@Autowired
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
