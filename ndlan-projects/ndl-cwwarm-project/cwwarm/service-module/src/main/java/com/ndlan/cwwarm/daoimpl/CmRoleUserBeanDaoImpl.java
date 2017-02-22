package com.ndlan.cwwarm.daoimpl;

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;

import com.ndlan.cwwarm.model.CmRoleUserBean;
import com.ndlan.cwwarm.dao.CmRoleUserBeanDao;
/**
 * �ֵ����ݿ������ӿ�ʵ����
 * @author LiuJQ
 */
@Repository("cmRoleUserBeanDao")
public class CmRoleUserBeanDaoImpl extends 
	DefualtBaseDaoImpl<CmRoleUserBean> implements CmRoleUserBeanDao {

}
