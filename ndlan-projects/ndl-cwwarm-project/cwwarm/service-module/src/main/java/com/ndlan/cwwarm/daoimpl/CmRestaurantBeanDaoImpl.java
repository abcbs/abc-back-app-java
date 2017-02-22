package com.ndlan.cwwarm.daoimpl;

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;

import com.ndlan.cwwarm.model.CmRestaurantBean;
import com.ndlan.cwwarm.dao.CmRestaurantBeanDao;
/**
 * �ֵ����ݿ������ӿ�ʵ����
 * @author LiuJQ
 */
@Repository("cmRestaurantBeanDao")
public class CmRestaurantBeanDaoImpl extends 
	DefualtBaseDaoImpl<CmRestaurantBean> implements CmRestaurantBeanDao {

}
