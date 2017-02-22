package com.ndlan.cwwarm.daoimpl;

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;

import com.ndlan.cwwarm.model.CwForHelpMsgBean;
import com.ndlan.cwwarm.dao.CwForHelpMsgBeanDao;
/**
 * �ֵ����ݿ������ӿ�ʵ����
 * @author LiuJQ
 */
@Repository("cwForHelpMsgBeanDao")
public class CwForHelpMsgBeanDaoImpl extends 
	DefualtBaseDaoImpl<CwForHelpMsgBean> implements CwForHelpMsgBeanDao {

}
