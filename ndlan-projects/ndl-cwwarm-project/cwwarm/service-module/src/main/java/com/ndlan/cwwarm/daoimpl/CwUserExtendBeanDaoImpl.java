package com.ndlan.cwwarm.daoimpl;

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;

import com.ndlan.cwwarm.model.CwUserExtendBean;
import com.ndlan.cwwarm.dao.CwUserExtendBeanDao;
/**
 * �ֵ����ݿ������ӿ�ʵ����
 * @author LiuJQ
 */
@Repository("cwUserExtendBeanDao")
public class CwUserExtendBeanDaoImpl extends 
	DefualtBaseDaoImpl<CwUserExtendBean> implements CwUserExtendBeanDao {

}
