package com.ndlan.cwwarm.daoimpl;

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;

import com.ndlan.cwwarm.model.CwAlertBean;
import com.ndlan.cwwarm.dao.CwAlertBeanDao;
/**
 * �ֵ����ݿ������ӿ�ʵ����
 * @author LiuJQ
 */
@Repository("cwAlertBeanDao")
public class CwAlertBeanDaoImpl extends 
	DefualtBaseDaoImpl<CwAlertBean> implements CwAlertBeanDao {

}
