package com.ndlan.framework.demo.impl;

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;
import com.ndlan.framework.demo.SysDictionaryDao;
import com.ndlan.framework.demo.domain.SysDictionary;

/**
 * 字典数据库操作类接口实现类
 * @author LiuJQ
 */
@Repository
public class SysDictionaryDaoImpl extends 
	DefualtBaseDaoImpl<SysDictionary> implements SysDictionaryDao {

}
