package com.ndlan.canyin.frontdesk.service.service3c.productinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.ndlan.canyin.base.entity.base3c.productinfo.ProCategory;
import com.ndlan.canyin.base.repository.dao3c.productinfo.ProCategoryDao;
import com.ndlan.canyin.frontdesk.service.BaseService;

@Service
@Lazy
public class ProCategoryService extends BaseService<ProCategoryDao, ProCategory>{
	
	private ProCategoryDao proCategoryDao;
	
	@Autowired
	public void setDao(ProCategoryDao dao) {
		super.setDao(dao);
		this.proCategoryDao=dao;
	}

	public ProCategoryDao getProCategoryDao() {
		return proCategoryDao;
	}

	public void setProCategoryDao(ProCategoryDao proCategoryDao) {
		this.proCategoryDao = proCategoryDao;
	}
	
	
	

}
