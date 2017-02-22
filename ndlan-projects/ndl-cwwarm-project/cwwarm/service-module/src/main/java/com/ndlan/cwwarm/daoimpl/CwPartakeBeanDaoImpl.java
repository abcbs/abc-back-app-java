package com.ndlan.cwwarm.daoimpl;

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;

import com.ndlan.cwwarm.model.CwPartakeBean;
import com.ndlan.cwwarm.dao.CwPartakeBeanDao;
/**
 * �ֵ����ݿ������ӿ�ʵ����
 * @author LiuJQ
 */
@Repository("cwPartakeBeanDao")
public class CwPartakeBeanDaoImpl extends 
	DefualtBaseDaoImpl<CwPartakeBean> implements CwPartakeBeanDao {

}
