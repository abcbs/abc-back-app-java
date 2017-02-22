package com.ndlan.cwwarm.daoimpl;

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;

import com.ndlan.cwwarm.model.CwCollectionBean;
import com.ndlan.cwwarm.dao.CwCollectionBeanDao;
/**
 * �ֵ����ݿ������ӿ�ʵ����
 * @author LiuJQ
 */
@Repository("cwCollectionBeanDao")
public class CwCollectionBeanDaoImpl extends 
	DefualtBaseDaoImpl<CwCollectionBean> implements CwCollectionBeanDao {

}
