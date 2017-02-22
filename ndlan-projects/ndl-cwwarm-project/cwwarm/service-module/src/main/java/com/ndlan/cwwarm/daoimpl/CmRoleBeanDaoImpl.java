package com.ndlan.cwwarm.daoimpl;

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;

import com.ndlan.cwwarm.model.CmRoleBean;
import com.ndlan.cwwarm.dao.CmRoleBeanDao;
/**
 * �ֵ����ݿ������ӿ�ʵ����
 * @author LiuJQ
 */
@Repository("cmRoleBeanDao")
public class CmRoleBeanDaoImpl extends 
	DefualtBaseDaoImpl<CmRoleBean> implements CmRoleBeanDao {

}
