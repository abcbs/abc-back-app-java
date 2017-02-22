package com.ndlan.canyin.base.repository.dao3c.employee;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ndlan.canyin.base.entity.base3c.employee.BaseMembershipCardClass;
import com.ndlan.canyin.base.repository.BaseDao;

public interface BaseMemberShipCardClass3cDao extends BaseDao<BaseMembershipCardClass,String>{
	 
	@Query("select a from BaseMembershipCardClass a")
	public List<BaseMembershipCardClass> findAllBaseMembershipCardClass();

}
