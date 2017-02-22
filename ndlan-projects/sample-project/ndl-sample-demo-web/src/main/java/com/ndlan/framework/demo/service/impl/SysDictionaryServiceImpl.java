package com.ndlan.framework.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;
import com.ndlan.framework.demo.SysDictionaryDao;
import com.ndlan.framework.demo.domain.SysDictionary;
import com.ndlan.framework.demo.service.SysDictionaryService;

/**
 * 字典信息服务类接口实现
 * @author LiuJQ
 */
//@Service("sysDictionaryService")
@Service
public class SysDictionaryServiceImpl extends DefaultAtomBaseServiceImpl<SysDictionaryDao,SysDictionary> 
	implements SysDictionaryService {
	@Autowired
	private SysDictionaryDao sysDictionaryDao;

	@Override
	public MyBatisDao<SysDictionary> getBaseDao() {
		return sysDictionaryDao;
	}

}
