package com.ndlan.cwwarm.daoimpl;

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;

import com.ndlan.cwwarm.model.CwOrgSiteBean;
import com.ndlan.cwwarm.dao.CwOrgSiteBeanDao;
/**
 * �ֵ����ݿ������ӿ�ʵ����
 * @author LiuJQ
 */
@Repository("cwOrgSiteBeanDao")
public class CwOrgSiteBeanDaoImpl extends 
	DefualtBaseDaoImpl<CwOrgSiteBean> implements CwOrgSiteBeanDao {

}
