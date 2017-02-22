package com.ndlan.cwwarm.daoimpl;

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;

import com.ndlan.cwwarm.model.CwWorkJobBean;
import com.ndlan.cwwarm.dao.CwWorkJobBeanDao;
/**
 * �ֵ����ݿ������ӿ�ʵ����
 * @author LiuJQ
 */
@Repository("cwWorkJobBeanDao")
public class CwWorkJobBeanDaoImpl extends 
	DefualtBaseDaoImpl<CwWorkJobBean> implements CwWorkJobBeanDao {

}
