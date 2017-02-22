package com.ndlan.cwwarm.daoimpl;

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;

import com.ndlan.cwwarm.model.CmEmployeeBean;
import com.ndlan.cwwarm.dao.CmEmployeeBeanDao;
/**
 * �ֵ����ݿ������ӿ�ʵ����
 * @author LiuJQ
 */
@Repository("cmEmployeeBeanDao")
public class CmEmployeeBeanDaoImpl extends 
	DefualtBaseDaoImpl<CmEmployeeBean> implements CmEmployeeBeanDao {

}
