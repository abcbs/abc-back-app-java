package com.ndlan.cwwarm.daoimpl;

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;

import com.ndlan.cwwarm.model.CwHoldActivityMsgBean;
import com.ndlan.cwwarm.dao.CwHoldActivityMsgBeanDao;
/**
 * �ֵ����ݿ������ӿ�ʵ����
 * @author LiuJQ
 */
@Repository("cwHoldActivityMsgBeanDao")
public class CwHoldActivityMsgBeanDaoImpl extends 
	DefualtBaseDaoImpl<CwHoldActivityMsgBean> implements CwHoldActivityMsgBeanDao {

}
