package com.ndlan.cwwarm.daoimpl;

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;

import com.ndlan.cwwarm.model.CwCommentBean;
import com.ndlan.cwwarm.dao.CwCommentBeanDao;
/**
 * �ֵ����ݿ������ӿ�ʵ����
 * @author LiuJQ
 */
@Repository("cwCommentBeanDao")
public class CwCommentBeanDaoImpl extends 
	DefualtBaseDaoImpl<CwCommentBean> implements CwCommentBeanDao {

}
