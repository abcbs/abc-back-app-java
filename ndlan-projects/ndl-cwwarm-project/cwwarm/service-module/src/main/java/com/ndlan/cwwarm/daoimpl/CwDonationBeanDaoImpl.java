package com.ndlan.cwwarm.daoimpl;

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;

import com.ndlan.cwwarm.model.CwDonationBean;
import com.ndlan.cwwarm.dao.CwDonationBeanDao;
/**
 * �ֵ����ݿ������ӿ�ʵ����
 * @author LiuJQ
 */
@Repository("cwDonationBeanDao")
public class CwDonationBeanDaoImpl extends 
	DefualtBaseDaoImpl<CwDonationBean> implements CwDonationBeanDao {

}
